import gui.SettingsWindow;


public class Main {
	
	private static String lcOSName = System.getProperty("os.name").toLowerCase();
	private static boolean IS_MAC = lcOSName.startsWith("mac os x");

	public static void main(String[] args) {
		// Mac specific tasks
		if (IS_MAC) {
			// Put menubar at the top of the screen
			System.setProperty("apple.laf.useScreenMenuBar", "true");
			
			// Set title of app
			System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Aktiespil");
		}
		
		System.out.println("Hello, this is the client");
		new SettingsWindow();
	}

}
