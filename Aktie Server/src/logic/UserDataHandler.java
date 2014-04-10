package logic;

import java.util.ArrayList;

public class UserDataHandler
{
	
	 public ArrayList<Client> playerList = new ArrayList<Client>();
	  
	 //Creates a lock that threads need to grab in order to enter some code
	 //this is used in order to prevent several threads attempting to use the same code at the same time
	 private final Object lock = new Object();
	
	 
	 public void countUsers() 
	{
		// TODO Auto-generated method stub
		 synchronized(lock) //Threads wanting to work with the method needs to take their turn untill this lock is released
		  {
		  
		  }
	}

	 
	 
	 /**
	  * sends a list of active names to all users
	  * @param room
	  */
	 public void updateUserList()
	  {
		 synchronized(lock) //Threads wanting to work with the method needs to take their turn untill this lock is released
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
 

