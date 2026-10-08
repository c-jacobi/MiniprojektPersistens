package model;

public class Equipment {

	private String material;
	private String style;

	public Equipment(String material, String style) {
		super();
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
