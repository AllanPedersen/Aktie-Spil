package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Color;

import javax.swing.border.EmptyBorder;

import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;

public class Lobby extends JPanel implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel invitePanel;
	private JPanel status;
	private JLabel lblPlayerName;
	private JLabel high1, high2, high3, high4, high5, high6, high7, high8, high9, high10;

	/**
	 * Create the panel.
	 */
	public Lobby() {
		super();
		this.setSize(800, 600);
		this.setLayout(null);
		
		JLabel lblVelkommen = new JLabel("Velkommen");
		lblVelkommen.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblVelkommen.setBounds(16, 16, 114, 22);
		add(lblVelkommen);
		
		lblPlayerName = new JLabel();
		lblPlayerName.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		lblPlayerName.setBounds(131, 16, 391, 22);
		add(lblPlayerName);
		
		status = new JPanel();
		status.setBackground(Color.GREEN);
		status.setBounds(763, 16, 20, 20);
		add(status);
		
		invitePanel = new JPanel();
		invitePanel.addMouseListener(this);
		FlowLayout flowLayout = (FlowLayout) invitePanel.getLayout();
		flowLayout.setVgap(7);
		invitePanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		invitePanel.setBackground(ClientWindow.green);
		invitePanel.setBounds(16, 526, 767, 35);
		add(invitePanel);
		
		JLabel lblInviter = new JLabel("Inviter til spil");
		lblInviter.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		invitePanel.add(lblInviter);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(16, 86, 506, 424);
		add(scrollPane);
		
		JList<String> playerList = new JList<String>();
		playerList.setModel(new AbstractListModel<String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			String[] values = new String[] {"Allan den gode gud", "Rasmus den lille tykke gris", "Peter med det store hoved", "Yvonne den lille julemus", "Marius den d\u00F8de giraf"};
			public int getSize() {
				return values.length;
			}
			public String getElementAt(int index) {
				return values[index];
			}
		});
		playerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(playerList);
		
		JLabel lblVlgEnModstander = new JLabel("V\u00E6lg en modstander at spille imod, eller vent p\u00E5 at en anmoder dig om et spil.");
		lblVlgEnModstander.setForeground(Color.DARK_GRAY);
		lblVlgEnModstander.setBounds(16, 63, 506, 16);
		add(lblVlgEnModstander);
		
		JLabel lblHighscores = new JLabel("Highscores:");
		lblHighscores.setForeground(Color.DARK_GRAY);
		lblHighscores.setBounds(547, 63, 236, 16);
		add(lblHighscores);
		
		JLabel pos1 = new JLabel("1.");
		pos1.setBounds(547, 101, 29, 16);
		add(pos1);
		
		JLabel pos2 = new JLabel("2.");
		pos2.setBounds(547, 129, 29, 16);
		add(pos2);
		
		JLabel pos3 = new JLabel("3.");
		pos3.setBounds(547, 157, 29, 16);
		add(pos3);
		
		JLabel pos4 = new JLabel("4.");
		pos4.setBounds(547, 185, 29, 16);
		add(pos4);
		
		JLabel pos5 = new JLabel("5.");
		pos5.setBounds(547, 213, 29, 16);
		add(pos5);
		
		JLabel pos6 = new JLabel("6.");
		pos6.setBounds(547, 241, 29, 16);
		add(pos6);
		
		JLabel pos7 = new JLabel("7.");
		pos7.setBounds(547, 269, 29, 16);
		add(pos7);
		
		JLabel pos8 = new JLabel("8.");
		pos8.setBounds(547, 297, 29, 16);
		add(pos8);
		
		JLabel pos9 = new JLabel("9.");
		pos9.setBounds(547, 325, 29, 16);
		add(pos9);
		
		JLabel pos10 = new JLabel("10.");
		pos10.setBounds(547, 353, 29, 16);
		add(pos10);
		
		high1 = new JLabel();
		high1.setBounds(588, 101, 189, 16);
		add(high1);
		
		high2 = new JLabel();
		high2.setBounds(588, 129, 189, 16);
		add(high2);
		
		high3 = new JLabel();
		high3.setBounds(588, 157, 189, 16);
		add(high3);
		
		high4 = new JLabel();
		high4.setBounds(588, 185, 189, 16);
		add(high4);
		
		high5 = new JLabel();
		high5.setBounds(588, 213, 189, 16);
		add(high5);
		
		high6 = new JLabel();
		high6.setBounds(588, 241, 189, 16);
		add(high6);
		
		high7 = new JLabel();
		high7.setBounds(588, 269, 189, 16);
		add(high7);
		
		high8 = new JLabel();
		high8.setBounds(588, 297, 189, 16);
		add(high8);
		
		high9 = new JLabel();
		high9.setBounds(588, 325, 189, 16);
		add(high9);
		
		high10 = new JLabel();
		high10.setBounds(588, 353, 189, 16);
		add(high10);
	}
	
	public void setServerStatus(int status) {
		switch (status) {
		case 1: // Everything is good, green
			this.status.setBackground(Color.GREEN);
			break;
		case 2: // Problems, yellow
			this.status.setBackground(Color.YELLOW);
			break;
		case 3: // Lost connection, red
			this.status.setBackground(Color.RED);
			break;
		default:
			this.status.setBackground(Color.GREEN);
			break;
		}
	}
	
	/**
	 * This method sets the username
	 * @param username
	 */
	public void setUsername(String username) {
		this.lblPlayerName.setText(username);
	}
	
	/**
	 * This method sets the highscores based on an arraylist. The first item in the arraylist will be the highest score and so on.
	 * @param highscores
	 */
	public void setHighScores(ArrayList<String> highscores) {
		int loop = 10;
		if (highscores.size() < 10) {
			loop = highscores.size();
		}
		
		for (int x = 0; x < loop; x++) {
			switch (x) {
			case 0:
				high1.setText(highscores.get(x));
				break;
			case 1:
				high2.setText(highscores.get(x));
				break;
			case 2:
				high3.setText(highscores.get(x));
				break;
			case 3:
				high4.setText(highscores.get(x));
				break;
			case 4:
				high5.setText(highscores.get(x));
				break;
			case 5:
				high6.setText(highscores.get(x));
				break;
			case 6:
				high7.setText(highscores.get(x));
				break;
			case 7:
				high8.setText(highscores.get(x));
				break;
			case 8:
				high9.setText(highscores.get(x));
				break;
			case 9:
				high10.setText(highscores.get(x));
				break;
			default:
				break;
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == invitePanel) {
			// TODO: Create real action
			JOptionPane.showConfirmDialog(this, "Invitation sendt");
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	/**
	 * This method is used to register when a user hovers the mouse over a specific element
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// Change color and cursor when hovers over invite button (JPanel)
		if (e.getSource() == invitePanel) {
			invitePanel.setBackground(ClientWindow.hoverGreen);
			invitePanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		
	}

	/**
	 * This method is used to register when a user stops hovering over an element
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// Returns color and pointer when mouse stops hovering over invite button
		if (e.getSource() == invitePanel) {
			invitePanel.setBackground(ClientWindow.green);
		}
	}
}
