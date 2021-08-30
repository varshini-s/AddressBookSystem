package com.bridgelabz.adressbooksystem;


public class AddressBook 

{
	 private int numberOfContacts;
	 private Contact[] contactList = new Contact[10];
	 private String AddressBookName;
	 
	 
	 
	 public AddressBook() 
	 {
		numberOfContacts=0;
	}
	
	 public int getNumberOfContacts()
	 {
		 
		 return numberOfContacts;
	 }
	 
	 public String getAddressBookName()
	 {
		 return AddressBookName;
		 
	 }
	 public Contact[] getContactList()
	 {
		 
		 return contactList;
	 }
	 public void setNumberOfContacts(int numberOfContacts)
	 {
		 this.numberOfContacts=numberOfContacts;
		 
	 }
	 
	 public void setAddressBookName(String AddressBookName)
	 {
		 this.AddressBookName=AddressBookName;
		 
	 }
	 
	public void addNewContact(Contact person)
	{
		
		
	}
	
	public void editContact(String number,String editInfo,String choice)
	{
		
		int contactIndex=hasContact(number);

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
		displayContactInfo(number);
		
		
	}
	
	public int hasContact(String number)
	{	int position=-1;
		
		for (int index=0;index<numberOfContacts;index++)
		{
			if (contactList[index].getPhoneNumber().equals(number))

			{
				position=index;
			}
			
		}
		
		return position;
	}
	
	public void deleteContact(String number)
	
	{
		
		int contactIndex=hasContact(number);
		if(contactIndex>=0)
		{
			while(contactIndex<numberOfContacts)
    		{
    			contactList[contactIndex]=contactList[contactIndex+1];
    			contactIndex++;
    		}
		}
		
	    
		
	}
	
	public void displayContactInfo(String number)
	{
		int contactIndex=hasContact(number);
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
