package model;

public class Product {

	private int productNumber;
	private String name;
	private int minStock;
	private int reservedStock;

	public Product(int productNumber, String name, int minStock, int reservedStock) {
		super();
		this.productNumber = productNumber;
		this.name = name;
		this.minStock = minStock;
		this.reservedStock = reservedStock;
	}

	public int getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(int productNumber) {
		this.productNumber = productNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMinStock() {
		return minStock;
	}

	public void setMinStock(int minStock) {
		this.minStock = minStock;
	}

	public int getReservedStock() {
		return reservedStock;
	}

	public void setReservedStock(int reservedStock) {
		this.reservedStock = reservedStock;
	}
}
