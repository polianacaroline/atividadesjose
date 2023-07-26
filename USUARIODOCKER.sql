insert into Usuario(login,senha,nivelacesso)
values('admin',md5('123'),'admin');
select * from Usuario;
select * from Usuario;


insert into Contato(telefonecelular,telefoneresidencial,email)
values('11-3636989','11-8585967','poliana@gmail.com');
desc Contato;
select * from Contato;

desc Endereco;
insert Endereco set tipo="Rua",logradouro="Padre Marcos", numero="23A",complemento="Apto 122 bloco 2", cep="03105-050",referencia="Poste azul",
bairro="Jardim novo", cidade="Sao Paulo", estado="SP";

desc Cliente;
insert Cliente set
nomecliente = "Andre Paz",
cpfcliente = "4545455-87",
rgcliente = "6565656",
sexocliente = "Masculino",
datanascimentocliente = "1957-02-10",
idendereco = 1,
idcontato = 1,
idusuario = 1;
 select * from Cliente;
 
 select
 cli.nomecliente, cli.cpfcliente,
 end.logradouro, end.bairro, end.cidade,
 con.telefonecelular, con.email,
 us.login, us.nivelacesso
 
 from Cliente cli inner join Usuario us on cli.idusuario=us.idusuario
 inner join Endereco end on cli.idendereco = end.idendereco
 inner join Contato con on cli.idcontato = con.idcontato;
 
 
 

