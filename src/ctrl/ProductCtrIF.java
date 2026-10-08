package ctrl;

import db.DataAccessException;
import model.Product;

public interface ProductCtrIF {

	Product findByProductNumber(int productNumber) throws DataAccessException;

	void updateStock(Product p) throws DataAccessException;

}
