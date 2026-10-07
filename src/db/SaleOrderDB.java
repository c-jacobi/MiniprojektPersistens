package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.SaleOrder;

public class SaleOrderDB implements SaleOrderDAO {
	private static final String SELECT_ALL_Q = "select orderno, date, deliveryStatus, deliveryDate, discountGiven from saleorders";
	private static final String FIND_BY_ORDER_NO = SELECT_ALL_Q + " where orderno = ?";

	private PreparedStatement findAll;
	private PreparedStatement findByOrderNo;

	public SaleOrderDB() throws DataAccessException {
		try {
			findAll = DBConnection.getInstance().getConnection().prepareStatement(SELECT_ALL_Q);
			findByOrderNo = DBConnection.getInstance().getConnection().prepareStatement(FIND_BY_ORDER_NO);
		} catch (SQLException e) {
			throw new DataAccessException("Could not prepare statements", e);
		}
	}

	@Override
	public List<SaleOrder> findAll() throws DataAccessException {
		try {
			ResultSet rs = findAll.executeQuery();
			List<SaleOrder> res = buildObjects(rs);
			return res;
		} catch (SQLException e) {
			throw new DataAccessException("Could not retrieve all orders.", e);
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

	private List<SaleOrder> buildObjects(ResultSet rs) throws DataAccessException {
		List<SaleOrder> res = new ArrayList<>();
		SaleOrder s = buildObject(rs);
		while (s != null) {
			res.add(s);
			s = buildObject(rs);
		}
		return res;
	}

	private SaleOrder buildObject(ResultSet rs) throws DataAccessException {
		SaleOrder s = null;
		try {
			if (rs.next()) {
				s = new SaleOrder(rs.getInt("orderno"), rs.getDate("date").toLocalDate(),
						rs.getString("deliveryStatus"), rs.getDate("deliveryDate").toLocalDate(),
						rs.getInt("discountGiven"));
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result set for sale orders.", e);
		}

		return s;
	}
}
