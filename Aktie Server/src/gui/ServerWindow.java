package gui;

import java.awt.Window.Type;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * This class is responsible for creating all the visual elements of the server
 * @author Allan
 *
 */
public class ServerWindow
{
    public JFrame frame;
	private String warningMessage_String = "Are you sure you want to terminate?";
	private String closeServer_String = "Close Server";

	
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
		this.frame.setType(Type.NORMAL);
		this.frame.setBounds(100, 100, 800, 600);

		
		
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //Sets this to do nothing and add my own listener further down the code
		this.frame.setResizable(false);
		this.frame.getContentPane().setLayout(new BoxLayout(this.frame.getContentPane(), BoxLayout.Y_AXIS));
	

		
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
}}