package logic;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Bank {
	
	private double amount;
	private ArrayList<Stock> stocks = new ArrayList<Stock>();
	private static Bank instance;
	// TODO: Add fee for buying stocks
	
	private Bank(double amount) {
		this.amount = amount;
	}
	
	public static void instantiateBank(double amount) {
		instance = new Bank(amount);
	}
	
	public static Bank getInstance() {
		return instance;
	}
	
	public double getAmount() {
		return this.amount;
	}
	
	/**
	 * This method is used to buy stocks.
	 * @param stock The stock the user wants to buy
	 * @param amount The amount of the stock the user wants to buy
	 * @return A boolean, true if succeeded, false if the bank doesn't have enough money
	 */
	public boolean buyStock(Stock stock, int amount) {
		if (amount < 0) {
			JOptionPane.showMessageDialog(null, "Du kan ikke foretage en handel med et negativt nummer", "Antal ikke tilladt", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		double totalCost = stock.getValue() * amount;
		
		if (this.getAmount() >= totalCost) {
			// Check if stock already exists, then add to existing.
			if (this.bankContainsStock(stock)) {
				for (Stock st : stocks) {
					if (st.equals(stock)) {
						st.setBankAmount(st.getBankAmount() + amount);
						st.setBoughtValue(stock.getValue());
						break;
					}
				}
			} else {
				// Buy stocks
				stock.setBankAmount(amount);
				stock.setBoughtValue(stock.getValue());
				this.stocks.add(stock);
			}
			
			// Update bank amount
			this.amount = this.amount - totalCost;
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Du har ikke nok penge til at foretage handlen", "Ikke nok penge", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
	}
	
	/**
	 * This method sells stock
	 * @param stock The stock the user wants to sell
	 * @param amount The amount of the stock the user wants to sell
	 * @return
	 */
	public boolean sellStock(Stock stock, int amount) {
		if (!bankContainsStock(stock)) return false;
		
		for (Stock st : stocks) {
			if (st.equals(stock)) {
				// Check if amount is less than or equal to what we have.
				if (st.getBankAmount() >= amount) {
					// Update stock value
					st.updateValue();
					
					// Calculate 
					double val = st.getValue() * amount;
					
					// Update bank amount
					this.amount = this.amount + val;
					
					// Remove stocks from list
					st.setBankAmount(st.getBankAmount() - amount);
				} else {
					JOptionPane.showMessageDialog(null, "Du kan ikke foretage en handel med flere aktier end du ejer.", "Antal ikke tilladt", JOptionPane.WARNING_MESSAGE);
					return false;
				}
				
				// No need to loop any more..	
				break;
			}
		}
		
		return false;
	}
	
	/**
	 * This method returns wether or not the user has any of the chosen stock in the bank
	 * @param stock The stock that will be cheched
	 * @return True if the bank contains stock of that type, false otherwise
	 */
	public boolean bankContainsStock(Stock stock) {
		
		for (Stock st : stocks) {
			if (st.equals(stock)) return true;
		}
	
		return false;
	}
	
	/**
	 * This method returns a full list of all the stocks the user has bought
	 * @return
	 */
	public ArrayList<Stock> stocksBankContains() {
		return this.stocks;
	}
	
	/**
	 * This method sells all remaining stock at the current time.
	 * The method returns the value of the bank when everything is sold.
	 * @return double for bank value.
	 */
	public double endGame() {
		for (Stock st : stocks) {			
			// Calculate 
			double val = st.getValueNow() * st.getBankAmount();
			
			// Update bank amount
			this.amount = this.amount + val;
			
		}
		return this.amount;
	}

}
