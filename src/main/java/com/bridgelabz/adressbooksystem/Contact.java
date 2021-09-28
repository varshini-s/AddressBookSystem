package com.bridgelabz.adressbooksystem;

import java.util.Objects;

public class Contact 

{
	private int contactId;
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String phoneNumber;
	private String email;
    private Address contactAddress;
	
    

	public Contact()
	{
		
	}
	public Contact(String firstName,String lastName,String address,String city,String state,String zip,String phoneNumber,String email)
	{
		this.firstName=firstName;
		this.lastName=lastName;
		this.address=address;
		this.city=city;
		this.state=state;
		this.zip=zip;
		this.phoneNumber=phoneNumber;
		this.email=email;
	}
	

	public Contact(String[] contact)
	{
		this.firstName=contact[0];
		this.lastName=contact[1];
		this.address=contact[2];
		this.city=contact[3];
		this.state=contact[4];
		this.zip=contact[5];
		this.phoneNumber=contact[6];
		this.email=contact[7];
		
		
	}
	
	public Contact(String firstName, String lastName, String phoneNumber, String email) 
	
	{
		this.firstName=firstName;
		this.lastName=lastName;
		this.phoneNumber=phoneNumber;
		this.email=email;
		
	}
	public String getFirstName()
	{
		return firstName;
	}
	public String getLastName()
	{
		return lastName;
	}
	public String getAddress()
	{
		return address;
	}
	public String getCity()
	{
		return city;
	}
	public String getState()
	{
		return state;
	}
	public String getZip()
	{
		return zip;
	}
	public String getPhoneNumber()
	{
		return phoneNumber;
	}
	public String getEmail()
	{
		return email;
	}
	
	
	//set methods
	
	public void setFirstName(String firstName)
	{
		 this.firstName=firstName;
	}
	public void setLastName(String lastName)
	{
		this.lastName=lastName;
	}
	public void setAddress(String address)
	{
		this.address=address;
	}
	public void setCity(String city)
	{
		this.city=city;
	}
	public void setState(String state)
	{
		this.state=state;
	}
	public void setZip(String zip)
	{
		this.zip=zip;
	}
	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber=phoneNumber;
	}
	public void setEmail(String email)
	{
		this.email=email;
	}
	
	
	public Address getContactAddress() {
		return contactAddress;
	}
	public void setContactAddress(Address contactAddress) {
		this.contactAddress = contactAddress;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		return Objects.equals(address, other.address) && Objects.equals(city, other.city)
				&& Objects.equals(contactAddress, other.contactAddress) && contactId == other.contactId
				&& Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(phoneNumber, other.phoneNumber)
				&& Objects.equals(state, other.state) && Objects.equals(zip, other.zip);
	}
	@Override
	public int hashCode() {
		return Objects.hash(address, city, contactAddress, contactId, email, firstName, lastName, phoneNumber, state,
				zip);
	}
	
	 @Override
	    public String toString() 
	 {
			return "firstName="+firstName+",lastName="+lastName+",address="+address+",city="+city+",zip="+zip+",state="+state+",phone number="+phoneNumber+
					",email="+email;
	    }
	
	
}