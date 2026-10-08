package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import db.CustomerDB;
import db.DataAccessException;
import model.Customer;

class CustomerDBTest {

	@BeforeEach
	void setUp() throws Exception {
		
	}

	@AfterEach
	void tearDown() throws Exception {
		
	}

	@Test
	public void testFindByPhone() throws DataAccessException {
		String phone = "723628362";
		try {
			Customer c = new CustomerDB().findByPhone(phone);
			assertNotNull(c);
			assertEquals(phone, c.getPhone());
		} catch (DataAccessException e) {
			fail("Couldn't do findByPhone on Customer");
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindByEmail() throws DataAccessException {
		String email = "oliver@hotmail.com";
		try {
			Customer c = new CustomerDB().findByEmail(email);
			assertNotNull(c);
			assertEquals(email, c.getEmail());
		} catch (DataAccessException e) {
			fail("Couldn't do findByEmail on Customer");
			e.printStackTrace();
		}
	}

}
