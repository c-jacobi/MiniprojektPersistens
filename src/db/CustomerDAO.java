package db;

import java.util.List;

import model.Customer;

public interface CustomerDAO {
	List<Customer> findAll() throws DataAccessException;

	Customer findByPhone(String phone) throws DataAccessException;

	Customer findByEmail(String email) throws DataAccessException;

}
