package com.bd2.redis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.bd2.redis.DAOS.BuildingDAO;
import com.bd2.redis.DAOS.OwnerDAO;
import com.bd2.redis.models.Building;
import com.bd2.redis.models.Owner;
import com.bd2.redis.models.Unit;

public class Controlador {
	private static BuildingDAO bdao;
	private static OwnerDAO odao;
	
	private static Scanner in;
	private static BufferedReader br;
	
	public static void init() throws IOException {
		bdao = new BuildingDAO();
		odao = new OwnerDAO();
		br = new BufferedReader(new InputStreamReader(System.in));
        in = new Scanner(System.in); 
        int opc = -1;
        while(opc!=9) {
        	printMenu();
        	opc = in.nextInt();
        	switch(opc) {
        		case 1:
        			agregarEdificio();
        			break;
        		case 2:
        			eliminarEdificio();
        			break;
        		case 3:
        			buscarEdificio();
        			break;
        		case 4:
        			obtenerEdificios();
        			break;
        		case 5:
        			agregarUnidad();
        			break;
        		case 6:
        			eliminarUnidad();
        			break;
        		case 7:
        			buscarUnidad();
        			break;
        		case 8:
        			getAllUnidades();
        			break;
        		case 9:
        			agregarOwner();
        			break;
        		case 10:
        			eliminarDuenio();
        			break;
        		case 11:
        			buscarDuenio();
        			break;
        		case 12:
        			getAllOwners();
        			break;
        		case 0:
        			System.out.println("terminado!");
        			return ;
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
		bdao.add(new Building(id, name, address, new ArrayList<>()));
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
		List<Building> buildings = bdao.getAll();
		for(Building bd: buildings)
			System.out.println(bd.toString());
	}
		
	private static void agregarUnidad() throws IOException {
		System.out.println("Escriba id del edificio: ");
		String buildingid = br.readLine();
		System.out.println("Escriba id: ");
		String id = br.readLine();
		System.out.println("Escriba estado: ");
		String status = br.readLine();
		System.out.println("Escriba tenant: ");
		String tenant = br.readLine();
		
		List<Owner> owners = null;
		
		bdao.addUnit(buildingid, new Unit(id,status,tenant,owners));
	}
	private static void eliminarUnidad() throws IOException {
		System.out.println("Escriba un id de edificio: ");
		String buildingid = br.readLine();
		System.out.println("Escriba un id de la unidad: ");
		String unitid = br.readLine();
		bdao.deleteUnit(buildingid, unitid);
	}
	private static void buscarUnidad() throws IOException {
		System.out.println("Escriba un id de edificio: ");
		String buildingid = br.readLine();
		System.out.println("Escriba un id de la unidad: ");
		String unitid = br.readLine();
		System.out.println(bdao.getUnit(buildingid, unitid).toString());
	}
	private static void getAllUnidades() throws IOException {
		System.out.println("Escriba un id de edificio: ");
		String buildingid = br.readLine();
		List<Unit> unidades = bdao.getAllUnits(buildingid);
		for(Unit u: unidades) 
			System.out.println(u.toString());
	}
	
	private static void agregarOwner() throws IOException {
		System.out.println("Escriba un id: ");
		String id = br.readLine();
		System.out.println("Escriba un nombre: ");
		String name = br.readLine();
		System.out.println("Escriba un apellido: ");
		String surname = br.readLine();
		
		odao.add(new Owner(id, name, surname, null));
	}
	private static void eliminarDuenio() throws IOException {
		System.out.println("Escriba un id: ");
		String id = br.readLine();
		odao.delete(id);
	}
	private static void buscarDuenio() throws IOException {
		System.out.println("Escriba un id: ");
		String id = br.readLine();
		System.out.println(odao.getOwner(id).toString());;
	}
	private static void getAllOwners() throws IOException {
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
		odao.addOwnsBuilding(idBuilding, idDuenio, idUnidad);
	}
	private static void eliminarDuenioUnidad() throws IOException {
		System.out.println("Escriba un id del edif: ");
		String idBuilding = br.readLine();
		System.out.println("Escriba un id de unidad: ");
		String idUnidad = br.readLine();
		System.out.println("Escriba un id del duenio: ");
		String idDuenio = br.readLine();

		odao.removeOwnsBuilding(idBuilding, idDuenio, idUnidad);
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
		System.out.println("11- Buscar duenio");
		System.out.println("12- Todos los duenios");
		
		System.out.println("13- Agregar duenio/propiedad");
		System.out.println("14- Eliminar Duenio/propiedad");
		System.out.println("15- vista 1");
		System.out.println("=============");
		System.out.println("0 - SALIR");
	}
}
