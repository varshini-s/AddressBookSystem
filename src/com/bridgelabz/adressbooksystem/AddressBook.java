package com.bridgelabz.adressbooksystem;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class AddressBook 

{
	 private String AddressBookName;
	private List<Contact> contactList = new LinkedList<Contact>();

	 
	 
	
	

	 public String getAddressBookName()
	 {
		 return AddressBookName;
		 
	 }
	
	
	 
	 public void setAddressBookName(String AddressBookName)
	 {
		 this.AddressBookName=AddressBookName;
		 
	 }
	 public List<Contact> getContactlist() {
			return contactList;
		}

		public void setContactlist(List<Contact> newlist) {
			this.contactList = newlist;
		}
	
	

	
}
