package Contas;

public class PessoaFisica {
	public static void main(String[] args) {
		Conta cc1 = new Conta();
		cc1.setCliente("Leandro Ramos");
		cc1.setSaldo(10000);
		System.out.println("Cliente:" + cc1.getCliente());
		cc1.exibirSaldo();
		System.out.println("-------------------");
		cc1.depositar(5000);
		cc1.exibirSaldo();
		Conta cc2 = new Conta();
		cc2.setCliente("Sirlene Sanches");
		cc2.setSaldo(5000);
		System.out.println("Cliente:" + cc2.getCliente());
		cc2.exibirSaldo();
		cc2.sacar(2000);
		cc2.exibirSaldo();
		System.out.println("----------------------");
		System.out.println("");
		System.out.println("Cliente:" + cc1.getCliente());
		System.out.println("Favorecido:" + cc2.getCliente());
		System.out.println("Valor da conta transferida: ");
		cc2.transferir(cc2, 1000);
		System.out.println("Relatorio Gerencial");
		Conta gerente = new Conta();
		double total = gerente.soma(cc1.getSaldo(), cc2.getSaldo());
		System.out.println("Saldo total: R$" + total);
	}
}
