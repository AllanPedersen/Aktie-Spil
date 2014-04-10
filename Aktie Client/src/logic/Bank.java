package logic;

import java.util.ArrayList;

public class Bank {
	
	private double amount;
	private ArrayList<Stock> stocks = new ArrayList<Stock>();
	
	public Bank(double amount) {
		this.amount = amount;
	}
	
	public double getAmount() {
		return this.amount;
	}
	
	/**
	 * 
	 * @param stock The stock the user wants to buy
	 * @param amount The amount of the stock the user wants to buy
	 * @return A boolean, true if succeeded, false if the bank doesn't have enough money
	 */
	public boolean buyStock(Stock stock, int amount) {
		
		
		return true;
	}
	
	/**
	 * 
	 * @param stock The stock the user wants to sell
	 * @param amount The amount of the stock the user wants to sell
	 * @return
	 */
	public boolean sellStock(Stock stock, int amount) {
		
		return true;
	}
	
	/**
	 * This method returns wether or not the user has any of the chosen stock in the bank
	 * @param stock The stock that will be cheched
	 * @return True if the bank contains stock of that type, false otherwise
	 */
	public boolean bankContainsStock(Stock stock) {
	
		return true;
	}
	
	/**
	 * This method returns a full list of all the stocks the user has bought
	 * @return
	 */
	public ArrayList<Stock> stocksBankContains() {
		return this.stocks;
	}

}
