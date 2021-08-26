package com.bridgelabz.adressbooksystem;

import java.util.Scanner;

public class AddressBookMain 
{

	public static void main(String[] args) 
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to Address book system program");
		
		AddressBook info = new AddressBook();

		String firstName,lastName,address,city,state,zip,phoneNumber,email;
		
		int choice=1;
				
		while(choice!=5)
		{
			System.out.println("Enter 1 to add contact,2 to edit contact,3 to delete,4 to display contact,5 to stop");
			System.out.println("Enter choice");
			 choice=scanner.nextInt();
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
					Contact person1= new Contact(firstName,lastName,address,city,state,zip,phoneNumber,email);
					info.addNewContact(person1);
					
					break;
					
				case 2:

					System.out.println("Enter first name and last name of contact to be edited");
					firstName=scanner.nextLine();
					lastName=scanner.nextLine();
					System.out.println("Enter the field and value to be edited");
					String fieldToBeEdited = scanner.nextLine();
					String valueToBeEdited=scanner.nextLine();
					info.editContact(firstName+lastName,valueToBeEdited,fieldToBeEdited);
					
					break;
				case 3:
					System.out.println("Enter first name and last name of contact to be deleted");
					firstName=scanner.nextLine();
					lastName=scanner.nextLine();
					info.deleteContact(firstName+lastName);
				
					break;
				case 4:
					System.out.println("Enter first name and last name of contact to be displayed");
					firstName=scanner.nextLine();
					lastName=scanner.nextLine();
					info.displayContactInfo(firstName+lastName);
			}
		}
		
		
		scanner.close();

	}

}
