package ie.gmit.sw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import ie.gmit.sw.Poison;

/**
 * This is a consumer class This gets the Random Minhashes from the shingles 
 * 
 * @author Naqi Ahmad
 *
 */
public class Consumer implements Runnable {

	private BlockingQueue<Shingle> bQueue;
	private int numOfMinH;
	private int[] minhashes;
	private ConcurrentMap<Integer,List<Integer>> map = new ConcurrentHashMap<Integer, List<Integer>>();
	private ExecutorService pool;

	//constructor 
	/**
	 * 
	 * @param bQueue is a BlockingQueue 
	 * @param numOfMinH int which is the size of the minhashes 
	 * @param poolSize size of the threads 
	 */
	public Consumer(BlockingQueue<Shingle> bQueue, int numOfMinH, int poolSize) {
		this.bQueue = bQueue;
		this.numOfMinH = numOfMinH;
		pool = Executors.newFixedThreadPool(poolSize);
		init();
	}
	//Initializes specified number of min-hashes 
	/**
	 * this method initializes the min-hash array randomly 
	 */
	public void init() {
		Random random = new Random();
		minhashes = new int[numOfMinH];
		for(int i=0; i < minhashes.length; i++) {
			minhashes[i] = random.nextInt();
		}
	}
	/**
	 * run method implements Runnable gets the minimum minhash value 
	 * 
	 */
	public void run() {
		int docCount = 2;
		while(docCount > 0) {
			try {
				Shingle s = bQueue.take();
				if(s instanceof Poison) {
					docCount--;
				} else {
					pool.execute(new Runnable() {
						@Override
						public void run() {

							List<Integer>list = map.get(s.getDocId());
							for(int i=0;i<minhashes.length;i++) {
								int value = s.getHashCode() ^ minhashes[i];
								list = map.get(s.getDocId());
								if(list == null) {
									list = new ArrayList<Integer>(Collections.nCopies(numOfMinH, Integer.MAX_VALUE));
									map.put(s.getDocId(),list);

								}
								else {		
									if(list.get(i)>value) {
										list.set(i, value);
									}
								}
							} 
							map.put(s.getDocId(), list);			
						}
					});
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		
		pool.shutdown();
		try {
			pool.awaitTermination(Long.MAX_VALUE, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		List<Integer> intersection = map.get(1);
		intersection.retainAll(map.get(2));
		//the calculation which calculates the percentage 
		float jack = (float)intersection.size()/(numOfMinH*2-(float)intersection.size());
		//displays the result
		System.out.println("\nDoc 1 and Doc 2 Matches: " + (jack) * 100 + " %");	

	}

}