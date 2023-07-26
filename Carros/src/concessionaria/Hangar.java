package concessionaria;

public class Hangar {

	
	public static void main(String[] args) {
		Avioes aviaoBoein7777 = new Avioes();
		aviaoBoein7777.ano = 2020;
		aviaoBoein7777.cor = "cinza";
		
	
		System.out.println("Ano: " + aviaoBoein7777.ano);
		System.out.println("cor: "  + aviaoBoein7777.cor);
		
		aviaoBoein7777.desligar();
		aviaoBoein7777.acelerar();
		
		
		
		
	}
}
