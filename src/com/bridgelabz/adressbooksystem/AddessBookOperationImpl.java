package com.bridgelabz.adressbooksystem;

public class AddessBookOperationImpl implements AddressBookOperationsIF

{
	
	@Override
	public int hasContact(String number,AddressBook addressbook)
	{
		Contact[] contactList = addressbook.getContactList();
		int numberOfContacts=addressbook.getNumberOfContacts();

		int position=-1;

		for (int index=0;index<numberOfContacts;index++)
		{
			if (contactList[index].getPhoneNumber().equals(number))

			{
				position=index;
			}
			
		}
		
		return position;
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
		Contact[] contactList = addressbook.getContactList();
		int numberOfContacts=addressbook.getNumberOfContacts();
		int contactIndex=hasContact(number,addressbook);
		if(contactIndex>=0)
		switch (choice) 
		{

			case "address":
				contactList[contactIndex].setAddress(editInfo);
				break;
			case "city":
				contactList[contactIndex].setCity(editInfo);
				break;
			case "state":
				contactList[contactIndex].setState(editInfo);
				break;
			case "zip":
				contactList[contactIndex].setZip(editInfo);
				break;
			case "phoneNumber":
				contactList[contactIndex].setPhoneNumber(editInfo);
				break;
			case "email":
				contactList[contactIndex].setEmail(editInfo);
				break;
				
		}
		
		System.out.println("After editing the details are:");
		displayContactInfo(number,addressbook);
		
	}

	@Override
	public void deleteContact(String number,AddressBook addressbook) 
	
	{
		Contact[] contactList = addressbook.getContactList();
		int numberOfContacts=addressbook.getNumberOfContacts();
		int contactIndex=hasContact(number,addressbook);
		if(contactIndex>=0)
		{
			while(contactIndex<numberOfContacts)
    		{
    			contactList[contactIndex]=contactList[contactIndex+1];
    			contactIndex++;
    		}
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
