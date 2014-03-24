package gui;

import javax.swing.JFrame;

public class ClientWindow extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClientWindow() {
		super();
		this.setTitle("Aktiespil");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 600);
		this.setResizable(false);
		this.setVisible(true);
	}

}
