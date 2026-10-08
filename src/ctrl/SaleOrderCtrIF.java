package ctrl;

import db.DataAccessException;
import model.SaleOrder;

public interface SaleOrderCtrIF {

	SaleOrder findByOrderNo(int orderNo) throws DataAccessException;

	SaleOrder saveOrder(SaleOrder order) throws DataAccessException;

}
