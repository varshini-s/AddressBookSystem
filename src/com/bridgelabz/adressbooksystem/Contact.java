package com.bridgelabz.adressbooksystem;

import java.util.Objects;

public class Contact 

{
	private String firstName,lastName,address,city,state,zip,phoneNumber,email;
	
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
	
	@Override
    public boolean equals(Object obj) 
	
	{
		
        if (obj == null) 
        {
            return false;
        }

        if (obj.getClass() != this.getClass()) 
        {
            return false;
        }

        final Contact other = (Contact) obj;
        


        if(this.lastName.equals(other.lastName)==false||this.firstName.equals(other.firstName)==false
         ||this.address.equals(other.address)==false||this.city.equals(other.city)==false
         ||this.state.equals(other.state)==false||this.zip.equals(other.zip)==false
         ||this.phoneNumber.equals(other.phoneNumber)==false||this.email.equals(other.email)==false)
        {
            return false;
        }
        
        
        return true;
    }
	
	@Override
	public int hashCode() 
	{
		return Objects.hash(firstName);
	}
	
	 @Override
	    public String toString() {
	        return firstName;
	    }
	
	
}
