import javax.swing.*;
public class Interface {
// two dialogue boxes - to take user input until they decide to quit - Using JOption Pane to reduce input issues
	public static String[] dialogueBox1 = {"Find the Shortest Path between Two Bus Stops", "Search for Bus Stop", "Search for Trips with Given Arrival Times"};
	public static String[] dialogueBox2 = {"I have another query", "Quit System"};
	public static void RunSystem() throws Exception{
		boolean exit = false;
		JPanel screen;
		while(!exit) {
			screen = new JPanel();
			screen.add(new JLabel("Welcome to Vancouver! Please choose one of the following 3 options."));
			int option = JOptionPane.showOptionDialog(null, screen, "Bus Management System", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, dialogueBox1, null);
			if(option == 0) {ShortestPath.takeUserInput();}
			if(option == 1) {SearchBusStop.takeUserInput();}
			if(option == 2) {SearchTripArrival.takeUserInput();}
			screen = new JPanel();
			screen.add(new JLabel("Do you have another query that we can answer?"));
			int quitReturn = JOptionPane.showOptionDialog(null, screen, "Bus Management System", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, dialogueBox2, null);
			if(quitReturn == 1) {exit = true;}
		}
	}
	
	public static void main(String[] args) throws Exception{
		RunSystem();
	}

}
