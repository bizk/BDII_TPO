package com.bd2.redis;

import java.util.Scanner;

public class Controlador {
	public static init() {
        Scanner in = new Scanner(System.in); 
        int opc = -1;
        while(opc!=9) {
        	printMenu();
        	opc = in.nextInt();
        }
	}
	
	private static void printMenu() {
		System.out.println("==== M E N U ====");
		System.out.println("1- Agregar Edificio");
		System.out.println("2- Eliminar Edificio");
		System.out.println("3- Buscar Edificio");
		System.out.println("4- Todos los Edificios");
		System.out.println("5- Agregar Unidad");
		System.out.println("6- Eliminar Unidad");
		System.out.println("7- Buscar Unidad");
		System.out.println("8- Todas las unidades por edificio");
		System.out.println("9- Agregar duenio");
		System.out.println("10- Eliminar Duenio");
		System.out.println("11- Todos los duenios");
		System.out.println("12- Modificar Duenio");
		System.out.println("13- Agregar duenio/propiedad");
		System.out.println("14- Eliminar Duenio/propiedad");
		System.out.println("15- vista 1");
		System.out.println("=============");
		System.out.println("0 - SALIR");
	}
}
