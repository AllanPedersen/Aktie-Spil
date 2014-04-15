package logic;

import xml.Parser;

public class Stock {
	
	private String name, symbol;
	private double value;
	private int bankAmount = 0;
	private double boughtValue = 0;
	
	public Stock(String name, double value) {
		this.setName(name);
		this.setValue(value);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getSymbol() {
		return this.symbol;
	}
	
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
	
	// Methods that can be called
	/**
	 * This method checks the value online and updates the value
	 */
	public void updateValue() {
		this.value = this.getValueNow();
	}
	
	/**
	 * This method returns the value of the stock right now
	 */
	public double getValueNow() {
		double newV = 0;
		
		newV = Parser.getValueFromSymbol(this.symbol);
		
		return newV;
	}
	
	public boolean equals(Object other) {
		if (this == other) return true;
		
		Stock ot = (Stock) other;
		
		if (this.name.equals(ot.name)) {
			return true;
		} else {
			return false;
		}
	}
	
	public int getBankAmount() {
		return this.bankAmount;
	}
	
	public void setBankAmount(int amount) {
		this.bankAmount = amount;
	}
	
	public void setBoughtValue(double val) {
		this.boughtValue = val;
	}
	
	public double getBoughtValue() {
		return this.boughtValue;
	}
}
