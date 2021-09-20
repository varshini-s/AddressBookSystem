package com.bridgelabz.addressbooksystem;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.bridgelabz.adressbooksystem.AddessBookServiceImpl;
import com.bridgelabz.adressbooksystem.Contact;
import com.opencsv.exceptions.CsvException;
import com.bridgelabz.adressbooksystem.AddessBookServiceImpl.IOService;
import com.bridgelabz.adressbooksystem.AddressBookFileIOService.FileType;
import com.bridgelabz.adressbooksystem.AddressBooksCollection;

public class AddressBookFileIOServiceTest 

{
	AddessBookServiceImpl addressBookOperations;
	AddressBooksCollection addressBookSystem = new AddressBooksCollection();

	String addressBookName;

	@Before
	public void initialSetUp()
	{
		addressBookOperations = new AddessBookServiceImpl();

		addressBookOperations.createAddressBook("Addressbook1");
		addressBookName="Addressbook1";
		addressBookOperations.addNewContact(new Contact("Sheldon","Cooper","texas","pasadena","california","11134","12345","abc@gmail.com"),addressBookName);
		addressBookOperations.addNewContact(new Contact("Ross ","Geller","groov street","aaa","New york","12311","89897677","ross@gmail.com"),addressBookName);
		addressBookOperations.addNewContact(new Contact("Ted","Mobsby","aaa","bbb","new york","44562","567890999","ted@gmail.com"),addressBookName);
		
		addressBookOperations.createAddressBook("Addressbook2");
		addressBookName="Addressbook2";
		addressBookOperations.addNewContact(new Contact("james","b","texas","pasadena","california","11134","12345","abc@gmail.com"),addressBookName);
		addressBookOperations.addNewContact(new Contact("susan ","aaa","groov street","aaa","New york","12311","89897677","ross@gmail.com"),addressBookName);
		addressBookOperations.addNewContact(new Contact("alan","c","aaa","bbb","new york","44562","567890999","ted@gmail.com"),addressBookName);	

	}


	@Test
	public void given3ContactsWhenWrittenToTXTFileShouldMatchContactEntries() throws IOException, CsvException
	{

		addressBookOperations.writeContactsOfAddressBook(IOService.FILE_IO,addressBookName,FileType.TXT);
		long txtEntries = addressBookOperations.countEntries(IOService.FILE_IO,FileType.TXT);
		Assert.assertEquals(3, txtEntries);


	}
	@Test
	public void given3ContactsWhenWrittenToCSVFileShouldMatchContactEntries() throws IOException, CsvException
	{

		addressBookOperations.writeContactsOfAddressBook(IOService.FILE_IO,addressBookName,FileType.CSV);
		long csvEntries = addressBookOperations.countEntries(IOService.FILE_IO,FileType.CSV);
		Assert.assertEquals(3, csvEntries);

	}
	@Test
	public void given3ContactsWhenWrittenToJSONFileShouldMatchContactEntries() throws IOException, CsvException
	{

		addressBookOperations.writeContactsOfAddressBook(IOService.FILE_IO,addressBookName,FileType.CSV);
		long jsonEntries = addressBookOperations.countEntries(IOService.FILE_IO,FileType.JSON);
		Assert.assertEquals(2, jsonEntries);

	}

	@Test
	public void givenFileOnReadingFromTXTFileShouldMatchContactsCount() throws IOException, CsvException
	{
		
		long txtEntries = addressBookOperations.readContactListData(IOService.FILE_IO,addressBookName,FileType.TXT);
		Assert.assertEquals(3, txtEntries);

	}
	@Test
	public void givenFileOnReadingFromCSVFileShouldMatchContactsCount() throws IOException, CsvException
	{
		
		long csvEntries = addressBookOperations.readContactListData(IOService.FILE_IO,addressBookName,FileType.CSV);
		Assert.assertEquals(3, csvEntries);

	}
	@Test
	public void givenFileOnReadingFromJSONFileShouldMatchContactsCount() throws IOException, CsvException
	{
		
		long jsonEntries = addressBookOperations.readContactListData(IOService.FILE_IO,addressBookName,FileType.JSON);
		Assert.assertEquals(2, jsonEntries);

	}

}
