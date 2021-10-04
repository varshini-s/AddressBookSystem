package com.bridgelabz.adressbooksystem;


public class UserEntryException extends Exception
{
	 public enum ExceptionType 
	{
		ENTERED_NULL, ENTERED_EMPTY
	}

	public ExceptionType type;

	public UserEntryException(ExceptionType type, String message) 
	{

		super(message);
		this.type = type;

	}

}
