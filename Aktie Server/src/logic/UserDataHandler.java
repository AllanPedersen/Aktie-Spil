package logic;


import java.util.ArrayList;

import javax.swing.SwingUtilities;

public class UserDataHandler
{
	
	 public ArrayList<Client> playerList = new ArrayList<Client>();
	  
	 //Creates a lock that threads need to grab in order to enter some code
	 //this is used in order to prevent several threads attempting to use the same code at the same time
	 private final Object lock = new Object();
	
	 /**
	  * Counts active users and updates the gui.
	  * to keep the project threadsafe, we need to add the changing of the GUI
	  * to the 'EDT, Event Dispatcher Thread' (Gui Thread)
	  */
	 public void countUsers() 
	 {
		 synchronized(lock) //Threads wanting to work with the method needs to take their turn until this lock is released
		 {
			 
		   SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
		
				Main.serverWindow.updateActivePlayers(playerList.size()); //sends amount of active players
			}
		});
		  }
	}

	 
	 
	 /**
	  * sends a list of active names to all users
	  * @param room
	  */
	 public void updateUserList()
	  {
		 synchronized(lock) //Threads wanting to work with the method needs to take their turn until this lock is released
		  { 
			  String users = "";	 
			  for(int i = 0; i < playerList.size(); i++)
				  {
					  users += ","+ playerList.get(i).name;
				  }
		  
				  for(int i = 0; i < playerList.size(); i++)
				  {
					  String message = "nu,"+ users;  
					  playerList.get(i).toClient_PrintWriter.println(message);
				  }
			  }
		  }
	  }
 

