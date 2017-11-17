# VetClinic
VetClinic with Spring Boot and MySql

>mysql -u root -p

>mysql show databases;

>create database vet;
>use vet;

CREATE TABLE role(id INT AUTO_INCREMENT PRIMARY KEY
                , description VARCHAR(255)
           		, active VARCHAR(1) DEFAULT 'Y'		
                 );		

CREATE TABLE user (id INT AUTO_INCREMENT PRIMARY KEY
                 , fullName VARCHAR(255) NOT NULL
				 , email VARCHAR(255) NOT NULL
				 , function VARCHAR(255)
				 , active VARCHAR(1) DEFAULT 'Y'
				 , dateIn DATE NOT NULL
				 , dateOut DATE
				 , roleId INT NOT NULL
				 , foreign key (roleId) references role(id)
				 );
				 
CREATE TABLE client(id INT AUTO_INCREMENT PRIMARY KEY
                  , fullName VARCHAR(255) NOT NULL
                  , email VARCHAR(255) NOT NULL
                  , address VARCHAR(255)				  
                 );				 
				 
CREATE TABLE pet(id INT AUTO_INCREMENT PRIMARY KEY
               , name VARCHAR(255) NOT NULL
			   , category VARCHAR(255) NOT NULL
			   , breed VARCHAR(255)
			   , birthday DATE NOT NULL
			   , deathday DATE
			   , cause VARCHAR(255)
			   , owner INT
			   , foreign key (owner) references client(id)
               );				 

CREATE TABLE visit (id INT AUTO_INCREMENT PRIMARY KEY
                  , pet INT NOT NULL
				  , owner INT NOT NULL
				  , purpose VARCHAR(255)
                  , dateVisit DATE NOT NULL
				  , timeVisit TIME NOT NULL
                  , foreign key (pet) references pet(id)
				  , foreign key (owner) references client(id)
                  );


create user 'spring_user'@'localhost' identified by 'spring_password';

grant all privileges on *.* to 'spring_user'@'localhost' with grant option;

insert into role values(1, 'Admin', 'Y');

insert into user values(1, 'Erika', 'erikanbs@gmail.com', 'Admin', 'Y', CURDATE(), null, 1);

insert into client values(1, 'John', 'bla@bla.com', null);

insert into pet values(1, 'meauw', 'cat', 'default', '2017-01-01',null, null, 1);

