package Contas;

public class Conta {
	// atributos

	public String cliente;
	public double saldo;

	public Conta() {
		super();
		System.out.println("Agência 2167");
		/**
		 * metodos simple exibir saldo das contas
		 */

	}

	// metodos
	public void exibirSaldo() {
		System.out.println("Saldo: R$" + saldo);
	}

	/**
	 * metodo com parametro(variavel)
	 * metodo 2 parametros(objeto e variavel)
	 * @param valor
	 */
	public void depositar(double valor) {
		saldo += valor;
		System.out.println("Créditos: R$" + valor);
	}

	public void sacar(double valor) {
		saldo -= valor;
		System.out.println("Débito: R$" + valor);

	}
	public void transferir(Conta destino, double valor) {
	this.sacar(valor);
	destino.depositar(valor);
	System.out.println("Transferencia: R$" + valor);
	}
	
	public double soma(double cc1, double cc2) {
		double total = cc1 + cc2;
		return total;		
	}
}