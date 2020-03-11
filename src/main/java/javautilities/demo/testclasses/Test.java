package javautilities.demo.testclasses;

import java.io.IOException;
import java.io.Serializable;
import java.nio.channels.Channel;

public class Test implements Serializable, Channel {
	
	public boolean isOpen() {
		return false;
	}

	public void close() throws IOException {
		// TODO Auto-generated method stub
		
	}

}
