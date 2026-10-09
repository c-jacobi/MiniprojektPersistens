package ctrl;

import db.DataAccessException;
import model.SaleOrder;

public interface SaleOrderCtrIF {

	SaleOrder findByOrderNo(int orderNo) throws DataAccessException;

	SaleOrder placeOrder(SaleOrder order) throws DataAccessException;

}
