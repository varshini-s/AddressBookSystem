package com.bridgelabz.addressbooksystem;

import java.io.IOException;
import java.util.ArrayList;
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
	public void  givenContactsInDB_WhenGivenState_ShouldMatchContactsCountInGivenState() 
	{
		
		List<Contact> contactList = addressBookOperations.readContactListOfState(IOService.DB_IO,"Karnataka");
		Assert.assertEquals(3, contactList.size());

	}
	@Test
	public void  givenContactsInDB_WhenGivenStateCity_ShouldReturnCountOfContactsInGivenAddressBook() 
	{
		
		int count = addressBookOperations.countOfContactsInGivenStateCity(IOService.DB_IO,"book1","Bangalore","Karnataka");
		Assert.assertEquals(1, count);

	}	
	@Test
	public void  givenContactsInDB_WhenGivenCity_ShouldReturnSortedContactsByName() 
	{
		List<String > expectedSortOrder=new ArrayList<String>();
		expectedSortOrder.add("Bob");
		expectedSortOrder.add("raj");
		List<String > retrievedSortedOrder = addressBookOperations.getSortedContactByName(IOService.DB_IO,"Mysore");
		Assert.assertEquals(expectedSortOrder, retrievedSortedOrder);

	}	
	@Test
	public void givenContactDB_WhenGivenAddresBookType_ShouldReturnCountOfContactsOfGivenType()
	{
		int count = addressBookOperations.countOfContactsInGivenType(IOService.DB_IO,"Friend");
		Assert.assertEquals(3, count);
	}
}
