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
	public void  givenContactsInDB_WhenRetrieved_ShouldMatchContactsCount() throws IOException, CsvException
	{
		
		List<Contact> contactList = addressBookOperations.readContactListData(IOService.DB_IO,"book1");
		Assert.assertEquals(2, contactList.size());
	}
	
	@Test
	public void  givenContactsInDB_WhenGivenState_ShouldMatchContactsCountInGivenState() throws IOException, CsvException
	{
		
		List<Contact> contactList = addressBookOperations.readContactListOfState(IOService.DB_IO,"Karnataka");
		Assert.assertEquals(3, contactList.size());

	}
	
}
