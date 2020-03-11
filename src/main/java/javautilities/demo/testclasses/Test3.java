package javautilities.demo.testclasses;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Test3 extends Test2 {

	private static final long serialVersionUID = 1L;

	public int easyToSet;
	private int mediumToSet;
	private int impossibleToSet;
	private double doubleMember;
	private boolean booleanMember;
	private Test test;
	private BufferedImage image;
	private List<Object> list = new ArrayList<>();
	
	public Test3() {
	}
	
	public Test3(int a, int b, double c, boolean d, Test e) {
		mediumToSet = a;
		impossibleToSet = b;
		doubleMember = c;
		booleanMember = d;
		test = e;
	}
	
	public void asd2(int a, String b) {
	}
	
	public static void staticMethod() {
		System.out.println("Test3");
	}
	
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
	
	public String toString() {
		return "easyToSet: " + easyToSet + "\n" + "mediumToSet: " + mediumToSet + "\n" + "impossibleToSet: " + impossibleToSet; 
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}
	
	public void nyomi() {
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

	public List<Object> getList() {
		return list;
	}

	public void setList(List<Object> list) {
		this.list = list;
	}
	
}
