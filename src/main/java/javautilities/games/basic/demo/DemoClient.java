package javautilities.games.basic.demo;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Set;

import javautilities.games.basic.net.Client;
import javautilities.games.basic.ui.DumbUI;

public class DemoClient {
	
	public static void main(String[] args) throws InterruptedException {
		DumbUI ui = new DumbUI();

		ui.render("Connecting to shit");
		
		Client c = new Client("A");
		
		try {
			c.messageListener = (message) -> {
				ui.render(message);
			};
			
			ui.characterButtonListener = (chosen) -> {
				System.out.println("chosen epic " + chosen);
				try {
					c.sendMessage("[init]\t" + chosen);
				} catch (IOException e) {
					e.printStackTrace();
				}
			};
			// now we hijack the keyboard manager
			KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
			manager.addKeyEventDispatcher(new KeyEventDispatcher () {
				private Set<Integer> heldKeys = new HashSet<>();
				
				public boolean dispatchKeyEvent(KeyEvent e) {
					if(e.getID() == KeyEvent.KEY_PRESSED) {
						if (!heldKeys.contains(e.getKeyCode())) {
							heldKeys.add(e.getKeyCode());
							try {
								c.sendMessage("[key]\t" + e.getKeyCode() + "\t" + KeyEvent.KEY_PRESSED);
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
					} else if (e.getID() == KeyEvent.KEY_RELEASED) {
						heldKeys.remove(e.getKeyCode());
						try {
							c.sendMessage("[key]\t" + e.getKeyCode() + "\t" + KeyEvent.KEY_RELEASED);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
			 
					// allow the event to be redispatched
					return false;
				}
			});
			
			
			c.join();

			
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
			ui.render("There is no server");
		} catch (IOException e) {
			e.printStackTrace();
			ui.render("There is no server");
		}

	}
	
	

}
