package ie.gmit.sw;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class Launch {
	
	public void StartThreads(String file, String fileTwo, int shingleSize, int numOfHashes, int poolSize) throws InterruptedException {
		//declare new queue 
		BlockingQueue<Shingle> queue = new LinkedBlockingQueue<Shingle>();	
		
		//create new threads 
		Thread t1 = new Thread(new FileParser(file, numOfHashes, shingleSize, queue, 1), "T1");
		Thread t2 = new Thread(new FileParser(fileTwo, numOfHashes, shingleSize, queue, 2), "T2");
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