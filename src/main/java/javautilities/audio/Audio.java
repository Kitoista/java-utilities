package javautilities.audio;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Audio {

	public static void play(String filename) {
		File file = new File(filename + ".wav");
	    try{
	        Clip clip = AudioSystem.getClip();
	        clip.open(AudioSystem.getAudioInputStream(file));
	        clip.start();
	    } catch (Exception e){
	        e.printStackTrace();
	    }
	}
	
}
