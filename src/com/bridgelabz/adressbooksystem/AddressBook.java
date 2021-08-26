package com.bridgelabz.adressbooksystem;

import java.util.HashMap;

public class AddressBook 

{
	
	HashMap<String, Contact> map = new HashMap<String,Contact>();
	
	
	public void addNewContact(Contact person)
	{
		map.put(person.getFirstName()+person.getLastName(),person);
		
	}
	
	
	
	
	
}
