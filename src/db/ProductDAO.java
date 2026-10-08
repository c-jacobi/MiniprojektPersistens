package db;

import model.Product;

public interface ProductDAO {
	Product findByProductNumber(int productNumber) throws DataAccessException;

	void updateStock(Product p) throws DataAccessException;

}
