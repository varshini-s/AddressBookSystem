package com.bridgelabz.addressbooksystem;

import org.junit.Assert;
import org.junit.Test;

import com.bridgelabz.adressbooksystem.AddessBookServiceImpl;
import com.bridgelabz.adressbooksystem.Contact;
import com.bridgelabz.adressbooksystem.AddessBookServiceImpl.IOService;

public class AddressBookFileIOServiceTest 

{
	
	@Test
	public void test()
	{
		
		AddessBookServiceImpl addressBookOperations = new AddessBookServiceImpl();

		addressBookOperations.createAddressBook("Addressbook1");
		String addressBookName="Addressbook1";
		addressBookOperations.addNewContact(new Contact("Sheldon","Cooper","texas","pasadena","california","11134","12345","abc@gmail.com"),addressBookName);
		addressBookOperations.addNewContact(new Contact("Ross ","Geller","groov street","aaa","New york","12311","89897677","ross@gmail.com"),addressBookName);
		addressBookOperations.addNewContact(new Contact("Ted","Mobsby","aaa","bbb","new york","44562","567890999","ted@gmail.com"),addressBookName);
		addressBookOperations.writeContactsOfAddressBook(IOService.FILE_IO,addressBookName);
		addressBookOperations.printData(IOService.FILE_IO);
		
		long entries = addressBookOperations.countEntries(IOService.FILE_IO);
		Assert.assertEquals(3, entries);
		
			
	}
	
}
