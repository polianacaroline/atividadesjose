create table endereco(
idendereco bigint auto_increment primary key,
tipo enum ('rua, avenida,alaemda,viela,rodovia,estrada,praca'),
logradouro varchar(100) not null,
numero varchar(100) not null,
complemento varchar(50),
cep varchar(10) not null,
bairro varchar(30) not null
 ) engine innodb charset=utf8mb4 collate=utf8mb4_general_ci;
 
create table contato(
idcontato bigint auto_increment primary key,
telefone varchar(20) not null,
email varchar(100) not null
 ) engine innodb charset=utf8mb4 collate=utf8mb4_general_ci;
 
 create table cliente(
 idcliente bigint auto_increment primary key,
 nomecliente varchar(15) not null,
 cpf varchar(15) not null,
 idcontato  bigint not null,
 idendereco  bigint not null,
 idusuario bigint not null
  ) engine innodb charset=utf8mb4 collate=utf8mb4_general_ci;
  
  show tables;
show databases;


/*criar relacionamento entre as tabelas:
 -cliente -> Usuario
 -cliente -> Endereco
 -cliente -> Contato
*/


/*
Para Estabelecer o relacionamento das tabelas clientes e contatos foi necessario alterar
 a tabela cliente, adicionando uma restriçao com o nome (constraint) com o nome fk_cliente_pk_contato
 e fazendo com que o campo idcontato da tabela cliente seja uma chave estrangeira(foreign key) e estabelecendo
 uma referecia a tabela de contatos com o campo, que e chave primaria, idcontato e, assim receber as informaçoes
 de contato da tabela contato.
 esse tipo de relacionamento e chamado de  um-para-muitos, ou seja um contato para mais de um cliente.
 */
 
 
 
ALTER TABLE cliente
ADD constraint `fk_cliente_pk_contato`
FOREIGN KEY (`idcontato`)
REFERENCES `contato`(`idcontato`);

ALTER TABLE cliente
ADD constraint `fk_cliente_pk_endereco`
FOREIGN KEY `cliente`(`idendereco`)
REFERENCES `endereco`(`idendereco`);

ALTER TABLE cliente
ADD constraint`id_unico` UNIQUE(`idusuario`);


ALTER TABLE cliente
ADD constraint `fk_cliente_pk_usuario`
FOREIGN KEY `cliente`(`idusuario`)
REFERENCES `usuario`(`idusuario`);





 