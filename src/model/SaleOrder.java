package model;

import java.time.LocalDate;

public class SaleOrder {

	private int orderNo;
	private LocalDate date;
	private String deliveryStatus;
	private LocalDate deliveryDate;
	private double discountGiven;

	public SaleOrder(int orderNo, LocalDate date, String deliveryStatus, LocalDate deliveryDate, double discountGiven) {
		super();
		this.orderNo = orderNo;
		this.date = date;
		this.deliveryStatus = deliveryStatus;
		this.deliveryDate = deliveryDate;
		this.discountGiven = discountGiven;
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

}
