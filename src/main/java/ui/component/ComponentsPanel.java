package ui.component;

import java.awt.Component;
import java.awt.FlowLayout;
import java.util.Collection;

import ui.component.converter.CollectionDisplayer;
import ui.component.converter.ObjectDisplayer;
import ui.defaults.KPanel;

public class ComponentsPanel extends KPanel {

	private static final long serialVersionUID = 1L;

	private Collection<?> objects;
	private CollectionDisplayer collConverter;
	private ObjectDisplayer objConverter;
	private Object drawLock = new Object();
	
	public ComponentsPanel() {
		init();
	}
	
	public ComponentsPanel(Collection<?> objects) {
		this.objects = objects;
		init();
	}

	private void init() {
		setLayout(new FlowLayout());
		repaint();
	}
	
	public void draw() {
		if (objConverter == null && collConverter == null) {
			return;
		}
		synchronized(drawLock) {
			this.removeAll();
			if (objects == null) {
				return;
			}
			if (collConverter != null) {
				for (Component comp : collConverter.toComponents(objects, null)) {
					if (comp != null) {
						this.add(comp);
					}
				}
			} else {
				for (Object object : objects) {
					Component comp = objConverter.toComponent(object, null);
					if (comp != null) {
						this.add(comp);
					}
				}
			}
		}
	}

	public void setObjects(Collection<?> objects) {
		this.objects = objects;
		repaint();
	}
	
	public Collection<?> getObjects() {
		return objects;
	}

	public ObjectDisplayer getObjectConverter() {
		return objConverter;
	}
	
	public CollectionDisplayer getCollectionConverter() {
		return collConverter;
	}

	public void setObjConverter(ObjectDisplayer objConverter) {
		this.objConverter = objConverter;
	}
	
	public void setCollConverter(CollectionDisplayer collConverter) {
		this.collConverter = collConverter;
	}
}
