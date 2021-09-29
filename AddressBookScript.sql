#UC 1
DROP DATABASE addressbook_service;
CREATE DATABASE addressbook_service;
USE addressbook_service;
SELECT DATABASE();

#UC 2
CREATE TABLE address_book 
(
    firstName VARCHAR(20) NOT NULL,
    lastName VARCHAR(20) NOT NULL,
    address VARCHAR(100) NOT NULL,
    city VARCHAR(20) NOT NULL,
    state VARCHAR(20) NOT NULL,
    zip VARCHAR(10) NOT NULL,
    phoneNumber CHAR(10) NOT NULL,
    email VARCHAR(20) NOT NULL
    
);

#UC 3
INSERT INTO address_book (firstName, lastName, address, city, state, zip, phoneNumber, email) VALUES 
('Raj', 'A', 'aaa', 'mysore', 'karnataka', '571771', '9897889999', 'abc@gmail.com'),
('amy', 'cooper', 'abc', 'bangalore', 'karnataka', '43566', '7898887188', 'amy@gmail.com'),
('Bob', 'J', 'groov street', 'bangalore', 'karnataka', '4566', '9897899999', 'bob@example.com');

#UC 4
UPDATE address_book 
SET address='ert street' 
WHERE firstName='Raj';

#UC 5
DELETE FROM address_book 
WHERE firstName='Bob';

#UC 9
ALTER TABLE address_book
ADD addressBookName varchar(20);

ALTER TABLE address_book
ADD addressBookType varchar(20);

UPDATE address_book 
SET addressBookName='book1',addressBookType='family'
WHERE firstName='Raj';

UPDATE address_book 
SET addressBookName='book2',addressBookType='friends'
WHERE firstName='amy';

INSERT INTO address_book (firstName, lastName, address, city, state, zip, phoneNumber, email,addressBookName,addressBookType) 
VALUES ('penny', 'clair', 'aaa street', 'bangalore', 'karnataka', '345', '9897098999', 'penny@example.com','book1','profession');
sBookType='family';


INSERT INTO address_book (firstName, lastName, address, city, state, zip, phoneNumber, email,addressBookName,addressBookType) 
VALUES ('Bob', 'J', 'groov street', 'bangalore', 'karnataka', '4566', '9897899999', 'bob@example.com','book2','friends');

INSERT INTO address_book (firstName, lastName, address, city, state, zip, phoneNumber, email,addressBookName,addressBookType) 
VALUES ('Bob', 'J', 'groov street', 'bangalore', 'karnataka', '4566', '9897899999', 'bob@example.com','book2','family');

DROP TABLE address_book;

CREATE TABLE address_book 
(
	address_book_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    address_book_name varchar(20) NOT NULL,

    PRIMARY KEY (address_book_id)
);

INSERT INTO address_book (address_book_name)
values ('book1'),('book2');

CREATE TABLE contact (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    firstName VARCHAR(20) NOT NULL,
    lastName VARCHAR(20) NOT NULL,
    phoneNumber CHAR(10) NOT NULL,
    email VARCHAR(20) NOT NULL,
    address_book_id INT UNSIGNED NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (address_book_id) references address_book(address_book_id)

);

INSERT INTO contact(firstName,lastName,phoneNumber,email,address_book_id)
values('Bob', 'J', '9897899999', 'bob@example.com',1),('amy', 'cooper', '6578445578', 'amy@example.com',1),('raj', 'a', '9987899999', 'raj@example.com',2);

CREATE TABLE address_book_contact
(
	address_book_id INT UNSIGNED NOT NULL,
    contact_id INT UNSIGNED NOT NULL,
	FOREIGN KEY (address_book_id) references address_book(address_book_id),
    FOREIGN KEY (contact_id) references contact(id)

);


INSERT INTO address_book_contact
VALUES(1,2),(2,2),(1,3),(2,1);

CREATE TABLE address 
(
    contact_id INT UNSIGNED NOT NULL AUTO_INCREMENT, 
    house_number VARCHAR(5) NOT NULL,
    street VARCHAR(100) NOT NULL,
    city VARCHAR(20) NOT NULL,
    state VARCHAR(20) NOT NULL,
    zip VARCHAR(10) NOT NULL,
    PRIMARY KEY (contact_id)    
);

INSERT INTO address
VALUES (1,23,'aaa','Mysore','Karnataka',12345),(2,34,'ert','Bangalore','Karnataka',45667),(3,234,'trr','Mysore','Karnataka',87666);

CREATE TABLE address_book_type 
(
    address_book_id INT UNSIGNED NOT NULL , 
    address_book_type VARCHAR(10) NOT NULL,
    FOREIGN KEY (address_book_id) references address_book(address_book_id)
    
);

INSERT INTO address_book_type
VALUES(1,'Friend'),(2,'Friend');




