package model;

public class GunReplica {

	private String calibre;
	private String material;

	public GunReplica (String calibre, String material) {
		super();
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
