package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.SaleOrder;

public class SaleOrderDB implements SaleOrderDAO {
	private static final String SELECT_ALL_Q = "select orderno, date, deliveryStatus, deliveryDate, discountGiven from saleorders";
	private static final String SAVE_ORDER = SELECT_ALL_Q + " "; // skal rettes

	private PreparedStatement saveOrder;

	public SaleOrderDB() throws DataAccessException {
		try {
			saveOrder = DBConnection.getInstance().getConnection().prepareStatement(SAVE_ORDER);
		} catch (SQLException e) {
			throw new DataAccessException("Could not prepare statements", e);
		}
	}

	@Override
	public SaleOrder saveOrder(int orderNo) throws DataAccessException { // skal laves
		// TODO Auto-generated method stub
		return null;
	}

}
