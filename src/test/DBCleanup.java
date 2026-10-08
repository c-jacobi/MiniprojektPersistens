package test;

import java.sql.SQLException;

import db.DBConnection;
import db.DataAccessException;

public class DBCleanup {
	public static void main(String[] args) throws SQLException, DataAccessException {
		cleanDB(); // call to the utility class that resets the database
		System.out.println("cleaned");
	}

	public static void cleanDB() throws SQLException, DataAccessException {
		e("delete from saleOrder where orderNo = 1112;");
	}

	private static void e(String sql) throws SQLException, DataAccessException {
		DBConnection.getInstance().getConnection().createStatement().executeUpdate(sql);
	}

}
