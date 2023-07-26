package minecraft;

public class Bloco {

	//atributos
	int resistencia;
	String textura;
	
	//construir (iniciar um codigo para cada objto)
	//o construtor precisa ter o mesmo nome de classe
	
	public Bloco() {
	System.out.println("");//pular uma linha
	System.out.println(" ____");
	System.out.println("|    |  ");
	System.out.println(" _____");
	}
	
	//métodos
	//método simples que vai executar uma ação
	void construir() {
		System.out.println("Bloco colocado");
	
	}
	void minerar( ) {
		System.out.println("Recursos obtidos");
	}
	void craftar() {
		System.out.println("Item criado");
		
	}
}
