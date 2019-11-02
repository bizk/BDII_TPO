package com.bdd2.models;

import java.util.Random;

public class Person {
	private String nameComplete;

	public Person(String name, String lastName) {
		this.nameComplete = new String(lastName + ", " + name);
	}

	public Person() {
		this.nameComplete = generateCompleteName();
	}
	
	private String generateCompleteName() {
		return new String(PersonLastNames.getLastNameRand() + ", " + PersonNames.getNameRand());
	}

	private enum PersonNames {
		Santiago, Carlos, Federico, Axel, Gabriel, Lucila, Marcelo, Agostina, Martzia, Azul, Walter, Nikola, Elon, Bill,
		Yesica, Sasha, Brenda;

		private static String getNameRand() {
			Random random = new Random();
			return values()[random.nextInt(values().length)].toString();
		}
	}

	private enum PersonLastNames {
		Yanzon, Harris, Parodi, RoitMan, Kalasnik, Meatmuega, Iconda, Costilla, DiePie, Casco, Fisher, Tesla, Musk,
		Gates, White, Gris;

		private static String getLastNameRand() {
			Random random = new Random();
			return values()[random.nextInt(values().length)].toString();
		}
	}
	
	public String nameComplete() {
		return this.nameComplete;
	}
}
