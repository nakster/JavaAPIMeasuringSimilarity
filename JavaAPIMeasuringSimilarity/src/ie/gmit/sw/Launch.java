package ie.gmit.sw;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * This is the Launch class which starts the threads 
 * @author naqi Ahmad
 *
 */
public class Launch {
	/**
	 * Takes in the values from Menu class and runs the threads 
	 * @param file This is the file that the user specifies 
	 * @param fileTwo This is the second file that the user specifies 
	 * @param shingleSize This is the size of each Shingle 
	 * @param numOfHashes This is the size of the min-hashes 
	 * @param poolSize This is the size of the threads 
	 * @throws InterruptedException thrown when a thread is waiting or sleeping
	 */
	public void StartThreads(String file, String fileTwo, int shingleSize, int numOfHashes, int poolSize) throws InterruptedException {
		//declare new queue 
		BlockingQueue<Shingle> queue = new LinkedBlockingQueue<Shingle>();	
		
		//create new threads 
		Thread t1 = new Thread(new FileParser(file, shingleSize, queue, 1), "T1");
		Thread t2 = new Thread(new FileParser(fileTwo, shingleSize, queue, 2), "T2");
		Thread t3 = new Thread(new Consumer(queue, numOfHashes, poolSize), "T3");
		//runs the threads 
		t1.start();
		t2.start();
		t3.start();
		//this joins 
		t1.join();
		t2.join();
		t3.join();
	}
}