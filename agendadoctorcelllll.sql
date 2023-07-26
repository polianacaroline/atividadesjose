/**
* Sistema para gestão de OS
*@authora Poliana Caroline
*/
drop database dbsistema;
create database dbsistema;
use dbsistema;
 create table usuarios(
 id int primary key auto_increment,
 nome varchar(50) not null,
 Login varchar(15) not null unique,
 Senha varchar(250)
);

describe usuarios;
-- uso do md5() para criptografar uma senha

drop table usuarios;
select * from usuarios;


show databases;
show tables;
insert into usuarios (nome,login,senha)
values ('henrique','admin',md5('admin')); 
select * from usuarios where login = "admin";
select * from login where login = "admin";
-- login(autenticaçao)
select * from usuarios where login = 'admin' and senha = md5('admin');


