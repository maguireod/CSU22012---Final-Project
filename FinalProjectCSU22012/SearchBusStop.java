import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SearchBusStop {
	
	// Implements TST Alogorithm
	public static void takeUserInput() {
		JPanel screen = new JPanel();
		screen.add(new JLabel("Choose a Bus Stop Search Option:"));
		Object[] instructions = {"Search by Full Name", "Search by First Few Characters "};
		int option = JOptionPane.showOptionDialog(null, screen, "Bus Management System", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, instructions, null);
	}
	public static void main(String[] args) {
	

	}

}
