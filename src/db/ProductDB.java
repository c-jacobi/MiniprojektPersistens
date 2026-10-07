package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Product;

public class ProductDB implements ProductDAO {
	private static final String SELECT_ALL_Q = "select productNumber, name, minStock, reservedStock from products";
	private static final String FIND_BY_PRODUCT_NUMBER = SELECT_ALL_Q + " where productNumber = ?";

	private PreparedStatement selectAll;
	private PreparedStatement findByProductNumber;

	public ProductDB() throws DataAccessException {
		try {
			selectAll = DBConnection.getInstance().getConnection().prepareStatement(SELECT_ALL_Q);
			findByProductNumber = DBConnection.getInstance().getConnection().prepareStatement(FIND_BY_PRODUCT_NUMBER);
		} catch (SQLException e) {
			throw new DataAccessException("Could not prepare statements", e);
		}
	}

	@Override
	public List<Product> findAll() throws DataAccessException {
		try {
			ResultSet rs = selectAll.executeQuery();
			List<Product> res = buildObjects(rs);
			return res;
		} catch (SQLException e) {
			throw new DataAccessException("Could not retrieve all products.", e);
		}
	}

	@Override
	public Product findByProductNumber(String productNumber) throws DataAccessException {
		try {
			findByProductNumber.setString(1, productNumber);
			ResultSet rs = findByProductNumber.executeQuery();
			Product res = buildObject(rs);
			return res;
		} catch (SQLException e) {
			throw new DataAccessException("Could not find product by this product number.", e);
		}
	}

	private List<Product> buildObjects(ResultSet rs) throws DataAccessException {
		List<Product> res = new ArrayList<>();
		Product p = buildObject(rs);
		while (p != null) {
			res.add(p);
			p = buildObject(rs);
		}
		return res;
	}

	private Product buildObject(ResultSet rs) throws DataAccessException {
		Product p = null;
		try {
			if (rs.next()) {
				p = new Product(rs.getInt("productNumber"), rs.getString("name"), rs.getInt("minStock"),
						rs.getInt("reservedStock"));
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result set for products.", e);
		}

		return p;
	}

}
