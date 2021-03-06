package logic;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


/**
This class opens a connection and waits for player to connect
 * @author Allan
 *
 */
public class CreateGameHost implements Runnable
{
	private ServerSocket ss;
	private Socket clientSocket;
	private boolean status;
	
	private int port;
	
	private String currency;
	private String time;
	private String player;

    
	/**
	 * Constructor used for starting server, needs  port
	 * @param port
	 */
	public CreateGameHost(SettingsDataHandler sdh, String currency, String player, String time)
	{
		this.currency = currency;
		this.time = time;
		this.player = player;
      ArrayList<String> getPortArrayList = new ArrayList<String>();
      getPortArrayList = sdh.getSettings();
		int temp = Integer.parseInt(getPortArrayList.get(1));
		this.port = temp+2;
      
      Thread connectorThread = new Thread(this); //adds itself to a thread
      connectorThread.start(); //starts run() as a new thread.
     
	}
	
	@Override
    public void run() 
	{
		try {
			ss = new ServerSocket(this.port); //creates the ServerSocket that users are suppose to connector to
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		
		
		
		changeStatus(); //changes boolean to true, and as such allows the while loop
		while(status)
	      {
	    	try {
				clientSocket = ss.accept(); //accepts incoming connection;
			} catch (IOException e) {
				e.printStackTrace();
			}
	    
	        ConnectionToPlayer ctp = new ConnectionToPlayer(clientSocket, time, currency, player); //creates a new client and gives it a socket and reference to ChatDataHandler
	    	Thread newClientThread = new Thread(ctp);//adds client to a thread
	    	changeStatus();
	    	newClientThread.start(); //Starts the thread
	    	
	      }
		try {
			ss.close(); //closes the socket so it can be reused again.
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * sets the boolean to the opposite value of its current
	 */
	public void changeStatus()
	{
		status = !status;    
	}
}
