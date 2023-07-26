package concessionaria;

import java.util.Random;

public class Carro {

	int ano;
	String cor;
	// construtor

	public Carro() {
		System.out.println("---------------------------------------");
		// alinha abaixo e cria um objeto do nome chassi
		String chassi = new String("123KJJCNFCEHIUHYUISHCVJS");
		//
		Random gerador = new Random();
		System.out.print("Chassi: * ");
		for (int i = 1; i < 16; i++)

		{
			char numeracao = (char) gerador.nextInt(chassi.length());
			System.out.print(chassi.charAt(numeracao));
		}
		System.out.println(" *");
	}

	void ligar() {
		System.out.println("ligar carro");
	}

	void desligar() {
		System.out.println("desligar carro");
	}

	void acelerar() {
		System.out.println("piiiiiiiiiiiiiiiii");
	}

}
