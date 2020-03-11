package ohtu.kivipaperisakset;

import java.util.Scanner;

public class Ihminen implements Pelaaja {

	private Scanner scanner;
	
	public Ihminen(Scanner scanner) {
		this.scanner = scanner;
	}
	
	@Override
	public String annaSiirto() {
		String siirto = scanner.nextLine();
		return siirto;
	}
	
	@Override
	public void asetaSiirto(String siirto) {
		// ei toimenpiteitä
	}
	
}
