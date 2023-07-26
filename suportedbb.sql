create database suportedb charset=utf8mb4 collate=utf8mb4_general_ci;

use suportedb;

create table chamado( 
idchamado bigint auto_increment primary key, 
solicitacao varchar(200) not null, 
departamentosolicitado varchar(200) not null, 
descricaochamado text not null, 
dataabertura varchar(20), 
dataresolucao varchar(20), 
statuschamado varchar(20), 
observacoes text, 
atendente varchar(200) 
)engine innodb charset=utf8mb4 collate=utf8mb4_general_ci; 

insert into  chamado(solicitacao,departamentosolicitado,descricaochamado,dataabertura,dataresolucao,statuschamado,observacoes,atendente)
values ('tela do celular trincada','assistencia tecnica','celular caiu','20/05/2023','20/07/2023','aberto','esta em observacao','mauro');



select * from chamado;