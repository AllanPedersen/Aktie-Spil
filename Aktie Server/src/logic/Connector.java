package logic;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * The connector class is controlling the connection to the server
 * it can be started or stopped by the administrator, and while running
 * users are able to connect and get their own socket.
 * This class also starts threads for every joining user.
 * @author Allan
 *
 */
public class Connector implements Runnable
{
	private ServerSocket ss;
	private Socket clientSocket;
	private UserDataHandler udh;
	
	volatile boolean status; //making the variable volatile ensures that whenever a thread changes it, the change becomes visible to other threads
	private int port;
	
    
	/**
	 * Constructor used for starting server, needs  port
	 * @param port
	 */
	public Connector(int port)
	{
      this.udh = new UserDataHandler(); //creates the instance of UDH
      this.port = port;
      
      Thread connectorThread = new Thread(this); //adds itself to a thread
      connectorThread.start(); //starts run() as a new thread.
     
	}
	


	/**
	 * As the Connector is made from the EDT, a new thread is started which listens for connecting clients.
	 */
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
	        Client client = new Client(clientSocket, udh); //creates a new client and gives it a socket and reference to ChatDataHandler
	    	Thread newClientThread = new Thread(client);//adds client to a thread
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
