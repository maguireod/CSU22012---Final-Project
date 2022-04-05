import java.util.Scanner;
import java.io.*;
public class ShortestPath {
	public static int Stops;
	public static double[][] tripCosts;
	public static void highestStopNumber() {
		int maximum = 0;
		try {
			File file = new File("stops.txt");
		    Scanner f = new Scanner(file);
		    Scanner s;
		    int i = 0;
		    int count = 0;
		    while(f.hasNextLine()) {
		    	s = new Scanner(f.nextLine());
		    	s.useDelimiter(",");
		    	if(count != 0) {
		    	    if((i = s.nextInt()) > maximum) 
		    	    	maximum = i;
		    	}
		    	count++;
		    }
		    f.close();
		    Stops = maximum + 1;
		    tripCosts = new double[Stops][Stops];
		} catch(Exception e) {}
	}
}
