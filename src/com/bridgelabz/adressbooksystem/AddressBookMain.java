package com.bridgelabz.adressbooksystem;

import java.util.Scanner;

public class AddressBookMain 
{

	public static void main(String[] args) 
	{
		String firstName,lastName,address,city,state,zip,phoneNumber,email;

		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to Address book system program");
		
		AddressBook addressBook1 = new AddressBook();
		AddressBook addressBook2 = new AddressBook();

		AddressBook AddressBookChoice;
		
		int choice=1;
				
		while(choice!=5)
		{
			System.out.println("Enter 1 to manipulate addressbook1, enter 2 to manipulate addressbok2");
			int bookChoice=scanner.nextInt();
			if(bookChoice==1)
			{
				 AddressBookChoice=addressBook1;
			}
			else
			{
				 AddressBookChoice=addressBook2;
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
						AddressBookChoice.addNewContact(person1);
							
					}
				
					break;
					
				case 2:

					System.out.println("Enter phone number of contact to be edited");
					phoneNumber=scanner.nextLine();
					System.out.println("Enter the field and value to be edited");
					String fieldToBeEdited = scanner.nextLine();
					String valueToBeEdited=scanner.nextLine();
					AddressBookChoice.editContact(phoneNumber,valueToBeEdited,fieldToBeEdited);
					
					break;
				case 3:
					System.out.println("Enter phone number of contact to be edited");
					phoneNumber=scanner.nextLine();
					AddressBookChoice.deleteContact(phoneNumber);
				
					break;
				case 4:
					System.out.println("Enter phone number of contact to be edited");
					phoneNumber=scanner.nextLine();
					AddressBookChoice.displayContactInfo(phoneNumber);
			}
		}
		
		scanner.close();
	}

}
