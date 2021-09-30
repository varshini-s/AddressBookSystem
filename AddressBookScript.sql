#UC 1
DROP DATABASE addressbook_service;
CREATE DATABASE addressbook_service;
USE addressbook_service;
SELECT DATABASE();


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
    date_added DATE NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (address_book_id) references address_book(address_book_id)

);

INSERT INTO contact(firstName,lastName,phoneNumber,email,address_book_id,date_added)
values('Bob', 'J', '9897899999', 'bob@example.com',1,'2019-01-07'),('amy', 'cooper', '6578445578', 'amy@example.com',1,'2020-08-21'),('raj', 'a', '9987899999', 'raj@example.com',2,'2018-01-03');

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




