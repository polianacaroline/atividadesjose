SELECT * FROM dbsistema.produtos;

create table fornecedores (
    idfornecedor int primary key auto_increment,
    razaosocial varchar(50) not null,
    nomefantasia varchar(50),
    cnpj varchar(14) unique,
    logradouro varchar(35) not null,
    numero varchar(10) not null,
    complemento varchar(20),
    cep varchar(20),
    bairro varchar(50) not null,
    referencia varchar(50),
    cidade varchar(30) not null,
    uf char(2),
    telefone varchar(12),
    celular varchar(12) not null,
    email varchar(50) not null,
    site varchar(45),
    vendedor varchar(45),
    ie varchar(45)

);



create table produtos (
codigo int primary key auto_increment,
barcode varchar(100) unique,
descricao varchar(100) not null,
foto longblob,
estoque int not null,
estoquemin int not null,
valor decimal(10,2) not null,
medida char(2) not null,
armazenagem varchar(50) not null,
idfornecedor int not null,
nome varchar(100) not null,
lote varchar(20) not null,
fabricante varchar(20),
lucro decimal(10,2),
dataent timestamp default current_timestamp,
dataval date,
razaosocial varchar(50) not null,
foreign key (idfornecedor) references fornecedores(idfornecedor),
foreign key (razaosocial) references fornecedores(razaosocial)

);

drop table fornecedores;


select * from fornecedores;

select * from produtos inner join fornecedores
on fornecedores.idfornecedor = produtos.idfornecedor where barcode = 1;



-- reposicao de estoque
select codigo as código,nome,date_format(dataval, '%d/%m/%y') as validade,estoque,
 estoquemin as estoque_mínimo from produtos where estoque < estoquemin;
 
 -- produtos vencidos
 select codigo as código,nome,
 date_format(dataval, '%d/%m/%y') as validade,
 date_format(dataval, '%d/%m/%y') as entrada
from produtos where dataval < dataent;
  
  -- custos dos produtos(patrimonio)
  select sum(valor * estoque) as Total from produtos;
  
  
  
  
  
  
  
 
 
 
 










