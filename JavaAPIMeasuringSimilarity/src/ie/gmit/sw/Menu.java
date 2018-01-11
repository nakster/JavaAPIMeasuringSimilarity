package ie.gmit.sw;

import java.util.Scanner;

/**
 * This is the Menu class that asks the user the Info needed to run the program
 *
 * @author  Naqi Ahmad
 * @version 1.0
 * @since   2018-01-11
 */
public class Menu {
	
	Launch l = new Launch();
	
	private Scanner console = new Scanner(System.in);
	
	/**
	 * Menu asks the user for the values and sends the values to the Launcher class
	 * @throws InterruptedException thrown when a thread is waiting or sleeping
	 */
	public Menu() throws InterruptedException {
		
			//ask the user following questions 
			System.out.println("Please Enter The Name Of File One: ");
			String f1 = console.next();
			
			System.out.println("Please Enter The Name Of File Two: ");
			String f2 = console.next();
			
			System.out.println("Please Enter Shingle size: ");
			int shingleSize = console.nextInt();
			
			System.out.println("Please Enter the Size for Number of Min Hashes: ");
			int NumOfMin = console.nextInt();
			
			System.out.println("Enter pool size: ");
			int pool = console.nextInt();
			
			//send the info to the launch class which then runs the threads 			
			l.StartThreads(f1, f2, shingleSize, NumOfMin, pool);	
		
	}
	
}