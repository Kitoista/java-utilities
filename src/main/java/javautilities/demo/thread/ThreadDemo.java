package javautilities.demo.thread;

import java.util.ArrayList;
import java.util.List;

import javautilities.thread.ThreadPool;

public class ThreadDemo {

	public static void main(String[] args) {
		Job job1 = new Job();
		Job job2 = new Job();
		Job job3 = new Job();
		ThreadPool<Integer, Integer> tp = new ThreadPool(job1, job2, job3);
		tp.startThreads();
		
		System.out.println("Started main");
		
		List<Integer> results = new ArrayList<>();
		results.add(1);
		results.add(2);
		results.add(3);
		for (int i = 0; i < 10; i++) {
			results = tp.nextResults(results);
			for (int j = 0; j < results.size(); j++) {
				System.out.println(results.get(j));
			}
		}
		
		tp.stopThreads();
		for (int i = 0; i < results.size(); i++) {
			System.out.println(results.get(i));
		}
		
	}
	
}
