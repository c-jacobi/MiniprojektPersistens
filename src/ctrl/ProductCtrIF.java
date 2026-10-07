package ctrl;

import java.util.List;

import db.DataAccessException;
import model.Product;

public interface ProductCtrIF {
	
	List<Product> findAll() throws DataAccessException;

	Product findByProductNumber(int productNumber) throws DataAccessException;
	
	

	
}


