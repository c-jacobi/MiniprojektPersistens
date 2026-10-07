package ctrl;

import java.util.List;

import db.DataAccessException;
import model.Customer;

public interface CustomerCtrIF {
	List<Customer> findAll() throws DataAccessException;

	Customer findByEmail(String email) throws DataAccessException;
	
	Customer findByPhone(String phone) throws DataAccessException;

	
}
