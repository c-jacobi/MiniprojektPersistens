package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Clothing;
import model.Equipment;
import model.GunReplica;
import model.Product;

public class ProductDB implements ProductDAO {
	private static final String SELECT_ALL_Q = "select productNo, name, minStock, reservedStock, productType from product";
	private static final String FIND_BY_PRODUCT_NUMBER = SELECT_ALL_Q + " where productNo = ?";
	private static final String UPDATE_STOCK = "update product set reservedStock = ? where productNo = ?";

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
	public Product findByProductNumber(int productNo) throws DataAccessException {
		try {
			findByProductNumber.setInt(1, productNo);
			ResultSet rs = findByProductNumber.executeQuery();
			Product res = buildObject(rs);
			return res;
		} catch (SQLException e) {
			throw new DataAccessException("Could not find product by this product number.", e);
		}
	}

	@Override
	public void updateStock(Product p) throws DataAccessException {
		final int rstock = p.getReservedStock();
		final int prodNo = p.getProductNumber();
		try {
			updateStock.setInt(1, rstock);
			updateStock.setInt(2, prodNo);
			updateStock.executeUpdate();
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
				if (rs.getString("productType").equals("Clothing")) {
					p = new Clothing(rs.getInt("size"), rs.getString("colour"), rs.getInt("productNo"),
							rs.getString("name"), rs.getInt("minStock"), rs.getInt("reservedStock"),
							rs.getString("productType"));
				} else if (rs.getString("productType").equals("Equipment")) {
					p = new Equipment(rs.getString("material"), rs.getString("style"), rs.getInt("productNo"),
							rs.getString("name"), rs.getInt("minStock"), rs.getInt("reservedStock"),
							rs.getString("productType"));
				} else if (rs.getString("productType").equals("GunReplica")) {
					p = new GunReplica(rs.getInt("productNo"), rs.getString("name"), rs.getInt("minStock"),
							rs.getInt("reservedStock"), rs.getString("productType"), rs.getString("caliber"),
							rs.getString("material"));
				}
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result set for products.", e);
		}

		return p;
	}

}
