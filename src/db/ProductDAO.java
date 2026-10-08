package db;

import model.Product;

public interface ProductDAO {
	Product findByProductNumber(int productNumber) throws DataAccessException;

	Product updateStock() throws DataAccessException;

}
