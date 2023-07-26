package br.com.projeto.papelaria.test;

import br.com.projeto.papelaria.dao.DAOContato;
import br.com.projeto.papelaria.dominio.Contato;

public class TesteObjeto {

	public static void main(String[] args) {
		
		Contato ct = new Contato();
		
		ct.setTelefone("1156562525");
		ct.setEmail("exemplo@gmail.com");
		
		
		DAOContato dc = new DAOContato();
		System.out.println(dc.cadastro(ct));
		

		
		
	}

}
