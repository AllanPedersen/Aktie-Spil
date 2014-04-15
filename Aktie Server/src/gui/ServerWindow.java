package gui;

import java.awt.Dimension;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Component;

import javax.swing.Box;

import logic.Connector;

/**
 * This class is responsible for creating all the visual elements of the server
 * @author Allan
 *
 */
public class ServerWindow
{
    public JFrame frame;
    
    //used for startbutton 
    private String[] serverStatus_array = {"Server is: Not Running", "Server is: Running"};
	private String start = "start";
	private String stop = "stop";
	
	private Connector c;
    
	
	
    private String warningMessage_String = "Are you sure you want to terminate?";
	private String closeServer_String = "Close Server";
	private JTextField port_textField;
    private JLabel port_label = new JLabel("select port:");
	private JButton startServer_Button = new JButton(start);
    private JPanel connectedUsers_panel = new JPanel();
    private JPanel startServer_panel = new JPanel();
    private JLabel activePlayers_label = new JLabel("0");  
    private Component horizontalGlue = Box.createHorizontalGlue();
    private JLabel howManyUsers_Label = new JLabel("Active Players:");
    private Component horizontalGlue_1 = Box.createHorizontalGlue();
    private JLabel serverStatus_label = new JLabel("Not Running");
    
    
    
	/**
	 * Create the application.
	 */
	public ServerWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.frame = new JFrame();
		this.frame.getContentPane().setBackground(Color.DARK_GRAY);
		this.frame.setType(Type.NORMAL);
		this.frame.setBounds(100, 100, 400, 100);

		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //Sets this to do nothing and add my own listener further down the code
		this.frame.setResizable(false);
		this.frame.getContentPane().setLayout(new BoxLayout(this.frame.getContentPane(), BoxLayout.Y_AXIS));
		
	
		
		this.startServer_panel.setBackground(Color.DARK_GRAY);
		this.frame.getContentPane().add(startServer_panel);
		this.startServer_panel.setLayout(new BoxLayout(startServer_panel, BoxLayout.X_AXIS));
	    this.port_label.setForeground(Color.GREEN);
		this.startServer_panel.add(port_label);
		
		this.port_textField = new JTextField();
		this.port_textField.setBackground(Color.DARK_GRAY);
		this.port_textField.setForeground(Color.GREEN);
		this.port_textField.setMaximumSize(new Dimension(50,25));
		this.startServer_panel.add(port_textField);
		this.port_textField.setColumns(10);
		this.startServer_Button.setBackground(Color.DARK_GRAY);
		this.startServer_Button.setForeground(Color.GREEN);
		this.startServer_panel.add(startServer_Button);
		
		this.serverStatus_label.setForeground(Color.GREEN);
		this.startServer_panel.add(serverStatus_label);
		
		this.startServer_panel.add(horizontalGlue);
		

		this.connectedUsers_panel.setBackground(Color.DARK_GRAY);
		this.frame.getContentPane().add(connectedUsers_panel);
		this.connectedUsers_panel.setLayout(new BoxLayout(connectedUsers_panel, BoxLayout.X_AXIS));
		this.howManyUsers_Label.setForeground(Color.GREEN);
		this.connectedUsers_panel.add(howManyUsers_Label);
		this.activePlayers_label.setForeground(Color.GREEN);
		this.connectedUsers_panel.add(activePlayers_label);
		
		this.connectedUsers_panel.add(horizontalGlue_1);
		
		
        /**
         * Forces the user to confirm wanting to shut down the server
         */
		this.frame.addWindowListener( new WindowAdapter()
		{
		    public void windowClosing(WindowEvent e)
		    {
		        JFrame frame = (JFrame)e.getSource();

		        int result = JOptionPane.showConfirmDialog(
		            frame,warningMessage_String,closeServer_String,
		            JOptionPane.YES_NO_OPTION);

		        if (result == JOptionPane.YES_OPTION)
		        {
		            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        }
		    }
		});
	
		 startServer_Button.addActionListener(new ActionListener() 
			{
				@Override
				public void actionPerformed(ActionEvent e) {
					
					
					if(startServer_Button.getText().equals(start))  //used to determind if server is not running
					{
						try 
						{
							int port = Integer.parseInt(port_textField.getText()); //the port must be an Integer
							serverStatus_label.setText(serverStatus_array[1]);
						
							startServer_Button.setText(stop);	
							c = new Connector(port); //starts server by creating the connector class
							
						} catch (NumberFormatException e1) {
							e1.printStackTrace();
						}			 
					}
					else if(startServer_Button.getText().equals(stop)) //if server is running
					{
						int dialogButton = JOptionPane.YES_NO_OPTION;
			             JOptionPane.showConfirmDialog (null, warningMessage_String,warningMessage_String,dialogButton);
	                    if(dialogButton == JOptionPane.YES_OPTION)
	                    { 
	                    	c.changeStatus(); //attempts to stop server
	                    	startServer_Button.setText("Start");
	                    	serverStatus_label.setText(serverStatus_array[0]);
	                    }
						
					}
					
				}
			});
			
		
	
	}
	
		/**
		 * changes the number of active users.
		 */
	    public void updateActivePlayers(int activePlayers)
		{
	    	
	    	System.out.println(activePlayers);
			this.activePlayers_label.setText(Integer.toString(activePlayers));
		}
	    
	   
}