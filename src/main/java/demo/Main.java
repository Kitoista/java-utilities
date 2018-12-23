package demo;

import java.io.FileNotFoundException;
import java.util.Arrays;

import demo.testclasses.Test2;
import demo.testclasses.Test3;
import meta.info.MetaInfo;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println(Arrays.toString(MetaInfo.getDeepInterfaces(Test2.class).toArray()));
		System.out.println(Arrays.toString(MetaInfo.getSuperClasses(Test3.class).toArray()));
	}
	
}
