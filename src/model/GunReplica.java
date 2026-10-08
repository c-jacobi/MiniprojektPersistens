package model;

public class GunReplica extends Product {

	private String calibre;
	private String material;

	public GunReplica(int productNumber, String name, int minStock,
			int reservedStock, String productType, String calibre, String material) {
		super(productNumber, name, minStock, reservedStock, productType);
		this.calibre = calibre;
		this.material = material;
	}

	public String getCalibre() {
		return calibre;
	}

	public void setCalibre(String calibre) {
		this.calibre = calibre;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}
}
