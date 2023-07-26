/**
* Agenda de contatos
*authora Poliana Caroline Lopes de souza
*/

-- comentarios em linha
-- exibir os bancos
show databases; 

-- apagar um banco de dados
drop database dbagenda;

-- criar um novo banco de dados
create database dbagenda;
 drop database dbagenda;
drop database  dbagenda;
-- selecionar o banco de dados a ser trabalhado
use dbagenda;

-- exibir tabelas do banco de dados
show tables;

-- criando uma tabela no banco de dados
-- int (tipo de dados: número inteiro)
-- primary key (chave primária)
-- auto_increment (numeração automática)
-- varchar (tipos de dados: String)
-- not null (campo obrigatório)
create table contatos (
 id int primary key auto_increment,
 nome varchar(50) not null,
 fone varchar(15) not null,
 email varchar(50)
 
);

-- descrever a tabela
describe contatos; 
-- selecionar tudo da tabela
select * from contatos;


