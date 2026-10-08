package model;

public class Equipment extends Product {

	private String material;
	private String style;

	public Equipment(String material, String style, int productNumber, String name, int minStock, int reservedStock, String productType) {
		super(productNumber, name, minStock, reservedStock, productType);
		this.material = material;
		this.style = style;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}
}
