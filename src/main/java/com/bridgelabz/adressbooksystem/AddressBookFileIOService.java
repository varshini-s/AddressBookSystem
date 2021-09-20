package com.bridgelabz.adressbooksystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvException;

public class AddressBookFileIOService 
{
	public static String ADDRESSBOOK_TXT_FILE_NAME="addressbook.txt";
	public static String ADDRESSBOOK_CSV_FILE_NAME="addressbook.csv";
	public static String ADDRESSBOOK_JSON_FILE_NAME="addressbook.json";

	public enum FileType {TXT,CSV,JSON}


	public void writeData(List<Contact> contactList,FileType fileType,AddressBooksCollection...addressBookSystem) throws IOException, CsvException 
	{
		if(fileType.equals(FileType.TXT))
		{
			StringBuffer contactBuffer = new StringBuffer();
			contactList.forEach(contact -> {
				String contactDataString =contact.toString().concat("\n");
				contactBuffer.append(contactDataString);
			});

			try
			{
				Files.write(Paths.get(ADDRESSBOOK_TXT_FILE_NAME),contactBuffer.toString().getBytes());
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		else if(fileType.equals(FileType.CSV))
		{
			try
			(Writer writer =Files.newBufferedWriter(Paths.get(ADDRESSBOOK_CSV_FILE_NAME))
			)
			{
				StatefulBeanToCsv<Contact> beanToCsv= new StatefulBeanToCsvBuilder(writer)
						.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
						.build();

				beanToCsv.write(contactList);
			}
			
		}
		else if(fileType.equals(FileType.JSON))
		{
			try
			{

				List<AddressBook> addressbookList = addressBookSystem[0].getAddressbookList();
		        Gson gson = new GsonBuilder().setPrettyPrinting().create();

				String json =gson.toJson(addressbookList);
				FileWriter writer = new FileWriter(ADDRESSBOOK_JSON_FILE_NAME);
				writer.write(json);
				writer.close();
	
			}
			catch(IOException e)
			{
				e.printStackTrace();
				
			}
		}
	}
	
	public void printData(FileType fileType) throws IOException, CsvException 
	{
		if(fileType.equals(FileType.TXT))
		{
			try 
			{
				Files.lines(new File(ADDRESSBOOK_TXT_FILE_NAME).toPath())
				.forEach(System.out::println);
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		else if(fileType.equals(FileType.CSV))
		{
			
			try
			(
				Reader reader =Files.newBufferedReader(Paths.get(ADDRESSBOOK_CSV_FILE_NAME));
				CSVReader csvReader = new CSVReader(reader);
			)
			{
				List<String[]> records = csvReader.readAll();
				for(String[] record:records)
				{
					System.out.println("First Name: "+record[0]);
					System.out.println("Last Name: "+record[1]);
					System.out.println("Adddress: "+record[2]);
					System.out.println("City: "+record[3]);
					System.out.println("Zip: "+record[4]);
					System.out.println("state: "+record[5]);
					System.out.println("Phone number: "+record[6]);
					System.out.println("Email: "+record[7]);

					System.out.println("=========================");
					
				}
			}
		}
		else if(fileType.equals(FileType.JSON))
		{
			
			List<AddressBook> addressBookList=this.readData();
			addressBookList.stream()
			.forEach(addressBook-> {addressBook.getContactList().stream().forEach(contact->System.out.println(contact));});
			
		}
		
	}
	
	public long countEntries(FileType fileType) throws IOException, CsvException
	{
		if(fileType.equals(FileType.TXT))
		{
			long entries=0;
			try {
				entries=Files.lines(new File(ADDRESSBOOK_TXT_FILE_NAME).toPath())
						.count();
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			return entries;
		}
		else if(fileType.equals(FileType.CSV))
		{
			return this.readData(FileType.CSV).size();
		}
		else if(fileType.equals(FileType.JSON))
		{
			
			return this.readData().size();
		}
		return 0;
	}

	
	public List<Contact> readData(FileType fileType) throws IOException, CsvException 
	{
		List<Contact> contactList = new ArrayList<Contact>();
		
		List<String[]> contactDataFromFile = new ArrayList<String[]>();
		if(fileType.equals(FileType.TXT))
		{
			try
			{
				List<String[]> contactDataFromTXTFile = new ArrayList<String[]>();

				Files.lines(new File(ADDRESSBOOK_TXT_FILE_NAME).toPath())
					.map(line->line.trim())
					.forEach(line->contactDataFromTXTFile.add(line.split(",")));
				
				
				for(int index=0;index<contactDataFromTXTFile.size();index++)
				{
					
					contactList.add(new Contact(contactDataFromTXTFile.get(index)[0].split("=")[1]
											,contactDataFromTXTFile.get(index)[1].split("=")[1]
											,contactDataFromTXTFile.get(index)[2].split("=")[1]
											,contactDataFromTXTFile.get(index)[3].split("=")[1]
											,contactDataFromTXTFile.get(index)[4].split("=")[1]
											,contactDataFromTXTFile.get(index)[5].split("=")[1]
											,contactDataFromTXTFile.get(index)[6].split("=")[1]
											,contactDataFromTXTFile.get(index)[7].split("=")[1]
											));
				}
				
				
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		else if(fileType.equals(FileType.CSV))
		{
			
			try
			(
				Reader reader =Files.newBufferedReader(Paths.get(ADDRESSBOOK_CSV_FILE_NAME));
				CSVReader csvReader = new CSVReader(reader);
			)
			{
				 contactDataFromFile = csvReader.readAll();
				 
				 for(int index=1;index<contactDataFromFile.size();index++)
					{
						
						contactList.add(new Contact(contactDataFromFile.get(index)[0]
												,contactDataFromFile.get(index)[1]
												,contactDataFromFile.get(index)[2]
												,contactDataFromFile.get(index)[3]
												,contactDataFromFile.get(index)[4]
												,contactDataFromFile.get(index)[5]
												,contactDataFromFile.get(index)[6]
												,contactDataFromFile.get(index)[7]
												));
					}
			}
			
		}
	
		return contactList;
	}
	
	public List<AddressBook> readData() throws IOException, CsvException 
	{
		BufferedReader br = new BufferedReader(new FileReader(ADDRESSBOOK_JSON_FILE_NAME));
		Gson gson= new Gson();
		AddressBook[] usrObj =gson.fromJson(br, AddressBook[].class);
		List<AddressBook> addressBookList = Arrays.asList(usrObj);
		
		return addressBookList;
		
	}

}
