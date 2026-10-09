package ctrl;

import db.DataAccessException;
import db.ProductDB;
import model.Product;

public class ProductCtr implements ProductCtrIF {
	private ProductDB productDB;

	public ProductCtr() throws DataAccessException {
		this.productDB = new ProductDB();
	}

	@Override
	public Product findByProductNumber(int productNumber) throws DataAccessException {
		return productDB.findByProductNumber(productNumber);

	}

	@Override
	public void updateStock(Product p) throws DataAccessException {
		productDB.updateStock(p);

	}

}
