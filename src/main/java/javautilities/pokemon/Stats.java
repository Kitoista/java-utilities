package javautilities.pokemon;

import java.util.Random;

public class Stats {

	public static final String[] statKeys = new String[]{"hp", "atk", "def", "spatk", "spdef", "spd"};
	
	public int hp;
	public int atk;
	public int def;
	public int spatk;
	public int spdef;
	public int spd;

	protected Stats(int hp, int atk, int def, int spatk, int spdef, int spd) {
		this.hp = hp;
		this.atk = atk;
		this.def = def;
		this.spatk = spatk;
		this.spdef = spdef;
		this.spd = spd;
	}
	
	public Stats set(String stat, int value) {
		switch (stat) {
		case "hp":
			hp = value;
		case "atk":
			atk = value;
		case "def":
			def = value;
		case "spatk":
			spatk = value;
		case "spdef":
			spdef = value;
		case "spd":
			spd = value;
		}
		return this;
	}
	
	public int get(String stat) {
		switch (stat) {
		case "hp":
			return hp;
		case "atk":
			return atk;
		case "def":
			return def;
		case "spatk":
			return spatk;
		case "spdef":
			return spdef;
		case "spd":
			return spd;
		}
		return 0;
	}
	
	public String toString() {
		return "HP: " + hp + "\n" +
			   "ATK: " + atk + "\n" + 
			   "DEF: " + def + "\n" + 
			   "SPATK: " + spatk + "\n" + 
			   "SPDEF: " + spdef + "\n" + 
			   "SPD: " + spd + "\n";
	}

	public static final Stats maxIvs = new Stats(31, 31, 31, 31, 31, 31);

	public static final Stats CLEFAIRY() { return new Stats(70, 45, 48, 60, 65, 35); }
	public static final Stats CHANSEY() { return new Stats(250, 5, 5, 35, 105, 50); }
	public static final Stats CLOYSTER() { return new Stats(50, 95, 180, 85, 45, 70); }
	public static final Stats PERSIAN() { return new Stats(65, 70, 60, 65, 65, 115); }
	public static final Stats SHUCKLE() { return new Stats(20, 10, 230, 10, 230, 5); }
	public static final Stats SNORLAX() { return new Stats(160, 110, 65, 65, 110, 30); }
	public static final Stats MEOWTH() { return new Stats(40, 45, 35, 40, 40, 90); }

	public static final Stats empty() {
		return new Stats(0, 0, 0, 0, 0, 0);
	}

	public static final Stats randomIvs() {
		Random r = new Random();
		return new Stats(r.nextInt(32), r.nextInt(32), r.nextInt(32), r.nextInt(32), r.nextInt(32), r.nextInt(32));
	}

}
