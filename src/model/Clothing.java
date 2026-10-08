package model;

public class Clothing extends Product{

	private int size;
	private String colour;

	public Clothing(int size, String colour, int productNumber, String name, int minStock, int reservedStock, String productType) {
		super(productNumber, name, minStock, reservedStock, productType);
		this.size = size;
		this.colour = colour;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}
}