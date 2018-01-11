* Github Download Link ====== https://github.com/nakster/JavaAPIMeasuringSimilarity.git
* GitHub Link =============== https://github.com/nakster/JavaAPIMeasuringSimilarity

The Project is a Java Api for Measuring Document similarity 

There is A jar file included in this project To run the Jar Do the following 
	* Go into CMD and go to the folder where the jar while is and run the following command
		* java –cp ./oop.jar ie.gmit.sw.Runner 
		* or ....
		* Java -jar ./oop.jar

There are seven files in the program 

Runner.java
	* Asks the user for the info needed to check the similarity between two files.
	* Features - The Menu class asks the user shingle Size, k and pool thread size.
	
Consumer.java
	* Minhashes the Shingles.
	* Implements Runnable 
	
FileParser.java
	* Parses the files 
	* makes Shingles 
	* Implements Runnable 
	
Launch.java
	* starts the threads 
	
Menu.java
	
Shingle.java
	* object class for shingles 
Poison.java
	* Poison places known data item on the queue and when the consumer reads this item it closes down. 
	* Extends Shingle.java

JavaDocs are also included.
The UML is also included which explains the relationships between the api

