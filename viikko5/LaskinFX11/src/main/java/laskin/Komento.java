package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public abstract class Komento {
    int edellinenArvo;
    
    TextField tuloskentta;
    TextField syotekentta;
    Button nollaa;
	
	public Komento(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
		this.edellinenArvo = Integer.parseInt(tuloskentta.getText());
		this.tuloskentta = tuloskentta;
		this.syotekentta = syotekentta;
		this.nollaa = nollaa;
    }

    public abstract void suorita();
    
    public void peru() {
		this.tuloskentta.setText("" + this.edellinenArvo);
    }
}