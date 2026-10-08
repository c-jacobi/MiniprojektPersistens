package db;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.SaleOrder;

public class SaleOrderDB implements SaleOrderDAO {
	private static final String SAVE_ORDER = "insert into saleOrder (orderNo, date, deliveryStatus, deliveryDate, discountGiven) values (?, ?, ?, ?, ?)";

	private PreparedStatement saveOrder;

	public SaleOrderDB() throws DataAccessException {
		try {
			saveOrder = DBConnection.getInstance().getConnection().prepareStatement(SAVE_ORDER);
		} catch (SQLException e) {
			throw new DataAccessException("Could not prepare statements", e);
		}
	}

	@Override
	public SaleOrder saveOrder(SaleOrder order) throws DataAccessException { // skal laves
		try {
			saveOrder.setInt(1, order.getOrderNo());
			saveOrder.setDate(2, Date.valueOf(order.getDate()));
			saveOrder.setString(3, order.getDeliveryStatus());
			saveOrder.setDate(4, Date.valueOf(order.getDeliveryDate()));
			saveOrder.setDouble(5, order.getDiscountGiven());
		} catch (SQLException e) {
			throw new DataAccessException("order failed to save.", e);
		}

		return order;
	}

}
