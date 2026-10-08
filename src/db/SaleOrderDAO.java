package db;

import model.SaleOrder;

public interface SaleOrderDAO {

	SaleOrder saveOrder(int orderNo) throws DataAccessException;
}
