package gui;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.UIManager;

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
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setVgap(13);
		panel_2.setBounds(6, 6, 263, 45);
		add(panel_2);
		
		JLabel lblDig = new JLabel("Dig:");
		panel_2.add(lblDig);
		
		JLabel lblYourMoney = new JLabel("$100.000 US");
		lblYourMoney.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		panel_2.add(lblYourMoney);
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_3.getLayout();
		flowLayout_1.setVgap(13);
		panel_3.setBounds(268, 6, 263, 45);
		add(panel_3);
		
		JLabel lblModstander = new JLabel("Modstander:");
		panel_3.add(lblModstander);
		
		JLabel lblOpponentMoney = new JLabel("$87.000 US");
		lblOpponentMoney.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		panel_3.add(lblOpponentMoney);
		
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
		
		JPanel all = new JPanel();
		all.setBounds(15, 80, 767, 462);
		add(all);
		all.setOpaque(true);
		all.setBackground(new Color(0,0,0,0));
		all.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 255, 439);
		all.add(scrollPane);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		
		JLabel lblNameofstock = new JLabel("NameOfStock");
		lblNameofstock.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNameofstock.setBounds(285, 6, 476, 16);
		all.add(lblNameofstock);
		
		JLabel lblMarket = new JLabel("Market");
		lblMarket.setForeground(Color.GRAY);
		lblMarket.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		lblMarket.setBounds(285, 23, 476, 16);
		all.add(lblMarket);
		
		JPanel btnBuy = new JPanel();
		btnBuy.setBackground(ClientWindow.green);
		FlowLayout fl_btnBuy = (FlowLayout) btnBuy.getLayout();
		fl_btnBuy.setVgap(13);
		btnBuy.setBounds(285, 402, 209, 41);
		all.add(btnBuy);
		
		JLabel lblKbAktie = new JLabel("K\u00F8b Aktier");
		btnBuy.add(lblKbAktie);
		
		JPanel btnSell = new JPanel();
		btnSell.setBackground(ClientWindow.red);
		FlowLayout fl_btnSell = (FlowLayout) btnSell.getLayout();
		fl_btnSell.setVgap(13);
		btnSell.setBounds(537, 402, 209, 41);
		all.add(btnSell);
		
		JLabel lblSlgAktier = new JLabel("S\u00E6lg Aktier");
		btnSell.add(lblSlgAktier);
		
		JLabel lblPridForAktie = new JLabel("Pris for aktie:");
		lblPridForAktie.setBounds(285, 68, 96, 16);
		all.add(lblPridForAktie);
		
		JLabel label = new JLabel("$25.00");
		label.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		label.setBounds(382, 68, 96, 16);
		all.add(label);
		
		JLabel lblDuEjerX = new JLabel("Du ejer X stk. af denne aktie til en samlet v\u00E6rdi af: $ XX.XX");
		lblDuEjerX.setBounds(285, 105, 461, 16);
		all.add(lblDuEjerX);
		
		JLabel lblDuKanKbe = new JLabel("Du kan k\u00F8be op til: XX stk. af denne aktie med din nuv\u00E6rende saldo.");
		lblDuKanKbe.setBounds(285, 125, 461, 16);
		all.add(lblDuKanKbe);
		
		JLabel lblAktiensPrisEr = new JLabel("Aktiens pris er faldet med XX.XX siden du foretog et k\u00F8b.");
		lblAktiensPrisEr.setBounds(285, 167, 461, 16);
		all.add(lblAktiensPrisEr);
		
		JLabel lblHvisDuSlger = new JLabel("Hvis du s\u00E6lger alle aktierne nu, vil du tabe $ XXX.XX");
		lblHvisDuSlger.setBounds(285, 187, 461, 16);
		all.add(lblHvisDuSlger);

	}
}
