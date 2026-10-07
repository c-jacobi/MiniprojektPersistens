package db;

import java.util.List;

import model.Product;

public interface ProductDAO {
	List<Product> findAll() throws DataAccessException;

	Product findByProductNumber(int productNumber) throws DataAccessException;

}
