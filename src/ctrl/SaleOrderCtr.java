package ctrl;

import java.util.List;

import db.DataAccessException;
import db.SaleOrderDB;
import model.SaleOrder;

public class SaleOrderCtr implements SaleOrderCtrIF {
	private SaleOrderDB saleOrderDB;

	public SaleOrderCtr() throws DataAccessException {
		this.saleOrderDB = new SaleOrderDB();
	}

	@Override
	public List<SaleOrder> findAll() throws DataAccessException {
		return saleOrderDB.findAll();
	}

	@Override
	public SaleOrder findByOrderNo(int orderNo) throws DataAccessException {
		return saleOrderDB.findByOrderNo(orderNo);
	}

}
