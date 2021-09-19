package com.bridgelabz.adressbooksystem;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class AddressBookFileIOService 
{
	public static String ADDRESSBOOK_FILE_NAME="addressbook.txt";


	public void writeData(List<Contact> contactList) 
	{
		StringBuffer contactBuffer = new StringBuffer();
		contactList.forEach(contact -> {
			String contactDataString =contact.toString().concat("\n");
			contactBuffer.append(contactDataString);
		});

		try
		{
			Files.write(Paths.get(ADDRESSBOOK_FILE_NAME),contactBuffer.toString().getBytes());
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}

		
	}
	


}
