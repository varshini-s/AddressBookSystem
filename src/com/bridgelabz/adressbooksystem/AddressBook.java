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
	public void deleteContact(String name)
	
	{
		
		map.remove(name);
		
		
	}
	
	public void displayContactInfo(String name)
	{
		Contact person=map.get(name);
		System.out.println("address:"+person.getAddress());
		System.out.println("city:"+person.getCity());
		System.out.println("state:"+person.getState());
		System.out.println("zip:"+person.getZip());
		System.out.println("phone number:"+person.getPhoneNumber());
		System.out.println("email:"+person.getEmail());

		
	}
	
	
	
	
	
	
}
