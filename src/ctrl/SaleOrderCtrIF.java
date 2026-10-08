package ctrl;

import java.util.List;


import db.DataAccessException;
import model.SaleOrder;

public interface SaleOrderCtrIF {
	SaleOrder saveOrder(int orderNo) throws DataAccessException;
	
}
