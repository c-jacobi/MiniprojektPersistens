package model;



public class Customer {
	private int id;
	private String name;
	private String address;
	private String zipCodeCity;
	private String city;
	private String email;
	private String phone;
	private String customerType;

	
	

	public Customer(int id, String name, String address, String zipCodeCity, String phoneNo, String email, String customerType) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.zipCodeCity = zipCodeCity;
		this.phone = phoneNo;
		this.email = email;
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




	public String getZipCodeCity() {
		return zipCodeCity;
	}




	public String getCity() {
		return city;
	}




	public String getEmail() {
		return email;
	}




	public String getPhone() {
		return phone;
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




	public void setZipcode(String zipCodeCity) {
		this.zipCodeCity = zipCodeCity;
	}




	public void setCity(String city) {
		this.city = city;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public void setPhone(String phone) {
		this.phone = phone;
	}




	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	
	
	
		

	

}
