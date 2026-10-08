package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Product;

public class ProductDB implements ProductDAO {
	private static final String SELECT_ALL_Q = "select productNumber, name, minStock, reservedStock from products";
	private static final String FIND_BY_PRODUCT_NUMBER = SELECT_ALL_Q + " where productNumber = ?";
	private static final String UPDATE_STOCK = SELECT_ALL_Q + FIND_BY_PRODUCT_NUMBER;

	private PreparedStatement findByProductNumber;
	private PreparedStatement updateStock;

	public ProductDB() throws DataAccessException {
		try {
			findByProductNumber = DBConnection.getInstance().getConnection().prepareStatement(FIND_BY_PRODUCT_NUMBER);
			updateStock = DBConnection.getInstance().getConnection().prepareStatement(UPDATE_STOCK);
		} catch (SQLException e) {
			throw new DataAccessException("Could not prepare statements", e);
		}
	}

	@Override
	public Product findByProductNumber(int productNumber) throws DataAccessException {
		try {
			findByProductNumber.setInt(1, productNumber);
			ResultSet rs = findByProductNumber.executeQuery();
			Product res = buildObject(rs);
			return res;
		} catch (SQLException e) {
			throw new DataAccessException("Could not find product by this product number.", e);
		}
	}

	@Override
	public void updateStock(Product p) throws DataAccessException { // skal laves
		final int rstock = p.getReservedStock();
		try {
			updateStock.setInt(4, rstock);
		} catch (SQLException e) {
			throw new DataAccessException("Could not update reserved stock to:" + rstock, e);
		}
	}

//	private List<Product> buildObjects(ResultSet rs) throws DataAccessException {
//		List<Product> res = new ArrayList<>();
//		Product p = buildObject(rs);
//		while (p != null) {
//			res.add(p);
//			p = buildObject(rs);
//		}
//		return res;
//	}

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
