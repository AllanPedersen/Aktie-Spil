package logic;

import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import gui.ClientWindow;

public class ServerConnector implements Runnable
{
	private SettingsDataHandler sdh;
    private ClientWindow cw; 
	private String name;
	private String ip;
	private String port;
	private String email;
	
	
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
				
				 if(this.textFromServer.startsWith("nu"))
				    {
					 	
					 final String[] divideUpdateString = this.textFromServer.split(","); //divides the incoming text into a String array
				    	final String[] userNames = new String[divideUpdateString.length-1];
				    	System.arraycopy(divideUpdateString, 1, userNames, 0, userNames.length); //copies everything from dividedUpdateString starting from position 1 to userNames
				    	System.out.println(userNames[0]);
				    	System.out.println(divideUpdateString[0]);
				    	SwingUtilities.invokeLater(new Runnable() {
							public void run() 
							{
								
							}
				    	});
				    	
				    }	 
				 if(this.textFromServer.startsWith("iu"))
				    {
				    }
				 if(this.textFromServer.startsWith("ai"))
				    {
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
}