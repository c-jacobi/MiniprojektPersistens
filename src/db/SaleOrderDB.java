package db;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Customer;
import model.SaleOrder;

public class SaleOrderDB implements SaleOrderDAO {
	private static final String SELECT_ALL_Q = "select orderNo, date, deliveryStatus, deliveryDate, discountGiven, customerId from saleOrder";
	private static final String SAVE_ORDER = "insert into saleOrder (orderNo, date, deliveryStatus, deliveryDate, discountGiven, customerId) values (?, ?, ?, ?, ?, ?)";
	private static final String FIND_BY_ORDER_NO = SELECT_ALL_Q + " where orderNo = ?";
	private PreparedStatement saveOrder;
	private PreparedStatement findByOrderNo;

	public SaleOrderDB() throws DataAccessException {
		try {
			saveOrder = DBConnection.getInstance().getConnection().prepareStatement(SAVE_ORDER);
			findByOrderNo = DBConnection.getInstance().getConnection().prepareStatement(FIND_BY_ORDER_NO);
		} catch (SQLException e) {
			throw new DataAccessException("Could not prepare statements", e);
		}
	}

	@Override
	public SaleOrder findByOrderNo(int orderNo) throws DataAccessException {
		try {
			findByOrderNo.setInt(1, orderNo);
			ResultSet rs = findByOrderNo.executeQuery();
			SaleOrder res = buildObject(rs);
			return res;
		} catch (SQLException e) {
			throw new DataAccessException("Could not find order by this order number.", e);
		}
	}

	@Override
	public SaleOrder saveOrder(SaleOrder order) throws DataAccessException {
		try {
			saveOrder.setInt(1, order.getOrderNo());
			saveOrder.setDate(2, Date.valueOf(order.getDate()));
			saveOrder.setString(3, order.getDeliveryStatus());
			saveOrder.setDate(4, Date.valueOf(order.getDeliveryDate()));
			saveOrder.setDouble(5, order.getDiscountGiven());
			saveOrder.setInt(6, order.getCustomer().getId());
			saveOrder.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException("order failed to save.", e);
		}

		return order;
	}

	private SaleOrder buildObject(ResultSet rs) throws DataAccessException, SQLException {
		SaleOrder s = null;

		try {
			if (rs.next()) {
				int customerId = rs.getInt("customerId");
				CustomerDB customerDB = new CustomerDB();
				Customer customer = customerDB.findById(customerId);
				s = new SaleOrder(rs.getInt("orderNo"), rs.getDate("date").toLocalDate(),
						rs.getString("deliveryStatus"), rs.getDate("deliveryDate").toLocalDate(),
						rs.getDouble("discountGiven"), customer);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result set for sale orders.", e);
		}

		return s;
	}

}
