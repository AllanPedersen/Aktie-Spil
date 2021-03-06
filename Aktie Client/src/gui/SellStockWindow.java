package gui;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import logic.Bank;
import logic.Stock;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class SellStockWindow extends JFrame implements MouseListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel btnSell, btnCancel;
	private JTextField amount;
	private Stock stock;
	private Bank bank;
	private StockPanel stPanel;
	private JLabel lblSaldo;

	public SellStockWindow(Stock stock, StockPanel stPan) {
		super();
		this.stock = stock;
		this.stock.updateValue();
		this.bank = Bank.getInstance();
		this.stPanel = stPan;
		this.setResizable(false);
		this.setSize(444, 357);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setVgap(8);
		panel.setBackground(Color.DARK_GRAY);
		getContentPane().add(panel, BorderLayout.NORTH);

		JLabel lblInvitationModtaget = new JLabel("S\u00E6lg aktier");
		lblInvitationModtaget.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblInvitationModtaget.setForeground(Color.WHITE);
		panel.add(lblInvitationModtaget);

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);

		btnSell = new JPanel();
		btnSell.addMouseListener(this);
		btnSell.setBackground(ClientWindow.green);
		FlowLayout fl_btnSell = (FlowLayout) btnSell.getLayout();
		fl_btnSell.setVgap(11);
		btnSell.setBounds(16, 183, 411, 39);
		panel_1.add(btnSell);

		JLabel lblAccepter = new JLabel("S\u00E6lg Aktier");
		btnSell.add(lblAccepter);

		btnCancel = new JPanel();
		btnCancel.addMouseListener(this);
		btnCancel.setBackground(ClientWindow.red);
		FlowLayout fl_btnCancel = (FlowLayout) btnCancel.getLayout();
		fl_btnCancel.setVgap(11);
		btnCancel.setBounds(16, 234, 411, 39);
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
		
		JLabel lblAsk = new JLabel("UD $" + this.stock.getValueNow());
		lblAsk.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblAsk.setBounds(75, 47, 352, 16);
		panel_1.add(lblAsk);
		
		JLabel lblKurs = new JLabel("Kurs:");
		lblKurs.setBounds(16, 47, 47, 16);
		panel_1.add(lblKurs);
		
		lblSaldo = new JLabel("");
		lblSaldo.setForeground(Color.GRAY);
		lblSaldo.setBounds(16, 155, 411, 16);
		panel_1.add(lblSaldo);
		
		JLabel lblKb = new JLabel("S\u00E6lg:");
		lblKb.setHorizontalAlignment(SwingConstants.TRAILING);
		lblKb.setBounds(16, 103, 58, 16);
		panel_1.add(lblKb);
		
		JLabel lblStk = new JLabel("stk.");
		lblStk.setBounds(369, 103, 58, 16);
		panel_1.add(lblStk);
		
		amount = new JTextField();
		amount.addKeyListener(this);
		amount.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		amount.setHorizontalAlignment(SwingConstants.CENTER);
		amount.setText("0");
		amount.setBounds(86, 87, 271, 48);
		panel_1.add(amount);
		amount.setColumns(10);

		this.setVisible(true);
		toFront();
	}
	
	public void sell() {
		bank.sellStock(stock, Integer.parseInt(amount.getText()));
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == btnSell) {
			sell();
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
		if (e.getSource() == this.btnSell) {
			btnSell.setBackground(ClientWindow.hoverGreen);
			btnSell.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		if (e.getSource() == this.btnCancel) {
			btnCancel.setBackground(ClientWindow.hoverRed);
			btnCancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource() == this.btnSell) {
			btnSell.setBackground(ClientWindow.green);
		}
		if (e.getSource() == this.btnCancel) {
			btnCancel.setBackground(ClientWindow.red);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getSource() == amount) {
			int amountField = 0;
			try {
				amountField = Integer.parseInt(amount.getText());
			} catch (Exception e2) {
			}
			double val = this.stock.getValue();
			double total = amountField * val;
			if (amountField > this.stock.getBankAmount()) {
				lblSaldo.setText("Du kan max saelge " + this.stock.getBankAmount() + " af denne aktie.");
			} else {
				lblSaldo.setText("Du f\u00E5r US $"+Math.round(total)+" for at s\u00E6lge " + amountField + " af denne aktie");
			}
		}
	}
}
