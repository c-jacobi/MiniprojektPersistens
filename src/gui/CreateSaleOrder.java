package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import ctrl.*;
import model.*;
import db.*;

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
	private JTextField addressField;
	private JTextField zipCodeCityField;
	private JTextField emailField;
	private JTable table;
	private DefaultTableModel tableModel;
	private SaleOrderCtr saleOrderCtr;
	private JCheckBox deliveryCheckBox;
	private List<SaleOrderLine> saleOrderLines;
	//private SaleOrderCtrIF saleOrderCtrIF;
	private ProductCtr productCtr;
	private int productNumber;
	//private CreateSaleOrder customerDB;
	private String productNumberStr;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TryMe tryMe = new TryMe(); 	// Creates test data before the GUI is shown
					tryMe.createTestData();
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
	 */
	public CreateSaleOrder() {
		//saleOrderCtrIF = new SaleOrderCtrIF();
		saleOrderCtr = new SaleOrderCtr();
		saleOrderLines = new ArrayList<>();
		productCtr = new ProductCtr();

		setTitle("Create Sale");
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

		// Adds a product to the order
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addButtonClicked();
			}
		});
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAdd.insets = new Insets(0, 0, 0, 5);
		gbc_btnAdd.gridx = 0;
		gbc_btnAdd.gridy = 0;
		southPanel.add(btnAdd, gbc_btnAdd);

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
				confirmButtonClicked();
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
		
		
		JLabel lblId = new JLabel("Customer ID:");
		GridBagConstraints gbc_lblId = new GridBagConstraints();
		gbc_lblId.anchor = GridBagConstraints.EAST;
		gbc_lblId.insets = new Insets(0, 0, 5, 5);
		gbc_lblId.gridx = 1;
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

		
		// Customer Name input field
		JLabel lblName = new JLabel("Name:");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 0;
		eastPanel.add(lblName, gbc_lblName);
		
		nameField = new JTextField();
		GridBagConstraints gbc_nameField = new GridBagConstraints();
		gbc_nameField.insets = new Insets(0, 0, 5, 0);
		gbc_nameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameField.gridx = 1;
		gbc_nameField.gridy = 0;
		eastPanel.add(nameField, gbc_nameField);
		nameField.setColumns(10);
		
		//
		JLabel lblAddress = new JLabel("Address:");
		GridBagConstraints gbc_lblAddress = new GridBagConstraints();
		gbc_lblAddress.anchor = GridBagConstraints.EAST;
		gbc_lblAddress.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddress.gridx = 0;
		gbc_lblAddress.gridy = 1;
		eastPanel.add(lblAddress, gbc_lblAddress);

		addressField = new JTextField();
		GridBagConstraints gbc_addressField = new GridBagConstraints();
		gbc_addressField.insets = new Insets(0, 0, 5, 0);
		gbc_addressField.fill = GridBagConstraints.HORIZONTAL;
		gbc_addressField.gridx = 1;
		gbc_addressField.gridy = 1;
		eastPanel.add(addressField, gbc_addressField);
		addressField.setColumns(10);
		
		JLabel lblZipCodeCity = new JLabel("Zip/City:");
		GridBagConstraints gbc_lblZipCodeCity = new GridBagConstraints();
		gbc_lblZipCodeCity.anchor = GridBagConstraints.EAST;
		gbc_lblZipCodeCity.insets = new Insets(0, 0, 5, 5);
		gbc_lblZipCodeCity.gridx = 0;
		gbc_lblZipCodeCity.gridy = 2;
		eastPanel.add(lblZipCodeCity, gbc_lblZipCodeCity);

		zipCodeCityField = new JTextField();
		GridBagConstraints gbc_zipCodeCityField = new GridBagConstraints();
		gbc_zipCodeCityField.insets = new Insets(0, 0, 5, 0);
		gbc_zipCodeCityField.fill = GridBagConstraints.HORIZONTAL;
		gbc_zipCodeCityField.gridx = 1;
		gbc_zipCodeCityField.gridy = 2;
		eastPanel.add(zipCodeCityField, gbc_zipCodeCityField);
		zipCodeCityField.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email:");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.EAST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 0;
		gbc_lblEmail.gridy = 3;
		eastPanel.add(lblEmail, gbc_lblEmail);

		emailField = new JTextField();
		GridBagConstraints gbc_emailField = new GridBagConstraints();
		gbc_emailField.insets = new Insets(0, 0, 5, 0);
		gbc_emailField.fill = GridBagConstraints.HORIZONTAL;
		gbc_emailField.gridx = 1;
		gbc_emailField.gridy = 3;
		eastPanel.add(emailField, gbc_emailField);
		emailField.setColumns(10);

		// Displays customer's phone number
		JLabel lblPhone = new JLabel("Phone:");
		GridBagConstraints gbc_lblPhone = new GridBagConstraints();
		gbc_lblPhone.anchor = GridBagConstraints.EAST;
		gbc_lblPhone.insets = new Insets(0, 0, 5, 5);
		gbc_lblPhone.gridx = 0;
		gbc_lblPhone.gridy = 4;
		eastPanel.add(lblPhone, gbc_lblPhone);

		phoneField = new JTextField();
		phoneField.setEditable(true); // Field is filled automatically
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
		typeField.setEditable(true); // Field is filled automatically
		GridBagConstraints gbc_typeField = new GridBagConstraints();
		gbc_typeField.insets = new Insets(0, 0, 5, 0);
		gbc_typeField.fill = GridBagConstraints.HORIZONTAL;
		gbc_typeField.gridx = 1;
		gbc_typeField.gridy = 5;
		eastPanel.add(typeField, gbc_typeField);
		typeField.setColumns(10);
		
		// Creates customer
				JButton btnCreate = new JButton("Create");
				btnCreate.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						createButtonClicked();
					}
				});
				GridBagConstraints gbc_btnCreate = new GridBagConstraints();
				gbc_btnCreate.anchor = GridBagConstraints.EAST;
				gbc_btnCreate.insets = new Insets(0, 0, 5, 0);
				gbc_btnCreate.gridx = 1;
				gbc_btnCreate.gridy = 7;
				eastPanel.add(btnCreate, gbc_btnCreate);

		// Checkbox for selecting pick-up as delivery method
		JLabel lblIsPickup = new JLabel("Is Pick Up:");
		GridBagConstraints gbc_lblIsPickup = new GridBagConstraints();
		gbc_lblIsPickup.anchor = GridBagConstraints.EAST;
		gbc_lblIsPickup.insets = new Insets(0, 0, 5, 5);
		gbc_lblIsPickup.gridx = 0;
		gbc_lblIsPickup.gridy = 6;
		eastPanel.add(lblIsPickup, gbc_lblIsPickup);

		deliveryCheckBox = new JCheckBox("");
		GridBagConstraints gbc_deliveryCheckBox = new GridBagConstraints();
		gbc_deliveryCheckBox.insets = new Insets(0, 0, 5, 0);
		gbc_deliveryCheckBox.gridx = 1;
		gbc_deliveryCheckBox.gridy = 6;
		eastPanel.add(deliveryCheckBox, gbc_deliveryCheckBox);

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

	private boolean createButtonClicked() {
	    try {
	        int id = Integer.parseInt(idField.getText());
	        String name = nameField.getText();
	        String address = addressField.getText();
	        String zipCodeCity = zipCodeCityField.getText();
	        String phone = phoneField.getText();
	        String email = emailField.getText();
	        String customerType = typeField.getText();

	        Customer customer = new Customer(
	                id,
	                name,
	                address,
	                zipCodeCity,
	                phone,
	                email,
	                customerType);

	        saleOrderCtr.createCustomer(customer);

	        JOptionPane.showMessageDialog(
	                CreateSaleOrder.this,
	                "Customer created successfully.");

	        return true;

	    } catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(
	                CreateSaleOrder.this,
	                "Customer ID must be a number.");
	        return false;

	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(
	                CreateSaleOrder.this,
	                "Error creating customer.");
	        e.printStackTrace();
	        return false;
	    }
	}
	
	private void addButtonClicked() {
		int productNumber = Integer.parseInt(productNumberStr);
		Product product = null;
		try {
			product = productCtr.findByProductNumber(productNumber);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    if (productNumberStr == null) {
	        JOptionPane.showMessageDialog(this, "Error. No product found.");

	        return;
	    }
	   
	    
	    

	    if (product == null) {
	        JOptionPane.showMessageDialog(this, "Error. No product found.");
	        return;
	    }

	    String qtyStr = JOptionPane.showInputDialog(this, "Enter quantity:");

	    if (qtyStr == null) {
	        return;
	    }

	    int qty;

	    try {
	        qty = Integer.parseInt(qtyStr);
	    } catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(this, "Error. Quantity must be a number.");
	        return;
	    }

	    if (qty <= 0) {
	        JOptionPane.showMessageDialog(this, "Error. Type a valid quantity.");
	        return;
	    }
	    
	    SaleOrderLine existingLine = findOrderLine(product);

	    if (existingLine != null) {
	        // Produkt findes allerede på ordren
	        int newQty = existingLine.getQuantity() + qty;
	        existingLine.setQuantity(newQty);

	        int rowIndex = saleOrderLines.indexOf(existingLine);
	        tableModel.setValueAt(newQty, rowIndex, 1);

	    } else {
	        // Opret ny ordrelinje
	        SaleOrderLine newLine = new SaleOrderLine(product, qty);

	        saleOrderLines.add(newLine);

	        tableModel.addRow(new Object[] {
	                product.getProductNumber(),
	                qty
	         
	        });
	    }
	}
	
	private void confirmButtonClicked() {
		JOptionPane.showMessageDialog(this, "Order confirmed");
		}
	
	
	private SaleOrderLine findOrderLine(Product product) {
		for (SaleOrderLine ol : saleOrderLines) {
			if (ol.getProduct().equals(product)) {
				return ol;
			}
		}
		return null;
	}


	private void cancelClicked() {
		this.dispose();
		this.setVisible(false);
	}
}
