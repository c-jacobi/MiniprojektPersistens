package db;

import java.util.List;

import model.SaleOrder;

public interface SaleOrderDAO {
	List<SaleOrder> findAll() throws DataAccessException;

	SaleOrder findByOrderNo(int orderNo) throws DataAccessException;
}
