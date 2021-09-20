package com.bridgelabz.adressbooksystem;

import java.util.LinkedList;
import java.util.List;

public class AddressBooksCollection 
{
	
	private List<AddressBook> addressbookList;
	public AddressBooksCollection() {
		addressbookList = new LinkedList<AddressBook>();
	}

	public List<AddressBook> getAddressbookList() 
	{
		return this.addressbookList;
	}

	public void setAddressbookList(List<AddressBook> addressbookList) 
	{
		this.addressbookList = addressbookList;
	}	

}
