package model;

public class SaleOrder {

	private int orderNo;
	private String datetime;
	private Boolean deliveryStatus;
	private String deliveryTime;
	private int discountGiven;
	private int invoiceId;

	public SaleOrder(int orderNo, String datetime, Boolean deliveryStatus, String deliveryTime, int discountGiven,
			int invoiceId) {
		super();
		this.orderNo = orderNo;
		this.datetime = datetime;
		this.deliveryStatus = deliveryStatus;
		this.deliveryTime = deliveryTime;
		this.discountGiven = discountGiven;
		this.invoiceId = invoiceId;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public Boolean getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(Boolean deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public String getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public int getDiscountGiven() {
		return discountGiven;
	}

	public void setDiscountGiven(int discountGiven) {
		this.discountGiven = discountGiven;
	}

	public int getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}
}
