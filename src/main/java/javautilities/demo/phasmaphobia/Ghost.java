package javautilities.demo.phasmaphobia;

public class Ghost {
	
	String name;
	int[] evidences = new int[3];
	String strength = "Nothing";
	String weakness = "Nothing";
	
	public Ghost(String name, String strength, String weakness, int... evidences) {
		this.name = name;
		this.strength = strength;
		this.weakness = weakness;
		this.evidences = evidences;
	}
	
	public boolean check(int evidence) {
		for (int i = 0; i < evidences.length; i++) {
			if (evidences[i] == evidence) {
				return true;
			}
		}
		return false;
	}

}
