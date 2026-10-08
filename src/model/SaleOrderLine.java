package model;

public class SaleOrderLine {

	private Product product;
	private int quantity;

	/**
	 * Instantiates a new order line.
	 *
	 * @param product  the chosen product
	 * @param quantity the quantity of the product
	 */
	public SaleOrderLine(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}

	/**
	 * Gets the product.
	 *
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * Gets the quantity.
	 *
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Sets the quantity.
	 * 
	 * @param quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
