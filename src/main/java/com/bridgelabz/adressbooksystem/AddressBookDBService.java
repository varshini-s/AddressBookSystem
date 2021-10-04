package com.bridgelabz.adressbooksystem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.ibatis.jdbc.ScriptRunner;


public class AddressBookDBService implements Runnable
{

	private static AddressBookDBService addressBookDBService;
	private PreparedStatement addressBookReadStatement;

	private Contact contact;
	String addressBookName;
	LocalDate dateAdded;
	
    public AddressBookDBService ()
    {
		
	}

	public AddressBookDBService(Contact contact,String  addressBookName,LocalDate dateAdded) 
	{
		this.contact=contact;
		this.addressBookName=addressBookName;
		this.dateAdded=dateAdded;
		
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
	
	public void setupDatabase() throws SQLException, FileNotFoundException
	{
		Connection connection = this.getConnection();
		ScriptRunner sr = new ScriptRunner(connection);
		Reader reader = new BufferedReader(new FileReader("AddressBookScript.sql"));
		sr.runScript(reader);


	}

	public List<ContactDTO> readContactList(String addressbookName) 

	{
		List<ContactDTO> contactList=new ArrayList<ContactDTO>();
		if(this.addressBookReadStatement==null)
		{
			String sql="SELECT * FROM contact JOIN address ON contact.id=address.contact_id JOIN address_book ON contact.address_book_id=address_book.address_book_id"
					+ " where address_book_name=?;";
			this.preparedStatementForGivenQuery(sql);
		}
		try
		{
			addressBookReadStatement.setString(1, addressbookName);
			ResultSet resultSet=addressBookReadStatement.executeQuery();
			contactList=this.getContactData(resultSet);

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return contactList;
	}

	private void preparedStatementForGivenQuery(String sql)
	{
		try
		{
			Connection connection = this.getConnection();
			addressBookReadStatement=connection.prepareStatement(sql);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	public List<ContactDTO> getContactData(String name) 
	{
		String sql = String.format("SELECT * FROM contact JOIN address ON contact.id=address.contact_id WHERE firstName='%s';",name);
		try (Connection connection = this.getConnection())
		{

			Statement statement=connection.createStatement();
			ResultSet resultSet= statement.executeQuery(sql);
			return this.getContactData(resultSet);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return null;
		
	}
	public List<ContactDTO> getContactData(ResultSet resultSet)
	{
		List<ContactDTO> contactList=new ArrayList<ContactDTO>();
		try 
		{

			while(resultSet.next())
			{
				int id=resultSet.getInt("id");
				String firstName=resultSet.getString("firstName");
				String lastName=resultSet.getString("lastName");
				String houseNumber=resultSet.getString("house_number");
				String street=resultSet.getString("street");
				String city=resultSet.getString("city");
				String state=resultSet.getString("state");
				String zip=resultSet.getString("zip");
				String phoneNumber=resultSet.getString("phoneNumber");
				String email=resultSet.getString("email");
				
				Address contactAddress = new Address(houseNumber, street, city, state, zip);
				ContactDTO contact = new ContactDTO(id,firstName, lastName,contactAddress,phoneNumber,email);
				contactList.add(contact);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}

		return contactList;

	}
	
	public int updateContactPhoneNumber(Contact contact, String phoneNumber) 
	{
		
		String sql = "UPDATE contact SET phoneNumber = ? WHERE firstName = ? AND lastName = ? AND email = ?;";
	
		
			this.preparedStatementForGivenQuery(sql);
	
		try
		{
			addressBookReadStatement.setString(1, contact.getPhoneNumber());
			addressBookReadStatement.setString(2, contact.getFirstName());
			addressBookReadStatement.setString(3, contact.getLastName());
			addressBookReadStatement.setString(4, contact.getEmail());


			return addressBookReadStatement.executeUpdate();

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return 0;

	}
	
	public int countOfContactsAddedInGivenDateRange(String startDate, String endDate)
	{
		String sql = String.format("SELECT * FROM contact JOIN address ON contact.id=address.contact_id"+
								 " WHERE date_added BETWEEN '%s' AND '%s';",Date.valueOf(startDate),Date.valueOf(endDate));

		 return getSelectQueryResult(sql).size();

	}
	public int countOfContactsInDataBase()
	{
		String sql = "SELECT * FROM contact JOIN address ON contact.id=address.contact_id;";
		 return getSelectQueryResult(sql).size();
		 
	}
	public List<ContactDTO> getSelectQueryResult(String sql)
	{
		try (Connection connection = this.getConnection())
		{

			Statement statement=connection.createStatement();
			ResultSet resultSet= statement.executeQuery(sql);
			return this.getContactData(resultSet);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return null;
		
		
	}
	

}

