package ohtu.kivipaperisakset;

import java.util.Scanner;

public class Pelimoodi {

	private Pelaaja pelaaja1;
	private Pelaaja pelaaja2;
    private Tuomari tuomari;
    private String ekanSiirto;
    private String tokanSiirto;
    
    public Pelimoodi(Pelaaja pelaaja1, Pelaaja pelaaja2) {
    	this.pelaaja1 = pelaaja1;
    	this.pelaaja2 = pelaaja2;
    	this.tuomari = new Tuomari();
    }

    public void pelaa() {

    	kierros();
        
        while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
            tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
            System.out.println(tuomari);
            System.out.println();

            kierros();
        }

        System.out.println();
        System.out.println("Kiitos!");
        System.out.println(tuomari);
    }
    
    private void kierros() {
    	System.out.print("Ensimmäisen pelaajan siirto: ");
        ekanSiirto = pelaaja1.annaSiirto();
        System.out.print("Toisen pelaajan siirto: ");
        tokanSiirto = pelaaja2.annaSiirto();
    }
    
    private static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }
}