package br.com.projeto.papelaria.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexao {

	protected Connection con = null;
	protected PreparedStatement pst = null;
	protected ResultSet rs = null;

	protected void abrirBanco() {
		try {

			// carregando o driver de comunicao com o banco de dado
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://10.26.44.220:6020/papelariadb?usertimesone=true", "root",
					"123@senac");

		} catch (ClassNotFoundException cnf) {

			System.out.println("Erro ao carregar o drive ->" + cnf.getMessage());
		} catch (SQLException se) {
			System.out.println("Erro ao estabelecer conexão ->" + se.getMessage());
		} catch (Exception e) {
			System.out.println("Erro inesperado ->" + e.getMessage());
		}
	}

	protected void fecharBanco() {
		try {
			con.close();

		} catch (SQLException se) {
			System.out.println("Não foi possivel fechar o banco ->" + se.getMessage());

		}

	}

}