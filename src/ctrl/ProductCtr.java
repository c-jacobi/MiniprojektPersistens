package ctrl;
import java.util.List;

import db.DataAccessException;
import db.ProductDB;
import model.Product;

public class ProductCtr  implements ProductCtrIF{
	private ProductDB productDB; 

	@Override
	public List<Product> findAll() throws DataAccessException {
		return productDB.findAll();
	}

	@Override
	public Product findByProductNumber(int ProductNumber) throws DataAccessException {
		return productDB.findByProductNumber(ProductNumber);
		
	}

}
