package db;

import model.SaleOrder;

public interface SaleOrderDAO {

	SaleOrder saveOrder(SaleOrder order) throws DataAccessException;

	SaleOrder findByOrderNo(int orderNo) throws DataAccessException;
}
