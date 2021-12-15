package javautilities.games.basic.net;

import java.net.Socket;

public interface ClientJoinedListener {

	void onJoin(Socket socker);
	
}
