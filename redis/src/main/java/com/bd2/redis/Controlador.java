package com.bd2.redis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

import com.bd2.redis.DAOS.BuildingDAO;
import com.bd2.redis.DAOS.OwnerDAO;
import com.bd2.redis.models.Building;
import com.bd2.redis.models.Owner;

public class Controlador {
	private static BuildingDAO bdao;
	private static OwnerDAO odao;
	
	private static Scanner in;
	private static BufferedReader br;
	
	public static void init() {
		bdao = new BuildingDAO();
		odao = new OwnerDAO();
		br = new BufferedReader(new InputStreamReader(System.in));
        in = new Scanner(System.in); 
        int opc = -1;
        while(opc!=9) {
        	printMenu();
        	opc = in.nextInt();
        	switch(opc) {
        		
        	}
        }
	}
	
	private static void agregarEdificio() throws IOException {
		System.out.println("Escriba un id: ");
		String id = br.readLine();
		System.out.println("Escriba un nombre: ");
		String name = br.readLine();
		System.out.println("Escriba una direccion: ");
		String address = br.readLine();
		
		
		
		bdao.add(new Building(id, name, address, null));
	}
	
	private static void eliminarEdificio() throws IOException {
		System.out.println("Escriba un id: ");
		String id = br.readLine();
		bdao.delete(bdao.getBuilding(id));
	}
	private static void buscarEdificio() throws IOException {
		System.out.println("Escriba un id: ");
		String id = br.readLine();
		System.out.println(bdao.getBuilding(id).toString());;
	}
	private static void obtenerEdificios() throws IOException {
		System.out.println("Escriba un id: ");
		String id = br.readLine();
		List<Building> buildings = bdao.getAll();
		for(Building bd: buildings)
			System.out.println(bd.toString());
	}
		
	private static void agregarOwner() throws IOException {
		System.out.println("Escriba un id: ");
		String id = br.readLine();
		System.out.println("Escriba un nombre: ");
		String name = br.readLine();
		System.out.println("Escriba un apellido: ");
		String surname = br.readLine();
		
		
		
		odao.add(new Owner(id, name, surname, units));
	}
	private static void eliminarDuenio() throws IOException {
		System.out.println("Escriba un id: ");
		String id = br.readLine();
		odao.delete(odao.getOwner(id));
	}
	private static void buscarDuenio() throws IOException {
		System.out.println("Escriba un id: ");
		String id = br.readLine();
		System.out.println(odao.getOwner(id).toString());;
	}
	private static void obtenerDuenios() throws IOException {
		List<Owner> owners = odao.getAll();
		for(Owner ow: owners)
			System.out.println(ow.toString());
	}
	
	
	
	
	private static void agregarDuenioUnidad() throws IOException {
		System.out.println("Escriba un id del edif: ");
		String idBuilding = br.readLine();
		System.out.println("Escriba un id de unidad: ");
		String idUnidad = br.readLine();
		System.out.println("Escriba un id del duenio: ");
		String idDuenio = br.readLine();
		Building bd = bdao.getBuilding(idBuilding);
		Owner ow =  odao.getOwner(idDuenio);
		Unit un =  udao.getUnit(idUnidad);
		bdao.addOwner(bd, un, ow);
		odao.addOwnsBuilding(bd, ow, un);
	}
	private static void eliminarDuenioUnidad() throws IOException {
		System.out.println("Escriba un id del edif: ");
		String idBuilding = br.readLine();
		System.out.println("Escriba un id de unidad: ");
		String idUnidad = br.readLine();
		System.out.println("Escriba un id del duenio: ");
		String idDuenio = br.readLine();

		Building bd = bdao.getBuilding(idBuilding);
		Owner ow =  odao.getOwner(idDuenio);
		Unit un =  udao.getUnit(idUnidad);
		bdao.removeOwner(bd, un, ow);
		odao.removeOwnsBuilding(bd, ow, un);
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
