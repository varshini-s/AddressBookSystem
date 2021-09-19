package com.bridgelabz.adressbooksystem;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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
	
	public void printData() 
	{
		try 
		{
			Files.lines(new File(ADDRESSBOOK_FILE_NAME).toPath())
			.forEach(System.out::println);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public long countEntries()
	{
		
		long entries=0;
		try {
			entries=Files.lines(new File(ADDRESSBOOK_FILE_NAME).toPath())
					.count();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return entries;
	}

	
	public List<Contact> readData()
	{
		List<Contact> contactList = new ArrayList<Contact>();
		
		List<String[]> contactDataFromFile = new ArrayList<String[]>();
		try
		{
			Files.lines(new File(ADDRESSBOOK_FILE_NAME).toPath())
				.map(line->line.trim())
				.forEach(line->contactDataFromFile.add(line.split(",")));
			
			for(int index=0;index<contactDataFromFile.size();index++)
			{
				
				contactList.add(new Contact(contactDataFromFile.get(index)[0].split("=")[1]
										,contactDataFromFile.get(index)[1].split("=")[1]
										,contactDataFromFile.get(index)[2].split("=")[1]
										,contactDataFromFile.get(index)[3].split("=")[1]
										,contactDataFromFile.get(index)[4].split("=")[1]
										,contactDataFromFile.get(index)[5].split("=")[1]
										,contactDataFromFile.get(index)[6].split("=")[1]
										,contactDataFromFile.get(index)[7].split("=")[1]
										));
			}
			
			
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		return contactList;
	}
}
