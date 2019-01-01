package demo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import demo.testclasses.Test3;
import io.serializable.SerializableIO;
import ui.component.bind.imp.BindedCollection;
import ui.component.bind.imp.ObjectComponent;
import ui.frame.Frames;
import ui.frame.Show;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		
		// new Show().component(new FrameHandler());
		
		System.out.println(Frames.getFrameBorderSize());
		System.out.println(Frames.getMaximalizedSize());
		
		Test3 t1 = null;
		Test3 t2 = null;
		try {
			t1 = (Test3) SerializableIO.load("t1.obj");
			t2 = (Test3) SerializableIO.load("t2.obj");
		} catch (ClassNotFoundException | IOException e1) {
			e1.printStackTrace();
		}
		
		t1.setName("nyomi");
		
		Show show = new Show();
		try (
			ObjectComponent bp = new ObjectComponent(Test3.class);
		) {
			((BindedCollection<Test3>) bp.getBindedProps().get("list")).setConstructor(() -> {
				return new Test3();
			});
			show.component(bp);
			
			sleep(1000);
			
			bp.setObject(t1);
			
			boolean preValue = t1.isBooleanMember();
			
			sleep(1000);
			
			while(t1.isBooleanMember() == preValue) {
				sleep(100);
			}
			
			bp.setObject(t2);
			
			preValue = t2.isBooleanMember();
			
			while(t2.isBooleanMember() == preValue) {
				sleep(100);
			}
			
			
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException | InstantiationException e) {
			e.printStackTrace();
		}
		
		try {
			SerializableIO.save(t1, "t1.obj");
			SerializableIO.save(t2, "t2.obj");
		} catch (IOException e) {
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
