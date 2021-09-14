package com.bridgelabz.adressbooksystem;

public interface AddressBookOperationsIF 

{
	public void addNewContact(Contact person,String  addressbookName);
	public void editContact(String number,String editInfo,String choice,String  addressbookName);
	public void deleteContact(String number,String  addressbookName);
	public int hasContact(String number,String  addressbookName);
	public void displayContactInfo(String number,String  addressbookName);
	public void searchPersonByState(String givenName,String state); 
	public void searchPersonByCity(String name,String city);
	public void getAllContactsInState(String state);
	public void getAllContactsInCity(String city);


	


}
