package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Color;

import javax.swing.border.TitledBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;

import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
		
		JLabel lblChrisDenOnde = new JLabel("Chris den onde ged");
		lblChrisDenOnde.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		lblChrisDenOnde.setBounds(131, 16, 391, 22);
		add(lblChrisDenOnde);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GREEN);
		panel.setBounds(763, 16, 20, 20);
		add(panel);
		
		invitePanel = new JPanel();
		invitePanel.addMouseListener(this);
		FlowLayout flowLayout = (FlowLayout) invitePanel.getLayout();
		flowLayout.setVgap(7);
		invitePanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		invitePanel.setBackground(ClientWindow.green);
		invitePanel.setBounds(16, 526, 506, 35);
		add(invitePanel);
		
		JLabel lblInviter = new JLabel("Inviter til spil");
		lblInviter.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		invitePanel.add(lblInviter);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(16, 86, 506, 424);
		add(scrollPane);
		
		JList<String> list = new JList<String>();
		list.setModel(new AbstractListModel<String>() {
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
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);
		
		JLabel lblVlgEnModstander = new JLabel("V\u00E6lg en modstander at spille imod, eller vent p\u00E5 at en anmoder dig om et spil.");
		lblVlgEnModstander.setForeground(Color.DARK_GRAY);
		lblVlgEnModstander.setBounds(16, 63, 506, 16);
		add(lblVlgEnModstander);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == invitePanel) {
			invitePanel.setBackground(ClientWindow.hoverGreen);
			invitePanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource() == invitePanel) {
			invitePanel.setBackground(ClientWindow.green);
		}
	}
}
