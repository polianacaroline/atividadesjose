CREATE DATABASE produtosdb
DEFAULT CHARACTER SET = 'utf8mb4';

use produtosdb;

create table produtos(
idproduto INT AUTO_INCREMENT  PRIMARY KEY,
nomeproduto VARCHAR(100),
descricao TEXT,
categoria VARCHAR(50),
lote varchar(20),
datafabricacao varchar(20),
datavalidade varchar(20),
preco DECIMAL (10,2),
imagemproduto varchar(200)
);
