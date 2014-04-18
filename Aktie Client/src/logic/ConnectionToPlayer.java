package logic;

import gui.ClientWindow;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class ConnectionToPlayer implements Runnable 
{ 
	private Socket socket;
	private PrintWriter sendToOther;
	private Scanner readingFromOther;
	private String textFromOther;
	/**
	 * Server Edition
	 * @param clientSocket
	 */
	public ConnectionToPlayer(Socket socket) {
		  this.socket = socket;  
		  establishIO();
	}

	/**
	 * Client edition
	 * @param ip
	 * @param port
	 */
	public ConnectionToPlayer(String ip, String playerInviting, String currency, String time)
	{
		
		
		//needs to get port, uses the serverport + 1
		String port;
		SettingsDataHandler sdh = new SettingsDataHandler();
		ArrayList<String> getPortArrayList = sdh.getSettings();
		port = getPortArrayList.get(1);
		
		
		try {
			this.socket = new Socket(ip, Integer.parseInt(port));
					} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "error");
			e.printStackTrace();
		}		
		establishIO();
		
		
		Main.clientWindow.gamePanel.startGame(currency, time);
		ClientWindow.changeLayout("Game");
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
