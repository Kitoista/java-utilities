package javautilities.events;

import java.util.ArrayList;
import java.util.List;

public class EventEmitter {

	List<EventListener> listeners = new ArrayList<>();
	
	public void emit(Event event) {
		listeners.forEach(listener -> {
			listener.onEvent(event);
		});
	}
	
	public void subscribe(EventListener listener) {
		listeners.add(listener);
	}

	public void unsubscribe(EventListener listener) {
		listeners.remove(listener);
	}
	
}
