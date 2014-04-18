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
	private PrintWriter sendToOther;
	private Scanner readingFromOther;
	private String textFromOther;
	private String currency;
	private String time;
	private String opponent;
	
	
	
	
	/**
	 * Server Edition
	 * @param clientSocket
	 */
	public ConnectionToPlayer(Socket socket, final String time, final String currency, final String opponent) {
			this.opponent = opponent;
			this.socket = socket;  
		  establishIO();
		  
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
	}

	/**
	 * Client edition
	 * @param ip
	 * @param port
	 */
	public ConnectionToPlayer(String ip, final String opponent, final String currency, final String time)
	{
		this.opponent = opponent;
	
		SettingsDataHandler sdh = new SettingsDataHandler();
		ArrayList<String> getPortArrayList = sdh.getSettings();
		
	    int port = Integer.parseInt(getPortArrayList.get(1));
		System.out.println(port);
		
		try {
			this.socket = new Socket(ip, port);
					} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "error");
			e.printStackTrace();
		}		
		establishIO();
		
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
	}

	
	public void establishIO()
	{
		
		  try {
				this.sendToOther = new PrintWriter(this.socket.getOutputStream()); //link printWriter to the sockets outputStream
				this.readingFromOther = new Scanner(this.socket.getInputStream()); //link scanner to the sockets inputStream      
			} catch (IOException e) {
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
			
				//I bought stocks
				if(textFromOther.startsWith("bs"))
				{
					
				}

				//I sold stocks
				if(textFromOther.startsWith("ss"))
				{
					
				}
				
				//my balance changed
				if(textFromOther.startsWith("bc"))
				{
					
				}
			    
			
			
			
			
			
			
			
			
			
			
			
			
			}
			
		}
			
	}

}
