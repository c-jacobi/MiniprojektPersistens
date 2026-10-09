package ctrl;

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

	public SaleOrder createOrder() {
		int orderNumber = nextOrderNumber++;
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
		if (product != null) {

			SaleOrderLine ol = new SaleOrderLine(product, qty);

			this.saleOrder.addSaleOrderLine(ol);
		}
	}

	@Override
	public SaleOrder findByOrderNo(int orderNo) throws DataAccessException {
		return saleOrderDB.findByOrderNo(orderNo);
	}

	@Override
	public SaleOrder placeOrder(SaleOrder saleOrder) throws DataAccessException {
		return saleOrderDB.saveOrder(saleOrder);
	}

}
