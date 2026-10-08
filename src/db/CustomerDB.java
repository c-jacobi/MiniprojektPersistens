package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Customer;

public class CustomerDB implements CustomerDAO {
	private static final String SELECT_ALL_Q = "select customerId, name, address, zipCodeCity, phoneNo, email, customerType from customer";
	private static final String FIND_BY_PHONE = SELECT_ALL_Q + " where phoneNo = ?";
	private static final String FIND_BY_EMAIL = SELECT_ALL_Q + " where email = ?";

	private PreparedStatement findByPhone;
	private PreparedStatement findByEmail;

	public CustomerDB() throws DataAccessException {
		try {
			findByPhone = DBConnection.getInstance().getConnection().prepareStatement(FIND_BY_PHONE);
			findByEmail = DBConnection.getInstance().getConnection().prepareStatement(FIND_BY_EMAIL);
		} catch (SQLException e) {
			throw new DataAccessException("Could not prepare statements", e);
		}
	}

	@Override
	public Customer findByPhone(String phone) throws DataAccessException {
		try {
			findByPhone.setString(1, phone);
			ResultSet rs = findByPhone.executeQuery();
			Customer res = buildObject(rs);
			return res;
		} catch (SQLException e) {
			throw new DataAccessException("Could not find customer by this phone number.", e);
		}
	}

	@Override
	public Customer findByEmail(String email) throws DataAccessException {
		try {
			findByEmail.setString(1, email);
			ResultSet rs = findByEmail.executeQuery();
			Customer res = buildObject(rs);
			return res;
		} catch (SQLException e) {
			throw new DataAccessException("Could not find customer by this email.", e);
		}
	}

	private Customer buildObject(ResultSet rs) throws DataAccessException {
		Customer c = null;
		try {
			if (rs.next()) {
				c = new Customer(rs.getInt("customerId"), rs.getString("name"), rs.getString("address"),
						rs.getString("zipCodeCity"), rs.getString("phoneNo"), rs.getString("email"),
						rs.getString("customerType"));
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result set for customers.", e);
		}

		return c;
	}

}
