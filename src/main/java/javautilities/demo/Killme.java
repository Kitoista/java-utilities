package javautilities.demo;

import java.awt.Dimension;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JPanel;

import javautilities.ui.frame.Show;

public class Killme {
	
	public static void main(String[] args) {
		Show s = new Show();
		JPanel p = new JPanel();
		p.setPreferredSize(new Dimension(1000, 1000));
		p.setLayout(null);
		p.add(new JLabel("nyomi"));
		s.component(p);
	}
	
}
