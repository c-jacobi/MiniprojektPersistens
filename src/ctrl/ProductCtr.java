package ctrl;


import db.DataAccessException;
import db.ProductDB;
import model.Product;

public class ProductCtr  implements ProductCtrIF{
	private ProductDB productDB; 

	
	

	@Override
	public Product findByProductNumber(int productNumber) throws DataAccessException {
		return productDB.findByProductNumber(productNumber);
		
	}




	
	}


