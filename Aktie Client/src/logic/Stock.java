package logic;

public class Stock {
	
	private String name;
	private double value;
	
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
		// TODO: Get value online
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
}
