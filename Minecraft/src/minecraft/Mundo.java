package minecraft;

public class Mundo {

	public static void main(String[] args) {
//instanciar(criar) o objeto
		Bloco blocoTerra = new Bloco();
		blocoTerra.resistencia = 1;
		blocoTerra.textura = "Terra";
		//+ adicionar outro texto dentro
		System.out.println("Bloco " + blocoTerra.textura);
		System.out.println("Resistência: " + blocoTerra.resistencia);
		blocoTerra.construir();
		blocoTerra.minerar();
	
		Bloco blocoMadeira = new Bloco();
		blocoMadeira.resistencia =2;
		blocoMadeira.textura = "Madeira";
		System.out.println("Bloco " + blocoMadeira.textura);
		System.out.println("Resistência: " + blocoMadeira.resistencia);
		blocoMadeira.minerar();
		blocoMadeira.craftar();
		
		//instanciar uma enxada de pedra
		Enxada enxadaMadeira = new Enxada();
		enxadaMadeira.textura = "Madeira";
		enxadaMadeira.resistencia = 2;
		enxadaMadeira.conquista = false;
		System.out.println("Enxada de " + enxadaMadeira.textura);
		System.out.println("Resistência: " + enxadaMadeira.resistencia);
		enxadaMadeira.arar();
		//validação de conquista
		if (enxadaMadeira.conquista == true) {
			System.out.println("-----------------------------");
			System.out.println("conquista obtida!");
			System.out.println("Dedicação séria. Hora do Plantio.");
			System.out.println("------------------------------");
		}
			
		
		
		
		
	}

}
