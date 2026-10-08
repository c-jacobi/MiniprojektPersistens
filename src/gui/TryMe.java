package gui;

import db.CustomerDB;
import db.ProductDB;
import db.DataAccessException;
import model.Customer;
import model.Product;

public class TryMe {

	/**
	 * Instantiates a new try me.
	 */
	//public TryMe() {

	/**
	 * Creates the test data.
	 */
		public void createTestData() {

        try {
        	
            // Test CustomerDB
            CustomerDB customerDB = new CustomerDB();

            Customer customer = customerDB.findByPhone("12345678");

            if (customer != null) {
                System.out.println("=== Kunde fundet ===");
                System.out.println(customer);
            } else {
                System.out.println("Ingen kunde fundet med dette telefonnummer.");
            }

            // Test ProductDB
            ProductDB productDB = new ProductDB();

            Product product = productDB.findByProductNumber(1001);

            if (product != null) {
                System.out.println("\n=== Produkt fundet ===");
                System.out.println(product);
            } else {
                System.out.println("Intet produkt fundet med dette produktnummer.");
            }

        } catch (DataAccessException e) {
            System.out.println("Fejl ved adgang til databasen:");
            e.printStackTrace();
        }
    }
}