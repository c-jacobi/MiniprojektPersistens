package model;

import java.time.LocalDate;

public class SaleOrder {

	private int orderNo;
	private LocalDate date;
	private String deliveryStatus;
	private LocalDate deliveryDate;
	private int discountGiven;

	public SaleOrder(int orderNo, LocalDate date, String deliveryStatus, LocalDate deliveryDate, int discountGiven) {
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

	public void setDeliveryTime(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public int getDiscountGiven() {
		return discountGiven;
	}

	public void setDiscountGiven(int discountGiven) {
		this.discountGiven = discountGiven;
	}

}
