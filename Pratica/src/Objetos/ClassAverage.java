package Objetos;

import java.util.Scanner;

public class ClassAverage{
	public static void main(String[] args)
	{
		//cria scanner para obter entrada a partir da janela
		Scanner input = new Scanner(System.in);
		//fase de inicialização
		int total = 0;// fase de processamento utiliza a soma das notas inseridas pelo usuario
		int gradeCounter = 1;// inicializa n da nota a ser inserida
		
		//fase de processsamento utiliza repeticao controlada
		while (gradeCounter <= 10) //faz o loop 10 vezesd
		{
			System.out.println("Enter grade"); //prompt
			int grade = input.nextInt();
			total = total + grade;
			gradeCounter = gradeCounter + 1;
			
		}
		int average = total / 10;
		
		
		System.out.printf("%nTotal of all 10 grades is %n", total);
		System.out.printf("Class average is %d%n", average);
		
	}//fim da classe average

		
		
		
		

		
		
		
		
	}


