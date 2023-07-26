package Classes;

public class Student {


private String name;
private double average;

//construtor inicializa variaveis de instancia
public Student(String name, double average)
{
	this.name = name;
	
	//valida que a mediae > 0.0 e <- 100.0
	//armazena o valor padrao da media
	
	if (average > 0.0)
		if (average <= 100.0)
			this.average = average;//instancia
}
//definir o nome Studente
public void setName(String name)
{
this.name = name;
}
//recupera o nome de Student
public String getName()
{
return name;
}
//public void a media de Student
public void setAverage(double studenAverage)
{
//valida que a media e <-0.0 e <-100.0
//armazena o valor da media variavel de instancia 

if (average > 0.0)
	if (average <= 100.0)
		this.average = average; //atribui a variavel de instancia
}

//recupera a media de student
public double getAverage()
{
	return average;
}
//determina e retorna a letra da nota de student
public String getLetterGrade()
{

String letterGrade = ""; //inicia como string
if (average >= 90.0)
	letterGrade = "A";
else if (average >= 80.0)
	letterGrade = "B";
else if (average >= 70.0)
	letterGrade = "C";
else if (average >= 60)
	letterGrade = "D";
else
	letterGrade = "F";



return letterGrade;
}
}






