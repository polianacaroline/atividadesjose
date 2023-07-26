package br.com.projeto.papelaria.dominio;

public class Funcionario extends InformacoesPessoais {

	private Long dFuncionario;
	private String nomeFuncionario;
	private String cpf;
	private String Cargo;
	
	public Funcionario(Usuario us, Contato ct, Endereco end) {
		super.usuario = us;
		super.contato = ct;
		super.endereco = end;
	}

	public Long getdFuncionario() {
		return dFuncionario;
	}

	public void setdFuncionario(Long dFuncionario) {
		this.dFuncionario = dFuncionario;
	}

	public String getNomeFuncionario() {
		return nomeFuncionario;
	}

	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCargo() {
		return Cargo;
	}

	public void setCargo(String cargo) {
		Cargo = cargo;
	}
	
	
}
