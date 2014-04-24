package logic;

import gui.ClientWindow;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class ConnectionToPlayer implements Runnable 
{ 
	private Socket socket;
	public static PrintWriter sendToOther;
	private Scanner readingFromOther;
	private String textFromOther;
	@SuppressWarnings("unused")
	private String currency;
	@SuppressWarnings("unused")
	private String time;
	@SuppressWarnings("unused")
	private String opponent;
	
	
	
	
	/**
	 * Server Edition
	 * @param clientSocket
	 */
	public ConnectionToPlayer(Socket socket, final String time, final String currency, final String opponent) {
			this.opponent = opponent;
			this.socket = socket;  
		    this.currency = currency;
		    this.time = time;
		    this.opponent = opponent;
		  SwingUtilities.invokeLater(new Runnable()
			{
			public void run()
			{
				try
				{
						Main.clientWindow.gamePanel.startGame(currency, time, opponent);
						ClientWindow.changeLayout("Game");
	    		}
	    		catch(Exception e)
	    		{
	    			e.printStackTrace();
	    		}
	    	}
	    });
			 establishIO();
	}

	/**
	 * Client edition
	 * @param ip
	 * @param port
	 */
	public ConnectionToPlayer(String ip, final String opponent, final String currency, final String time)
	{
	    this.currency = currency;
	    this.time = time;
		this.opponent = opponent;
	
		SettingsDataHandler sdh = new SettingsDataHandler();
	
		 ArrayList<String> getPortArrayList = new ArrayList<String>();
		getPortArrayList = sdh.getSettings();
		
		int temp = Integer.parseInt(getPortArrayList.get(1));
		int port = temp+2;
		
		try {
			this.socket = new Socket(ip, port);
					} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "error");
			e.printStackTrace();
		}				
				SwingUtilities.invokeLater(new Runnable()
					{
					public void run()
					{
						try
						{
								Main.clientWindow.gamePanel.startGame(currency, time, opponent);
								ClientWindow.changeLayout("Game");
			    		}
			    		catch(Exception e)
			    		{
			    			e.printStackTrace();
			    		}
			    	}
			    });
				establishIO();
	}

	
	public void establishIO()
	{
		
		  try {
				ConnectionToPlayer.sendToOther = new PrintWriter(this.socket.getOutputStream()); //link printWriter to the sockets outputStream
				this.readingFromOther = new Scanner(this.socket.getInputStream()); //link scanner to the sockets inputStream      
			}  
		  catch (IOException e) {
				e.printStackTrace();
			}	
		  new Thread(this).start(); //starts the new Thread
	
	}

	public void run() {
		
		
		while(true)
		{
			if(this.readingFromOther.hasNextLine())
			{
				this.textFromOther = this.readingFromOther.nextLine();
				
				
				//balance change
				if(textFromOther.startsWith("bc"))
				{
					 final String[] divideChatString = this.textFromOther.split(",", 2); //splits data into a max of 2 values
					 SwingUtilities.invokeLater(new Runnable()
						{
						public void run()
						{
							try
							{
								double temp = Double.parseDouble(divideChatString[1]);
								Main.clientWindow.gamePanel.setOpponentMoney(temp);
				    		}
				    		catch(Exception e)
				    		{
				    			e.printStackTrace();
				    		}
				    	}
				    });
				}

				//I sold stocks
				if(textFromOther.startsWith("ss"))
				{
					//not yet implemented
				}
				
				//bought stock
				if(textFromOther.startsWith("bs"))
				{
					//not yet implemented
				}
			    
	
			
			}
			
		}
			
	}

}
