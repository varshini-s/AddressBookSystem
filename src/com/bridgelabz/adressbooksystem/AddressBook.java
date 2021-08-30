package com.bridgelabz.adressbooksystem;


public class AddressBook 

{
	 private int numberOfContacts=0;
	 private Contact[] contactList = new Contact[10];
	 private String AddressBookName;
	 
	 
	 
	 public AddressBook() 
	 {
		numberOfContacts=0;
	}
	
	 public int getNumberOfContacts()
	 {
		 
		 return numberOfContacts;
	 }
	 
	 public String getAddressBookName()
	 {
		 return AddressBookName;
		 
	 }
	 public Contact[] getContactList()
	 {
		 
		 return contactList;
	 }
	 public void setNumberOfContacts(int numberOfContacts)
	 {
		 this.numberOfContacts=numberOfContacts;
		 
	 }
	 
	 public void setAddressBookName(String AddressBookName)
	 {
		 this.AddressBookName=AddressBookName;
		 
	 }
	
	

	
}
