package Contas;

public class Conta {
	// atributos

	private String cliente;
	private double saldo;

	public Conta() {
		super();
		System.out.println("Agência 2167");
		/**
		 * metodos simple exibir saldo das contas
		 */

	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	// metodos
	protected void exibirSaldo() {
		System.out.println("Saldo: R$" + saldo);
	}
	//encapsulamento//

	/**
	 * metodo com parametro(variavel)
	 * metodo 2 parametros(objeto e variavel)
	 * @param valor
	 */
	 void depositar(double valor) {
		saldo += valor;
		System.out.println("Créditos: R$" + valor);
	}

	void sacar(double valor) {
		saldo -= valor;
		System.out.println("Débito: R$" + valor);

	}
	void transferir(Conta destino, double valor) {
	this.sacar(valor);
	destino.depositar(valor);
	System.out.println("Transferencia: R$" + valor);
	}
	
	double soma(double cc1, double cc2) {
		double total = cc1 + cc2;
		return total;	
		
	}
}