package javautilities.games.basic.net;

import java.net.Socket;

public interface ClientMessageListener {

	void onMessage(Socket client, Object message);
	
}
