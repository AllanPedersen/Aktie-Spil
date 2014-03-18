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

public class SettingsWindow extends JFrame implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public SettingsWindow() {
		super();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600, 400);
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
		settingsPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		settingsPanel.add(panel, BorderLayout.SOUTH);
		
		JButton btnGem = new JButton("Gem");
		panel.add(btnGem);
		
		
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
