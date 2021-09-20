package com.bridgelabz.adressbooksystem;

import java.io.IOException;
import java.util.Scanner;

import com.bridgelabz.adressbooksystem.AddessBookServiceImpl.IOService;
import com.opencsv.exceptions.CsvException;

public class AddressBookMenu 

{
	public void performOperations() throws IOException, CsvException
	{

		String firstName,lastName,address,city,state,zip,phoneNumber,email,name;

		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to Address book system program");

		AddessBookServiceImpl addressBookOperations = new AddessBookServiceImpl();
		AddressBooksCollection addressBookSystem = new AddressBooksCollection();

		int addressBookMenuChoice;

		do
		{
			System.out.println("Menu: \n1)Create addressbook \n2)Perform operations on given addressbook \n3)Quit");
			addressBookMenuChoice=scanner.nextInt();
			scanner.nextLine();
			if(addressBookMenuChoice==1)
			{
				System.out.println("Enter name for the addressbook");
				String addressBookName = scanner.nextLine();
				addressBookOperations.createAddressBook(addressBookName);

			}

			else if(addressBookMenuChoice==2)
			{
				System.out.println("Enter addressbook name to perform operations:");
				String addressbookName=scanner.next();
				System.out.println("Menu for the given addressbook: \n1)Add contact\n2)Edit contact\n3Delete contac\n4)Display contact"
						+ "\n5)Search by City\n6)Search by State\n7)Get all contacts in city\n8Get all contacts in state\n"
						+ "9)Count people in city\n10)Count peopel in state \n11)Sort by name)"
						+ "\n12)sort by city \n13)sort by state \n14)sort by zip\n15)Write to file\n16)Read from file");
				int choice = scanner.nextInt();
				scanner.nextLine();

				switch (choice) 
				{
				case 1:

					System.out.println("Enter first and last name of the person");
					firstName=scanner.nextLine();
					lastName=scanner.nextLine();
					System.out.println("Enter address ");
					address=scanner.nextLine();
					System.out.println("Enter city ");
					city=scanner.nextLine();
					System.out.println("Enter state ");
					state=scanner.nextLine();
					System.out.println("Enter zip ");
					zip=scanner.nextLine();
					System.out.println("Enter phone number ");
					phoneNumber=scanner.nextLine();
					System.out.println("Enter email ");
					email=scanner.nextLine();
					Contact person= new Contact(firstName,lastName,address,city,state,zip,phoneNumber,email);
					addressBookOperations.addNewContact(person,addressbookName);
					break;

				case 2:

					System.out.println("Enter phone number of contact to be edited");
					phoneNumber=scanner.nextLine();
					System.out.println("Enter the field and value to be edited");
					String fieldToBeEdited = scanner.nextLine();
					String valueToBeEdited=scanner.nextLine();
					addressBookOperations.editContact(phoneNumber,valueToBeEdited,fieldToBeEdited,addressbookName);
					break;
				case 3:
					System.out.println("Enter phone number of contact to be deleted");
					phoneNumber=scanner.nextLine();
					addressBookOperations.deleteContact(phoneNumber,addressbookName);
					break;
				case 4:
					System.out.println("Enter phone number of contact to be display");
					phoneNumber=scanner.nextLine();
					addressBookOperations.displayContactInfo(phoneNumber,addressbookName);
					break;
				

				case 5:
					System.out.println("Enter name of the person to search");
					name=scanner.nextLine();
					System.out.println("Enter city to search");
					city=scanner.nextLine();
					addressBookOperations.searchPersonByCity(name, city);
					break;
				case 6:
					System.out.println("Enter name of the person to search");
					name=scanner.nextLine();
					System.out.println("Enter state to search");
					state=scanner.nextLine();
					addressBookOperations.searchPersonByState(name, state);
					break;
				case 7:
					System.out.println("Enter city to search");
					city=scanner.nextLine();
					addressBookOperations.getAllContactsInCity(city);
					break;
				case 8:
					System.out.println("Enter state to search");
					state=scanner.nextLine();
					addressBookOperations.getAllContactsInState(state);
					break;
				case 9:
					System.out.println("Enter city to search");
					city=scanner.nextLine();
					addressBookOperations.countPeopleinCity(city);
					break;
				case 10:
					System.out.println("Enter state to search");
					state=scanner.nextLine();
					addressBookOperations.countPeopleinState(state);
					break;
				case 11:
					addressBookOperations.sortByName();
					break;
				case 12:
					addressBookOperations.sortByCity();
					break;
				case 13:
					addressBookOperations.sortByState();
					break;
				case 14:
					addressBookOperations.sortByZip();
					break;
				case 15:
					addressBookOperations.writeContactsOfAddressBook(IOService.FILE_IO,addressbookName);
					break;
				case 16:
					addressBookOperations.readContactListData(IOService.FILE_IO,addressbookName);

				}

			}
			else if(addressBookMenuChoice==3)
			{
				break;
			}
			else
			{
				System.out.println("Wrong choice");
			}
		}while(addressBookMenuChoice!=3);

	}
}
