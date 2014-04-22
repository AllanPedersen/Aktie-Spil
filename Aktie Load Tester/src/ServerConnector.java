import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class ServerConnector implements Runnable
{
	private String name;
	private String ip = "127.0.0.1"; //Ip for server
	private int port = 16000;
	private Socket socket;

	//used to communicate with the server
	private String textFromServer;
	private Scanner readingFromServer;
	private PrintWriter sendToServer; //I use PrintWriter to access the 'print' methods.
	private long start, finish;

	public ServerConnector(String name)
	{
		this.name = name;
		this.start = System.currentTimeMillis();
		connect(); //connects to Server
	}

	@Override
	public void run() 
	{
		while(true)
		{
			if(this.readingFromServer.hasNextLine())
			{
				if (this.finish == 0) {
					this.finish = System.currentTimeMillis();
				}
				this.textFromServer = this.readingFromServer.nextLine();

				//updates active users
				if(this.textFromServer.startsWith("nu"))
				{

					final String[] divideUpdateString = this.textFromServer.split(","); //divides the incoming text into a String array
					final String[] userNames = new String[divideUpdateString.length-1];
					System.arraycopy(divideUpdateString, 1, userNames, 0, userNames.length); //copies everything from dividedUpdateString starting from position 1 to userNames


				}	 

			}	    
		}

	}

	public boolean connect()
	{

		try {
			this.socket = new Socket(this.ip, this.port);
			this.sendToServer = new PrintWriter(this.socket.getOutputStream()); //link printWriter to the sockets outputStream
			this.readingFromServer = new Scanner(this.socket.getInputStream()); //link scanner to the sockets inputStream 

			//Server expects to get the underlying information when attempting to establish connection
			sendToServer.println("fm," + this.name + "," + this.ip + "," + this.port + "," + ""); 
			sendToServer.flush();


			new Thread(this).start(); //starts the new Thread
			return true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "error");
			e.printStackTrace();
			return false;	
		}	

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
	
	public long getDiff() {
		return this.finish - this.start;
	}
}