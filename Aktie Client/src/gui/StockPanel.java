package gui;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import xml.Parser;
import logic.Bank;
import logic.Stock;

public class StockPanel extends JPanel implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JPanel btnBuy, btnSell;
	private static boolean hasStock = false;
	private static Stock selectedStock = null;
	private ArrayList<Stock> stocks;
	private static JLabel lblStockName, lblStockPrice, lblDuEjerX, lblDuKanKbe, lblAktiensPrisEr, lblHvisDuSlger, lblPridForAktie, lblYourMoney, lblOpponentMoney, lblTime;
	private JList<String> list;
	private DefaultListModel<String> listModel;
	private static Bank bank;


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

		lblYourMoney = new JLabel();
		lblYourMoney.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		panel_2.add(lblYourMoney);

		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_3.getLayout();
		flowLayout_1.setVgap(13);
		panel_3.setBounds(268, 6, 263, 45);
		add(panel_3);

		JLabel lblModstander = new JLabel("Modstander:");
		panel_3.add(lblModstander);

		lblOpponentMoney = new JLabel();
		lblOpponentMoney.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		panel_3.add(lblOpponentMoney);

		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_4.getLayout();
		flowLayout_2.setVgap(13);
		panel_4.setBounds(531, 6, 263, 45);
		add(panel_4);

		JLabel lblTidTilbage = new JLabel("Tid tilbage:");
		panel_4.add(lblTidTilbage);

		lblTime = new JLabel("1 time 00 min");
		lblTime.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		panel_4.add(lblTime);

		JPanel all = new JPanel();
		all.setBounds(15, 63, 767, 511);
		add(all);
		all.setOpaque(true);
		all.setBackground(new Color(0,0,0,0));
		all.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 255, 490);
		all.add(scrollPane);

		listModel = new DefaultListModel<String>();

		list = new JList<String>();
		list.setModel(listModel);
		list.addMouseListener(this);
		scrollPane.setViewportView(list);

		lblStockName = new JLabel();		
		lblStockName.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblStockName.setBounds(285, 6, 476, 16);
		all.add(lblStockName);

		JLabel lblMarket = new JLabel("");
		lblMarket.setForeground(Color.GRAY);
		lblMarket.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		lblMarket.setBounds(285, 23, 476, 16);
		all.add(lblMarket);

		btnBuy = new JPanel();
		btnBuy.addMouseListener(this);
		btnBuy.setBackground(ClientWindow.green);
		FlowLayout fl_btnBuy = (FlowLayout) btnBuy.getLayout();
		fl_btnBuy.setVgap(13);
		btnBuy.setBounds(285, 455, 223, 41);
		all.add(btnBuy);

		JLabel lblKbAktie = new JLabel("K\u00F8b Aktier");
		btnBuy.add(lblKbAktie);

		btnSell = new JPanel();
		btnSell.setBackground(ClientWindow.red);
		btnSell.addMouseListener(this);

		FlowLayout fl_btnSell = (FlowLayout) btnSell.getLayout();
		fl_btnSell.setVgap(13);
		btnSell.setBounds(520, 455, 226, 41);
		all.add(btnSell);

		JLabel lblSlgAktier = new JLabel("S\u00E6lg Aktier");
		btnSell.add(lblSlgAktier);

		lblPridForAktie = new JLabel();
		lblPridForAktie.setBounds(285, 68, 96, 16);
		all.add(lblPridForAktie);

		lblStockPrice = new JLabel();
		lblStockPrice.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblStockPrice.setBounds(382, 68, 96, 16);
		all.add(lblStockPrice);

		lblDuEjerX = new JLabel();
		lblDuEjerX.setBounds(285, 105, 461, 16);
		all.add(lblDuEjerX);

		lblDuKanKbe = new JLabel();
		lblDuKanKbe.setBounds(285, 125, 461, 16);
		all.add(lblDuKanKbe);

		lblAktiensPrisEr = new JLabel();
		lblAktiensPrisEr.setBounds(285, 167, 461, 16);
		all.add(lblAktiensPrisEr);

		lblHvisDuSlger = new JLabel();
		lblHvisDuSlger.setBounds(285, 187, 461, 16);
		all.add(lblHvisDuSlger);

		JSeparator separator = new JSeparator();
		separator.setBounds(6, 48, 788, 12);
		add(separator);
	}
	
	public void startGame(String amount, String time) {
		// Parse strings to int and double values.
		amount = amount.replaceAll("[.]", "");
		int am = Integer.parseInt(amount);
		Bank.instantiateBank(am);
		bank = Bank.getInstance();
		this.setStockList(Parser.getAllStocks());
		this.setOpponentMoney(am);
		
		// TODO Set timer..
		lblTime.setText(time);
	}

	public void setStockList(ArrayList<Stock> stocks) {
		// Only set if stocks array is empty
		if (this.stocks == null || this.stocks.size() == 0) {
			this.stocks = stocks;

			// Populate list with stocks
			for (Stock stock : this.stocks) {
				listModel.addElement(stock.getName());
			}
		}
	}
	
	public void setOpponentMoney(double money) {
		lblOpponentMoney.setText("$" + Math.round(money));
	}
	
	public void setTime(String time) {
		lblTime.setText(time);
	}

	public void updateView() {
		// If stock is bought set hasStock
		if (selectedStock.getBankAmount() > 0) {
			hasStock = true;
		} else {
			hasStock = false;
		}
		
		lblPridForAktie.setText("Pris for aktie: ");

		// Set name of stock
		lblStockName.setText(selectedStock.getName());

		// Set value of stock
		lblStockPrice.setText("$" + selectedStock.getValue());

		// Set number of bought stocks
		lblDuEjerX.setText("Du ejer "+ selectedStock.getBankAmount() +" stk. af denne aktie til en samlet v\u00E6rdi af: $ " + Math.round((selectedStock.getBankAmount() * selectedStock.getValueNow())));

		// Set how many stocks can be bought
		// TODO Get from info from bank..
		lblDuKanKbe.setText("Du kan k\u00F8be op til: " + Math.floor(bank.getAmount() / selectedStock.getValue()) + " stk. af denne aktie med din nuv\u00E6rende saldo.");

		// Check if stock is bought
		if (hasStock) {
			if (selectedStock.getBoughtValue() > selectedStock.getValueNow()) {
				// Lost money
				lblAktiensPrisEr.setText("Aktiens pris er faldet med $ " + Math.round(Math.abs((selectedStock.getBoughtValue() - selectedStock.getValueNow()))) + " siden du foretog et k\u00F8b.");

				// Calculate loss if everything is sold
				double loss = Math.abs(((selectedStock.getBoughtValue() - selectedStock.getValueNow()) * selectedStock.getBankAmount()));

				lblHvisDuSlger.setText("Hvis du s\u00E6lger alle aktierne nu, vil du tabe $ " + Math.round(loss));
			} else {
				// Gained money
				lblAktiensPrisEr.setText("Aktiens pris er steget med $ " + Math.abs(Math.round((selectedStock.getBoughtValue() - selectedStock.getValueNow()))) + " siden du foretog et k\u00F8b.");

				// Calculate gain if everything is sold
				double gain = ((selectedStock.getBoughtValue() - selectedStock.getValueNow()) * selectedStock.getBankAmount());

				lblHvisDuSlger.setText("Hvis du s\u00E6lger alle aktierne nu, vil du vinde $ " + Math.abs(Math.round(gain)));
			}

			btnSell.setBackground(ClientWindow.red);

		} else {
			lblAktiensPrisEr.setText("");
			lblHvisDuSlger.setText("");
			btnSell.setBackground(Color.GRAY);
		}	

		// Update money
		lblYourMoney.setText("$" + Math.round(bank.getAmount()));
		
		this.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == btnBuy) {
			// Open buy panel with the selected stock
			new BuyStockWindow(selectedStock, this);
		}

		if (e.getSource() == btnSell) {
			// Make sure stock is purchased
			if (hasStock) {
				// Open sell panel with selected stock
				new SellStockWindow(selectedStock, this);
			}
		}

		if (e.getSource() == list) {
			if (list.getSelectedIndex() >= 0) {

				// Get selected Stock
				Stock selected = stocks.get(list.getSelectedIndex());

				// Set as selected stock
				selectedStock = selected;

				// Update the view
				updateView();
				this.repaint();
			}
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
		if (e.getSource() == btnBuy) {
			btnBuy.setBackground(ClientWindow.hoverGreen);
			btnBuy.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		if (e.getSource() == btnSell) {
			if (hasStock) {
				btnSell.setBackground(ClientWindow.hoverRed);
				btnSell.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource() == btnBuy) {
			btnBuy.setBackground(ClientWindow.green);
		}
		if (e.getSource() == btnSell) {
			if (hasStock) {
				btnSell.setBackground(ClientWindow.red);
			}
		}
	}
}
