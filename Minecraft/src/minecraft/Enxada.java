package minecraft;
// a palavra Extends cria uma herança

public class Enxada extends Bloco {

//atributos 
	// boolean e uma variavel vdd ou falso
	boolean conquista;

	// métodos
	// construtor
	public Enxada() {
		System.out.println("");
		System.out.println(" -__");
		System.out.println(" /");
		System.out.println(" /");

	}

	public void arar() {
		System.out.println("Terra arada ._._._.");
		conquista = true;
	//método
		//uso de polimorfismo para modificar o comportamento
		//compprtameno obrigatorio
		//obrigatorio usar o mesmo metodo
		}
	
	public void minerar() {
		System.out.println("Dano Causado!");
		
		
	}


}