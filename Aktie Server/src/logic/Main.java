package logic;
import gui.*;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;



/**
 * Main class is what runs when someone launches the program.
 * @author Allan
 *
 */
public class Main 
{
	static ServerWindow serverWindow;
	
	public static void main(String[] args) 	
	{
		/**
		 * Sets another chosen design instead of the normal swing design
		 */
		try {
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		}
		catch(Exception e)	{
		}
	
		/**
		 * This is crucial when dealing with Swing and multiple threads
		 * Swing make use of EDT(Event Dispatching Thread) a single thread that handles all changes.
		 * This will create problems in certain scenarios.
		 * e.g. the server is connecting while a user is resizing the window
		 * you won't be able to make changes to the window untill the server is done connecting.
		 * This way we add the creation of the GUI object to its own thread which is added to the EDT queue
		 * And can be run without being blocked.
		 */
	    SwingUtilities.invokeLater(new Runnable()
	    {
	    	public void run()
	    	{
	    		try
	    		{
	    			serverWindow = new ServerWindow();
	    			serverWindow.frame.setVisible(true);
	    		}
	    		catch(Exception e)
	    		{
	    			e.printStackTrace();
	    		}
	    	}
	    });
	}
}