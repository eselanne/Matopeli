import javax.swing.JFrame;

public class Main {

	/**
	 * Luo ikkunan.
	 */
	public Main() {
		
		JFrame frame = new JFrame();
		Game game = new Game();
		
		frame.add(game);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setTitle("Matopeli");
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	/**
	 * Käynnistää ohjelman.
	 * @param args
	 */
	public static void main(String[] args) {
		new Main();
	}

}
