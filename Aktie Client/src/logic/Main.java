package logic;
import javax.swing.SwingUtilities;

import gui.ClientWindow;
import gui.InviteWindow;
import gui.SettingsWindow;

public class Main {
	
	private static String lcOSName = System.getProperty("os.name").toLowerCase();
	public static boolean IS_MAC = lcOSName.startsWith("mac os x");
	static ClientWindow clientWindow;

	public static void main(String[] args)
	{
	

		// Check if settings isset
		if (SettingsDataHandler.settingsExists()) {
			// Create connection to server, if succes open lobby, otherwise open settings
			  SwingUtilities.invokeLater(new Runnable()
			    {
			    	public void run()
			    	{
			    		try
			    		{
			    			clientWindow = new ClientWindow();
			    		}
			    		catch(Exception e)
			    		{
			    			e.printStackTrace();
			    		}
			    	}
			    });
			//Main.invite("Allan", "1 time", "100.000");
		} else {
			new SettingsWindow();
		}
		
	}
	
	public static void invite(String name, String time, String money) {
		new InviteWindow();
	}

}
