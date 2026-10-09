package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SaleOrder {

	private int orderNo;
	private LocalDate date;
	private String deliveryStatus;
	private LocalDate deliveryDate;
	private double discountGiven;
	private Customer customer;
	private List<SaleOrderLine> saleOrderLines;

	public SaleOrder(int orderNo, LocalDate date, String deliveryStatus, LocalDate deliveryDate, double discountGiven,
			Customer customer) {
		super();
		this.orderNo = orderNo;
		this.date = date;
		this.deliveryStatus = deliveryStatus;
		this.deliveryDate = deliveryDate;
		this.discountGiven = discountGiven;
		this.customer = customer;
		this.saleOrderLines = new ArrayList<>();
	}

	public double priceNoDiscount() {
		double totalPrice = 0.00;
		for (SaleOrderLine sol : saleOrderLines) {
			totalPrice += 500.00 * sol.getQuantity();
		}
		return totalPrice;
	}

	public double calculateDiscountedPrice(Customer customer) {
		double originalPrice = priceNoDiscount();
		double price = originalPrice;
		if (customer.getCustomerType().equals("CLUB") && originalPrice >= 1500) {
			price = originalPrice * 0.95;
		}
		discountGiven = originalPrice - price;
		return price;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public double getDiscountGiven() {
		return discountGiven;
	}

	public void setDiscountGiven(double discountGiven) {
		this.discountGiven = discountGiven;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void addSaleOrderLine(SaleOrderLine orderLine) {
		if (orderLine != null) {
			this.saleOrderLines.add(orderLine);
		}
	}

}
