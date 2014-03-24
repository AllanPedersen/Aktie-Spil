package gui;


import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;

public class SettingsWindow extends JFrame implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField ipTextField;
	private JTextField portTextField;
	private JTextField nameTextField;
	private JTextField emailTextField;
	
	public SettingsWindow() {
		super();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(550, 300);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		FlowLayout fl_topPanel = new FlowLayout();
		fl_topPanel.setVgap(10);
		JPanel topPanel = new JPanel(fl_topPanel);
		topPanel.setBackground(Color.DARK_GRAY);
		
		getContentPane().add(topPanel, BorderLayout.NORTH);
		
		JLabel lblAktiespilSettings = new JLabel("Aktiespil - Indstillinger");
		lblAktiespilSettings.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
		lblAktiespilSettings.setForeground(Color.WHITE);
		topPanel.add(lblAktiespilSettings);
		
		JPanel settingsPanel = new JPanel();
		getContentPane().add(settingsPanel, BorderLayout.CENTER);
		settingsPanel.setLayout(null);
		
		JLabel lblServerIp = new JLabel("Server IP:");
		lblServerIp.setBounds(58, 45, 115, 16);
		settingsPanel.add(lblServerIp);
		
		JLabel lblServerPort = new JLabel("Server Port:");
		lblServerPort.setBounds(58, 73, 115, 16);
		settingsPanel.add(lblServerPort);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(58, 101, 115, 16);
		settingsPanel.add(lblName);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(58, 129, 115, 16);
		settingsPanel.add(lblEmail);
		
		ipTextField = new JTextField();
		ipTextField.setBounds(169, 39, 324, 28);
		settingsPanel.add(ipTextField);
		ipTextField.setColumns(10);
		
		portTextField = new JTextField();
		portTextField.setColumns(10);
		portTextField.setBounds(169, 67, 324, 28);
		settingsPanel.add(portTextField);
		
		nameTextField = new JTextField();
		nameTextField.setColumns(10);
		nameTextField.setBounds(169, 95, 324, 28);
		settingsPanel.add(nameTextField);
		
		emailTextField = new JTextField();
		emailTextField.setColumns(10);
		emailTextField.setBounds(169, 123, 324, 28);
		settingsPanel.add(emailTextField);
		
		JButton btnGem = new JButton("Gem");
		btnGem.setBounds(240, 182, 75, 29);
		settingsPanel.add(btnGem);
		
		this.setResizable(false);
		this.setVisible(true);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
