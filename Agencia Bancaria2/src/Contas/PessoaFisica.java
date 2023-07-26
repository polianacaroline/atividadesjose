package Contas;

public class PessoaFisica {
	public static void main(String[] args) {
		Conta cc1 = new Conta();
		cc1.cliente = "Leandro Ramos";
		cc1.saldo = 1000;
		System.out.println("Cliente:" + cc1.cliente);
		cc1.exibirSaldo();
		System.out.println("-------------------");
		cc1.depositar(5000);
		cc1.exibirSaldo();
		Conta cc2 = new Conta();
		cc2.cliente = "Sirlene Sanches";
		cc2.saldo = 5000;
		System.out.println("Cliente:" + cc2.cliente);
		cc2.exibirSaldo();
		cc2.sacar(2000);
		cc2.exibirSaldo();
		System.out.println("----------------------");
		System.out.println("");
		System.out.println("Cliente:" + cc1.cliente);
		System.out.println("Favorecido:" + cc2.cliente);
	    System.out.println("Valor da conta transferida: ");
		cc2.transferir(cc2, 1000);
		System.out.println("Relatorio Gerencial");
        Conta gerente = new Conta();
        double total = gerente.soma(cc1.saldo, cc2.saldo);
        System.out.println("Saldo total: R$" + total);
	}
}
