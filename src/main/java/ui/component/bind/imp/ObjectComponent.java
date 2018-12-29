package ui.component.bind.imp;

import java.awt.BorderLayout;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import meta.Property;
import ui.component.bind.BindedProperty;
import ui.component.bind.BindedPropertyFactory;

public class ObjectComponent extends JPanel implements AutoCloseable {

	private static final long serialVersionUID = 1L;

	private Map<String, BindedProperty<?>> bindedProps = new HashMap<>();
	private Class<?> type;
	private Object object;
	private JLabel label;
	
	public ObjectComponent(Class<?> type) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		this.object = null;
		this.type = type;
		init();
	}
	
	public ObjectComponent(Object object) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		this.object = object;
		this.type = object.getClass();
		init();
	}
	
	private void init() throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		Collection<Property> props = Property.getProperties(type);
		for (Property property : props) {
			if(property.getName().equals("class")) {
				continue;
			}
			BindedProperty<?> bp = BindedPropertyFactory.create(property, object);
			bindedProps.put(property.getName(), bp);
		}
		initGui();
	}
	
	private void initGui() {
		this.setLayout(new BorderLayout());

		JPanel center = new JPanel();
		JPanel north = new JPanel();
		
		this.add(north, BorderLayout.NORTH);
		this.add(center, BorderLayout.CENTER);
		
		label = new JLabel(type.getSimpleName());
		north.add(label);
		
		for (String key : bindedProps.keySet()) {
			center.add(bindedProps.get(key));
		}
		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
	}
	
	public Collection<BindedProperty<?>> getBindedProps() {
		return bindedProps.values();
	}

	@Override
	public void close() {
		for (BindedProperty<?> bindedProperty : bindedProps.values()) {
			bindedProperty.close();
		}
	}
	
	
	// getters setters XXX
	
	public void setObject(Object object) {
		if (this.object == object) {
			return;
		}
		for (BindedProperty<?> bindedProperty : bindedProps.values()) {
			bindedProperty.setObject(object);
		}
		this.object = object;
	}
	
	public Object getObject() {
		return object;
	}
	
}
