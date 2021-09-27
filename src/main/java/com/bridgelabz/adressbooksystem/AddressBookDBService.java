package com.bridgelabz.adressbooksystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class AddressBookDBService 
{
	
	private static AddressBookDBService addressBookDBService;
	private PreparedStatement addressBookReadStatement;
	
	public AddressBookDBService()
	{

	}

	public static AddressBookDBService getInstance()
	{
		if(addressBookDBService==null)
		{
			addressBookDBService=new AddressBookDBService();
		}
		return  addressBookDBService;
	}
	private Connection getConnection() throws SQLException 
	{
		String jdbcURL ="jdbc:mysql://localhost:3306/addressbook_service?allowPublicKeyRetrieval=true&useSSL=false";
		String username="root";
		String password="Demo@1234";
		Connection connection = null;
		System.out.println("Connecting to database"+jdbcURL);
		connection=DriverManager.getConnection(jdbcURL,username,password);
		System.out.println("Connection is successfull!!"+connection);


		return connection;
	}

	public List<Contact> readContactList(String addressbookName) 

	{

		String sql=String.format("SELECT * FROM contact JOIN address ON contact.address_id=address.address_id JOIN address_book ON contact.address_book_id=address_book.address_book_id"
				+ " where address_book_name=\"%s\";",addressbookName);



		List<Contact> contactList=new ArrayList<Contact>();
		try (Connection connection = this.getConnection())
		{

			Statement statement=connection.createStatement();
			ResultSet resultSet=statement.executeQuery(sql);


			while(resultSet.next())
			{
				String firstName=resultSet.getString("firstName");
				String lastName=resultSet.getString("lastName");
				String houseNumber=resultSet.getString("house_number");
				String street=resultSet.getString("street");
				String city=resultSet.getString("city");
				String state=resultSet.getString("state");
				String zip=resultSet.getString("zip");
				String address=houseNumber+street+city+zip;
				String phoneNumber=resultSet.getString("phoneNumber");
				String email=resultSet.getString("email");


				contactList.add(new Contact(firstName, lastName, address,city,state,zip,phoneNumber,email));
			}

		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}

		return contactList;
	}

	public List<Contact> readContactListOfState(String givenState) 
	{
		String sql=String.format("SELECT * FROM contact JOIN address ON contact.address_id=address.address_id"+
				" where address.state=\"%s\";",givenState);

		List<Contact> contactList=new ArrayList<Contact>();
		try (Connection connection = this.getConnection())
		{

			Statement statement=connection.createStatement();
			ResultSet resultSet=statement.executeQuery(sql);


			while(resultSet.next())
			{
				String firstName=resultSet.getString("firstName");
				String lastName=resultSet.getString("lastName");
				String houseNumber=resultSet.getString("house_number");
				String street=resultSet.getString("street");
				String city=resultSet.getString("city");
				String state=resultSet.getString("state");
				String zip=resultSet.getString("zip");
				String address=houseNumber+street+city+zip;
				String phoneNumber=resultSet.getString("phoneNumber");
				String email=resultSet.getString("email");


				contactList.add(new Contact(firstName, lastName, address,city,state,zip,phoneNumber,email));
			}

		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}

		return contactList;

	}

	public int countOfContactsInGivenStateCity(String city, String state, String addressBook) 
	{

		int count=0;
		String sql=String.format("SELECT count(id) FROM contact JOIN address ON contact.address_id=address.address_id"+
				" JOIN address_book ON contact.address_book_id=address_book.address_book_id where contact.address_id in"+
				" (Select address_id from address where state=\"%s\" and city=\"%s\") and address_book_name=\"%s\";",state,city,addressBook);


		try (Connection connection = this.getConnection())
		{

			Statement statement=connection.createStatement();
			ResultSet resultSet=statement.executeQuery(sql);
			while(resultSet.next())
			{
				count++;
			}
			return count;

		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return 0;




	}

	public List<String> getSortedContactByName(String city) 
	{

		String sql=String.format("SELECT * FROM contact JOIN address ON contact.address_id=address.address_id"+
				" where address.city=\"%s\" ORDER BY firstName ASC;",city);




		List<String > sortedContactList = new ArrayList<String>();
		try (Connection connection = this.getConnection())
		{

			Statement statement=connection.createStatement();
			ResultSet resultSet=statement.executeQuery(sql);


			while(resultSet.next())
			{
				String firstName=resultSet.getString("firstName");
				sortedContactList.add(firstName);
			}

			return sortedContactList;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}


		return null;
	}

	public int countOfContactsInGivenType(String type) 
	{
		int count=0;
		String sql=String.format("SELECT count(id) FROM address_book JOIN address_book_type" + 
				" ON address_book.address_book_id=address_book_type.address_book_id JOIN contact"+
				" ON address_book.address_book_id=contact.address_book_id"+
				" where address_book_type.address_book_type=\"%s\";",type);
		try (Connection connection = this.getConnection())
		{

			Statement statement=connection.createStatement();
			ResultSet resultSet=statement.executeQuery(sql);
			while(resultSet.next())
			{
				count=resultSet.getInt("count(id)");
			}
			return count;

		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return 0;

	}
}
