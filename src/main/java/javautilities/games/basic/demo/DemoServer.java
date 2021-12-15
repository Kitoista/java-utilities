package javautilities.games.basic.demo;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import javautilities.games.basic.core.Game;
import javautilities.games.basic.core.Mob;
import javautilities.games.basic.core.characters.CharacterFactory;
import javautilities.games.basic.core.characters.Character;
import javautilities.games.basic.net.Server;

public class DemoServer {
	
	public static final int STATE_JOINED = 0;
	public static final int STATE_INITIALIZED = 1;
	public static final int STATE_ON_MAP = 2;
	
	Game game;
	Server server;
	Map<Socket, Character> characters = new HashMap<>();
	Map<Socket, Integer> states = new HashMap<>();
	
	public static void main(String[] args) throws IOException, InterruptedException {
		new DemoServer();
	}
	
	DemoServer() throws IOException, InterruptedException {
		game = new Game();
		
		server = new Server();
		
		server.joinedListener = (client) -> {
			states.put(client, STATE_JOINED);
		};
		
		server.messageListener = (client, data) -> {
			int state = states.get(client);
			if (state == STATE_INITIALIZED) {
				String message = (String) data;
				if (message.startsWith("[init]\t")) {
					String chosen = message.replace("[init]\t", "");
					// check if they can join etc
					System.out.println("Some bullshit");

					characters.put(client, game.addCharacter(chosen));
					String map = "";
					for (Mob mob : game.map.staticMobs) {
						map += mob + "\t";
					}
					try {
						System.out.println("Sending map info");
						server.sendMessage(client, "[map]\t" + map.trim());
						server.sendMessage(client, "[mobId]\t" + characters.get(client).id);
					} catch (IOException e) {
						e.printStackTrace();
					}
					states.put(client, STATE_ON_MAP);
				}
			} else if (state == STATE_ON_MAP) {
				String message = (String) data;
				if (message.startsWith("[key]\t")) {
					String[] parts = message.split("\t");
					int key = Integer.parseInt(parts[1]);
					int id = Integer.parseInt(parts[2]);
					
					if (id == KeyEvent.KEY_PRESSED) {
						if (key == KeyEvent.VK_LEFT) {
							characters.get(client).moveLeft();
						} else if (key == KeyEvent.VK_RIGHT) {
							characters.get(client).moveRight();
						} else if (key == KeyEvent.VK_UP) {
							characters.get(client).jump();
						}
					} else {
						if (key == KeyEvent.VK_LEFT && characters.get(client).velocity.x < 0) {
							characters.get(client).stopMoving();
						} else if (key == KeyEvent.VK_RIGHT && characters.get(client).velocity.x > 0) {
							characters.get(client).stopMoving();
						}

					}
					
				}
			}
		};
		
		System.out.println("start");
		server.start();

		for (int i = 0; i >= 0; ++i) {
			Thread.sleep(10);
			game.tick();
			
			
			for (Socket client : server.clients()) {
				int state = states.get(client);
				if (state == STATE_JOINED) {
					initClient(client);
				} else if (state == STATE_INITIALIZED) {
					
				} else if (state == STATE_ON_MAP) {
					sendTick(client);
				} else {
					server.sendMessage(client, "ping " + i);
				}
			}
			
		}
		
		server.stop();
		
	}
	
	void initClient(Socket client) throws IOException {
		String list = "";
		for (String element : CharacterFactory.list()) {
			list += element + "\t";
		}
		states.put(client, STATE_INITIALIZED);
		server.sendMessage(client, "[init]\t" + list.trim());
	}

	void sendTick(Socket client) {
		String mobs = "";
		for (Mob mob : game.map.movingMobs) {
			mobs += mob + "\t";
		}
		try {
			server.sendMessage(client, "[tick]\t" + mobs.trim());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
