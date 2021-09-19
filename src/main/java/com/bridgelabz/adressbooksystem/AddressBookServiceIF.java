package com.bridgelabz.adressbooksystem;

import java.util.List;

import com.bridgelabz.adressbooksystem.AddessBookServiceImpl.IOService;

public interface AddressBookServiceIF 

{
	public void addNewContact(Contact person,String  addressbookName);
	public void editContact(String number,String editInfo,String choice,String  addressbookName);
	public void deleteContact(String number,String  addressbookName);
	public int hasContact(String number,String  addressbookName);
	public void displayContactInfo(String number,String  addressbookName);
	public void searchPersonByState(String givenName,String state); 
	public void searchPersonByCity(String name,String city);
	public void getAllContactsInState(String state);
	public void getAllContactsInCity(String city);
	public void countPeopleinCity(String city);
	public void countPeopleinState(String state);
	public void sortByName();
	public void sortByCity();
	public void sortByState();
	public void sortByZip();
	
	public void createAddressBook(String addressbookName);
	public AddressBook getAddressBook(String addressbookName);
	
	public List<Contact> getContactList(String  addressbookName);
	public void writeContactsOfAddressBook(IOService ioService,String  addressbookName);
	public void printData(IOService ioService);
	public long countEntries(IOService ioService); 
}
