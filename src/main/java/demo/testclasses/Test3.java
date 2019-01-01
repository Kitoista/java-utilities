package demo.testclasses;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Test3 extends Test2 {

	private static final long serialVersionUID = 1L;

	public int easyToSet;
	private int mediumToSet;
	private int impossibleToSet;
	private double doubleMember;
	private boolean booleanMember;
	private Test test;
	private transient BufferedImage image;
	private List<Integer> list = new ArrayList<>();
	
	public Test3() {
	}
	
	public Test3(int a, int b, double c, boolean d, Test e) {
		mediumToSet = a;
		impossibleToSet = b;
		doubleMember = c;
		booleanMember = d;
		test = e;
	}
	
	
	// methods
	
	
	public void asd2(int a, String b) {
	}
	
	
	public void nyomi() {
	}
	
	
	// static methods
	
	
	public static void staticMethod() {
		System.out.println("Test3");
	}
	
	
	// orverrides
	
	
	public String toString() {
		return "easyToSet: " + easyToSet + "\n" + "mediumToSet: " + mediumToSet + "\n" + "impossibleToSet: " + impossibleToSet; 
	}

	
	// basic IO
	
	
	private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        ImageIO.write(image, "png", out); // png is lossless
    }
	
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        image = ImageIO.read(in);
    }
	
	
	// getters setters XXX
	
	
	public void setMediumToSet(int mediumToSet) {
		this.mediumToSet = mediumToSet;
		this.impossibleToSet = 2 * mediumToSet;
	}

	public int getImpossibleToSet() {
		return impossibleToSet;
	}
	
	public int getMediumToSet() {
		return mediumToSet;
	}
	
	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}
	
	public double getDoubleMember() {
		return doubleMember;
	}

	public void setDoubleMember(double doubleMember) {
		this.doubleMember = doubleMember;
	}

	public boolean isBooleanMember() {
		return booleanMember;
	}

	public void setBooleanMember(boolean booleanMember) {
		this.booleanMember = booleanMember;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public List<Integer> getList() {
		return list;
	}

	public void setList(List<Integer> list) {
		this.list = list;
	}
	
}
