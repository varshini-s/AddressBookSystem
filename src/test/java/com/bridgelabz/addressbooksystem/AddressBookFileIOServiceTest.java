package com.bridgelabz.addressbooksystem;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.bridgelabz.adressbooksystem.AddessBookServiceImpl;
import com.bridgelabz.adressbooksystem.Contact;
import com.bridgelabz.adressbooksystem.AddessBookServiceImpl.IOService;

public class AddressBookFileIOServiceTest 

{
	AddessBookServiceImpl addressBookOperations;
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

	}


	@Test
	public void given3ContactsWhenWrittenToFileShouldMatchContactEntries()
	{

		addressBookOperations.writeContactsOfAddressBook(IOService.FILE_IO,addressBookName);
		addressBookOperations.printData(IOService.FILE_IO);

		long entries = addressBookOperations.countEntries(IOService.FILE_IO);
		Assert.assertEquals(3, entries);


	}

	@Test
	public void givenFileOnReadingFromFileShouldMatchContactsCount()
	{
		long entries = addressBookOperations.readContactListData(IOService.FILE_IO,addressBookName);
		Assert.assertEquals(3, entries);


	}

}
