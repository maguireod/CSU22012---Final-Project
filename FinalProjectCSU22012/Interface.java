import javax.swing.*;
public class Interface {
// two dialogue boxes - to take user input until they decide to quit - Using JOption Pane to reduce input issues
	public static String[] dialogueBox1 = {"Find the Shortest Path between Two Bus Stops", "Search for Bus Stop", "Search for Trips with Given Arrival Times"};
	public static String[] dialogueBox2 = {"I have another query", "I have no other queries"};
	public static void takeUserInput() {
		JPanel firstScreen = new JPanel();
		firstScreen.add(new JLabel("Welcome to Vancouver! Please choose one of the following 3 options."));
		int option = JOptionPane.showOptionDialog(null, firstScreen, "Bus Management System", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, dialogueBox1, null);
		if(option == 0) {/*take to shortestPath Class*/};
		if(option == 1) {/*take to SearchBusStopClass*/};
		if(option == 2) {/*take to SearchTripArrival*/};
		JPanel secondScreen = new JPanel();
		secondScreen.add(new JLabel("Do you have another query that we can answer?"));
		int quitReturn = JOptionPane.showOptionDialog(null, secondScreen, "Bus Management System", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, dialogueBox2, null);
		if(quitReturn == 0) {/*return to firstScreen - need some sort of loop????*/};
		if(quitReturn == 1) {return;}
	}
	public static void main(String[] args) {
		takeUserInput();
	}

}
