package ctrl;

import db.DataAccessException;
import model.Customer;

public interface CustomerCtrIF {

	Customer findById(int id) throws DataAccessException;

	Customer findByPhone(String phone) throws DataAccessException;

	Customer findByEmail(String email) throws DataAccessException;

}
