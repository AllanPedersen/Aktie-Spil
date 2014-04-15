package gui;

import javax.swing.JFrame;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import logic.Player;

public class InviteWindow extends JFrame implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblMoney, lblTime, lblSpillernavn;
	private JPanel btnAccept, btnIgnore;
	private Player player;

	public InviteWindow(String player, String time, String currency) {
		super();
		this.setResizable(false);
		this.setSize(444, 266);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setVgap(8);
		panel.setBackground(Color.DARK_GRAY);
		getContentPane().add(panel, BorderLayout.NORTH);

		JLabel lblInvitationModtaget = new JLabel("Invitation modtaget");
		lblInvitationModtaget.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblInvitationModtaget.setForeground(Color.WHITE);
		panel.add(lblInvitationModtaget);

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);

		JLabel lblDuErBlevet = new JLabel("Du er blevet inviteret til et spil af:");
		lblDuErBlevet.setBounds(16, 16, 212, 16);
		panel_1.add(lblDuErBlevet);

		lblSpillernavn = new JLabel(player);
		lblSpillernavn.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblSpillernavn.setBounds(240, 16, 198, 16);
		panel_1.add(lblSpillernavn);

		JLabel lblSpilletsVarighed = new JLabel("Spillets varighed:");
		lblSpilletsVarighed.setBounds(16, 59, 139, 16);
		panel_1.add(lblSpilletsVarighed);

		JLabel lblStartKapital = new JLabel("Start kapital:");
		lblStartKapital.setBounds(16, 87, 139, 16);
		panel_1.add(lblStartKapital);

		lblTime = new JLabel(time);
		lblTime.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblTime.setBounds(167, 59, 139, 16);
		panel_1.add(lblTime);

		lblMoney = new JLabel(currency);
		lblMoney.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblMoney.setBounds(167, 87, 139, 16);
		panel_1.add(lblMoney);

		btnAccept = new JPanel();
		btnAccept.addMouseListener(this);
		btnAccept.setBackground(ClientWindow.green);
		FlowLayout fl_btnAccept = (FlowLayout) btnAccept.getLayout();
		fl_btnAccept.setVgap(11);
		btnAccept.setBounds(16, 135, 188, 39);
		panel_1.add(btnAccept);

		JLabel lblAccepter = new JLabel("Accepter");
		btnAccept.add(lblAccepter);

		btnIgnore = new JPanel();
		btnIgnore.addMouseListener(this);
		btnIgnore.setBackground(ClientWindow.red);
		FlowLayout fl_btnIgnore = (FlowLayout) btnIgnore.getLayout();
		fl_btnIgnore.setVgap(11);
		btnIgnore.setBounds(240, 135, 188, 39);
		panel_1.add(btnIgnore);

		JLabel lblIgnorer = new JLabel("Afvis");
		btnIgnore.add(lblIgnorer);

		this.setVisible(true);
		toFront();
	}	

	/**
	 * This method accepts the invitation from the other player
	 */
	private void acceptInvitation() {
		// TODO: Notify gameserver of decission
		
		// Change main window view to gamepanel
		ClientWindow.changeLayout("Game");
		
		// Close popup window
		this.dispose();
	}

	/**
	 * This method declines the invitation from the other player
	 */
	private void declineInvitation() {
		// TODO: Notify gameserver of decission
		
		// Close popup window
		this.dispose();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == btnAccept) {
			acceptInvitation();
		}
		if (e.getSource() == btnIgnore) {
			declineInvitation();
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
		if (e.getSource() == this.btnAccept) {
			btnAccept.setBackground(ClientWindow.hoverGreen);
			btnAccept.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		if (e.getSource() == this.btnIgnore) {
			btnIgnore.setBackground(ClientWindow.hoverRed);
			btnIgnore.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource() == this.btnAccept) {
			btnAccept.setBackground(ClientWindow.green);
		}
		if (e.getSource() == this.btnIgnore) {
			btnIgnore.setBackground(ClientWindow.red);
		}
	}
}
