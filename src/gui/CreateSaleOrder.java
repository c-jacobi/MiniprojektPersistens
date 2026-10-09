package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import ctrl.SaleOrderCtr;
import db.DataAccessException;
import model.Customer;
import model.Product;
import model.SaleOrder;
import model.SaleOrderLine;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import java.awt.Font;

public class CreateSaleOrder extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField idField;
	private JTextField nameField;
	private JTextField phoneField;
	private JTextField typeField;
	private JTable table;
	private DefaultTableModel tableModel;
	private SaleOrderCtr orderCtrl;
	private List<SaleOrderLine> orderLines;
	private JTextField cvrField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					CreateSaleOrder frame = new CreateSaleOrder();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws DataAccessException 
	 */
	public CreateSaleOrder() throws DataAccessException {
		orderCtrl = new SaleOrderCtr();
		orderLines = new ArrayList<>();

		setTitle("Create Offer");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Exits window
		setSize(1000, 700); // Window size
		setLocationRelativeTo(null); // Window opens in center of the screen

		// Creates main content panel
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBorder(new EmptyBorder(10, 20, 10, 20)); //Adding empty space between content and frame

		// Bottom panel containing action buttons
		JPanel southPanel = new JPanel();
		contentPane.add(southPanel, BorderLayout.SOUTH);
		
		GridBagLayout gbl_southPanel = new GridBagLayout(); // GridBagLayout is used to place buttons in specific positions
		gbl_southPanel.columnWidths = new int[] { 70, 70, 70, 275, 50, 0, 0 };
		gbl_southPanel.rowHeights = new int[] { 31, 0 };
		gbl_southPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_southPanel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		southPanel.setLayout(gbl_southPanel);

		// Deletes the selected order line
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteOrderLine();
			}
		});
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDelete.insets = new Insets(0, 0, 0, 5);
		gbc_btnDelete.gridx = 2;
		gbc_btnDelete.gridy = 0;
		southPanel.add(btnDelete, gbc_btnDelete);

		// Adds a product to the offer
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					addButtonClicked();
				} catch (DataAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAdd.insets = new Insets(0, 0, 0, 5);
		gbc_btnAdd.gridx = 0;
		gbc_btnAdd.gridy = 0;
		southPanel.add(btnAdd, gbc_btnAdd);

		// Edits the selected order line
		JButton btnEdite = new JButton("Edit");
		btnEdite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editOrderLine();
			}
		});
		GridBagConstraints gbc_btnEdite = new GridBagConstraints();
		gbc_btnEdite.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEdite.insets = new Insets(0, 0, 0, 5);
		gbc_btnEdite.gridx = 1;
		gbc_btnEdite.gridy = 0;
		southPanel.add(btnEdite, gbc_btnEdite);

		// Cancels the offer creation and closes the window
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelClicked();
			}
		});
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancel.gridx = 4;
		gbc_btnCancel.gridy = 0;
		southPanel.add(btnCancel, gbc_btnCancel);

		// Confirms and creates the offer
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					confirmButtonClicked();
				} catch (DataAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_btnConfirm = new GridBagConstraints();
		gbc_btnConfirm.gridx = 5;
		gbc_btnConfirm.gridy = 0;
		southPanel.add(btnConfirm, gbc_btnConfirm);

		// Top panel containing headings for product list and customer information
		JPanel northPanel = new JPanel();
		contentPane.add(northPanel, BorderLayout.NORTH);
		
		GridBagLayout gbl_northPanel = new GridBagLayout();
		gbl_northPanel.columnWidths = new int[] { 50, 300, 0, 0 };
		gbl_northPanel.rowHeights = new int[] { 21, 0 };
		gbl_northPanel.columnWeights = new double[] { 0.0, 100.0, 0.0, Double.MIN_VALUE };
		gbl_northPanel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		northPanel.setLayout(gbl_northPanel);
		northPanel.setBorder(new EmptyBorder(10, 20, 10, 20)); //Adding empty space between content and frame

		// Heading for product list
		JLabel lblProductList = new JLabel("Product list:");
		lblProductList.setFont(new Font("Arial", Font.BOLD, 14));
		
		GridBagConstraints gbc_lblProductList = new GridBagConstraints();
		gbc_lblProductList.anchor = GridBagConstraints.WEST;
		gbc_lblProductList.insets = new Insets(0, 0, 0, 5);
		gbc_lblProductList.gridx = 0;
		gbc_lblProductList.gridy = 0;
		northPanel.add(lblProductList, gbc_lblProductList);

		// Heading for customer information
		JLabel lblCustomerInfo = new JLabel("Customer info:");
		lblCustomerInfo.setFont(new Font("Arial", Font.BOLD, 14));
		
		GridBagConstraints gbc_lblCustomerInfo = new GridBagConstraints();
		gbc_lblCustomerInfo.anchor = GridBagConstraints.WEST;
		gbc_lblCustomerInfo.gridx = 2;
		gbc_lblCustomerInfo.gridy = 0;
		northPanel.add(lblCustomerInfo, gbc_lblCustomerInfo);

		// Right panel containing customer fields
		JPanel eastPanel = new JPanel();
		contentPane.add(eastPanel, BorderLayout.EAST);
		
		GridBagLayout gbl_eastPanel = new GridBagLayout();
		gbl_eastPanel.columnWidths = new int[] { 61, 61, 0 };
		gbl_eastPanel.rowHeights = new int[] { 16, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_eastPanel.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		gbl_eastPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		eastPanel.setLayout(gbl_eastPanel);
		eastPanel.setBorder(new EmptyBorder(10, 20, 10, 20)); //Adding empty space between content and frame

		// Customer ID input field
		JLabel lblId = new JLabel("Customer ID:");
		GridBagConstraints gbc_lblId = new GridBagConstraints();
		gbc_lblId.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblId.insets = new Insets(0, 0, 5, 5);
		gbc_lblId.gridx = 0;
		gbc_lblId.gridy = 0;
		eastPanel.add(lblId, gbc_lblId);

		idField = new JTextField();
		GridBagConstraints gbc_idField = new GridBagConstraints();
		gbc_idField.insets = new Insets(0, 0, 5, 0);
		gbc_idField.fill = GridBagConstraints.HORIZONTAL;
		gbc_idField.gridx = 1;
		gbc_idField.gridy = 0;
		eastPanel.add(idField, gbc_idField);
		idField.setColumns(10);

		// Finds customer based on entered customer ID
		JButton btnFind = new JButton("Find");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					findButtonClicked();
				} catch (DataAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_btnFind = new GridBagConstraints();
		gbc_btnFind.anchor = GridBagConstraints.EAST;
		gbc_btnFind.insets = new Insets(0, 0, 5, 0);
		gbc_btnFind.gridx = 1;
		gbc_btnFind.gridy = 1;
		eastPanel.add(btnFind, gbc_btnFind);

		// Displays name for private customers
		JLabel lblName = new JLabel("Name:");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 2;
		eastPanel.add(lblName, gbc_lblName);

		nameField = new JTextField();
		nameField.setEditable(false);// Field is filled automatically
		GridBagConstraints gbc_nameField = new GridBagConstraints();
		gbc_nameField.insets = new Insets(0, 0, 5, 0);
		gbc_nameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameField.gridx = 1;
		gbc_nameField.gridy = 2;
		eastPanel.add(nameField, gbc_nameField);
		nameField.setColumns(10);

		// Displays CVR number for commercial customers
		JLabel lblCvr = new JLabel("Email:");
		GridBagConstraints gbc_lblCvr = new GridBagConstraints();
		gbc_lblCvr.anchor = GridBagConstraints.EAST;
		gbc_lblCvr.insets = new Insets(0, 0, 5, 5);
		gbc_lblCvr.gridx = 0;
		gbc_lblCvr.gridy = 3;
		eastPanel.add(lblCvr, gbc_lblCvr);

		cvrField = new JTextField();
		cvrField.setEditable(false); // Field is filled automatically
		GridBagConstraints gbc_cvrField = new GridBagConstraints();
		gbc_cvrField.insets = new Insets(0, 0, 5, 0);
		gbc_cvrField.fill = GridBagConstraints.HORIZONTAL;
		gbc_cvrField.gridx = 1;
		gbc_cvrField.gridy = 3;
		eastPanel.add(cvrField, gbc_cvrField);
		cvrField.setColumns(10);

		// Displays customer's phone number
		JLabel lblPhone = new JLabel("Phone:");
		GridBagConstraints gbc_lblPhone = new GridBagConstraints();
		gbc_lblPhone.anchor = GridBagConstraints.EAST;
		gbc_lblPhone.insets = new Insets(0, 0, 5, 5);
		gbc_lblPhone.gridx = 0;
		gbc_lblPhone.gridy = 4;
		eastPanel.add(lblPhone, gbc_lblPhone);

		phoneField = new JTextField();
		phoneField.setEditable(false); // Field is filled automatically
		GridBagConstraints gbc_phoneField = new GridBagConstraints();
		gbc_phoneField.insets = new Insets(0, 0, 5, 0);
		gbc_phoneField.fill = GridBagConstraints.HORIZONTAL;
		gbc_phoneField.gridx = 1;
		gbc_phoneField.gridy = 4;
		eastPanel.add(phoneField, gbc_phoneField);
		phoneField.setColumns(10);

		// Displays whether the customer is private or commercial
		JLabel lblType = new JLabel("Customer Type:");
		GridBagConstraints gbc_lblType = new GridBagConstraints();
		gbc_lblType.anchor = GridBagConstraints.EAST;
		gbc_lblType.insets = new Insets(0, 0, 5, 5);
		gbc_lblType.gridx = 0;
		gbc_lblType.gridy = 5;
		eastPanel.add(lblType, gbc_lblType);

		typeField = new JTextField();
		typeField.setEditable(false); // Field is filled automatically
		GridBagConstraints gbc_typeField = new GridBagConstraints();
		gbc_typeField.insets = new Insets(0, 0, 5, 0);
		gbc_typeField.fill = GridBagConstraints.HORIZONTAL;
		gbc_typeField.gridx = 1;
		gbc_typeField.gridy = 5;
		eastPanel.add(typeField, gbc_typeField);
		typeField.setColumns(10);

		// Scroll pane containing the product table
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		// Table displaying products added to the offer
		table = new JTable();
		scrollPane.setViewportView(table);

		// Creates table model and prevents direct editing in the table
		tableModel = new DefaultTableModel(new Object[] { "Product", "Quantity", "Price" }, 0) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(tableModel);
	}

	/**
	 * Finds a customer by customer ID and displays customer information.
	 *
	 * @return true if customer was found, otherwise false
	 * @throws DataAccessException 
	 */
	private boolean findButtonClicked() throws DataAccessException {
		int customerID = Integer.parseInt(idField.getText());
		Customer customer = orderCtrl.findCustomerById(customerID);
		if (customer == null) {
			JOptionPane.showMessageDialog(CreateSaleOrder.this, "Error. Customer not found.");
			return false;
		}

				// Shows different information depending on customer type
			String name = customer.getName();
			String email = customer.getEmail();
			nameField.setText(name);
			cvrField.setText(email);
			typeField.setText("PRIVATE");
		

		String phone = customer.getPhone();
		phoneField.setText(phone);
		return true;
	}


	/**
	 * Confirms and creates the offer if a customer and products are registered.
	 * @throws DataAccessException 
	 */
	private void confirmButtonClicked() throws DataAccessException {
		SaleOrder order = orderCtrl.createOrder();

		int customerID = Integer.parseInt(idField.getText());
		Customer customer = orderCtrl.findCustomerById(customerID);

		if (customer == null) {
		    JOptionPane.showMessageDialog(CreateSaleOrder.this, "Error. Customer not found.");
		    return;
		}

		order.setCustomer(customer);

		for (SaleOrderLine line : orderLines) {
		    order.addSaleOrderLine(line);
		}

		order.setDeliveryStatus("Placed");
		order.setDate(LocalDate.now());
		order.setDeliveryDate(LocalDate.now());

		orderCtrl.placeOrder(order);
		clear();

	}
	
	/**
	 * Adds a product to the offer based on product barcode and quantity.
	 * @throws DataAccessException 
	 */
	private void addButtonClicked() throws DataAccessException {
		int productNumber =  Integer.parseInt(JOptionPane.showInputDialog(this, "Enter product number:"));

		Product product = orderCtrl.findProductByProductNo(productNumber);

		if (product == null) {
			JOptionPane.showMessageDialog(CreateSaleOrder.this, "Error. No product found.");
			return;
		}

		int qty;
		String qtyStr = JOptionPane.showInputDialog(this, "Enter quantity:");
		if (qtyStr == null) {
			return;
		}

		try {
			qty = Integer.parseInt(qtyStr);
		} catch (NumberFormatException n) {
			JOptionPane.showMessageDialog(CreateSaleOrder.this, "Error. Quantity must be a number.");
			return;
		}

		if (qty <= 0) {
			JOptionPane.showMessageDialog(CreateSaleOrder.this, "Error. Type a valid quantity.");
			return;
		}

		SaleOrderLine existingLine = findOrderLine(product);

		if (existingLine != null) {
			// Product exists

			// Calculate new quantity
			int newQty = existingLine.getQuantity() + qty;
			existingLine.setQuantity(newQty);

			// Get row index of the existing orderline
			int rowIndex = orderLines.indexOf(existingLine);

			// set new quantity value in table
			tableModel.setValueAt(newQty, rowIndex, 1);

		} else {
			// Product has not previously been added

			// Create new orderline
			SaleOrderLine newLine = new SaleOrderLine(product, qty);
			// Add orderline to list of orderlines
			orderLines.add(newLine);

			// Add orderline to table
			tableModel.addRow(new Object[] { product.getName(), qty});
		}
	}
	
	/**
	 * Finds an existing order line for a specific product.
	 *
	 * @param product the product to search for
	 * @return the matching order line, or null if not found
	 */
	private SaleOrderLine findOrderLine(Product product) {
		for (SaleOrderLine ol : orderLines) {
			if (ol.getProduct().equals(product)) {
				return ol;
			}
		}
		return null;
	}
	
	/**
	 * Deletes the selected order line from both list and table.
	 */
	private void deleteOrderLine() {
		int rowIndex = table.getSelectedRow();

		if (rowIndex == -1) {
			JOptionPane.showMessageDialog(this, "Please select an item from the list to delete.");
			return;
		}
		int choice = JOptionPane.showConfirmDialog(CreateSaleOrder.this, "Are you sure you want to delete this item?",
				"Delete Item", JOptionPane.YES_NO_OPTION);
		if (choice == JOptionPane.YES_OPTION) {
			orderLines.remove(rowIndex);
			tableModel.removeRow(rowIndex);
		}
	}

	/**
	 * Edits the quantity of the selected order line.
	 */
	private void editOrderLine() {
		int rowIndex = table.getSelectedRow();

		if (rowIndex == -1) {
			JOptionPane.showMessageDialog(this, "Please select an item from the list to edit.");
			return;
		}

		SaleOrderLine orderLine = orderLines.get(rowIndex);

		String qtyStr = JOptionPane.showInputDialog(this, "Enter new quantity: ", orderLine.getQuantity());

		if (qtyStr == null) {
			return; // When cancel is clicked for example
		}

		try {
			int newQty = Integer.parseInt(qtyStr);

			if (newQty <= 0) {
				JOptionPane.showMessageDialog(this, "Quantity must be greater than 0.");
				return;
			}

			// Update quantity in OrderLine
			orderLine.setQuantity(newQty);

			// Update quantity in Table
			tableModel.setValueAt(newQty, rowIndex, 1);

		} catch (NumberFormatException n) {
			JOptionPane.showMessageDialog(this, "Quantity must be a number.");
		}
	}

	/**
	 * Cancels offer creation and closes the window.
	 */
	private void cancelClicked() {
		this.dispose();
		this.setVisible(false);
	}

	/**
	 * Clears all input fields, order lines and table rows.
	 */
	private void clear() {
		idField.setText("");
		nameField.setText("");
		cvrField.setText("");
		phoneField.setText("");
		typeField.setText("");
		orderLines.clear();
		tableModel.setRowCount(0);
	}
}
