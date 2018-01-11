package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;

public class FileParser implements Runnable{
	private String fileName;
	private int sizeOfShingle;
	private int docId;
	@SuppressWarnings("unused")
	private int numOfMin;//number of Min hashes
	private BlockingQueue<Shingle> queue;
	private Deque<String> buffer = new LinkedList<>();
	
	//constructor 
	public FileParser(String file, int shingleSize,BlockingQueue<Shingle> queue,int docId) {
		super();
		this.fileName = file;
		this.sizeOfShingle = shingleSize;
		this.queue = queue;
		this.docId = docId;
	}
	//run method 
	public void run() {
		try {
			//bf to read in the file 
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
			//line declared as null
			String line = null;
			//reDS LINE BY LINE WHILE its not null
			while((line = br.readLine())!= null) {
				//this skips the line if the lenght of line is 0
				if(line.length()>0) {
					//converts it to uppercase 
					String uLine = line.toUpperCase();
					//gets each word
					String [] words = uLine.split("\\s+");
					//adds it to the buffer 
					addWordsToBuffer(words);
					//get nextshingle 
					Shingle s = getNextShingle();
					//put the shingle onto the queue 
					queue.put(s);
				}
			}
			
			//close the buffer when finshed 
			br.close();
			flushBuffer();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	//this adds words to the linked list
	private void addWordsToBuffer(String[] words) {
		for (String s: words) {
			buffer.add(s);
		}
	}
	//this gets the words and makes a shingle 
	private Shingle getNextShingle() {
		StringBuilder sb = new StringBuilder();
		int counter = 0;
		while(counter < sizeOfShingle) {
			if(buffer.peek()!=null) {
				sb.append(buffer.poll());
				counter++;
			}
			else {
				counter = sizeOfShingle;
			}
			
		}
		if(sb.length() > 0) {
			return (new Shingle(docId,sb.toString().hashCode()));
		}
		else {
			return null;
		}
		
	}
	
	private void flushBuffer() throws InterruptedException{

		while(buffer.size() > 0) {
			Shingle s = getNextShingle();
			if(s != null) {
				queue.put(s);
			}
		}
		//poison
		queue.put(new Poison(docId, 0));
	}
	

}