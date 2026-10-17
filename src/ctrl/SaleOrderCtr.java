package ctrl;

import java.time.LocalDate;

import db.DataAccessException;
import db.SaleOrderDB;
import model.Customer;
import model.Product;
import model.SaleOrder;
import model.SaleOrderLine;

public class SaleOrderCtr implements SaleOrderCtrIF {
	private SaleOrderDB saleOrderDB;
	private CustomerCtr customerCtr;
	private ProductCtr productCtr;
	private SaleOrder saleOrder;
	private int nextOrderNumber = 1001;

	public SaleOrderCtr() throws DataAccessException {
		this.saleOrderDB = new SaleOrderDB();
		this.customerCtr = new CustomerCtr();
		this.productCtr = new ProductCtr();
	}

	public SaleOrder createOrder() throws DataAccessException {
		int orderNumber = nextOrderNumber++;
		while (saleOrderDB.findByOrderNo(orderNumber) != null) {
			orderNumber++;
		}
		saleOrder = new SaleOrder(orderNumber, null, null, null, 0, null);
		return saleOrder;
	}

	public Customer findCustomerById(int id) throws DataAccessException {
		return customerCtr.findById(id);
	}

	public Customer findCustomerByPhone(String phoneNo) throws DataAccessException {
		return customerCtr.findByPhone(phoneNo);
	}

	public Customer findCustomerByEmail(String email) throws DataAccessException {
		return customerCtr.findByEmail(email);
	}

	public Product findProductByProductNo(int pNo) throws DataAccessException {
		return productCtr.findByProductNumber(pNo);
	}

	public void addSaleOrderLine(int productNumber, int qty) throws DataAccessException {
		Product product = productCtr.findByProductNumber(productNumber);

		if (product == null) {
			return;
		}

		SaleOrderLine newLine = new SaleOrderLine(product, qty);
		saleOrder.addSaleOrderLine(newLine);
	}

	@Override
	public boolean placeOrder(int customerId) throws DataAccessException {
		Customer customer = customerCtr.findById(customerId);

		if (customer == null) {
			return false;
		}

		saleOrder.setCustomer(customer);
		saleOrder.setDate(LocalDate.now());
		saleOrder.setDeliveryStatus("Pending");
		saleOrder.setDeliveryDate(LocalDate.now().plusDays(3));

		saleOrderDB.saveOrder(saleOrder);
		return true;
	}

	@Override
	public SaleOrder findByOrderNo(int orderNo) throws DataAccessException {
		return saleOrderDB.findByOrderNo(orderNo);
	}
}
