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
			 
		   SwingUtilities.invokeLater(new Runnable()
		   {
			
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
					  playerList.get(i).toClient_PrintWriter.flush();
				  }
			  }
		  }



	 /**
	  * invites a user to join a game
	  * @param playerToInvite
	  * @param ip
	  * @param invitingPlayer
	  */
	public void invitePlayer(String playerToInvite, String ip, String invitingPlayer) {
		 synchronized(lock) //Threads wanting to work with the method needs to take their turn until this lock is released
		  {
			 
			 for(int i = 0; i < playerList.size(); i++)
			  {
				 if(playerToInvite.equals(playerList.get(i)))
				 {
				  String message = "iu," + ip + "," + invitingPlayer;	 
				  playerList.get(i).toClient_PrintWriter.println(message);
				  playerList.get(i).toClient_PrintWriter.flush();
				  break; //atm. placed in order to avoid inviting players with same name
				 }
			}
			 
		  }
		  
		}


	/**
	 * removes client from list
	 * @param client
	 */
	public void removeMe(Client client) {
		synchronized(lock) //Threads wanting to work with the method needs to take their turn until this lock is released
		  {
			 for(int i = 0; i < playerList.size(); i++)
			  {
				  if(playerList.contains(client))
				  {
					  playerList.remove(client);
				  }
			  } 
			
		  }
	}
	
	public void acceptInvitation(String ip, String acceptingPlayer, String invitingPlayer)
	{
		synchronized(lock) //Threads wanting to work with the method needs to take their turn until this lock is released
		  {
			for(int i = 0; i < playerList.size(); i++)
			  {
			    if(playerList.get(i).name.equals(invitingPlayer))
			    {
			    	String message = "ai," + ip + "," + acceptingPlayer;
			    	playerList.get(i).toClient_PrintWriter.println(message);
			    	playerList.get(i).toClient_PrintWriter.flush();
			    }
			  }
		  }
	}
	
	  }
 