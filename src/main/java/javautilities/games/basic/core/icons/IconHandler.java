package javautilities.games.basic.core.icons;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IconHandler {

	String getIcon();
	void tick();
	
	static IconHandler load(String data) {
		if (data == null) {
			return new StaticIcon(null);
		}
		String[] rows = data.split("\r\n");
		
		if ("Animation".equals(rows[0])) {
			String[] icons = Arrays.copyOfRange(rows, 1, rows.length);
			return new Animation(icons);
		}
		if ("StateAnimation".equals(rows[0])) {
			Map<String, String[]> icons = new HashMap<String, String[]>();
			String currentState = null;
			List<String> currentList = null;
			
			for (int i = 1; i < rows.length; ++i) {
				String row = rows[i];
				if (row.startsWith("--")) {
					if (currentState != null && currentList != null) {
						String[] array = new String[currentList.size()];
						icons.put(currentState, currentList.toArray(array));
					}
					currentState = row.replace("--", "");
					currentList = new ArrayList<String>();
				} else {
					currentList.add(row);
				}
			}
			if (currentState != null && currentList != null) {
				String[] array = new String[currentList.size()];
				icons.put(currentState, currentList.toArray(array));
			}
			
			return new StateAnimation(icons);
		}
		return new StaticIcon(rows[0]);
	}
	
	static IconHandler load(Path file) {
		try {
			return load(Files.readString(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new StaticIcon(null);
	}
	
}
