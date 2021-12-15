package javautilities.demo.phasmaphobia;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.text.StyleConstants;

import javautilities.ui.defaults.KButton;
import javautilities.ui.defaults.KCheckBox;
import javautilities.ui.defaults.KFrame;
import javautilities.ui.defaults.KLabel;
import javautilities.ui.defaults.KPanel;
import javautilities.ui.defaults.KSlider;
import javautilities.ui.defaults.KTextPane;
import javautilities.ui.defaults.UI;
import javautilities.ui.frame.Frames;

public class Phasmaphobia {

	KFrame frame;
	KPanel main;
	
	static final Color possibleColor = UI.fontColor;
	static final Color existingColor = Color.green;
	static final Color forbiddenColor = Color.red;

	static final int EMF5 = 0;
	static final int Fingerprints = 1;
	static final int GhostWriting = 2;
	static final int SpiritBox = 3;
	static final int FreezingTemperature = 4;
	static final int GhostOrb = 5;
	static final int DOTSProjector = 6;
	static final String[] EvidenceNames = new String[] { "EMF 5", "Fingerprints", "Ghost Writing", "Spirit Box", "Freezing Temperature", "Ghost Orb", "D.O.T.S Projector" };
	
	KLabel[] labels = new KLabel[EvidenceNames.length];
	KCheckBox[] trueFields = new KCheckBox[EvidenceNames.length];
	KCheckBox[] falseFields = new KCheckBox[EvidenceNames.length];
	
	KButton clear = new KButton("Clear");
	KSlider translucency = new KSlider(JSlider.HORIZONTAL, 5, 100, 70);
	KTextPane result = new KTextPane();
	
	Ghost[] ghosts = new Ghost[] {
			new Ghost("Spirit", "Nothing", "Smudge", EMF5, GhostWriting, SpiritBox),
			new Ghost("Wraith", "Floor is lava", "Salty and toxic", EMF5, SpiritBox, DOTSProjector),
			new Ghost("Phantom", "Don't look, it hurts", "Shy on photo", SpiritBox, Fingerprints, DOTSProjector),
			new Ghost("Poltergeist", "Throws shit", "When can't throw shit", Fingerprints, SpiritBox, GhostWriting),
			new Ghost("Banshee", "Can't juggle with it", "Crucifix is maybe useful", GhostOrb, Fingerprints, DOTSProjector),
			new Ghost("Jinn", "The further the faster", "Fueled by electicity", EMF5, Fingerprints, FreezingTemperature),
			new Ghost("Mare", "Strong in the dark", "Weak in the light", SpiritBox, GhostWriting, GhostOrb),
			new Ghost("Revenant", "You can't run", "But you can hide bitch", GhostOrb, FreezingTemperature, GhostWriting),
			new Ghost("Shade", "Socially awkward", "Socially awkward", EMF5, GhostWriting, FreezingTemperature),
			new Ghost("Demon", "Attacks like a madlad", "Free luigi board", GhostWriting, Fingerprints, FreezingTemperature),
			new Ghost("Yurei", "Drains ur sanity", "Smoke 'em outta there!", DOTSProjector, FreezingTemperature, GhostOrb),
			new Ghost("Oni", "Why are you runnin'", "Easy to find (not shy)", EMF5, FreezingTemperature, DOTSProjector),
			new Ghost("Yokai", "Stay quiet", "Stay quiet", DOTSProjector, SpiritBox, GhostOrb),
			new Ghost("Hantu", "Iceskating", "Melts", Fingerprints, FreezingTemperature, GhostOrb),
			new Ghost("Goryo", "Have to set up a solo party", "Kept on chains", DOTSProjector, EMF5, Fingerprints),
			new Ghost("Myling", "Sneaky hunt", "Louder than Sophie's mother", GhostWriting, EMF5, Fingerprints),
			new Ghost("Onryo", "No fire no life", "Fire = life", SpiritBox, GhostOrb, FreezingTemperature),
			new Ghost("The Twins", "They got each other's backs", "And make it double", EMF5, SpiritBox, FreezingTemperature),
			new Ghost("Raiju", "Dynamo", "Succ electricity", EMF5, GhostOrb, DOTSProjector),
			new Ghost("Obake", "Sneaky sneaky", "Shapeshift evidence", EMF5, GhostOrb, Fingerprints),
	};
	
	boolean dirty = false;
	
	public static void main(String[] args) {
		new Phasmaphobia();
	}
	
	public Phasmaphobia() {
		JFrame.setDefaultLookAndFeelDecorated(true);

		SwingUtilities.invokeLater(() -> {
			
			frame = new KFrame();
			KPanel content = new KPanel();
			frame.setName("Phasmaphobia Journal");
			frame.setLayout(new FlowLayout());
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(200, 350);
			frame.setLocationRelativeTo(null);
			content.setLayout(new FlowLayout());
			frame.add(content);
			
			main = new KPanel();
			main.setLayout(new BorderLayout());
			
			main.add(evidencePanel(), BorderLayout.NORTH);
			main.add(resultPanel(), BorderLayout.CENTER);
			
			content.add(main);
			Frames.setTranslucency(frame, translucency.getValue() / 100f);
			frame.setAlwaysOnTop(true);
			frame.pack();
			frame.setVisible(true);
			filter();
		});
	}
	
