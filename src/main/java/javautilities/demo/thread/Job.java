package javautilities.demo.thread;

import java.util.Random;

import javautilities.thread.ThreadPool;

public class Job implements ThreadPool.ThreadJob<Integer, Integer> {
	@Override
	public Integer run(int index, Integer input) {
		Random r = new Random();
		int counter = 1;
		for (int i = 0; i < 1000000000; i++) {
			counter *= -1;
			counter += r.nextInt(2) - 1;
		}
		return counter;
	}
}