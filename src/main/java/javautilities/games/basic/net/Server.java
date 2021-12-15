package javautilities.games.basic.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Server {

	public static final int READY = 0;
	public static final int RUNNING = 1;
	
	public ClientMessageListener messageListener;
	public ClientJoinedListener joinedListener;
	
	ServerSocket server;
	int port = 19969;
	
	int state = READY;
	protected Thread acceptThread;
	protected Map<Socket, Thread> readThreads = new HashMap<>();
	
	public void start() throws IOException {
		if (state != READY) {
			return;
		}
		server = new ServerSocket(19969);
		state = RUNNING;
		readThreads = new HashMap<>();
		
		acceptThread = new Thread(() -> {
			try {
				while (state == RUNNING) {
					Socket client = server.accept();
					onJoin(client);
					readThreads.put(client, new Thread(() -> {
						while (state == RUNNING) {
							try {
								ObjectInputStream in = new ObjectInputStream(client.getInputStream());
								Object message = in.readObject();
								onMessage(client, message);
							} catch (SocketException e) {
								if (e.getMessage() == "Connection reset") {
									System.out.println(client + " disconnected");
									readThreads.remove(client);
									break;
								} else {
									e.printStackTrace();
								}
							} catch (IOException e) {
								e.printStackTrace();
							} catch (ClassNotFoundException e) {
								e.printStackTrace();
							}
						}
					}));
					readThreads.get(client).start();
				}
				
			} catch (IOException e) {
				try {
					stop();
				} catch (IOException e1) {
					// idgaf
				}
			}
		});
		acceptThread.start();
	}
	
	public void stop() throws IOException {
		if (state != RUNNING) {
			return;
		}
		state = READY;
		server.close();
	}
	
	protected void onJoin(Socket client) {
		System.out.println(this + " " + client + " has joined");
		if (joinedListener != null) {
			joinedListener.onJoin(client);
		}
	}
	
	protected void onMessage(Socket client, Object message) {
		// System.out.println(this + " " + client + " has sent me message " + message);
		if (messageListener != null) {
			messageListener.onMessage(client, message);
		}
	}
	
	public void sendMessage(Socket client, Object message) throws IOException {
		if (state != RUNNING) {
			return;
		}
		ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream()); 
		out.writeObject(message);
		out.flush();
	}
	
	public Set<Socket> clients() {
		return readThreads.keySet();
	}
	
}
