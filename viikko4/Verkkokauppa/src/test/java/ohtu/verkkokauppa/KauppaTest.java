
package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {

	Pankki pankki;
	Viitegeneraattori viite;
	Varasto varasto;
	Kauppa k;

	@Before
	public void setUp() {
	    pankki = mock(Pankki.class);
	    viite = mock(Viitegeneraattori.class);
	    varasto = mock(Varasto.class);
	    k = new Kauppa(varasto, pankki, viite);
	}
	
    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        
        when(viite.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(), anyInt());   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }
    
    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaParametreilla() {
    	when(viite.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu oikeilla parametreilla
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(5));
    }
    
    //aloitetaan asiointi, koriin lisätään kaksi eri tuotetta, joita varastossa on ja suoritetaan ostos, varmista että kutsutaan pankin metodia tilisiirto oikealla asiakkaalla, tilinumerolla ja summalla
    @Test
    public void ostettaessaKaksiTuotettaJoihinOnVaraaPankinMetodiaTilisiirtoKutsutaanOikeillaParametreilla() {
    	when(viite.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "mehu", 8));
        
        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(2);     // ostetaan tuotetta numero 2 eli mehua
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu oikeilla parametreilla
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(13));
    }
    
    //aloitetaan asiointi, koriin lisätään kaksi samaa tuotetta, jota on varastossa tarpeeksi ja suoritetaan ostos, varmista että kutsutaan pankin metodia tilisiirto oikealla asiakkaalla, tilinumerolla ja summalla
    @Test
    public void ostettaessaKaksiSamaaTuotettaJoitaVarastossaRiittavastiPankinMetodiaTilisiirtoKutsutaanOikeillaParametreilla() {
    	when(viite.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        
        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli lisää maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu oikeilla parametreilla
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(10));
    }
    
    
    //aloitetaan asiointi, koriin lisätään tuote, jota on varastossa tarpeeksi ja tuote joka on loppu ja suoritetaan ostos, varmista että kutsutaan pankin metodia tilisiirto oikealla asiakkaalla, tilinumerolla ja summalla
    @Test
    public void ostettaessaKahtaTuotettaJostaVainToistaVarastossaRiittavastiPankinMetodiaTilisiirtoKutsutaanOikeillaParametreilla() {
    	when(viite.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(0);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "mehu", 8));
        
        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(2);     // yritetään ostaa tuotetta numero 2 eli mehua
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu oikeilla parametreilla
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(5));
    }
    
    @Test
    public void aloitaAsiointiMetodinKutsuminenNollaaEdellisenOstoksenTiedot() {
    	when(viite.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(20);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "mehu", 8));
        
        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu oikeilla parametreilla
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(5));
        
        k.aloitaAsiointi();
        k.lisaaKoriin(2);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu oikeilla parametreilla
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(8));
    }
    
    @Test
    public void jokainenMaksuSaaUudenViitenumeron() {
    	when(viite.uusi()).
    	thenReturn(42).
    	thenReturn(31).
    	thenReturn(19);
    	
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(20);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "mehu", 8));
        
        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");
        
        k.aloitaAsiointi();
        k.lisaaKoriin(2);     // ostetaan tuotetta numero 2 eli mehua
        k.tilimaksu("pekka", "12345");

        // 
        verify(viite, times(2)).uusi();
    }
    
    @Test
    public void tuotteenPoistaminenKoristaPoistaaTuotteen() {
    	
    	Tuote t = new Tuote(1, "maito", 5);
    	
    	when(viite.uusi()).thenReturn(42);
    	
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(t);
        
        // tehdään ostokset
        k.aloitaAsiointi();
        
        k.lisaaKoriin(1);     // lisätään tuote 1 eli maito ostoskoriin       
        k.poistaKorista(1);     // poistetaan tuote 1 eli maito ostoskorista
        
        verify(varasto, times(1)).palautaVarastoon(t);   
    }
}


//tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa)

