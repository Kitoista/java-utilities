package ui.component.bind;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import meta.Property;
import ui.component.bind.exception.UnableToHandleTypeException;
import ui.defaults.KPanel;

public abstract class BindedProperty<T> extends KPanel implements AutoCloseable {

	private static final long serialVersionUID = 1L;
	private static final int memToUiPollTime = 100;
	
	protected Property property;
	protected Object object;
	
	private boolean isClosed;
	private boolean objectChanged;
	private Thread memThread;
	
	private Collection<PropertyChangedListener> listeners = new ArrayList<>();
	
	private T lastValue = null;
	private T currentValue = null;
	
	protected BindedProperty(Property property, Object object) {
		this.property = property;
		this.object = object;
		init();
	}
	
	private void init() {
		try {
			Method canHandle = getClass().getMethod("canHandle", Class.class);
			if (!((Boolean) canHandle.invoke(null, property.getType()))) {
				throw new UnableToHandleTypeException(property.getType());
			}
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			throw new RuntimeException("CanHandle method had a problem: " + e.getMessage());
		}
		memThread = generateMemThread();
		memThread.setName("BIND-" + property.getOwner().getSimpleName() + "." + property.getName());
		turnMemThread(shouldMemThreadContinue());
		initGui();
	}
	
	
	// should be called by call subclasses
	
	protected void onGuiChanged(T value) throws IllegalArgumentException, ReflectiveOperationException {
		if (property.hasSet() && object != null) {
			property.set(object, value);
		}
	}
	
	
	// useful to override
	
	protected void onMemChanged(Object obj) {
		callListeners(obj);
	}
	
	protected T copy(T value) {
		return value;
	}
	
	protected boolean equalsThreadCheck(T a, T b) {
		return Objects.equals(a, b);
	}
	
	public static boolean canHandle(Class<?> type) {
		return type.isInstance(type);
	}
	
	
	// abstract methods
	
	protected abstract void initGui();
	
	
	// overridden methods
	
	@Override
	public void setVisible(boolean value) {
		if (isVisible() == value) {
			return;
		}
		super.setVisible(value);
		turnMemThread(value);
	}
	
	@Override
	public void close() {
		isClosed = true;
	}
	
	
	// implementation
	
	private boolean shouldMemThreadContinue() {
		return (isVisible() && property.hasGet() && !isClosed && object != null);
	}
	
	private void turnMemThread(boolean on) {
		if (on) { // should not run anymore,
			if (!memThread.isAlive()) {
				try {
					memThread.start();
				} catch (IllegalThreadStateException e) {
				}
			}
		} else {
			if (memThread.isAlive()) {
				memThread.interrupt();
			}
		}
	}
	
	protected Thread generateMemThread() {
		Thread re = new Thread(new Runnable() {

			@SuppressWarnings("unchecked")
			public void run() {
				try {
					if (object == null) {
						return;
					}
					lastValue = copy((T) property.get(object));
					onMemChanged(lastValue);
					while (shouldMemThreadContinue()) {
						Thread.sleep(memToUiPollTime);
						currentValue = (T) property.get(object);
						if (!equalsThreadCheck(lastValue, currentValue) || objectChanged) {
							objectChanged = false;
							lastValue = copy(currentValue);
							onMemChanged(currentValue);
						}
					}
				} catch (IllegalArgumentException | ReflectiveOperationException e1) {
					e1.printStackTrace();
				} catch (InterruptedException e) {
				}
			
			}
		});
		return re;
	}
	
	
	// getters setters etc XXX
	
	public Property getProperty() {
		return property;
	}
	public void setProperty(Property property) {
		if (!canHandle(property.getType())) {
			throw new UnableToHandleTypeException(property.getType());
		}
		this.property = property;
	}
	
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
		objectChanged = true;
		lastValue = null;
		turnMemThread(shouldMemThreadContinue());
	}
	
	public void addPropertyChangeListener(PropertyChangedListener pcl) {
		listeners.add(pcl);
	}
	public void removePropertyChangeListener(PropertyChangedListener pcl) {
		listeners.remove(pcl);
	}
	
	private void callListeners(Object obj) {
		for (PropertyChangedListener listener : listeners) {
			listener.onChange(obj);
		}
	}
}
