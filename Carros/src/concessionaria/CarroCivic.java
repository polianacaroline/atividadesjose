package concessionaria;

public class CarroCivic {

	public static void main(String[] args) {
		Carro CarroCivic = new Carro();
		CarroCivic.ano = 2020;
		CarroCivic.cor = "cinza";
		
		System.out.println("civic");
		System.out.println("Ano: " + CarroCivic.ano);
		System.out.println("cor: "  + CarroCivic.cor);
		CarroCivic.ligar();
		CarroCivic.desligar();
		CarroCivic.acelerar();
		
		Carro carroRangerRover = new Carro();
		carroRangerRover.ano = 2020;
		carroRangerRover.cor = "cinza";
		
		System.out.println("RangerRover");
		System.out.println("Ano: " +  carroRangerRover.ano);
		System.out.println("cor: "  + carroRangerRover.cor);
		carroRangerRover.ligar();
		carroRangerRover.desligar();
		carroRangerRover.acelerar();

		
		
	}

}
