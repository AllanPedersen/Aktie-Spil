package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.util.ArrayList;

public class ClientWindow extends JFrame {
	
	/**
	 * The main window for the client application.
	 * The window will contain a cardlayout, that contains the different screens.
	 */
	private static final long serialVersionUID = 1L;
	private static JPanel contentPane, cards;
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
		
		Lobby lobby = new Lobby();
		
		// Test highscore array. Will be replaced with server content.
		// TODO: Replace with server content
		ArrayList<String> highscores = new ArrayList<String>();
		highscores.add("Chris");
		highscores.add("Allan");
		highscores.add("Peter");
		highscores.add("Mads");
		highscores.add("Maria");
		
		lobby.setHighScores(highscores);
		lobby.setServerStatus(1);
		lobby.setUsername("Chris the master of all");

		cl = new CardLayout();
		cards = new JPanel(cl);
		cards.add(lobby, "Lobby");
		
		contentPane.add(cards);
		
		// Show the default layout
		cl.show(cards, "Lobby");
		
		// Lock window size and set visible
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public static void changeLayout(String layout) {
		// TODO: Check if specified layout exists
		// TODO: Change layout or throw exception
	}

}
