package javautilities.string;

public class Strings {
	
	public static String repeat(String wot, int num) {
		String re = "";
		for (int i = 0; i < num; ++i) {
			re += wot;
		}
		return re;
	}

	public static boolean isEmpty(String text) {
		return text == null || text.isEmpty();
	}

	public static String capitalizeFirst(String str) {
		if (isEmpty(str)) return str;
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
	
	public static String lowerCaseFirst(String str) {
		if (isEmpty(str)) return str;
		return str.substring(0, 1).toLowerCase() + str.substring(1);
	}
	
}
