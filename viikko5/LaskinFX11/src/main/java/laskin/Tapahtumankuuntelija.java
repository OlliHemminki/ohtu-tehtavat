package laskin;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.util.Map;
import java.util.HashMap;

public class Tapahtumankuuntelija implements EventHandler {
    private Button undo;
    
    private Map<Button, Komento> komennot;
    private Komento edellinen = null;
 

    public Tapahtumankuuntelija(TextField tuloskentta, TextField syotekentta, Button plus, Button miinus, Button nollaa, Button undo) {
        this.undo = undo;
        komennot = new HashMap<>();
        komennot.put(plus, new Summa(tuloskentta, syotekentta,  nollaa, undo) );
        komennot.put(miinus, new Erotus(tuloskentta, syotekentta, nollaa, undo) );
        komennot.put(nollaa, new Nollaa(tuloskentta, syotekentta,  nollaa, undo) );
    }
    
    @Override
    public void handle(Event event) {
        if ( event.getTarget() != undo ) {
            Komento komento = komennot.get((Button)event.getTarget());
            komento.suorita();
            edellinen = komento;
        } else {
            edellinen.peru();
            edellinen = null;
        }                  
    }
}