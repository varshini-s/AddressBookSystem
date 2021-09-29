package com.bridgelabz.adressbooksystem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.jdbc.ScriptRunner;


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
	
	public void setupDatabase() throws SQLException, FileNotFoundException
	{
		Connection connection = this.getConnection();
		ScriptRunner sr = new ScriptRunner(connection);
		Reader reader = new BufferedReader(new FileReader("AddressBookScript.sql"));
		sr.runScript(reader);


	}

	public List<Contact> readContactList(String addressbookName) 

	{
		List<Contact> contactList=new ArrayList<Contact>();
		if(this.addressBookReadStatement==null)
		{
			this.preparedStatementForContactData();
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

	private void preparedStatementForContactData()
	{
		try
		{
			Connection connection = this.getConnection();
			String sql="SELECT * FROM contact JOIN address ON contact.id=address.contact_id JOIN address_book ON contact.address_book_id=address_book.address_book_id"
					+ " where address_book_name=?;";

			addressBookReadStatement=connection.prepareStatement(sql);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public List<Contact> readContactListOfState(String givenState) 
	{
		String sql=String.format("SELECT * FROM contact JOIN address ON contact.id=address.contact_id"+
				" where address.state=\"%s\";",givenState);

		List<Contact> contactList=new ArrayList<Contact>();
		try (Connection connection = this.getConnection())
		{

			Statement statement=connection.createStatement();
			ResultSet resultSet=statement.executeQuery(sql);
			contactList=this.getContactData(resultSet);

		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}

		return contactList;
	}

	public List<Contact> getContactData(ResultSet resultSet)
	{
		List<Contact> contactList=new ArrayList<Contact>();
		try 
		{

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
				
				Address contactAddress = new Address(houseNumber, street, city, state, zip);
				Contact contact = new Contact(firstName, lastName,phoneNumber,email);
				contact.setContactAddress(contactAddress);
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
		String sql=String.format("SELECT count(id) FROM contact JOIN address ON contact.id=address.contact_id"+
				" JOIN address_book ON contact.address_book_id=address_book.address_book_id where contact.id in"+
				" (Select contact_id from address where state=\"%s\" and city=\"%s\") and address_book_name=\"%s\";",state,city,addressBook);

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

		String sql=String.format("SELECT * FROM contact JOIN address ON contact.id=address.contact_id"+
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

	public Contact addContact(String firstName,String lastName,String houseNumber,String street,String city,String state,String zip,String phoneNumber,String email,int addressBookId)
	{
		int contactId=-1;
		Connection connection=null;
		Contact contact=null;

		try 
		{
			connection=this.getConnection();
			connection.setAutoCommit(false);
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}

		try(Statement statement=connection.createStatement())
		{
			String sql=String.format("INSERT INTO contact(firstName,lastName,phoneNumber,email,address_book_id) "
					+ "values ('%s','%s','%s','%s','%s');",firstName,lastName,phoneNumber,email,addressBookId);
			int rowAffected = statement.executeUpdate(sql,statement.RETURN_GENERATED_KEYS);
			if(rowAffected==1)
			{
				ResultSet resultSet=statement.getGeneratedKeys();
				if(resultSet.next()) contactId=resultSet.getInt(1);
			}

		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			try 
			{
				connection.rollback();

			} catch (Exception ex) 
			{

				ex.printStackTrace();
			}
		}


		try(Statement statement=connection.createStatement())
		{

			String sql=String.format("Insert into address values "
					+ "('%d','%s','%s','%s','%s','%s');",contactId,houseNumber,street,city,state,zip);
			int rowAffected=statement.executeUpdate(sql);
			if(rowAffected==1)
			{
				contact = new Contact(firstName,lastName,phoneNumber,email);
				contact.setContactAddress(new Address(houseNumber, street, city, state, zip));

			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			try 
			{
				connection.rollback();

			} catch (Exception ex) 
			{
				ex.printStackTrace();
			}
		}
		try 
		{
			connection.commit();

		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			if(connection!=null)
			{
				try 
				{
					connection.close();

				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		}

		
		return contact;
	}

	public List<Contact> getContactData(String name) 
	{
		String sql = String.format("SELECT * from contact WHERE firstName=?'%s';",name);
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



