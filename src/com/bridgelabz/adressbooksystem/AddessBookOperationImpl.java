package com.bridgelabz.adressbooksystem;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class AddessBookOperationImpl implements AddressBookOperationsIF

{
	
	@Override
	public int hasContact(String number,AddressBook addressbook)
	{
		int givenContactIndex=-1;

		List<Contact> contactList = addressbook.getNewlist();
		for(int index=0;index<contactList.size();index++)
		{

			if(contactList.get(index).getPhoneNumber().equals(number))
			{
				givenContactIndex=index;

			}
		}
		return givenContactIndex;
	}
	
	@Override
	public void addNewContact(Contact person,AddressBook addressbook) 
	{
		Contact[] contactList = addressbook.getContactList();
		int numberOfContacts=addressbook.getNumberOfContacts();
		contactList[numberOfContacts]=person;
		numberOfContacts++;
		addressbook.setNumberOfContacts(numberOfContacts);
		
	}

	@Override
	public void editContact(String number, String editInfo, String choice,AddressBook addressbook) 
	{

		List<Contact> contactList = addressbook.getNewlist();

		int contactIndex=hasContact(number,addressbook);
		if(contactIndex>=0)
			switch (choice) 
			{

			case "address":
				contactList.get(contactIndex).setAddress(editInfo);
				break;
			case "city":
				contactList.get(contactIndex).setCity(editInfo);
				break;
			case "state":
				contactList.get(contactIndex).setState(editInfo);
				break;
			case "zip":
				contactList.get(contactIndex).setZip(editInfo);
				break;
			case "phoneNumber":
				contactList.get(contactIndex).setPhoneNumber(editInfo);
				break;
			case "email":
				contactList.get(contactIndex).setEmail(editInfo);
				break;

			}

		System.out.println("After editing the details are:");
		displayContactInfo(number,addressbook);

	}

	@Override
	public void deleteContact(String number,AddressBook addressbook) 

	{
		List<Contact> newlist = addressbook.getNewlist();
		int contactIndex=hasContact(number,addressbook);
		if(contactIndex>=0)
		{

			newlist.remove(contactIndex);
		}

	}

	

	@Override
	public void displayContactInfo(String number,AddressBook addressbook) 
	
	{
		Contact[] contactList = addressbook.getContactList();
		int numberOfContacts=addressbook.getNumberOfContacts();

		int contactIndex=hasContact(number,addressbook);
		if(contactIndex>=0)
		{
			Contact contact=contactList[contactIndex];
			System.out.println("address:"+contact.getAddress());
			System.out.println("city:"+contact.getCity());
			System.out.println("state:"+contact.getState());
			System.out.println("zip:"+contact.getZip());
			System.out.println("phone number:"+contact.getPhoneNumber());
			System.out.println("email:"+contact.getEmail());
			
		}
		
		
	}

	
	
	
}
