package model;

public class Freight {
	private String metode;
	private boolean pickUp;
	private double freeThreshold;
	private Customer customer;
	
	public Freight(String metode, boolean pickUp, double freeTreshold) {
		this.metode = metode;
		this.pickUp = pickUp;
		this.freeThreshold = freeThreshold; //denne bliver ikke brugt. men vi beholder attribut 
		
	}
	
	public boolean IsPickup() {
		return pickUp;
	}
	
	public double calculateFreightCost(double orderTotal) {
	String customerType = customer.getCustomerType();
	double result = 0;
	if(customerType.equals("private") && orderTotal < 2000 	&& !pickUp) {
		result = 40;
	}
	
		return result;
	}

}
