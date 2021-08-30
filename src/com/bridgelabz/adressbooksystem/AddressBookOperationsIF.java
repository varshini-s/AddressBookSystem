package com.bridgelabz.adressbooksystem;

public interface AddressBookOperationsIF 

{
	public void addNewContact(Contact person,AddressBook addressbook);
	public void editContact(String number,String editInfo,String choice,AddressBook addressbook);
	public void deleteContact(String number,AddressBook addressbook);
	public int hasContact(String number,AddressBook addressbook);
	public void displayContactInfo(String number,AddressBook addressbook);

	


}
