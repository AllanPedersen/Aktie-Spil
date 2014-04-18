package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.util.ArrayList;
import logic.ServerConnector;
import logic.SettingsDataHandler;

public class ClientWindow extends JFrame {
	
	/**
	 * The main window for the client application.
	 * The window will contain a cardlayout, that contains the different screens.
	 */
	private static final long serialVersionUID = 1L;
	private static JPanel contentPane, cards;
	private StockPanel gamePanel;
	private static CardLayout cl;
	
	public Lobby lobby;
	public static Color green = new Color(183, 210, 120);
	public static Color hoverGreen = new Color(212, 250, 125);
	public static Color red = new Color(254, 99, 99);
	public static Color hoverRed = new Color(241, 145, 145);

	private ServerConnector sc;
	
	public ClientWindow() {
		super();
		SettingsDataHandler sdh = new SettingsDataHandler();	
		this.sc = new ServerConnector(this, sdh);
		this.setTitle("Aktiespil");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		this.lobby = new Lobby(this.sc);
		
		 /**
	     * Used to react when user closes the application.
	     * Notifies the server to remove from all populated lists.
	     */
		  Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

		        @Override
				public void run() {
		          sc.removeMeFromServer();
		         
		        }
		    }));
		
		// Test highscore array. Will be replaced with server content.
		// TODO: Replace with server content
		ArrayList<String> highscores = new ArrayList<String>();
		highscores.add("Chris: 780.000 kr");
		highscores.add("Allan: 15.000 kr");
		highscores.add("Peter: 12 kr");
		highscores.add("Mads: 10 kr");
		highscores.add("Maria: 10 kr");
		
	
		ArrayList<String> settings = new ArrayList<String>();
		settings = sdh.getSettings();
		
		lobby.setHighScores(highscores);
		lobby.setServerStatus(1);
		lobby.setUsername(settings.get(2));
		
		gamePanel = new StockPanel();

		cl = new CardLayout();
		cards = new JPanel(cl);
		cards.add(lobby, "Lobby");
		cards.add(gamePanel, "Game");
		
		contentPane.add(cards);
		
		// Show the default layout
		cl.show(cards, "Game");
		
		// Lock window size and set visible
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public static void changeLayout(String layout) {
		cl.show(cards, layout);
	}
}
