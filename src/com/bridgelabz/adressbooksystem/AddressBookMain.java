package com.bridgelabz.adressbooksystem;

import java.util.Scanner;

public class AddressBookMain 
{

	public static void main(String[] args) 
	{
		
		String firstName,lastName,address,city,state,zip,phoneNumber,email;

		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to Address book system program");
		
		AddressBook[] addressBook = new AddressBook[10];
		AddessBookOperationImpl addressBookOperations = new AddessBookOperationImpl();
		 
		System.out.println("Enter number of address book to create");
		int numberOfBooks=scanner.nextInt();
		for(int index=0;index<numberOfBooks;index++)
		{
			System.out.println("Enter the name of the addressbook to be created");
			String addressbookName=scanner.next();
			addressBook[index]= new AddressBook();
			addressBook[index].setAddressBookName(addressbookName);

		}
		 
		 

		AddressBook AddressBookChoice = null;
		
		int choice=1;
				
		while(choice!=5)
		{
			System.out.println("Enter the name of the address book to perform operations");
			String bookName=scanner.next();
			for (int index=0;index<numberOfBooks;index++)
			{
				if (addressBook[index].getAddressBookName().equals(bookName))

				{
					AddressBookChoice=addressBook[index];
					
				}
				
			}
			
			
			System.out.println("Enter 1 to add contact,2 to edit contact,3 to delete,4 to display contact,5 to stop");
			System.out.println("Enter choice");
			 choice=scanner.nextInt();
			 scanner.nextLine(); 
			 
			switch (choice) 
			{
				case 1:
					System.out.println("Enter number of people to be added");
					int numberOfPeople=scanner.nextInt();
					scanner.nextLine();
					for(int index=0;index<numberOfPeople;index++)
					{
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
						Contact person1= new Contact(firstName,lastName,address,city,state,zip,phoneNumber,email);
						addressBookOperations.addNewContact(person1,AddressBookChoice);
							
					}
				
					break;
					
				case 2:

					System.out.println("Enter phone number of contact to be edited");
					phoneNumber=scanner.nextLine();
					System.out.println("Enter the field and value to be edited");
					String fieldToBeEdited = scanner.nextLine();
					String valueToBeEdited=scanner.nextLine();
					addressBookOperations.editContact(phoneNumber,valueToBeEdited,fieldToBeEdited,AddressBookChoice);
					
					break;
				case 3:
					System.out.println("Enter phone number of contact to be deleted");
					phoneNumber=scanner.nextLine();
					addressBookOperations.deleteContact(phoneNumber,AddressBookChoice);
				
					break;
				case 4:
					System.out.println("Enter phone number of contact to be display");
					phoneNumber=scanner.nextLine();
					addressBookOperations.displayContactInfo(phoneNumber,AddressBookChoice);
			}
		}
		
		scanner.close();
	}

}
