package  controlador;

import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.annotation.Generated;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators.Ln;

import com.bdd2.models.HistoricVal;
import com.bdd2.models.Investment;
import com.bdd2.models.Person;
import com.bdd2.models.Transaction;
import com.bdd2.mongo.MongoDAO;
import com.mongodb.BasicDBObject;

public class Controlador {
	private static MongoDAO mongoDAO;
	private static Scanner in;
	private static BufferedReader br;
	
	public static void init() throws IOException {
		mongoDAO = new MongoDAO();
		br = new BufferedReader(new InputStreamReader(System.in));
        in = new Scanner(System.in); 
        int opc = -1;
        while(opc!=9) {
        	printMenu();
        	opc = in.nextInt();
        	switch(opc) {
        		case 1:
					try {
						agregarInversion();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
        			break;
        		case 2:
					try {
						deleteInversion();
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
        		case 3:
					try {
						findInversion();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        			break;
        		case 4:
        			findAll();
        			break;
        		case 5:
        			automaticGenerateInvestment();
        			break;
        		case 11:
        			vista1();
        			break;
        		case 12:
        			vista2();
        			break;
        		case 0:
        			System.out.println("Adios! :)");
        			return;
        	}
        }
	}
	
	private static void vista2() throws IOException {
		System.out.println("Inserte nombre de la inversion: ");
		String name = br.readLine();	
		mongoDAO.vista2(name);
	}

	private static void vista1() {
		mongoDAO.vista1();
	}
	
	
	private static void agregarInversion() throws IOException {
		System.out.println("Inserte nombre: ");
		String name = br.readLine();
		
		System.out.println("Inserte tipo: ");
		String tipo = br.readLine();
		
		System.out.println("Inserte Fecha: ");
		System.out.println("Dia: ");
		int dia = in.nextInt();
		System.out.println("Mes: ");
		int mes = in.nextInt();
		System.out.println("Ano: ");
		int ano = in.nextInt();
		Date fdc = new Date(dia,mes,ano);
		
		System.out.println("Inserte Precio Actual: ");
		float actualValue = in.nextFloat();
		
		System.out.println("Inserte Precio de compra: ");
		float buyValue = in.nextFloat();
		
		//We will generate 10 but we cold generate more
		System.out.println("--- VALORES HISTORICOS --- ");
		List<BasicDBObject> historicValues = new ArrayList();
		for(int i = 0; i < 5; i++) {
			historicValues.add(generateHistoricValue());
		}
		
		List<BasicDBObject> transactions = new ArrayList();
		for(int i = 0; i < 3; i++) {
			transactions.add(generateTransaction());
		}
		
		List<BasicDBObject> opinions = new ArrayList();
		for(int i = 0; i < 3; i++) {
			opinions.add(generateOpinion());
		}
		Investment investment = new Investment(name, tipo, fdc, actualValue, buyValue, historicValues, transactions, opinions);
		System.out.println(investment.getBasicDBObject());
		mongoDAO.addInversion(investment);
	}
	private static void deleteInversion() throws IOException {
		System.out.println("Inserte nombre: ");
		String name = br.readLine();
		mongoDAO.deleteInversion(name);
	}
	private static void findInversion() throws IOException {
		System.out.println("Inserte nombre: ");
		String name = br.readLine();
		mongoDAO.findInversion(name);
	}
	private static void findAll() {
		mongoDAO.findAll();
	}
	
	private static void printMenu() {
		System.out.println("==== M E N U ====");
		System.out.println("1- Agregar Inversion");
		System.out.println("2- Eliminar Inversion");
		System.out.println("3- Buscar Inversion");
		System.out.println("4- Todos las inversiones");
		System.out.println("5 - generar Automaticamente Inversion");
		System.out.println("11- obtener inversion con mayor cantidad de operaciones");
		System.out.println("12- obtener recomendaciones");
		System.out.println("=============");
		System.out.println("0 - SALIR");
	}
	
	private static BasicDBObject generateTransaction() throws IOException {
		System.out.println("inserte importe: ");
		float importe = in.nextFloat();
		System.out.println("inserte nombre asesor: ");
		String asesor = br.readLine();
		System.out.println("Inserte nombre del operador: ");
		String operador= br.readLine();
		System.out.println("Ingrese fecha");
		System.out.println("Dia: ");
		int dia = in.nextInt();
		System.out.println("Mes: ");
		int mes = in.nextInt();
		System.out.println("Ano: ");
		int ano = in.nextInt();
		Date fecha = new Date(dia,mes,ano);
		System.out.println("inserte tipo: ");
		String tipo = br.readLine();
		return MongoDAO.createOperation(importe, asesor, operador, fecha, tipo);
	}
	private static BasicDBObject generateHistoricValue() {
		System.out.println("Ingrese fecha");
		System.out.println("Dia: ");
		int dia = in.nextInt();
		System.out.println("Mes: ");
		int mes = in.nextInt();
		System.out.println("Ano: ");
		int ano = in.nextInt();
		Date date = new Date(dia,mes,ano);
		System.out.println("Inserte Precio : ");
		float precio = in.nextFloat();
		return mongoDAO.createHistoricValue(date, precio);
	}
	private static BasicDBObject generateOpinion() throws IOException {
		System.out.println("inserte nombre autor: ");
		String autor = br.readLine();
		System.out.println("Inserte situacion actual: ");
		String situacionActual= br.readLine();
		System.out.println("Ingrese fecha");
		System.out.println("Dia: ");
		int dia = in.nextInt();
		System.out.println("Mes: ");
		int mes = in.nextInt();
		System.out.println("Ano: ");
		int ano = in.nextInt();
		Date fecha = new Date(dia,mes,ano);
		System.out.println("Inserte factores externbis: ");
		String factoresExternos= br.readLine();
		System.out.println("Inserte futuro: ");
		String futuro= br.readLine();
		System.out.println("Inserte recomendacion: ");
		String recomendacion= br.readLine();

		return mongoDAO.createRecomendacion(autor, fecha, situacionActual, factoresExternos, futuro, recomendacion);
	}
	
	private static void automaticGenerateInvestment() {
		List<BasicDBObject> historicValues = new ArrayList();
		List<BasicDBObject> transactions = new ArrayList();
		List<BasicDBObject> opinions = new ArrayList();
		for(int i = 0; i<10; i++) {
			HistoricVal hv = new HistoricVal();
			historicValues.add(hv.getBasicDBObject());
		}
		for(int i = 0; i<5; i++) {
			Person asesor = new Person();
			Person operator = new Person();
			Transaction tr = new Transaction(asesor.getNameComplete(), operator.getNameComplete());
			transactions.add(tr.getBasicDBObject());
		}
		for(int i = 0; i<5; i++) {
			Person asesor = new Person();
			Person operator = new Person();
			Transaction op = new Transaction(asesor.getNameComplete(), operator.getNameComplete());
			opinions.add(op.getBasicDBObject());
		}
		
		Investment investment = new Investment(historicValues,transactions,opinions);
		System.out.println(investment.toString());
		mongoDAO.addInversion(investment);
	}	

}
