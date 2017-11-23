create database szpital DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
use szpital;
#drop database szpital;

CREATE TABLE lekarze (
    id_l INT AUTO_INCREMENT,
    name VARCHAR(35),
    last VARCHAR(35),
    cellphone  VARCHAR(20),
    PRIMARY KEY (id_l)
);
#drop table lekarze;


CREATE TABLE pacjenci (
    id_p INT AUTO_INCREMENT,
    name VARCHAR(35),
    last VARCHAR(35),
    cellphone VARCHAR(20),
    pesel VARCHAR(11),
    PRIMARY KEY (id_p)
);
#drop table pacjenci;


CREATE TABLE wizyty(
    id_w INT AUTO_INCREMENT,
	date DATE,
    time time,
    id_l int,
    id_p int,
    PRIMARY KEY (id_w),
    foreign key (id_l) references lekarze (id_l),
    foreign key (id_p) references pacjenci (id_p)
);

insert into lekarze (name, last , cellphone) values ('Mariusz', 'Kowalski','667 668 633');
insert into lekarze (name, last , cellphone) values ('Grzegorz', 'Nowak','667 668 611');
insert into lekarze (name, last , cellphone) values ('Marek', 'Wiśniewski','667 668 622');
insert into lekarze (name, last , cellphone) values ('Michał', 'Kowal','667 668 655');

insert into pacjenci (name, last , cellphone, pesel) values ('Kunekunda', 'Jasielska','667 668 533', '88011213278' );
insert into pacjenci (name, last , cellphone, pesel) values ('Anna', 'Adamiak','667 668 783', '66012113278');
insert into pacjenci (name, last , cellphone, pesel) values ('Janusz', 'Antkowiak','667 668 783', '54011113278');
insert into pacjenci (name, last , cellphone, pesel) values ('Olbrycht', 'Smith','667 668 783', '98011113278');

insert into wizyty (date, time, id_l, id_p) values ('2016-12-10', '9:00',  1, 1);
insert into wizyty (date, time, id_l, id_p) values ('2017-06-10', '10:00',  2, 2);
insert into wizyty (date, time, id_l, id_p) values ('2017-12-10', '11:00',  3, 3);
insert into wizyty (date, time, id_l, id_p) values ('2018-01-01', '11:00',  4, 4);



Create view przyszle_wizyty as select id_w, date_format(date,'%Y-%m-%d') as date, time_format(time,'%H:%m') as hour, concat_ws(' ',lekarze.last,lekarze.name) as lekarz, concat_ws(' ',pacjenci.last,pacjenci.name) as pacjent 
	from 
    wizyty
    left join lekarze on lekarze.id_l = wizyty.id_l
    left join pacjenci on pacjenci.id_p = wizyty.id_p
	where wizyty.date >= curdate();
    
Create view przeszle_wizyty as select id_w, date_format(date,'%Y-%m-%d') as date, time_format(time,'%H:%m') as hour, concat_ws(' ',lekarze.last,lekarze.name) as lekarz, concat_ws(' ',pacjenci.last,pacjenci.name) as pacjent 
	from 
    wizyty
    left join lekarze on lekarze.id_l = wizyty.id_l
    left join pacjenci on pacjenci.id_p = wizyty.id_p
	where wizyty.date < curdate();
    
    

SELECT * FROM lekarze where id_l =5;

SELECT * FROM wizyty;
SELECT * FROM przyszle_wizyty;
SELECT * FROM przeszle_wizyty;
#drop database szpital;

