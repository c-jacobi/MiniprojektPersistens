package ctrl;

import db.DataAccessException;
import db.SaleOrderDB;
import model.Customer;
import model.SaleOrder;

public class SaleOrderCtr implements SaleOrderCtrIF {
	private SaleOrderDB saleOrderDB;
	private CustomerCtr customerCtr;

	public SaleOrderCtr() throws DataAccessException {
		this.saleOrderDB = new SaleOrderDB();
	}

	@Override
	public SaleOrder findByOrderNo(int orderNo) throws DataAccessException {
		return saleOrderDB.findByOrderNo(orderNo);
	}

	@Override
	public SaleOrder saveOrder(SaleOrder saleOrder) throws DataAccessException {
		return saleOrderDB.saveOrder(saleOrder);
	}

	public Customer findCustomerById(int id) throws DataAccessException {
		return customerCtr.findById(id);
	}

	public Customer findCustomerByPhone(String phoneNo) throws DataAccessException {
		return customerCtr.findByPhone(phoneNo);
	}

	public Customer findCustomerByEmail(String email) throws DataAccessException {
		return customerCtr.findByEmail(email);
	}

}
