package string;

public class Strings {
	
	public static String repeat(String wot, int num) {
		String re = "";
		for (int i = 0; i < num; ++i) {
			re += wot;
		}
		return re;
	}

}
