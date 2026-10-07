package ctrl;

import java.util.List;

import org.junit.jupiter.api.Order;

import db.DataAccessException;

public interface SaleOrderCtrIF {
	List<Order> findAll() throws DataAccessException;

	Order findByOrderNo(int orderNo) throws DataAccessException;
	
	Order findByPhone(String phone) throws DataAccessException;
	
	Order findEmail(String email) throws DataAccessException; 
}
