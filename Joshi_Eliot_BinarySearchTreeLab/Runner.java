import javax.swing.JFrame;

public class Runner {
	public static void main(String args[]) {
		Screen sc = new Screen();
		JFrame frame = new JFrame("Country");
		
		frame.add(sc);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
