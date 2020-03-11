package ohtu.kivipaperisakset;

import java.util.Scanner;

public class Pelitehdas {
	
	private Scanner scanner;
	
	public Pelitehdas(Scanner scanner) {
		this.scanner = scanner;
	}
	
	public static Pelimoodi KPSPelaajaVsPelaaja(Scanner scanner) {
        return new Pelimoodi(new Ihminen(scanner), new Ihminen(scanner));
    }

	public static Pelimoodi KPSTekoaly(Scanner scanner) {
        return new Pelimoodi(new Ihminen(scanner), new Tekoaly());
    }
	
	public static Pelimoodi KPSParempiTekoaly(Scanner scanner) {
        return new Pelimoodi(new Ihminen(scanner), new TekoalyParannettu(20));
    }
    
}
