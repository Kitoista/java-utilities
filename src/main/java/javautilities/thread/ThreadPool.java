package javautilities.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class ThreadPool<I, O> {

	Thread[] threads;
	List<ThreadFunction<I, O>> threadFunctions = new ArrayList<>();
	Semaphore[] threadLocks;
	Semaphore[] mainLocks;
	boolean running = false;
	int counter = 0;
	
	public ThreadPool(ThreadJob<I, O>... jobs) {
		threads = new Thread[jobs.length];
		threadLocks = new Semaphore[jobs.length];
		mainLocks = new Semaphore[jobs.length];
		for (int i = 0; i < jobs.length; i++) {
			threadLocks[i] = new Semaphore(0);
			mainLocks[i] = new Semaphore(0);
			threadFunctions.add(new ThreadFunction<I, O>(i, mainLocks[i], threadLocks[i], this, jobs[i]));
			threads[i] = new Thread(threadFunctions.get(i));
		}
	}
	
	public void startThreads() {
		running = true;
		System.out.println("Started threads");
		for (Thread thread : threads) {
			thread.start();
		}
	}
	
	public List<O> nextResults(List<I> inputs) {
		List<O> results = new ArrayList<>();
		
		++counter;
		
		for (int i = 0; i < threadFunctions.size(); i++) {
			threadFunctions.get(i).input = inputs.get(i);
		}

		for (int i = 0; i < threadLocks.length; i++) {
			synchronized(threadLocks[i]) {
				System.out.println("Notify thread(" + i + ")");
				threadLocks[i].notify();
			}
		}
		
		for (int i = 0; i < mainLocks.length; i++) {
			synchronized(mainLocks[i]) {
				if (counter > threadFunctions.get(i).counter) {
					System.out.println("Wait thread(" + i + ")");
					try { mainLocks[i].wait(); } catch (InterruptedException e) {}
				}
			}
		}
		
		for (int i = 0; i < threadFunctions.size(); i++) {
			results.add(threadFunctions.get(i).result);
		}
		
		return results;
	}
	
	public void stopThreads() {
		for (int i = 0; i < threadLocks.length; i++) {
			synchronized(threadLocks[i]) {
				running = false;
				System.out.println("Notify thread(" + i + ")");	
				threadLocks[i].notify();
			}
		}
	}
	
	
	protected class ThreadFunction<I, O> implements Runnable {
		protected int index;
		protected Object mainLock;
		protected Object threadLock;
		protected ThreadJob<I, O> job;
		protected ThreadPool<I, O> parent;
		public int counter = 0;
		public I input;
		public O result;
		
		public ThreadFunction(int index, Object mainLock, Object threadLock, ThreadPool<I, O> parent, ThreadJob<I, O> job) {
			this.index = index;
			this.mainLock = mainLock;
			this.threadLock = threadLock;
			this.parent = parent;
			this.job = job;
		}

		@Override
		public void run() {
			System.out.println("Started thread(" + index + ")");
			synchronized(threadLock) {
				if (parent.counter <= counter) {
					try { threadLock.wait(); } catch (InterruptedException e) {}
				}
			}
			System.out.println("First signal(" + index + ")");
			
			while(parent.running) {
				result = job.run(index, input);
				++counter;
				
				synchronized(threadLock) {
					synchronized(mainLock) {
						System.out.println("Notify main(" + index + ")");
						mainLock.notify();
					}
					if (parent.counter <= counter) {
						try { threadLock.wait(); } catch (InterruptedException e) { }
					}
				}
			}
			System.out.println("Thread end(" + index + ")");
		}
	}
	
	public interface ThreadJob<I, O> {
		O run(int index, I input);
	}
	
}
