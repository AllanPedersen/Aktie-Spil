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

import logic.Bank;
import logic.ConnectionToPlayer;
import logic.ServerConnector;
import logic.Stock;

import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class BuyStockWindow extends JFrame implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel btnBuy, btnCancel;
	private JTextField amount;
	private Bank bank;
	private Stock stock;
	private StockPanel stPanel;

	public BuyStockWindow(Stock stock, StockPanel pan) {
		super();
		this.bank = Bank.getInstance();
		this.stock = stock;
		this.stock.updateValue();
		this.stPanel = pan;
		this.setResizable(false);
		this.setSize(444, 379);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setVgap(8);
		panel.setBackground(Color.DARK_GRAY);
		getContentPane().add(panel, BorderLayout.NORTH);

		JLabel lblInvitationModtaget = new JLabel("K\u00F8b aktie");
		lblInvitationModtaget.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblInvitationModtaget.setForeground(Color.WHITE);
		panel.add(lblInvitationModtaget);

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);

		btnBuy = new JPanel();
		btnBuy.addMouseListener(this);
		btnBuy.setBackground(ClientWindow.green);
		FlowLayout fl_btnBuy = (FlowLayout) btnBuy.getLayout();
		fl_btnBuy.setVgap(11);
		btnBuy.setBounds(16, 208, 411, 39);
		panel_1.add(btnBuy);

		JLabel lblAccepter = new JLabel("K\u00F8b Aktier");
		btnBuy.add(lblAccepter);

		btnCancel = new JPanel();
		btnCancel.addMouseListener(this);
		btnCancel.setBackground(ClientWindow.red);
		FlowLayout fl_btnCancel = (FlowLayout) btnCancel.getLayout();
		fl_btnCancel.setVgap(11);
		btnCancel.setBounds(16, 259, 411, 39);
		panel_1.add(btnCancel);

		JLabel lblIgnorer = new JLabel("Fortryd");
		btnCancel.add(lblIgnorer);
		
		JLabel lblAktie = new JLabel("Aktie:");
		lblAktie.setBounds(16, 19, 47, 16);
		panel_1.add(lblAktie);
		
		JLabel lblName = new JLabel(this.stock.getName());
		lblName.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblName.setBounds(75, 19, 352, 16);
		panel_1.add(lblName);
		
		JLabel lblAsk = new JLabel("UD $" + this.stock.getValue());
		lblAsk.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblAsk.setBounds(75, 47, 352, 16);
		panel_1.add(lblAsk);
		
		JLabel lblKurs = new JLabel("Kurs:");
		lblKurs.setBounds(16, 47, 47, 16);
		panel_1.add(lblKurs);
		
		JLabel lblSaldo = new JLabel("Du har "+ Math.round(this.bank.getAmount()) +" til r\u00E5dighed til k\u00F8b");
		lblSaldo.setForeground(Color.GRAY);
		lblSaldo.setBounds(16, 75, 411, 16);
		panel_1.add(lblSaldo);
		
		int number = (int) Math.floor(this.bank.getAmount() / this.stock.getValue());
		
		JLabel lblCanBuy = new JLabel("Du kan k\u00F8be op til "+number+" af denne aktie.");
		lblCanBuy.setForeground(Color.GRAY);
		lblCanBuy.setBounds(16, 93, 411, 16);
		panel_1.add(lblCanBuy);
		
		JLabel lblKb = new JLabel("K\u00F8b:");
		lblKb.setHorizontalAlignment(SwingConstants.TRAILING);
		lblKb.setBounds(16, 149, 58, 16);
		panel_1.add(lblKb);
		
		JLabel lblStk = new JLabel("stk.");
		lblStk.setBounds(369, 149, 58, 16);
		panel_1.add(lblStk);
		
		amount = new JTextField();
		amount.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		amount.setHorizontalAlignment(SwingConstants.CENTER);
		amount.setText("0");
		amount.setBounds(86, 133, 271, 48);
		panel_1.add(amount);
		amount.setColumns(10);

		this.setVisible(true);
		toFront();
	}
	
	private void buy() {
		this.bank.buyStock(stock, Integer.parseInt(this.amount.getText()));
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == btnBuy) {
			this.buy();
			ConnectionToPlayer.sendToOther.println("bs"+","+this.bank.getAmount());
			ConnectionToPlayer.sendToOther.flush();
			stPanel.updateView();
			this.dispose();
		}
		if (e.getSource() == btnCancel) {
			this.dispose();
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
		if (e.getSource() == this.btnBuy) {
			btnBuy.setBackground(ClientWindow.hoverGreen);
			btnBuy.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		if (e.getSource() == this.btnCancel) {
			btnCancel.setBackground(ClientWindow.hoverRed);
			btnCancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource() == this.btnBuy) {
			btnBuy.setBackground(ClientWindow.green);
		}
		if (e.getSource() == this.btnCancel) {
			btnCancel.setBackground(ClientWindow.red);
		}
	}
}
