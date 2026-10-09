package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import db.DataAccessException;
import db.ProductDB;
import model.Product;

class ProductDBTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	public void testFindByProductNumber() throws DataAccessException {
		int pnumber = 12345;
		try {
			Product p = new ProductDB().findByProductNumber(pnumber);
			assertNotNull(p);
			assertEquals(pnumber, p.getProductNumber());
		} catch (DataAccessException e) {
			fail("Couldn't do findByProductNumber on product");
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdateReservedStock() throws DataAccessException {
		int pNo = 12345;
		try {
			ProductDB pDB = new ProductDB();
			Product currentP = pDB.findByProductNumber(pNo);

			int newStock = 5;

			currentP.setReservedStock(newStock);
			pDB.updateStock(currentP);

			Product newP = pDB.findByProductNumber(pNo);
			assertEquals(newStock, newP.getReservedStock());

			// revert back to original stock
			int originalStock = 3;
			newP.setReservedStock(originalStock);
			pDB.updateStock(newP);

		} catch (DataAccessException e) {
			fail("Couldn't update reserved stock on the product");
			e.printStackTrace();
		}
	}

}
