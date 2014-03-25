package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;

public class ClientWindow extends JFrame {
	
	/**
	 * The main window for the client application.
	 * The window will contain a cardlayout, that contains the different screens.
	 */
	private static final long serialVersionUID = 1L;
	private static JPanel lobby, contentPane, cards;
	private static int currentCard = 1;
	private static CardLayout cl;
	public static Color green = new Color(183, 210, 120);
	public static Color hoverGreen = new Color(212, 250, 125);

	public ClientWindow() {
		super();
		this.setTitle("Aktiespil");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 600);		
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		cl = new CardLayout();
		cards = new JPanel(cl);
		cards.add(new Lobby(), "Lobby");
		
		contentPane.add(cards);
		
		// Show the default layout
		cl.show(cards, "Lobby");
		
		
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public static void changeLayout(String layout) {
		// Check if specified layout exists
		// Change layout or throw exception
	}

}
