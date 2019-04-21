import javax.swing.JFrame;

public class Main {

	
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
	
	public static void main(String[] args) {
		new Main();
	}

}
