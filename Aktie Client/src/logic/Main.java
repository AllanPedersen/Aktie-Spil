package logic;
import javax.swing.SwingUtilities;
import gui.ClientWindow;
import gui.SettingsWindow;

public class Main {
	
	private static String lcOSName = System.getProperty("os.name").toLowerCase();
	public static boolean IS_MAC = lcOSName.startsWith("mac os x");
	static ClientWindow clientWindow;

	public static void main(String[] args)
	{
	

		// Check if settings is set
		if (SettingsDataHandler.settingsExists()) {
			// Create connection to server, if succes open lobby, otherwise open settings
			  SwingUtilities.invokeLater(new Runnable()
			    {
			    	public void run()
			    	{
			    		try
			    		{
			    			
			    			clientWindow = new ClientWindow();
			    			// TODO Remove and instanciate from server
			    		}
			    		catch(Exception e)
			    		{
			    			e.printStackTrace();
			    		}
			    	}
			    });
		} else {
			new SettingsWindow();
		}
		
	}
	

}
