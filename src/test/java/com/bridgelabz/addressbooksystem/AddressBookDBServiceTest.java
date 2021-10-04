package com.bridgelabz.addressbooksystem;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.bridgelabz.adressbooksystem.AddessBookServiceImpl;
import com.bridgelabz.adressbooksystem.Address;
import com.bridgelabz.adressbooksystem.AddressBookDBService;
import com.bridgelabz.adressbooksystem.Contact;
import com.bridgelabz.adressbooksystem.ContactDTO;
import com.bridgelabz.adressbooksystem.IOServiceType.IOService;
import com.bridgelabz.adressbooksystem.UserEntryException;
import com.opencsv.exceptions.CsvException;

public class AddressBookDBServiceTest 
{
	AddessBookServiceImpl addressBookOperations;

	@Before
	public void initialSetUp() throws FileNotFoundException, SQLException
	{
		addressBookOperations = new AddessBookServiceImpl();
		addressBookOperations.setUpDataBase();
	}

	@Test
	public void  givenContactsInDB_WhenRetrieved_ShouldMatchContactsCount() throws IOException, CsvException, UserEntryException
	{

		List<ContactDTO> contactList = addressBookOperations.readContactListDataFromDB("book1");
		Assert.assertEquals(2, contactList.size());
	}
	@Test
	public void  givenContactsInDB_WhenGivenAddressBookNameAsNull_ShouldThrowCustomException() 
	{
			
		try 
		{

			addressBookOperations.readContactListDataFromDB(null);
		}
		catch (UserEntryException e) 
		{
			Assert.assertEquals( "Please enter valid AddressBook Name",e.getMessage());
		}
	}
	@Test
	public void  givenContactsInDB_WhenGivenAddressBookNameAsEmpty_ShouldThrowCustomException() 
	{
			
		try 
		{

			addressBookOperations.readContactListDataFromDB("");
		}
		catch (UserEntryException e) 
		{
			Assert.assertEquals( "Please enter valid AddressBook Name",e.getMessage());
		}
	}

	@Test
	public void  givenContactsInDB_WhenGivenState_ShouldMatchContactsCountInGivenState() 
	{

		List<ContactDTO> contactList = addressBookOperations.readContactListOfState(IOService.DB_IO,"Karnataka");
		Assert.assertEquals(3, contactList.size());

	}
	@Test
	public void  givenContactsInDB_WhenGivenStateCity_ShouldReturnCountOfContactsInGivenAddressBook() 
	{

		int count = addressBookOperations.countOfContactsInGivenStateCity(IOService.DB_IO,"book1","Bangalore","Karnataka");
		Assert.assertEquals(1, count);

	}	
	@Test
	public void  givenContactsInDB_WhenGivenCity_ShouldReturnSortedContactsByName() throws UserEntryException 
	{
		List<String > expectedSortOrder=new ArrayList<String>();
		expectedSortOrder.add("Bob");
		expectedSortOrder.add("raj");
		List<String > retrievedSortedOrder = addressBookOperations.getSortedContactByName(IOService.DB_IO,"Mysore");
		Assert.assertEquals(expectedSortOrder, retrievedSortedOrder);
	}	
	@Test
	public void  givenContactsInDB_WhenGivenCityNameAsNull_ShouldThrowCustomException() 
	{
			
		try 
		{

			List<String > expectedSortOrder=new ArrayList<String>();
			expectedSortOrder.add("Bob");
			expectedSortOrder.add("raj");
		    addressBookOperations.getSortedContactByName(IOService.DB_IO,null);
		}
		catch (UserEntryException e) 
		{
			Assert.assertEquals( "Please enter valid city Name",e.getMessage());
		}
	}
	@Test
	public void  givenContactsInDB_WhenGivenCityNameAsEmpty_ShouldThrowCustomException() 
	{
			
		try 
		{

			List<String > expectedSortOrder=new ArrayList<String>();
			expectedSortOrder.add("Bob");
			expectedSortOrder.add("raj");
		    addressBookOperations.getSortedContactByName(IOService.DB_IO,"");
		}
		catch (UserEntryException e) 
		{
			Assert.assertEquals( "Please enter valid city Name",e.getMessage());
		}
	}
	@Test
	public void givenContactDB_WhenGivenAddresBookType_ShouldReturnCountOfContactsOfGivenType()
	{
		int count = addressBookOperations.countOfContactsInGivenType(IOService.DB_IO,"Friend");
		Assert.assertEquals(3, count);
	}


	@Test
	public void givenNewEContact_WhenAddedShouldSyncWithDB() throws IOException, CsvException, UserEntryException
	{
		addressBookOperations.readContactListDataFromDB( "Book1");	
		Address address = new Address("123", "bbb", "yk", "rrr", "12345");
		Contact contact = new Contact("susan", "prevensie",address , "1234512345", "ddd@example.com");
		addressBookOperations.addContact(contact,"book1",LocalDate.now());
		boolean result= addressBookOperations.checkContactInSyncWithDB("susan");
		Assert.assertTrue(result);

	}

	@Test
	public void givenContactDB_WhenGivenDate_ShouldReturnCountOfContactsAddedInGivenDateRange()
	{

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String startDate="2018-01-02";
		String endDate="2019-01-02";

		LocalDate.parse(startDate, formatter);
		LocalDate.parse(endDate, formatter);

		int count = addressBookOperations.countOfContactsAddedInGivenDateRange(IOService.DB_IO,startDate,endDate);
		Assert.assertEquals(1, count);
	}


	@Test
	public void givenTwoThreads_WhenTryingToAddSameContact_OnlyOneMustBeAbleToAdd() throws InterruptedException
	{

		CountDownLatch lock = new CountDownLatch(2);

		Address address= new Address("23", "aaa", "Mysore", "Karnataka", "12345");
		Contact contact = new Contact("susan", "prevensie",address,"1234512345", "ddd@example.com");
		AddressBookDBService addressBookDBService= new AddressBookDBService(contact,"book1",LocalDate.now());

		Thread thread1 = new Thread(addressBookDBService);
		Thread thread2 = new Thread(addressBookDBService);
		thread1.setName("Person1");
		thread2.setName("Person2");

		thread1.start();
		thread2.start();	

		lock.await(3000, TimeUnit.MILLISECONDS);
		int count=addressBookOperations.countOfContacts(IOService.DB_IO);

		Assert.assertEquals(4, count);

	}

	
	@Test
	public void givenInfoToUpdateContact_WhenUpdatedShouldSyncWithDB() throws IOException, CsvException, UserEntryException
	{
		addressBookOperations.readContactListDataFromDB( "Book1");
		Address address= new Address("23", "aaa", "Mysore", "Karnataka", "12345");
		Contact contact = new Contact("Bob","J",address,"1234123455","bob@example.com");
		addressBookOperations.updateContactPhoneNumber(IOService.DB_IO,contact,"1234123455");
		
		boolean result= addressBookOperations.checkContactInSyncWithDB("Bob");
		Assert.assertTrue(result);

	}
}

