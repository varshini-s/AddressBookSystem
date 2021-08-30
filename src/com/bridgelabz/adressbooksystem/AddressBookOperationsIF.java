package com.bridgelabz.adressbooksystem;

public interface AddressBookOperationsIF 

{
	public void addNewContact(Contact person);
	public void editContact(String number,String editInfo,String choice);
	public void deleteContact(String number);
	public void displayContactInfo(String number);

	


}
