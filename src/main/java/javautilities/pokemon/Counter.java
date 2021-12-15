package javautilities.pokemon;

public class Counter {

	int lvl = 100;
	int base = 65;
	int iv = 31;
	int ev = 252;
	float nature = 1.1f;
	
	int atk = 236;
	int def = 119;
	int power = 100;
	
	public static void main(String[] args) {
		Counter p = new Counter();
		
		System.out.println(p.otherStat());
		System.out.println(p.hp());
		System.out.println(p.damage());
	}

	public int otherStat() {
		return (int) ((2*base+iv+(ev/4)*lvl/100+5)*nature);
	}
	
	public int hp() {
		return (2*base+iv+(ev/4)*lvl)/100+lvl+10;
	}
	
	public int damage() {
		return ((2*lvl)/5 + 2) * power * atk / def / 50 + 2;
	}
	
}
