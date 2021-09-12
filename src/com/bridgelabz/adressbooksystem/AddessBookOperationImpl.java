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
	public void addNewContact(Contact person,AddressBook addressbook) 
	{
		List<Contact> contactList = addressbook.getContactList();

		if(contactList.contains(person))
		{
			System.out.println("This contact already exists in the adressbook "+addressbook.getAddressBookName());

		}
		else
		{
			contactList.add(person);
			HashMap<String, List<Contact>> cityDictionary=addressbook.getCityDictionary();
			if(cityDictionary.containsKey(person.getCity()))
			{
				List<Contact> givenCityList = cityDictionary.get(person.getCity());
				givenCityList.add(person);
				cityDictionary.put(person.getCity(), givenCityList);

			}
			else
			{
				List<Contact> givenCityList = new LinkedList<Contact>();
				givenCityList.add(person);
				cityDictionary.put(person.getCity(), givenCityList);

			}


			HashMap<String, List<Contact>> stateDictionary=addressbook.getStateDictionary();
			if(stateDictionary.containsKey(person.getState()))
			{
				List<Contact> givenStateList = stateDictionary.get(person.getState());
				givenStateList.add(person);
				stateDictionary.put(person.getState(), givenStateList);

			}
			else
			{
				List<Contact> givenStateList = new LinkedList<Contact>();
				givenStateList.add(person);
				stateDictionary.put(person.getState(), givenStateList);

			}
		}



	}

	@Override
	public void editContact(String number, String editInfo, String choice,AddressBook addressbook) 
	{

		List<Contact> contactList = addressbook.getContactList();

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
		List<Contact> newlist = addressbook.getContactList();
		int contactIndex=hasContact(number,addressbook);
		if(contactIndex>=0)
		{

			newlist.remove(contactIndex);
		}

	}



	@Override
	public void displayContactInfo(String number,AddressBook addressbook) 

	{
		List<Contact> contactList = addressbook.getContactList();

		int contactIndex=hasContact(number,addressbook);
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
	public void searchPersonByState(String givenName,String state,AddressBook addressbook) 
	{

		HashMap<String, List<Contact>> stateDictionary=addressbook.getStateDictionary();
		if(stateDictionary.containsKey(state))
		{
			List<Contact> givenStateList = stateDictionary.get(state);
			System.out.println("In the addressbook "+addressbook.getAddressBookName()+" list of people in given name present in state "+state);
			for(int index=0;index<givenStateList.size();index++)
			{
				if(givenStateList.get(index).getFirstName().equals(givenName))
				{
					System.out.println(givenStateList.get(index).getFirstName()+" "+givenStateList.get(index).getLastName());
				}
			}

		}
		else
		{

			System.out.println("There's no contact list for state "+state);
		}

	}


	@Override
	public void searchPersonByCity(String givenName,String city,AddressBook addressbook)  
	{
		HashMap<String, List<Contact>> cityDictionary=addressbook.getCityDictionary();
		if(cityDictionary.containsKey(city))
		{
			List<Contact> givenCityList = cityDictionary.get(city);
			System.out.println("In the addressbook "+addressbook.getAddressBookName()+" list of people in given name present in city "+city);
			for(int index=0;index<givenCityList.size();index++)
			{


				if(givenCityList.get(index).getFirstName().equals(givenName))
				{
					System.out.println(givenCityList.get(index).getFirstName()+" "+givenCityList.get(index).getLastName());
				}
			}

		}
		else
		{
			System.out.println("There's no contact list for the city"+city);
		}


	}

	@Override
	public void getAllContactsInState(String state, AddressBook addressbook)
	{
		HashMap<String, List<Contact>> stateDictionary=addressbook.getStateDictionary();
		if(stateDictionary.containsKey(state))
		{
			List<Contact> givenStateList = stateDictionary.get(state);
			System.out.println("List of people in state "+state+" In the adressbook "+addressbook.getAddressBookName());
			for(int index=0;index<givenStateList.size();index++)
			{
				System.out.println(givenStateList.get(index).getFirstName()+" "+givenStateList.get(index).getLastName());
			}
		}
		else
		{

			System.out.println("There's no contact list for the state "+state);
		}


	}
	@Override
	public void getAllContactsInCity(String city, AddressBook addressbook) 
	{
		HashMap<String, List<Contact>> cityDictionary=addressbook.getCityDictionary();
		if(cityDictionary.containsKey(city))
		{
			List<Contact> givenCityList = cityDictionary.get(city);
			System.out.println("List of people in city "+city+" In the adressbook "+addressbook.getAddressBookName());
			for(int index=0;index<givenCityList.size();index++)
			{
				System.out.println(givenCityList.get(index).getFirstName()+" "+givenCityList.get(index).getLastName());
			}
		}
		else
		{

			System.out.println("There's no contact list for the city"+city);
		}

	}
	@Override
	public int countPeopleinCity(String city, AddressBook addressbook)
	{
		HashMap<String, List<Contact>> cityDictionary=addressbook.getCityDictionary();
		if(cityDictionary.containsKey(city))
		{

			return cityDictionary.get(city).size();
		}
		else
		{

			return 0;
		}
	}

	@Override
	public int countPeopleinState(String state, AddressBook addressbook)
	{
		HashMap<String, List<Contact>> stateDictionary=addressbook.getStateDictionary();
		if(stateDictionary.containsKey(state))
		{
			return stateDictionary.get(state).size();
		}
		else
		{

			return 0;
		}

	}






}
