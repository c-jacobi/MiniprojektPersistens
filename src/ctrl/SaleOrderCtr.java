package ctrl;

import db.DataAccessException;
import db.SaleOrderDB;
import model.SaleOrder;

public class SaleOrderCtr implements SaleOrderCtrIF {
	private SaleOrderDB saleOrderDB;

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

}
