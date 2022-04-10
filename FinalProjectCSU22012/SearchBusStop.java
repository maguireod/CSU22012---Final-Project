import java.io.*;
import java.util.Scanner;
import javax.swing.*;

public class SearchBusStop {
	
	// Implements TST Alogorithm
	public static void takeUserInput() {
		JPanel screen = new JPanel();
		screen.add(new JLabel("Choose a Bus Stop Search Option:"));
		Object[] instructions = {"Search by Full Name", "Search by First Few Characters "};
		int option = JOptionPane.showOptionDialog(null, screen, "Bus Management System", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, instructions, null);
	    screen = new JPanel();
	    screen.add(new JLabel("Enter Bus Stop Name: "));
	    Object[] submit = {"Enter"};
	    JTextField input = new JTextField(20);
	    screen.add(input);
	    int clicked = JOptionPane.showOptionDialog(null, screen, "Bus Management System", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, submit, null);
	    if(clicked == 0) {
	    	TST<String> tst = new TST<String>();
	    	CreateTST("stops.txt", tst);
	    	findUsingTST(option, (input.getText().toUpperCase()), tst);
	    }
	}	
	public static void CreateTST(String filename, TST<String> st) {
    	try {
    		Scanner file = new Scanner(new File(filename));
    		String[] values;
    		while(file.hasNextLine()) {
    			values = file.nextLine().trim().split(",");
    			String lineInfo = " | Stop Id: " + values[0] + " | Stop Code: " + values[1] + " | Stop Desc: " + values[3] + 
    					" | Stop Lat: " + values[4] + " | Stop Lon: " + values[5] + " | Zone Id: " + values[6];
    		    st.put(move(values[2]), lineInfo);
    		}
    	} catch (Exception e) {
    		JOptionPane.showMessageDialog(null, "Error: System requires maintenance.");
    	}
    }
    public static String move(String stop) {
    	String newStop = stop;
    	char[]arrayExtended;
    	int extend;
    	if(stop.contains("FLAGSTOP")) { 
    		extend = stop.length() + 9;
    		arrayExtended = new char[extend];
    		for(int i = 0; i < stop.length(); i ++) 
    			arrayExtended[i] = stop.charAt(i);
    		arrayExtended[extend-9] =  ' ';
    		for(int i = 8; i > 0; i--)
    			arrayExtended[extend - i] = arrayExtended[8-i];
    		newStop = String.valueOf(arrayExtended).substring(9);
    	}
    	if(Character.isWhitespace(newStop.charAt(2))) {
    		extend = newStop.length() + 3;
    		arrayExtended = new char[extend];
    		for(int i = 0; i < newStop.length(); i++) 
    			arrayExtended[i] = newStop.charAt(i);
    		arrayExtended[extend - 3] = ' ';
    		arrayExtended[extend - 2] = arrayExtended[0];
    		arrayExtended[extend - 1] = arrayExtended[1];
    		newStop = String.valueOf(arrayExtended).substring(3);
    	}
    	return newStop;
    }
    public static void findUsingTST(int type, String input, TST<String> tst) {
    	String stops = "";
    	if(type == 1) {
    		for(String newStop: tst.keysWithPrefix(input)) 
    			stops = stops + newStop + tst.get(newStop) + "\n";
    		if(stops == "")
    			stops = "No Bus Stops Found";
    	}
    	else {
    		if(tst.get(input) != null)
    			stops = input + tst.get(input);
    		else
    			stops = "No Bus Stop Found.";
    	}
    	JOptionPane.showMessageDialog(null, stops);
    }

}
