package com.bridgelabz.addressbooksystem;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;

import com.bridgelabz.adressbooksystem.AddessBookServiceImpl;
import com.bridgelabz.adressbooksystem.AddressBookSystem;
import com.bridgelabz.adressbooksystem.Contact;

public class AddressBookServiceTest 
{

	AddessBookServiceImpl addressBookOperations;
	AddressBookSystem addressBookSystem = new AddressBookSystem();
	Contact contact1,contact2,contact3,contact4,contact5,contact6;

	String addressBookName;

	ByteArrayOutputStream byteArrayOutputStream;
	PrintStream printStream;

	@Before
	public void initialSetUp()
	{
		addressBookOperations = new AddessBookServiceImpl();

		addressBookOperations.createAddressBook("Addressbook1");
		addressBookName="Addressbook1";
		contact1=new Contact("Sheldon","Cooper","texas","aaa","california","11134","12345","abc@gmail.com");
		contact2=new Contact("Ross ","Geller","groov street","aaa","New york","12311","89897677","ross@gmail.com");
		contact3=new Contact("Ted","Mobsby","aaa","bbb","New york","44562","567890999","ted@gmail.com");
		addressBookOperations.addNewContact(contact1,addressBookName);
		addressBookOperations.addNewContact(contact2,addressBookName);
		addressBookOperations.addNewContact(contact3,addressBookName);

		addressBookOperations.createAddressBook("Addressbook2");
		addressBookName="Addressbook2";
		contact4=new Contact("james","b","texas","pasadena","california","11134","12345","abc@gmail.com");
		contact5=new Contact("susan ","aaa","groov street","aaa","New york","12311","89897677","ross@gmail.com");
		contact6=new Contact("alan","c","aaa","bbb","new york","44562","567890999","ted@gmail.com");
		addressBookOperations.addNewContact(contact4,addressBookName);
		addressBookOperations.addNewContact(contact5,addressBookName);
		addressBookOperations.addNewContact(contact6,addressBookName);	


		byteArrayOutputStream = new ByteArrayOutputStream();
		printStream = new PrintStream(byteArrayOutputStream);
	}


	
}
