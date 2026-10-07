package ctrl;

import java.util.List;


import db.DataAccessException;
import model.SaleOrder;

public interface SaleOrderCtrIF {
	List<SaleOrder> findAll() throws DataAccessException;

	SaleOrder findByOrderNo(int orderNo) throws DataAccessException;
	
}
