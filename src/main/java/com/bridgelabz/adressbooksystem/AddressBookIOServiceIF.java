package com.bridgelabz.adressbooksystem;

import java.io.IOException;
import java.util.List;

import com.bridgelabz.adressbooksystem.AddessBookServiceImpl.IOService;
import com.bridgelabz.adressbooksystem.AddressBookFileIOService.FileType;
import com.opencsv.exceptions.CsvException;

public interface AddressBookIOServiceIF 
{

	public void createAddressBook(String addressbookName);
	public AddressBook getAddressBook(String addressbookName);
	
	public List<Contact> getContactList(String  addressbookName);
	public void writeContactsOfAddressBook(IOService ioService,String  addressbookName,FileType... fileType) throws IOException, CsvException;
	public void printData(IOService ioService,FileType... fileType) throws IOException, CsvException;
	public long countEntries(IOService ioService,FileType... fileType) throws IOException, CsvException;
	public List<Contact> readContactListData(IOService ioService,String  addressbookName,FileType... fileType) throws IOException, CsvException;
}
