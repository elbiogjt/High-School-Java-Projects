import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;

public class Runner {
	public static final String saveFile = "save.serial";
	public static void main(String args[]) {
		Screen sc = new Screen();
		JFrame frame = new JFrame("World");
		
		frame.add(sc);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			public void run() {
				try {
					FileOutputStream fos = new FileOutputStream(saveFile);
					ObjectOutputStream out = new ObjectOutputStream(fos);
					out.writeObject(sc.save());
					fos.close();
					out.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}));
	}
}
