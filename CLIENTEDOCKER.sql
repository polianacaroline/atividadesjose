create database bancoacesso charset=utf8mb4 collate=utf8mb4_general_ci;
use bancoacesso;
create table cliente(
idcliente int auto_increment primary key,
nomecliente varchar(100) not null,
email varchar(100) not null,
cpf varchar(20) not null unique,
telefone varchar(15) not null,
idade int not null
)engine Innodb charset=utf8mb4 collate=utf8mb4_general_ci;

insert into cliente(nomecliente,email,cpf,telefone,idade)
values('Poliana','poliana@gmail.com','454545458','7785896',19);

insert into cliente(nomecliente,email,cpf,telefone,idade)
values('larissa','larissa@gmail.com','45445656','11252563',20);
insert into cliente(nomecliente,email,cpf,telefone,idade)
values('thainara','thainara@gmail.com','4545898958','745455896',19);
insert into cliente(nomecliente,email,cpf,telefone,idade)
values('thaina','thaina@gmail.com','4545454777','778555',18);
insert into cliente(nomecliente,email,cpf,telefone,idade)
values('bervely','bervely@gmail.com','8585545458','778588989',25);
insert into cliente(nomecliente,email,cpf,telefone,idade)
values('lucas','lucas@gmail.com','454545458','77858999',26);
insert into cliente(nomecliente,email,cpf,telefone,idade)
values('amanda','amanda@gmail.com','8787545458','7785898',36);
insert into cliente(nomecliente,email,cpf,telefone,idade)
values('yury','yury@gmail.com','454545433','778554632',89);

select * from cliente;

drop table cliente;