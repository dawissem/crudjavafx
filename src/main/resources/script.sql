create database crud;
use crud;

create table students(
id int auto_increment primary Key,
firstName varchar(255) not null,
lastName varchar(255) not null,
birthDate varchar (255),
course varchar(255) not null
);

insert into students
(firstName,lastName,birthDate,course)
values ("wissem","MANAI","21/07/2000","Java");

insert into students
(firstName, lastName, birthDate, course )
values ("Mohamed", "Ben ali", "15/04/1998", "Base de données");

insert into students
(firstName,lastName,birthDate, course )
values ("amine", "Hamra", "01/12/1999", "UML");



-- hedha 3a mysql shell
insert into students
(firstName, lastName, birthDate, course )
values ("wissemaaa", "MANAIaaaa", "21/05/1992", "Javaaaa");