package ui.component.bind.imp;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.MouseInfo;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import geometry.Positioner;
import meta.Property;
import ui.component.Components;
import ui.component.bind.BindedProperty;
import ui.defaults.KButton;
import ui.defaults.KLabel;
import ui.defaults.KPanel;
import ui.frame.Frames;
import ui.frame.Show;



/** 
 * A class for binding collection properties.<br/>
 * Can detect == equality and size changes.<br/>
 * Can not detect contained object changes.
 */
public class BindedCollection extends BindedProperty<Collection<?>> {

	private static final long serialVersionUID = 1L;
	
	private JPanel center;
	private JPanel north;
	private Map<Object, JPanel> objectCompomponents = new HashMap<>();
	
	private JButton expandBtn;
	private int lastSize = 0;
	
	private boolean isExpanded = false;
	
	protected BindedCollection(Property property, Object object) {
		super(property, object);
	}
	
	@Override
	protected void initGui() {
		setLayout(new BorderLayout());
		
		initPanels();
		
		expandBtn = new JButton("+");
		expandBtn.setMargin(new Insets(0,5,0,5));
		expandBtn.setFocusPainted(false);
		expandBtn.addActionListener(e -> {
			if (isExpanded) {
				collapse();
			} else {
				expand();
			}
		});
		
		north.add(expandBtn, BorderLayout.WEST);
		north.add(new KLabel(property.getName()), BorderLayout.CENTER);
	}
	
	private void initPanels() {
		center = new KPanel();
		north = new KPanel();
		
		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
		north.setLayout(new BorderLayout());
		
		this.add(center, BorderLayout.CENTER);
		this.add(north, BorderLayout.NORTH);
	}
	
	private void expand() {
		isExpanded = true;
		expandBtn.setText("-");
		center.setVisible(true);
		refreshObjects();
		Components.getParentFrame(this).pack();
	}
	
	private void collapse() {
		isExpanded = false;
		expandBtn.setText("+");
		center.setVisible(false);
		Components.getParentFrame(this).pack();
	}
	
	private void refreshObjects() {
		if (object == null) return;
		try {
			Collection<?> collection = (Collection<?>) property.get(object);
			Set<Object> notFounds = new HashSet<>(objectCompomponents.keySet());
			for (Object object : collection) {
				notFounds.remove(object);
				if (!objectCompomponents.containsKey(object)) {
					addObject(object);
				}
			}
			for (Object object : notFounds) {
				removeObject(object);
			}
			Components.getParentFrame(this).repaint();
			Components.getParentFrame(this).pack();
		} catch (IllegalArgumentException | ReflectiveOperationException e) {
			e.printStackTrace();
		}
	}
	
	private void addObject(Object object) {
		JPanel entry = createEntry(object);
		objectCompomponents.put(object, entry);
		center.add(entry);
	}
	
	private void removeObject(Object object) {
		onRemoveEntry(object);
		JPanel entry = objectCompomponents.get(object);
		objectCompomponents.remove(object);
		center.remove(entry);
	}
	
	protected JPanel createEntry(Object object) {
		JPanel re = new KPanel();
		JButton showBtn = new KButton("show");
		showBtn.addActionListener(e -> {
			onShowEntry(object);
		});
		re.add(new KLabel(object.getClass().getSimpleName()));
		re.add(showBtn);
		return re;
	}
	
	protected void onShowEntry(Object object) {
		try {
			ObjectComponent objComp = new ObjectComponent(object.getClass());
			objComp.setObject(object);
			Show show = new Show();
			show.component(objComp);
			show.getFrame().setLocation(Positioner.placeInside(MouseInfo.getPointerInfo().getLocation(), show.getFrame().getSize(), Frames.getMaximalizedSize()));
			show.getFrame().addWindowListener(new WindowAdapter() {
			
				@Override
				public void windowClosing(WindowEvent e) {
					objComp.close();
					show.getFrame().dispose();
				}
				
			});
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException e) {
			e.printStackTrace();
		}
	}
	
	protected void onRemoveEntry(Object object) {
	}
	
	@Override
	protected void onMemChanged(Object obj) {
		if (isExpanded) {
			refreshObjects();
		}
	}
	
	@Override
	protected boolean equalsThreadCheck(Collection<?> a, Collection<?> b) {
		boolean sizeChanged = false;
		if (b != null && b.size() != lastSize) {
			lastSize = b.size();
			sizeChanged = true;
		}
		return super.equalsThreadCheck(a, b) && !sizeChanged;
	}
	
	public static boolean canHandle(Class<?> type) {
		return (Collection.class.isAssignableFrom(type));
	}
	
}