	KPanel evidencePanel() {
		KPanel panel = new KPanel();
		panel.setLayout(new GridLayout(EvidenceNames.length + 1, 2));
		
		panel.add("Evidence", "True - False");
		
		for (int i = 0; i < EvidenceNames.length; ++i) {
			labels[i] = panel.add(EvidenceNames[i]);
			
			KPanel container = new KPanel();
			KCheckBox trueField = new KCheckBox();
			KCheckBox falseField = new KCheckBox();
			container.add(trueFields[i] = trueField);
			container.add(falseFields[i] = falseField);
			panel.add(container);
			
			trueFields[i].addActionListener(e -> {
				boolean wasDirty = dirty;
				dirty = true;
				if (trueField.isSelected()) {
					falseField.setSelected(false);
				}
				if (!wasDirty) {
					filter();
				}
			});
			
			falseFields[i].addActionListener(e -> {
				boolean wasDirty = dirty;
				dirty = true;
				if (falseField.isSelected()) {
					trueField.setSelected(false);
				}
				if (!wasDirty) {
					filter();
				}
			});
		}
		
		return panel;
	}
	
	KPanel resultPanel() {
		KPanel panel = new KPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		result.setTextAlignment(StyleConstants.ALIGN_CENTER);
		KPanel container = new KPanel();
		container.add(translucency);
		container.add(clear);
		panel.add(container);
		panel.add(result);
		panel.add("  ");
		panel.add("  ");
		
		clear.addActionListener(e -> {
			dirty = true;
			for (int i = 0; i < EvidenceNames.length; ++i) {
				trueFields[i].setSelected(false);
				falseFields[i].setSelected(false);
			}
			filter();
		});
		
		translucency.addChangeListener(e -> {
			frame.setOpacity(translucency.getValue() / 100f);
		});
		return panel;
	}
	
	List<Ghost> filter() {
		List<Ghost> canBe = new ArrayList<>(Arrays.asList(ghosts));
		
		Set<Integer> existingEvidences = new HashSet<>();
		Set<Integer> forbiddenEvidences = new HashSet<>();

		for (int evidenceIndex = 0; evidenceIndex < EvidenceNames.length; evidenceIndex++) {
			if (trueFields[evidenceIndex].isSelected()) {
				existingEvidences.add(evidenceIndex);
			} else if (falseFields[evidenceIndex].isSelected()) {
				forbiddenEvidences.add(evidenceIndex);
			} else {
				continue;
			}

			for (int ghostIndex = 0; ghostIndex < ghosts.length; ++ghostIndex) {
				boolean ghostHasIt = false;
				for (int i = 0; i < ghosts[ghostIndex].evidences.length; ++i) {
					if (ghosts[ghostIndex].evidences[i] == evidenceIndex) {
						ghostHasIt = true;
					}
				}
				if ((existingEvidences.contains(evidenceIndex) && !ghostHasIt) || 
					(forbiddenEvidences.contains(evidenceIndex) && ghostHasIt)
				) {
					canBe.remove(ghosts[ghostIndex]);
				}
			}
		}
		
		if (canBe.isEmpty()) {
			result.setText("- There was no match -");
			
		} else if (canBe.size() == ghosts.length) {
			result.setText("- Anything is possible -");
			for (int i = 0; i < EvidenceNames.length; ++i) {
				labels[i].setForeground(possibleColor);
			}
		} else {
			String text = "";
			Set<Integer> possibleEvidences = new HashSet<>();

			for (int i = 0; i < canBe.size(); i++) {
				if (i % 3 == 0 && i != 0) {
					text += "\n";
				}
				text += canBe.get(i).name + " ";
				
				for (int j = 0; j < canBe.get(i).evidences.length; ++j) {
					int evidenceIndex = canBe.get(i).evidences[j];
					
					if (!existingEvidences.contains(evidenceIndex) &&
						!forbiddenEvidences.contains(evidenceIndex)
					) {
						possibleEvidences.add(evidenceIndex);
					}
					
				}
			}

			if (canBe.size() == 1) {
				text += "\nStrength: " + canBe.get(0).strength + "\nWeakness: " + canBe.get(0).weakness;
			}

			result.setText(text);
			
			for (int i = 0; i < EvidenceNames.length; ++i) {
				if (possibleEvidences.contains(i)) {
					labels[i].setForeground(possibleColor);
				} else if (existingEvidences.contains(i)) {
					labels[i].setForeground(existingColor);
				} else if (forbiddenEvidences.contains(i)) {
					labels[i].setForeground(forbiddenColor);
				} else {
					labels[i].setForeground(forbiddenColor);
				}
			}
		}
		
		dirty = false;
		return canBe;
	}
	
}
