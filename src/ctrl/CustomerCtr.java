package ctrl;

import db.DataAccessException;
import db.CustomerDB;
import model.Customer;

public class CustomerCtr implements CustomerCtrIF {
	private CustomerDB customerDB;

	public CustomerCtr() throws DataAccessException {
		this.customerDB = new CustomerDB();

	}

	@Override
	public Customer findById(int id) throws DataAccessException {
		return customerDB.findById(id);
	}

	@Override
	public Customer findByEmail(String email) throws DataAccessException {
		return customerDB.findByEmail(email);
	}

	@Override
	public Customer findByPhone(String phone) throws DataAccessException {
		return customerDB.findByPhone(phone);

	}

}
