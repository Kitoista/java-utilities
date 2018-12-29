package demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import javax.imageio.ImageIO;

import demo.testclasses.Test2;
import demo.testclasses.Test3;
import meta.MetaInfo;
import meta.MetaMethods;
import number.NumberParseException;
import number.Numbers;
import ui.component.bind.imp.ObjectComponent;
import ui.frame.Frames;
import ui.frame.Show;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {

		System.out.println(Frames.getFrameBorderSize());
		System.out.println(Frames.getMaximalizedSize());
		
		Test3 t1 = new Test3();
		Test3 t2 = new Test3(10, 40, 53.1, true, null);
		
		t1.setName("nyomi");
		
		Show show = new Show();
		try (
			ObjectComponent bp = new ObjectComponent(Test3.class);
		) {
			show.component(bp);
			
			sleep(1000);
			
			bp.setObject(t1);
			
			sleep(1000);
			
			try {
				t1.setImage(ImageIO.read(new File("dewgong.jpg")));
				t2.setImage(ImageIO.read(new File("snorlax.jpg")));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			sleep(1000);
			
			while(!t1.isBooleanMember()) {
				sleep(100);
			}
			
			bp.setObject(t2);
			
			while(t2.isBooleanMember()) {
				sleep(100);
			}
			
			
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException | InstantiationException e) {
			e.printStackTrace();
		}
		
		System.out.println("ended main");

	}

	private static void sleep(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
