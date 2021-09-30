package com.bridgelabz.adressbooksystem;

import java.io.Serializable;
import java.util.Objects;

public class Contact implements Serializable

{

	private int contactId;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String email;
	private Address address;

	public Contact()
	{

	}

	public Contact(int contactId, String firstName,String lastName, Address contactAddress, String phoneNumber, String email) 
	{
		this.contactId = contactId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = contactAddress;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}


	public Contact(String firstName, String lastName, Address address,String phoneNumber, String email) 
	{
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.address = address;
	}
	
	public Contact(String[] contact)
	{
		this.firstName=contact[0];
		this.lastName=contact[1];
		this.phoneNumber=contact[3];
		this.email=contact[4];

	}

	public String getFirstName()
	{
		return firstName;
	}
	public String getLastName()
	{
		return lastName;
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

	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber=phoneNumber;
	}
	public void setEmail(String email)
	{
		this.email=email;
	}


	public Address getContactAddress() {
		return address;
	}
	public void setContactAddress(Address contactAddress) {
		this.address = contactAddress;
	}


	@Override
	public int hashCode() 
	{
		return Objects.hash(address, contactId, email, firstName, lastName, phoneNumber);
	}
	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		return Objects.equals(address, other.address) && contactId == other.contactId
				&& Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(phoneNumber, other.phoneNumber);
	}
	@Override
	public String toString() 
	{
		return "Contact [contactId=" + contactId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", phoneNumber=" + phoneNumber + ", email=" + email + ", contactAddress=" + address + "]";
	}




}