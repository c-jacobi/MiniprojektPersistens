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
	public SaleOrder saveOrder(int orderNo) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	
	

	
	}


