package javautilities.ui.component.displayer;

import java.awt.Component;
import java.awt.Dimension;
import java.util.Collection;

public interface CollectionDisplayer {

	Collection<Component> toComponents(Collection<?> collection, Dimension size);
	
}
