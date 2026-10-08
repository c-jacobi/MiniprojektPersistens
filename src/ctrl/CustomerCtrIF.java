package ctrl;



import db.DataAccessException;
import model.Customer;

public interface CustomerCtrIF {
	

	Customer findByEmail(String email) throws DataAccessException;
	
	Customer findByPhone(String phone) throws DataAccessException;

	
}
