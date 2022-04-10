import java.util.Scanner;

import javax.swing.*;

import java.io.*;
public class ShortestPath {
	
	public int Stops;
	public double[][] tripCosts;
	public double[] distanceToStop;
	public int[] previousStop;
	
	public void intializeStorageVariables() {
		int maximum = 0;
		try {
		    Scanner f = new Scanner(new File("stops.txt"));
		    f.nextLine();
		    String [] values;
		    while(f.hasNextLine()) {
		    	values = f.nextLine().split(",");
		    	if(Integer.parseInt(values[0]) > maximum) 
		    	   maximum = Integer.parseInt(values[0]);
		    }
		    f.close();
		    Stops = maximum + 1;
		    tripCosts = new double[Stops][Stops];
		    for(int i = 0; i < tripCosts.length; i++) {
		    	for(int j = 0; j < tripCosts.length; j++) {
		    		if(i == j) 
		    			tripCosts[i][j] = 0;
		    		else
		    			tripCosts[i][j] = Integer.MAX_VALUE;
		    	}
		    }
		    populateTripCosts();
		} catch(Exception e) {}
	}
	
	public void populateTripCosts() throws Exception {
		Scanner readRows = new Scanner(new File("transfers.txt"));
		readRows.nextLine();
		String values[];
		while(readRows.hasNextLine()) {
			values = readRows.nextLine().split(",");
			if(Integer.parseInt(values[2]) == 2) 
				tripCosts[Integer.parseInt(values[0])][Integer.parseInt(values[1])] = Integer.parseInt(values[3]) / 100;
			else if(Integer.parseInt(values[2]) == 0)
				tripCosts[Integer.parseInt(values[0])][Integer.parseInt(values[1])] = 2;
		}
		readRows.close();
		readRows = new Scanner(new File("stop_times.txt"));
		readRows.nextLine();
		int lastRouteID; int currentRouteID = 0;
		int currentStop; int nextStop = 0;
		while((readRows.hasNextLine())) {
			values = readRows.nextLine().split(",");
			lastRouteID = currentRouteID;
			currentRouteID = Integer.parseInt(values[0]);
			currentStop = nextStop;
			nextStop = Integer.parseInt(values[3]);
			if(lastRouteID == currentRouteID)
				tripCosts[currentStop][nextStop] = 1; 
		}
		readRows.close();
	}
	public String shortestPath(int source, int sink) {
		distanceToStop = new double[Stops];
		previousStop = new int[Stops];
		boolean wasHere[] = new boolean[Stops];
		if(source == sink) {return "Stay at Stop " + source + ". Cost = 0";}
		for(int index = 0; index < Stops; index ++)
			distanceToStop[index] = Double.POSITIVE_INFINITY;
		distanceToStop[source] = 0;
		int location = source;
		wasHere[source] = true;
		for(int i = 0; i < distanceToStop.length; i++) {
			for(int j = 0; j < tripCosts[location].length; j++) {
				if(!wasHere[j] && distanceToStop[j] > distanceToStop[location] + tripCosts[location][j]) { 
					distanceToStop[j] = distanceToStop[location] + tripCosts[location][j];
					previousStop[j] = location;
				}
			}
			wasHere[location] = true;
			double shortestPath = Double.MAX_VALUE;
			for(int k = 0; k < distanceToStop.length; k++) {
				if(!wasHere[k] && shortestPath > distanceToStop[k]) {
					location = k;
					shortestPath = distanceToStop[k];
		        }			
			}
		}
		return ShortestPathPrint(source, sink);
	}
	
	public String ShortestPathPrint(int start, int finish) {
		if(distanceToStop[finish] >= Integer.MAX_VALUE)
			return "There is no such path.";
		int source = start;
		int sink = finish;
		String path = "";
		while(source != sink) {
			path = "\n" + previousStop[sink] + path;
			sink = previousStop[sink];
		}
	    path = path + "\n" + finish;
		return "Cost = " + distanceToStop[finish] + ". Path listed below: " + path;
	}
	public int busStopFetch(String entry) {
		JPanel screen = new JPanel();
		String inputStatement = "Enter the " + entry + " Bus Stop:";
		screen.add(new JLabel(inputStatement));
		Object[] instructions = {"Continue"};
		JTextField inputField = new JTextField(5);
		screen.add(inputField);
		JOptionPane.showOptionDialog(null, screen, "Bus Management System", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, instructions, null);
		try {
			int stop;
			if((stop = Integer.parseInt(inputField.getText())) < Stops && stop > 1)
				return stop;
			else {
				JOptionPane.showMessageDialog(null, "Stop doesn't exist: Try again.");
				return busStopFetch(entry);
			}
				
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Invalid input: Check the list of bus stops and try again.");
			return busStopFetch(entry);
		}
	}
	public static void takeUserInput() throws Exception {
		ShortestPath busStops = new ShortestPath();
		busStops.intializeStorageVariables();
		String output = busStops.shortestPath( busStops.busStopFetch("first"), busStops.busStopFetch("second")) ;
		JOptionPane.showMessageDialog(null, output);
	}
}
