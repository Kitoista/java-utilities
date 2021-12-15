package javautilities.pokemon;

import java.util.ArrayList;
import java.util.List;

public class Pokemon {

	public int lvl = 100;
	public Stats base;
	public Stats ivs = Stats.randomIvs();
	public Stats evs = Stats.empty();
	public Stats nature = Stats.empty();
	
	public String name = "Pokemon";
	
	public Stats stats;
	
	public boolean eviolite;
	
	public static List<Pokemon> pokemons = new ArrayList<Pokemon>();
	
	public Pokemon(String name, Stats base) {
		this.name = name;
		this.base = base;
		pokemons.add(this);
	}
	
	public Stats countStats() {
		Stats re = Stats.empty();
		re.hp = hp();
		re.atk = otherStat("atk");
		re.def = otherStat("def");
		re.spatk = otherStat("spatk");
		re.spdef = otherStat("spdef");
		re.spd = otherStat("spd");
		
		if (eviolite) {
			re.spdef *= 1.5;
			re.def *= 1.5;
		}
		
		this.stats = re;
		return re;
	}
	
	public static void init() {
		for (Pokemon pokemon : pokemons) {
			pokemon.countStats();
			System.out.println(pokemon);
		}
	}
	
	public int dealDamage(int power, String mode, Pokemon enemy) {
		int A = stats.atk;
		int D = enemy.stats.def;
		if (mode == "spatk") {
			A = stats.spatk;
			D = enemy.stats.spdef;
		}
		int re = ((2*lvl)/5 + 2) * power * A / D / 50 + 2;
		System.out.println(
			name + " attacked " + enemy.name + " for " + re + " damage (" + mode + ").\n" +
			"It was " + re + "/" + enemy.stats.hp + " (" + (re * 100 / enemy.stats.hp) + "%)");
		return re;
	}
	
	private int otherStat(String stat) {
		int natureSign = nature.get(stat);
		float nature = natureSign < 0 ? 0.9f : natureSign > 0 ? 1.1f : 1;
		return (int) (((2*base.get(stat)+ivs.get(stat)+(evs.get(stat)/4))*lvl/100+5)*nature);
	}
	
	private int hp() {
		return ((2*base.hp+ivs.hp+(evs.hp/4))*lvl)/100+lvl+10;
	}

	public String toString() {
		String firstTabs = "";
		for (int i=0;i<name.length() / 10;++i) {
			firstTabs += "\t";
		}
		return name + " (" + lvl + ") \tBase\tIvs\tEvs" + "\n" +
			   "HP:       " + firstTabs + stats.hp + "\t" + base.hp + "\t" + ivs.hp + "\t" + evs.hp + "\n" +
			   "ATK:      " + firstTabs + stats.atk + "\t" + base.atk + "\t" + ivs.atk + "\t" + evs.atk + "\n" + 
			   "DEF:      " + firstTabs + stats.def + "\t" + base.def + "\t" + ivs.def + "\t" + evs.def + "\n" + 
			   "SPATK:    " + firstTabs + stats.spatk + "\t" + base.spatk + "\t" + ivs.spatk + "\t" + evs.spatk + "\n" + 
			   "SPDEF:    " + firstTabs + stats.spdef + "\t" + base.spdef + "\t" + ivs.spdef + "\t" + evs.spdef + "\n" + 
			   "SPD:      " + firstTabs + stats.spd + "\t" + base.spd + "\t" + ivs.spd + "\t" + evs.spd + "\n";
	}
	
}
