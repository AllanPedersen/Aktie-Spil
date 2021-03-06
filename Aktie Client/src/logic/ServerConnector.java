package logic;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import gui.ClientWindow;
import gui.DoInviteWindow;
import gui.InviteWindow;

public class ServerConnector implements Runnable
{
	private SettingsDataHandler sdh;
    private ClientWindow cw; 
	private String name;
	private String ip;
	private String port;
	private String email;
	
	private DoInviteWindow doInvitWindow;
	
	
    private Socket socket;
    
    //used to communicate with the server
    private String textFromServer;
    private Scanner readingFromServer;
    private PrintWriter sendToServer; //I use PrintWriter to access the 'print' methods.
	
    
    
    
	public ServerConnector(ClientWindow cw, SettingsDataHandler sdh)
    {
	  this.cw = cw;
	  this.sdh = sdh;
	  getUserSettings(); //collects userSettings
	  connect(); //connects to Server
    }

	@Override
	public void run() 
	{
		while(true)
		{
			if(this.readingFromServer.hasNextLine())
			{
				this.textFromServer = this.readingFromServer.nextLine();
				
				 //updates active users
				 if(this.textFromServer.startsWith("nu"))
				    {
					 	
					 final String[] divideUpdateString = this.textFromServer.split(","); //divides the incoming text into a String array
				    	final String[] userNames = new String[divideUpdateString.length-1];
				    	System.arraycopy(divideUpdateString, 1, userNames, 0, userNames.length); //copies everything from dividedUpdateString starting from position 1 to userNames
				    	
				    	SwingUtilities.invokeLater(new Runnable() {
							public void run() 
							{
								
								// Players here created for test purposes
								ArrayList<Player> players = new ArrayList<Player>();
								 for(int i = 0; i < userNames.length; i++)
								  {
									 players.add(new Player(userNames[i]));
								  }
								cw.lobby.setPlayerList(players);
								
							}
				    	});
				    	
				    }	 
				 
				 //invited to a game
				 if(this.textFromServer.startsWith("iu"))
				    {
					 final String[] divideString = this.textFromServer.split(","); 
					 //String message = "iu," + ip + "," + invitingPlayer + "," + time + "," + currency;	
				    
					 SwingUtilities.invokeLater(new Runnable() {
							public void run() 
							{
								new InviteWindow(divideString[1], divideString[2], divideString[3], divideString[4], ServerConnector.this);
							}
				    	});
				    }
				 
				 //a user has accepted invitation
				 if(this.textFromServer.startsWith("ai"))
				    {
					 final String[] divideString = this.textFromServer.split(",");
					 System.out.println(divideString[1] + " accepted invitation");
					 SwingUtilities.invokeLater(new Runnable() {	
						 public void run() 
							{
								doInvitWindow.dispose();

							   	//tells server I'm closing
								removeMeFromServer();
								
								new CreateGameHost(sdh, doInvitWindow.selectedCurrency, doInvitWindow.selectedPlayer, doInvitWindow.selectedTime);
								//closing connection to server
								try {
									socket.getOutputStream().close();
									socket.getInputStream().close();
									socket.getOutputStream().equals(null);
									socket.getInputStream().equals(null);
								    socket.close();
									socket = null;
								} catch (IOException e) {
				
								}
								
							}
				    	});
					 
				    }
				 
				 //a user has denied invitation
				 if(this.textFromServer.startsWith("di"))
				    {
					 final String[] divideString = this.textFromServer.split(","); 
					 SwingUtilities.invokeLater(new Runnable() {
							public void run() 
							{
								doInvitWindow.dispose();
								System.out.println(divideString[1] + " denied!");
								//game needs to end here
							}
				    	});
					 
				    }
			}	    
		}
		
	}
	
	
	
	
	
	public boolean connect()
	{
		
		try {
			this.socket = new Socket(this.ip, Integer.parseInt(this.port));
			this.sendToServer = new PrintWriter(this.socket.getOutputStream()); //link printWriter to the sockets outputStream
			this.readingFromServer = new Scanner(this.socket.getInputStream()); //link scanner to the sockets inputStream 
		
			//Server expects to get the underlying information when attempting to establish connection
			sendToServer.println("fm," + this.name + "," + this.ip + "," + this.port + "," + this.email); 
			sendToServer.flush();
			
			
			new Thread(this).start(); //starts the new Thread
			return true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "error");
			e.printStackTrace();
			return false;	
		}	
		
	}
	
	
	
	
	
	
	/**
	 * gets userSettings 
	 */
	private void getUserSettings()
	{
		ArrayList<String> userSettings = new ArrayList<String>();
		userSettings = sdh.getSettings();
		this.ip = userSettings.get(0);
	    this.port = userSettings.get(1);
		this.name = userSettings.get(2);
		this.email = userSettings.get(3);
		
	}

	/**
	 * used to tell the server that the application has been closed
	 */
	public void removeMeFromServer() {
		
	    sendToServer.println("x");
	      sendToServer.flush();
		
	}
	/**
	 * used to invite a player
	 * uses the ip as a header
	 * @param doInviteWindow 
	 * @param user
	 */
	public void inviteUser(String playerToInvite, String currency, String time, DoInviteWindow doInviteWindow)
	{
		this.doInvitWindow = doInviteWindow; // need to keep this reference to accept or deny invitation
		String message;
		String ipAdresse = "";
		try {
			 ipAdresse = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		message = "ip," + playerToInvite + "," + time + "," + currency + "," + ipAdresse;
		sendToServer.println(message);
	    sendToServer.flush();
	}

	/**
	 * accepts an invitation through the server
	 * @param player
	 */
	public void acceptInvitation(String player) {
		
	    String message = "ai," + player;
		sendToServer.println(message);
	    sendToServer.flush();
		
	}

	/**
	 * denies an invitation through the server
	 * @param player
	 */
	public void denyInvitation(String player) {
		String message = "di," + player;
		sendToServer.println(message);
	    sendToServer.flush();
		
	}

	public void closeConnection() {
		//closing connection to server
		try {
			socket.getOutputStream().close();
			socket.getInputStream().close();
			socket.getOutputStream().equals(null);
			socket.getInputStream().equals(null);
		    socket.close();
			socket = null;
		} catch (IOException e) {
		
	}

	}
}