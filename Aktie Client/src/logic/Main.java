package logic;
import gui.ClientWindow;
import gui.SettingsWindow;


public class Main {
	
	private static String lcOSName = System.getProperty("os.name").toLowerCase();
	public static boolean IS_MAC = lcOSName.startsWith("mac os x");

	public static void main(String[] args) {
		System.out.println("Hello, this is the client");
		
		// Check if settings isset
		if (SettingsDataHandler.settingsExists()) {
			// Create connection to server, if succes open lobby, otherwise open settings
			Main.openLobby();
		} else {
			new SettingsWindow();
		}
		
	}
	
	/**
	 * This method opens the lobby window for the user. It is called when a connection to the game server is established
	 */
	public static void openLobby() {
		new ClientWindow();
	}

}
