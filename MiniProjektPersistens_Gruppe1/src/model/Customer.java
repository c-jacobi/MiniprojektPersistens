package model;



public class Customer {
	private int id;
	private String name;
	private String address;
	private int zipcode;
	private String city;
	private String email;
	private String phoneNo;
	private String customerType;

	
	

	public Customer(int id, String name, String address, int zipcode, String city, String email, String phoneNo, String customerType) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.zipcode= zipcode;
		this.city = city;
		this.email = email;
		this.phoneNo = phoneNo;
		this.customerType = customerType;
		
	}




	public int getId() {
		return id;
	}




	public String getName() {
		return name;
	}




	public String getAddress() {
		return address;
	}




	public int getZipcode() {
		return zipcode;
	}




	public String getCity() {
		return city;
	}




	public String getEmail() {
		return email;
	}




	public String getPhoneNo() {
		return phoneNo;
	}




	public String getCustomerType() {
		return customerType;
	}




	public void setId(int id) {
		this.id = id;
	}




	public void setName(String name) {
		this.name = name;
	}




	public void setAddress(String address) {
		this.address = address;
	}




	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}




	public void setCity(String city) {
		this.city = city;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}




	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	
	
	
		

	

}
