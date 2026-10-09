package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import db.CustomerDB;
import db.DataAccessException;
import db.SaleOrderDB;
import model.Customer;
import model.SaleOrder;

class SaleOrderDBTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
		DBCleanup.cleanDB();
	}

	@Test
	public void testFindByOrderNo() throws DataAccessException {
		int orderNo = 1111;
		try {
			SaleOrder s = new SaleOrderDB().findByOrderNo(orderNo);
			assertNotNull(s);
			assertEquals(orderNo, s.getOrderNo());
		} catch (DataAccessException e) {
			fail("Couldn't do findByProductNumber on product");
			e.printStackTrace();
		}
	}

	@Test
	public void testSaveOrder() throws DataAccessException {
		try {
			SaleOrderDB sDB = new SaleOrderDB();
			CustomerDB cDB = new CustomerDB();

			int orderId = 1112;
			LocalDate date = LocalDate.parse("2026-10-08");
			String deliveryStatus = "Shipped";
			LocalDate deliveryDate = LocalDate.parse("2026-10-14");
			Double discount = 27.5;
			Customer customer = cDB.findByPhone("723628362");

			SaleOrder saleOrder = new SaleOrder(orderId, date, deliveryStatus, deliveryDate, discount, customer);

			sDB.saveOrder(saleOrder);

			SaleOrder savedOrder = sDB.findByOrderNo(orderId);
			assertNotNull(savedOrder);

		} catch (DataAccessException e) {
			fail("Couldn't save the order");
			e.printStackTrace();
		}
	}

}
