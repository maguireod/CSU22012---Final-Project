import java.io.*;
import java.util.*;
import javax.swing.*;
public class SearchTripArrival <Key extends Comparable <Key>, Value> {
	
	private static class TripData{
		public String trip_id;
		public String departure_time;
		public String stop_id;
		public String stop_sequence;
		public String stop_headsign;
		public String pickup_type;
	    public String drop_off_type;
	    public String shape_dist_traveled;
	    
	    public TripData(String trip_id, String departure_time, String stop_id, String stop_sequence, String stop_headsign, String pickup_type, String drop_off_type, String shape_dist_traveled) {
	    	this.trip_id = trip_id;
			this.departure_time = departure_time;
			this.stop_id = stop_id;
			this.stop_sequence = stop_sequence;
			this.stop_headsign = stop_headsign; 
			this.pickup_type = pickup_type;
		    this.drop_off_type = drop_off_type;
		    this.shape_dist_traveled = shape_dist_traveled;
	    }
	}
	public HashMap<String, ArrayList<TripData>> timeMap = new HashMap<String, ArrayList<TripData>>();
	public static void createMap(SearchTripArrival<String, String> s, File file) {
		try {
			Scanner readFile = new Scanner(file);
			readFile.nextLine();
			String[]data;
			while(readFile.hasNextLine()) {
				data = readFile.nextLine().trim().split(",");
				String travelled = ((data.length > 8)? data[8]: "None");
				if(!s.timeMap.containsKey(data[1].trim())) 
					s.timeMap.put(data[1].trim(), new ArrayList<>());
				TripData tripData = new TripData(data[0], data[2], data[3], data[4], data[5], data[6], data[7], travelled);
				s.timeMap.get(data[1].trim()).add(tripData);
			}
			readFile.close();
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error: System requires maintance");
		}
	}
	public String output(ArrayList<TripData> tripsSorted) {
		String output = "";
		for(int i = 0; i < tripsSorted.size(); i++) {
			TripData sortedTrip = tripsSorted.get(i);
			output +="\nTrip ID: " + tripsSorted.get(i).trip_id +
					" || Departure Time: " + sortedTrip.departure_time + 
					" || Stop ID: " + sortedTrip.stop_id + 
					" || Stop Sequence: " + sortedTrip.stop_sequence + 
					" || Stop Headsign: " + sortedTrip.stop_headsign + 
					" || Pick Up Type: " + sortedTrip.pickup_type + 
					" || Drop Off: " + sortedTrip.drop_off_type + 
					" || Distance Travelled: " + sortedTrip.shape_dist_traveled;
		}
		return output;
	}
	public static void takeUserInput() {
		JPanel screen = new JPanel();
		screen.add(new JLabel("Enter the bus arrival time in the form 'HH:MM:SS' : "));
		Object[] instruction = {"Enter"};
		JTextField input = new JTextField(20);
		screen.add(input);
		int clicked = JOptionPane.showOptionDialog(null, screen, "Bus Management System", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, instruction, null);
		if (clicked == 0) {
			String originalInput = input.getText();
			String time = originalInput.replaceAll(":", "");
			int intTime = 0;
			try {
				intTime = Integer.parseInt(time);
			} catch(Exception e) {
				JOptionPane.showMessageDialog(null, "Cannot input letters, Try Again");
				takeUserInput();
			}
			if(intTime < 0 || intTime > 235959) {
				JOptionPane.showMessageDialog(null, "Times must be between 00:00:00 - 23:59:59. Try again");
				return;
			}
			try {
				SearchTripArrival<String, String> map = new SearchTripArrival<String, String>();
				createMap(map, new File("stop_times.txt"));
				ArrayList<TripData> tripsSorted = map.timeMap.get(originalInput);
				Collections.sort(tripsSorted, (tripA, tripB) -> tripA.trip_id.compareTo(tripB.trip_id));
				String output = map.output(tripsSorted);
				if(output != "") 
					JOptionPane.showMessageDialog(null, "Arrival Time: " + originalInput + output);
				else 
					JOptionPane.showMessageDialog(null, "No buses arrive at that time.");
			} catch(Exception e) {
				JOptionPane.showMessageDialog(null, "Invalid input, Try Again");
				takeUserInput();
			}
		}
	}

}
