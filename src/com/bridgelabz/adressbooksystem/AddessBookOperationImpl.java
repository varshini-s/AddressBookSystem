package com.bridgelabz.adressbooksystem;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.function.Predicate;


public class AddessBookOperationImpl implements AddressBookOperationsIF

{

	AddressBookSystem addressbookSystem = new AddressBookSystem();


	public void createAddressBook(String addressbookName)
	{
		List<AddressBook> addressbookList=addressbookSystem.getAddressbookList();
		AddressBook addressBook = new AddressBook();
		addressBook.setAddressBookName(addressbookName);
		addressbookList.add(addressBook);	
		addressbookSystem.setAddressbookList(addressbookList);

	}

	public AddressBook getAddressBook(String addressbookName)
	{
		List<AddressBook> addressbookList=addressbookSystem.getAddressbookList();


		for(int index=0;index<addressbookList.size();index++)
		{

			if(addressbookList.get(index).getAddressBookName().equals(addressbookName))
			{
				return addressbookList.get(index);
			}
		}

		return null;
	}


	@Override
	public int hasContact(String number,String  addressbookName)
	{
		int givenContactIndex=-1;

		AddressBook addressbook=getAddressBook(addressbookName);
		List<Contact> contactList = addressbook.getContactList();
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
	public void addNewContact(Contact person,String  addressbookName) 
	{
		AddressBook addressbook=getAddressBook(addressbookName);
		List<Contact> contactList = addressbook.getContactList();

		contactList.add(person);
		Stream<Contact> stream=contactList.stream().distinct();

		contactList = contactList.stream()
				.distinct()
				.collect(Collectors.toList());

		addressbook.setContactList(contactList);


	}

	@Override
	public void editContact(String number, String editInfo, String choice,String  addressbookName) 
	{
		AddressBook addressbook=getAddressBook(addressbookName);


		List<Contact> contactList = addressbook.getContactList();

		int contactIndex=hasContact(number,addressbookName);
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
		displayContactInfo(number,addressbookName);

	}

	@Override
	public void deleteContact(String number,String  addressbookName) 

	{
		AddressBook addressbook=getAddressBook(addressbookName);

		List<Contact> newlist = addressbook.getContactList();
		int contactIndex=hasContact(number,addressbookName);
		if(contactIndex>=0)
		{

			newlist.remove(contactIndex);
		}

	}


	@Override
	public void displayContactInfo(String number,String  addressbookName) 

	{
		AddressBook addressbook=getAddressBook(addressbookName);

		List<Contact> contactList = addressbook.getContactList();

		int contactIndex=hasContact(number,addressbookName);
		if(contactIndex>=0)
		{
			Contact contact=contactList.get(contactIndex);
			System.out.println("address:"+contact.getAddress());
			System.out.println("city:"+contact.getCity());
			System.out.println("state:"+contact.getState());
			System.out.println("zip:"+contact.getZip());
			System.out.println("phone number:"+contact.getPhoneNumber());
			System.out.println("email:"+contact.getEmail());

		}


	}

	@Override
	public void searchPersonByState(String givenName,String state) 
	{

		List<AddressBook> addressbookList=addressbookSystem.getAddressbookList();

		System.out.println("all contacts in given name in given state are:");
		addressbookList.stream()
		.forEach(addressBook->addressBook.getContactList()
		.stream().filter(contact->contact.getState().equals(state) && contact.getFirstName().equals(givenName))
		.forEach(System.out::println));
	}


	@Override
	public void searchPersonByCity(String givenName,String city)  
	{
		
		List<AddressBook> addressbookList=addressbookSystem.getAddressbookList();

		System.out.println("all contacts in given name in given city are:");
		addressbookList.stream()
		.forEach(addressBook->addressBook.getContactList()
		.stream().filter(contact->contact.getCity().equals(city) && contact.getFirstName().equals(givenName))
		.forEach(System.out::println));

	}

	@Override
	public void getAllContactsInState(String state)
	{
		List<AddressBook> addressbookList=addressbookSystem.getAddressbookList();

		System.out.println("all contacts in given state are:");
		addressbookList.stream()
		.forEach(addressBook->addressBook.getContactList()
		.stream().filter(contact->contact.getState().equals(state)).forEach(System.out::println));

	}
	@Override
	public void getAllContactsInCity(String city)
	{

		List<AddressBook> addressbookList=addressbookSystem.getAddressbookList();

		System.out.println("all contacts in given city are:");
		addressbookList.stream()
		.forEach(addressBook->addressBook.getContactList()
		.stream().filter(contact->contact.getCity().equals(city)).forEach(System.out::println));

	}
	@Override
	public void countPeopleinCity(String city)
	{

		List<AddressBook> addressbookList=addressbookSystem.getAddressbookList();

		addressbookList.stream()
		.forEach(addressBook->System.out.println("Number of contacts of given city in addressbook"+addressBook.getAddressBookName()+" is"+addressBook.getContactList()
		.stream().filter(contact->contact.getCity().equals(city)).count()));


	}


	@Override
	public void countPeopleinState(String state)
	{
		List<AddressBook> addressbookList=addressbookSystem.getAddressbookList();

		addressbookList.stream()
		.forEach(addressBook->System.out.println("Number of contacts of given state in addressbook"+addressBook.getAddressBookName()+" is"+addressBook.getContactList()
		.stream().filter(contact->contact.getState().equals(state)).count()));

	}

	@Override
	public void sortByName() 
	{
		List<AddressBook> addressbookList=addressbookSystem.getAddressbookList();

		System.out.println("all contacts in given city are:");
		addressbookList.stream()
		.forEach(addressBook->addressBook.getContactList()
		.stream().sorted((person1,person2)->person1.getFirstName().compareTo(person2.getFirstName())).forEach(System.out::println));
	}
	
	@Override
	public void sortByCity() 
	{
		List<AddressBook> addressbookList=addressbookSystem.getAddressbookList();

		System.out.println("all contacts in given city are:");
		addressbookList.stream()
		.forEach(addressBook->addressBook.getContactList()
		.stream().sorted((person1,person2)->person1.getCity().compareTo(person2.getCity())).forEach(System.out::println));
	}
	
	@Override
	public void sortByState() 
	{
		List<AddressBook> addressbookList=addressbookSystem.getAddressbookList();

		System.out.println("all contacts in given city are:");
		addressbookList.stream()
		.forEach(addressBook->addressBook.getContactList()
		.stream().sorted((person1,person2)->person1.getState().compareTo(person2.getState())).forEach(System.out::println));
	}
	

}


