package com.bridgelabz.adressbooksystem;

import java.io.IOException;

import com.opencsv.exceptions.CsvException;

public class AddressBookMain 
{

	public static void main(String[] args) throws IOException, CsvException
	{
		
		AddressBookMenu menu = new AddressBookMenu();
		menu.performOperations();
	}
	
}
