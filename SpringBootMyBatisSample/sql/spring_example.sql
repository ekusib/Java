create database spring_example;

use spring_example;

drop table if exists employee;

create table employee(
	id int auto_increment PRIMARY KEY,
	name varchar(500) not null,
	mail varchar(500) not null,
	position_no int not null,
	insert_dt datetime default  '2000/01/01',
	update_dt datetime default  '2000/01/01',
	index(id)
);


insert into employee(name, mail, position_no, insert_dt, update_dt) VALUES('�ɓ��T��Y', 'ito@sync-web.jp', 1, sysdate(), sysdate());
insert into employee(name, mail, position_no, insert_dt, update_dt) VALUES('�基��l', 'm-obuchi@sync-web.jp', 2, sysdate(), sysdate());
insert into employee(name, mail, position_no, insert_dt, update_dt) VALUES('�ʊ_�[��', 'm.tamagaki@sync-web.jp', 3, sysdate(), sysdate());
insert into employee(name, mail, position_no, insert_dt, update_dt) VALUES('������', 't.shimizu@sync-web.jp', 4, sysdate(), sysdate());

drop table if exists position_info;

create table position_info(
	id int not null,
	position_name varchar(500) not null,
	insert_dt datetime default  '2000/01/01',
	update_dt datetime default  '2000/01/01',
	index(id)
);


insert into position_info(id, position_name,insert_dt, update_dt) VALUES(1, '�В�', sysdate(), sysdate());
insert into position_info(id, position_name,insert_dt, update_dt) VALUES(2, '�}�l�[�W���[', sysdate(), sysdate());
insert into position_info(id, position_name,insert_dt, update_dt) VALUES(3, 'IT�X�y�V�����X�g', sysdate(), sysdate());
insert into position_info(id, position_name,insert_dt, update_dt) VALUES(4, '���[�_�[', sysdate(), sysdate());



drop table if exists login;

create table login(
	id int auto_increment PRIMARY KEY,
	name varchar(500) not null,
	password varchar(500) not null,
	insert_dt datetime default  '2000/01/01',
	update_dt datetime default  '2000/01/01',
	index(id)
);

insert into login(name, password, insert_dt, update_dt) VALUES('sampleUser', 'samplePass', sysdate(), sysdate());
