package br.com.projeto.papelaria.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import br.com.projeto.papelaria.dominio.Contato;
import br.com.projeto.papelaria.repository.AtualizarApagar;

public class DAOContato extends Conexao implements AtualizarApagar<Contato>{

	@Override
	public String cadastro(Contato obj) {
		String idcontato = "";
		
		try {
			//abrir o banco
		abrirBanco();
			String sql ="insert into contato(telefone,email)values(?,?)";
			//preparar a conexao para executar a query
			
			//o comando statement.return generated retorna o id gerado para o cadastro 
			pst =con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			//realizar a passagem de parametros a consulta sql
			pst.setString(1, obj.getTelefone());
			pst.setString(2, obj.getEmail());
            		
			
			int r = pst.executeUpdate();
			rs = pst.getGeneratedKeys();
			//esse comando obtem o id gerado no momento do cadastro do contato e retorna um resultset.
        //testar o resulset
			if(rs.next()) {
				idcontato = String.valueOf(rs.getInt(1));
			}
			
			
			else {
				idcontato = null;
			}
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();	
		}
		finally {
			fecharBanco();
		}
		return idcontato;
	}

	@Override
	public List<Contato> consulta() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Contato> consulta(Contato obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String atualizar(Contato obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String apagar(Contato obj) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}
