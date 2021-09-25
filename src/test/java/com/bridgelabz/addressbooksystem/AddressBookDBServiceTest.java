package com.bridgelabz.addressbooksystem;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.bridgelabz.adressbooksystem.AddessBookServiceImpl;
import com.bridgelabz.adressbooksystem.Contact;
import com.bridgelabz.adressbooksystem.AddessBookServiceImpl.IOService;
import com.opencsv.exceptions.CsvException;

public class AddressBookDBServiceTest 
{
	AddessBookServiceImpl addressBookOperations;

	@Before
	public void initialSetUp()
	{
		addressBookOperations = new AddessBookServiceImpl();
	}
	
	@Test
	public void  givenAddressBookInDB_WhenRetrieved_ShouldMatchEmployeeCount() throws IOException, CsvException
	{
		
		List<Contact> contactList = addressBookOperations.readContactListData(IOService.DB_IO,"book1");
		Assert.assertEquals(2, contactList.size());
	}
}
