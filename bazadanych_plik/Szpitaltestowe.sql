create database szpital DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
use szpital;

CREATE TABLE lekarze (
    id_l INT AUTO_INCREMENT,
    name VARCHAR(35),
    last VARCHAR(35),
    cellphone INT,

    PRIMARY KEY (id_l)
);
#drop table lekarze;


CREATE TABLE pacjenci (
    id_p INT AUTO_INCREMENT,
    name VARCHAR(35),
    last VARCHAR(35),
    cellphone VARCHAR(12),
    pesel VARCHAR(11),
    PRIMARY KEY (id_p)
);
#drop table pacjenci;


CREATE TABLE wizyty(
    id_w INT AUTO_INCREMENT,
	date TIME,
    id_l int,
    id_p int,
    PRIMARY KEY (id_w),
    foreign key (id_l) references lekarze (id_l),
    foreign key (id_p) references pacjenci (id_p)
);



insert into lekarze (name, last , cellphone, monday_begin, monday_finish) values ('Mariusz', 'Kowalski', 666666666, '8:30',now());


SELECT 
    id as 'Lp.',name as Imię, last as Nazwisko, cellphone Telefon, concat_ws(' - ',DATE_FORMAT(monday_begin, '%H:%i'),DATE_FORMAT(monday_finish, '%H:%i')) as Poniedziałek, monday_begin
FROM
    lekarze;

#drop database szpital;

#select date_format(yourDateColumn, '%h:%i');