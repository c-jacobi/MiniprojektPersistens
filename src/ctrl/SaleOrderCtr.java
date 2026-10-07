package ctrl;

import java.util.List;

import org.junit.jupiter.api.Order;

import db.DataAccessException;

public class SaleOrderCtr implements SaleOrderCtrIF{

	@Override
	public List<Order> findAll() throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order findByOrderNo(int orderNo) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order findByPhone(String phone) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order findEmail(String email) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}
	

}
