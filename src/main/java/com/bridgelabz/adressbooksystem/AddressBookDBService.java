package com.bridgelabz.adressbooksystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AddressBookDBService 
{
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



}
