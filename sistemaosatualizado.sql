
create table fornecedores(
idfornecedor int primary key auto_increment,
razaosocial varchar(50) not null,
nomefantasia varchar(50),
cnpj varchar(14)unique,
logradouro varchar(35) not null,
numero varchar(10) not null,
complemento varchar(20),
cep varchar(20),
bairro varchar(50) not null,
referencia varchar(50) not null,
cidade varchar(30) not null,
uf char(2),
telefone varchar(12),
celular varchar(12) not null,
email varchar(50) not null unique,
ie varchar(20),
site varchar(50),
vendedor varchar(35)
);

select * from fornecedores;
select * from produtos;
show databases; 

use dbsistema;



show tables;
drop table fornecedores;
drop table produtos;

create table produtos(
codigoproduto int primary key auto_increment,
barcode varchar(100)unique,
descricao varchar(100) not null,
foto  longblob,
estoque int not null,
estoquemin int not null,
valor decimal(10,2) not null,
medida char(2),
armazenagem varchar (25) not null,
idfornecedor int not null,
nome varchar(100) not null,
lote varchar (20) not null,
fabricante varchar(50),
dataent timestamp default current_timestamp,
dataval date not null,
lucro decimal (10,2),
foreign key (idfornecedor) references fornecedores(idfornecedor)
);

insert into produtos(descricao,estoque, estoquemin, valor, armazenagem, idfornecedor, nome, lote, dataval)
value ('Tv Samsung',10,2, 20000,'prateleira 5',1,'televisao samsung','2', 20280802);




-- date trabalhar em data (AAAA-MM-DD)
-- char /gasta menos memoria/( string de caracteres nao variaveis)
-- varchar (valores compimento)
-- longblob (armazena imagens no formato binario)



select * from usuarios;
select * from produtos;
drop table usuarios;
drop table produtos;
insert into usuarios(nome,login,senha,perfil)values('Poliana','poliana',md5('123@senac'), 'user');

select * from fornecedores
inner join produtos
on produtos.idfornecedor = fornecedores.idfornecedor;


create table usuarios (
    id int primary key auto_increment,
    nome varchar(50) not null,
    login varchar(15) not null unique,
    senha varchar(250) not null,
    perfil varchar(10) not null
);

