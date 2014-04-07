package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class DoInviteWindow extends JFrame implements MouseListener, WindowListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String btnTextWaitForPlayer = "Invitation sendt. Vent venligst";
	private JPanel btnInvite;
	private JLabel lblInviterSpilelr;
	private boolean waiting = false;

	/**
	 * Create the frame.
	 */
	public DoInviteWindow() {
		super();
		this.addWindowListener(this);
		setResizable(false);
		setSize(450, 322);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setVgap(8);
		panel.setBackground(Color.DARK_GRAY);
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblInviterSpiller = new JLabel("Inviter spiller");
		lblInviterSpiller.setForeground(Color.WHITE);
		lblInviterSpiller.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		panel.add(lblInviterSpiller);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		btnInvite = new JPanel();
		btnInvite.addMouseListener(this);
		btnInvite.setBackground(ClientWindow.green);
		FlowLayout fl_btnInvite = (FlowLayout) btnInvite.getLayout();
		fl_btnInvite.setVgap(11);
		btnInvite.setBounds(17, 192, 415, 44);
		panel_1.add(btnInvite);
		
		lblInviterSpilelr = new JLabel("Inviter spiller");
		lblInviterSpilelr.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		btnInvite.add(lblInviterSpilelr);
		
		JLabel lblSendInvitationTil = new JLabel("Send invitation til:");
		lblSendInvitationTil.setBounds(17, 17, 115, 16);
		panel_1.add(lblSendInvitationTil);
		
		JLabel lblDenOndeGed = new JLabel("Den onde ged Chris");
		lblDenOndeGed.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblDenOndeGed.setBounds(144, 17, 288, 16);
		panel_1.add(lblDenOndeGed);
		
		JLabel lblLngdeAfSpil = new JLabel("L\u00E6ngde af spil:");
		lblLngdeAfSpil.setBounds(17, 60, 115, 16);
		panel_1.add(lblLngdeAfSpil);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"20 min", "30 min", "1 time", "2 timer", "5 timer"}));
		comboBox.setBounds(17, 81, 415, 27);
		panel_1.add(comboBox);
		
		JLabel lblStartKapital = new JLabel("Start kapital:");
		lblStartKapital.setBounds(17, 120, 115, 16);
		panel_1.add(lblStartKapital);
		
		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {"10.000", "100.000", "500.000", "1.000.000", "5.000.000"}));
		comboBox_1.setSelectedIndex(2);
		comboBox_1.setBounds(17, 141, 415, 27);
		panel_1.add(comboBox_1);
		
		
		setVisible(true);
		toFront();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == btnInvite) {
			btnInvite.setBackground(Color.gray);
			lblInviterSpilelr.setText(btnTextWaitForPlayer);
			waiting = true;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {		
	}

	@Override
	public void mouseReleased(MouseEvent e) {		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (!waiting) {
			if (e.getSource() == btnInvite) {
				btnInvite.setBackground(ClientWindow.hoverGreen);
				btnInvite.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (!waiting) {
			if (e.getSource() == btnInvite) {
				btnInvite.setBackground(ClientWindow.green);
			}
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Cancel invite
		System.out.println("CANCEL INVITE");
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}
}
