package com.bridgelabz.adressbooksystem;

import java.util.HashMap;



public class AddressBook 

{
	
	HashMap<String, Contact> map = new HashMap<String,Contact>();
	
	
	public void addNewContact(Contact person)
	{
		map.put(person.getFirstName()+person.getLastName(),person);
		
	}
	
	public void editContact(String name,String editInfo,String choice)
	{
		Contact person=map.get(name);
		
		switch (choice) 
		{

			case "address":
				person.setAddress(editInfo);
				map.put(person.getFirstName()+person.getLastName(),person);
				
				break;
			case "city":
				person.setCity(editInfo);
				map.put(person.getFirstName()+person.getLastName(),person);
				break;
			case "state":
				person.setState(editInfo);
				map.put(person.getFirstName()+person.getLastName(),person);
				break;
			case "zip":
				person.setZip(editInfo);
				map.put(person.getFirstName()+person.getLastName(),person);
				break;
			case "phoneNumber":
				person.setPhoneNumber(editInfo);
				map.put(person.getFirstName()+person.getLastName(),person);
				break;
			case "email":
				person.setEmail(editInfo);
				map.put(person.getFirstName()+person.getLastName(),person);
				break;
			
	
			
		}
		
	}
	
	
	
	
}
