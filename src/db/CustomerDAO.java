package db;

import model.Customer;

public interface CustomerDAO {

	Customer findByPhone(String phone) throws DataAccessException;

	Customer findByEmail(String email) throws DataAccessException;

}
