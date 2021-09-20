package com.bridgelabz.adressbooksystem;

import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import com.bridgelabz.adressbooksystem.AddessBookServiceImpl.IOService;
import com.bridgelabz.adressbooksystem.AddressBookFileIOService.FileType;
import com.opencsv.exceptions.CsvException;

public interface AddressBookServiceIF 

{
	public void addNewContact(Contact person,String  addressbookName);
	public void editContact(String number,String editInfo,String choice,String  addressbookName);
	public void deleteContact(String number,String  addressbookName);
	public int hasContact(String number,String  addressbookName);
	public void displayContactInfo(String number,String  addressbookName);
	public void searchPersonByState(String givenName,String state,PrintStream... printStreamObject);
	public void searchPersonByCity(String givenName,String city,PrintStream... printStreamObject);  
	public void getAllContactsInState(String state,PrintStream... printStreamObject);
	public void getAllContactsInCity(String city,PrintStream... printStreamObject);
	public void countPeopleinCity(String city,PrintStream... printStreamObject);
	public void countPeopleinState(String state,PrintStream... printStreamObject);
	public void sortByName(PrintStream... printStreamObject);
	public void sortByCity(PrintStream... printStreamObject);
	public void sortByState(PrintStream... printStreamObject);
	public void sortByZip(PrintStream... printStreamObject);
	
	public void createAddressBook(String addressbookName);
	public AddressBook getAddressBook(String addressbookName);
	
	public List<Contact> getContactList(String  addressbookName);
	public void writeContactsOfAddressBook(IOService ioService,String  addressbookName,FileType... fileType) throws IOException, CsvException;
	public void printData(IOService ioService,FileType... fileType) throws IOException, CsvException;
	public long countEntries(IOService ioService,FileType... fileType) throws IOException, CsvException;
	public long readContactListData(IOService ioService,String  addressbookName,FileType... fileType) throws IOException, CsvException;

}
