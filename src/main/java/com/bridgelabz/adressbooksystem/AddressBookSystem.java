package com.bridgelabz.adressbooksystem;

import java.util.LinkedList;
import java.util.List;

public class AddressBookSystem 
{
	
	private List<AddressBook> addressbookList = new LinkedList<AddressBook>();

	public List<AddressBook> getAddressbookList() 
	{
		return addressbookList;
	}

	public void setAddressbookList(List<AddressBook> addressbookList) 
	{
		this.addressbookList = addressbookList;
	}	

}
