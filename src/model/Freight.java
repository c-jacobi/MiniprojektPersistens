package model;

public class Freight {
	private String method;
	private boolean pickUp;
	private double freeThreshold = 2500;
	private Customer customer;
	private double baseCost = 45;

	public Freight(String method, boolean pickUp, double freeThreshold) {
		this.method = method;
		this.pickUp = pickUp;
		this.freeThreshold = freeThreshold;

	}

	public boolean IsPickup() {
		return pickUp;
	}

	public double calculateFreightCost(double orderTotal) {
		int discountedCost = 0;
		String customerType = customer.getCustomerType();
		if (pickUp) {
			return discountedCost;
		} else if (customerType.equals("PRIVATE") && orderTotal >= freeThreshold && !pickUp) {
			return discountedCost;
		} else {
			return baseCost;
		}
	}

}
