package gui;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Font;

public class StockPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public StockPanel() {
		this.setSize(800, 600);
		setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(6, 60, 788, 508);
		add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setOpaque(true);
		panel.setBackground(new Color(0,0,0,0));
		tabbedPane.addTab("Alle aktier", null, panel, null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(true);
		panel_1.setBackground(new Color(0,0,0,0));
		tabbedPane.addTab("Mine aktier", null, panel_1, null);
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setVgap(13);
		panel_2.setBounds(6, 6, 263, 45);
		add(panel_2);
		
		JLabel lblDig = new JLabel("Dig:");
		panel_2.add(lblDig);
		
		JLabel lblUs = new JLabel("$100.000 US");
		lblUs.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		panel_2.add(lblUs);
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_3.getLayout();
		flowLayout_1.setVgap(13);
		panel_3.setBounds(268, 6, 263, 45);
		add(panel_3);
		
		JLabel lblModstander = new JLabel("Modstander:");
		panel_3.add(lblModstander);
		
		JLabel lblUs_1 = new JLabel("$87.000 US");
		lblUs_1.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		panel_3.add(lblUs_1);
		
		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_4.getLayout();
		flowLayout_2.setVgap(13);
		panel_4.setBounds(531, 6, 263, 45);
		add(panel_4);
		
		JLabel lblTidTilbage = new JLabel("Tid tilbage:");
		panel_4.add(lblTidTilbage);
		
		JLabel lblTime = new JLabel("1 time 13 min");
		lblTime.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		panel_4.add(lblTime);

	}
}
