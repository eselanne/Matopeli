import javax.swing.JFrame;

public class Main {

	
	public Main() {
		
		JFrame frame = new JFrame();
		Game game = new Game();
		
		frame.add(game);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Matopeli");
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Main();
	}

}
