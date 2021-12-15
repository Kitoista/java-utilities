package javautilities.games.basic.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	
	public static final int READY = 0;
	public static final int JOINED = 1;

	String name;
	Socket server;
	String address = "81.217.87.129";
	int port = 19969;
	
	public ServerMessageListener messageListener;
	public ServerJoinedListener joinedListener;
	
	int state = READY;
	
	Thread readThread;
	
	public Client(String name) {
		this.name = name;
	}
	
	public void join() throws UnknownHostException, IOException {
		if (state != READY) {
			return;
		}
		server = new Socket(address, port);
		onJoin();
		readThread = new Thread(() -> {
			while (state == JOINED) {
				try {
					ObjectInputStream in = new ObjectInputStream(server.getInputStream());
					Object message = in.readObject();
					onMessage(message);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
		readThread.start();
	}
	
	public void onJoin() {
		state = JOINED;
		System.out.println(this + " has joined to the server");
		if (joinedListener != null) {
			joinedListener.onJoin();
		}
	}
	protected void onMessage(Object message) {
		// System.out.println(this + " got message from server " + message);
		if (messageListener != null) {
			messageListener.onMessage(message);
		}
	}
	
	public void sendMessage(Object message) throws IOException {
		if (state != JOINED) {
			return;
		}
		ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
		out.writeObject(message);
		out.flush();
	}
	
	public String toString() {
		return name;
	}
	
}
