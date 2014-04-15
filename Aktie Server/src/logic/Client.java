package logic;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable {
	
	private boolean connected = true;
	private Socket clientSocket;
	private UserDataHandler udh;
	private String fromClient_String;
	private Scanner fromClient_Scanner;
	public PrintWriter toClient_PrintWriter;
	
	public String name = "";
	public String email = "";
	public String port = "";
	public String ip = "";

	public Client(Socket clientSocket, UserDataHandler udh)
	{
	   this.clientSocket = clientSocket;
	   this.udh = udh;
	   
	   try {
			this.toClient_PrintWriter = new PrintWriter(this.clientSocket.getOutputStream()); //link printWriter to the sockets outputStream
			this.fromClient_Scanner = new Scanner(this.clientSocket.getInputStream()); //link scanner to the sockets inputStream      
		} catch (IOException e) {
			e.printStackTrace();
		}	
	   
	   /**
	    * expects client to send its personal data to the server upon connecting
	    */
	   if(fromClient_Scanner.hasNextLine())
		{
		   this.fromClient_String = fromClient_Scanner.nextLine();
		   String[] divideChatString = this.fromClient_String.split(",", 5); //splits data into 5 seperate values
		   //Disregards the [0] position which contains the header 'fm'
		   this.name = divideChatString[1];
		   this.ip = divideChatString[2];
		   this.port = divideChatString[3];
		   this.email = divideChatString[4];
		}
       udh.joinPlayers(this); //sends itself to join the list of active players
	   udh.countUsers(); //used to update on server how many active players there are.       
	  
	   udh.updateUserList(); //sends a new list of active users to all connected players

	}

	@Override
	public void run() 
	{

		while(connected) 
        {
			if(fromClient_Scanner.hasNextLine())
			{
				this.fromClient_String = fromClient_Scanner.nextLine();
	        	
	            //request to invite player
				if(this.fromClient_String.startsWith("ip"))
				{
					String[] divideChatString = this.fromClient_String.split(",", 2);
					  //Disregards the [0] position which contains the header 'ip'
					   udh.invitePlayer(divideChatString[1], ip, name);
				}	
				
				//user wants to accept invitation
				if(this.fromClient_String.startsWith("ai"))
				{
					String[] divideChatString = this.fromClient_String.split(",", 2);
					 //Disregards the [0] position which contains the header 'ai'
					udh.acceptInvitation(ip, name, divideChatString[1]); //accepts invitation
						
					
				}
				
				//user closed his application
				if(this.fromClient_String.startsWith("x"))
				{
					udh.removeMe(this); //removes client from arrayList
					udh.updateUserList(); //updates the view for all other clients
					this.connected = false; //stops the while loop
					udh.countUsers();
				}	
				
			}
        }
		
		
		
		
		
}}
