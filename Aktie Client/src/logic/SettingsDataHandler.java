package logic;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;


/**
 * @author Allan
 *
 */

public class SettingsDataHandler 
{
	private String storedData;
	private String temporaryData;
	private String username;

	/**
	 * takes notice if firstly the folder exists, and secondly the settings.txt
	 * if none exists then creates both.
	 */
	public SettingsDataHandler()
	{
		/**
		 * If not mac, use these settings
		 */
		if (!Main.IS_MAC) {
			this.username = System.getProperty("user.name");
			File directory = new File("c:\\users\\" + username + "\\appData\\local\\Aktie Client");

			// if the directory does not exist, create it
			if (!directory.exists()) 
			{  directory.mkdir(); 
			}

			this.storedData = "c:\\users\\" + username + "\\appData\\local\\Aktie Client\\settings.txt";
			this.temporaryData = "c:\\users\\" + username + "\\appData\\local\\Aktie Client\\settings2.txt";

			if(!new File(storedData).exists())
			{
				createNewFile();
			}
			/**
			 * If Mac use these settings
			 */
		} else {
			this.username = System.getProperty("user.home");
			File directory = new File(this.username +"/Documents/Aktie Client");

			// if the directory does not exist, create it
			if (!directory.exists()) 
			{  directory.mkdir(); 
			}

			this.storedData = this.username +"/Documents/Aktie Client/settings.txt";
			this.temporaryData = this.username +"/Documents/Aktie Client/settings2.txt";

			if(!new File(storedData).exists())
			{
				createNewFile();
			}
		}
	}

	public static boolean settingsExists() {
		String storedData;
		if (Main.IS_MAC) {
			String username = System.getProperty("user.home");
			storedData = username +"/Documents/Aktie Client/settings.txt";
		} else {
			String username = System.getProperty("user.name");
			storedData = "c:\\users\\" + username + "\\appData\\local\\Aktie Client\\settings.txt";
		}

		if (new File(storedData).exists()) {
			return true;
		} else {
			return false;
		}

	}



	/**
	 * Uses a bufferedReader to get all the data in a text document, then returns it as an Arraylist
	 * @return
	 */
	public ArrayList<String> getSettings()
	{	
		String line;
		ArrayList<String> temp = new ArrayList<String>(); //creates and ArrayList to store the users
		BufferedReader reader;
		try{       
			reader = new BufferedReader(new FileReader(storedData));
			while((line = reader.readLine()) != null) 
			{
				temp.add(line.substring((line.lastIndexOf(',') +1), line.length()));
			}
			reader.close();
		}
		catch(IOException e){
			JOptionPane.showMessageDialog(null, "Error");
			e.printStackTrace();
		}
		return temp;
	}


	/**
	 * creates a new blank file to be used in case the program is newly installed or the old one is deleted.
	 */
	public void createNewFile()
	{
		File f = new File(this.storedData);
		try {
			BufferedWriter writer;
			f.createNewFile();
			writer = new BufferedWriter(new FileWriter(f));
			writer.append("ip,");
			writer.newLine();
			writer.append("port,");
			writer.newLine();
			writer.append("navn,");
			writer.newLine();
			writer.append("email,");
			writer.newLine();
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	public void writeNewSettings(ArrayList<String> newList)
	{
		BufferedWriter writer;
		File tempFile = new File(this.temporaryData); 
		try{       
			writer = new BufferedWriter(new FileWriter(tempFile));
			writer.append("ip,"+newList.get(0));
			writer.newLine();
			writer.append("port,"+newList.get(1));
			writer.newLine();
			writer.append("navn,"+newList.get(2));
			writer.newLine();
			writer.append("email,"+newList.get(3));
			writer.newLine();
			writer.close();
		}
		catch(IOException e){
			JOptionPane.showMessageDialog(null, "Error");
			e.printStackTrace();
		}
		File oldFile = new File(this.storedData); //gets the old file
		oldFile.delete(); //deletes the old file
		tempFile.renameTo(oldFile); //rename new file to old file
	}

	//	public String getWindowsUsername()
	//	{
	//		return windowsUsername;
	//	}
}

