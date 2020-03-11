package javautilities.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.imageio.ImageIO;

import javautilities.demo.testclasses.Test3;
import javautilities.ui.component.bind.imp.ObjectComponent;
import javautilities.ui.frame.Frames;
import javautilities.ui.frame.Show;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		
		// new Show().component(new FrameHandler());
		
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
			
			for (int i = 0; i < 10; i++) {
				t1.getList().add(i);
				sleep(500);
				System.out.println("added " + i);
			}
			t1.getList().add(t1);
			
			
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
