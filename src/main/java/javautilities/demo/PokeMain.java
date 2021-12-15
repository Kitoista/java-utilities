package javautilities.demo;

import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JSlider;

import javautilities.pokemon.Pokemon;
import javautilities.pokemon.Stats;
import javautilities.ui.defaults.KLabel;
import javautilities.ui.defaults.KPanel;
import javautilities.ui.frame.Show;

public class PokeMain {

	public static void main(String[] args) {

		Pokemon meowth19B = new Pokemon("Meowth", Stats.MEOWTH());
		Pokemon meowth19G = new Pokemon("Meowth", Stats.MEOWTH());
		Pokemon meowth20B = new Pokemon("Meowth", Stats.MEOWTH());
		Pokemon meowth20G = new Pokemon("Meowth", Stats.MEOWTH());
		Pokemon meowth21B = new Pokemon("Meowth", Stats.MEOWTH());
		Pokemon meowth21G = new Pokemon("Meowth", Stats.MEOWTH());
		Pokemon meowth22B = new Pokemon("Meowth", Stats.MEOWTH());
		Pokemon meowth22G = new Pokemon("Meowth", Stats.MEOWTH());

		meowth19B.lvl = 19;
		meowth19B.ivs.hp = 0;
		meowth19G.lvl = 19;
		meowth19G.ivs.hp = 31;
		meowth20B.lvl = 20;
		meowth20B.ivs.hp = 0;
		meowth20G.lvl = 20;
		meowth20G.ivs.hp = 31;
		meowth21B.lvl = 21;
		meowth21B.ivs.hp = 0;
		meowth21G.lvl = 21;
		meowth21G.ivs.hp = 31;
		meowth22B.lvl = 22;
		meowth22B.ivs.hp = 0;
		meowth22G.lvl = 22;
		meowth22G.ivs.hp = 31;
		
		Pokemon.init();

		System.out.println(meowth19B.lvl + " " + meowth19B.stats.hp + "-" + meowth19G.stats.hp);
		System.out.println(meowth20B.lvl + " " + meowth20B.stats.hp + "-" + meowth20G.stats.hp);
		System.out.println(meowth21B.lvl + " " + meowth21B.stats.hp + "-" + meowth21G.stats.hp);
		System.out.println(meowth22B.lvl + " " + meowth22B.stats.hp + "-" + meowth22G.stats.hp);
		
		/*Show show = new Show();
		
		KPanel panel = new KPanel();
		panel.add(pokemonPanel(randClefairy));
		panel.add(pokemonPanel(randPersian));
		
		show.component(panel);*/
/*
		snorlax.dealDamage(100, "atk", defClefairy);
		snorlax.dealDamage(100, "atk", almostDefClefairy);
		
		defClefairy.dealDamage(100, "spatk", snorlax);
		almostDefClefairy.dealDamage(100, "spatk", snorlax);

		snorlax.dealDamage(100, "atk", chansey);
		chansey.dealDamage(100, "spatk", snorlax);*/
	}
	
	public static KPanel pokemonPanel(Pokemon pokemon) {
		KPanel re = new KPanel();
		re.setLayout(new GridLayout(0, 2));
		re.add(new KLabel(pokemon.name + " "));
		re.add(new KLabel("lvl " + pokemon.lvl));

		re.add(new KLabel("STATS"));
		re.add(new KLabel(""));
		
		re.add(new KLabel("STATS"));
		re.add(new KLabel(""));
		
		return re;
	}
	
	private static List<Component> statsPanel(Stats stats, boolean canModify) {
		List<Component> re = new ArrayList<>();
		
		re.add(new KLabel("hp"));
		
		return re;
	}
	
}
