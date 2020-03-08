package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Summa extends Komento {

	public Summa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
		super(tuloskentta, syotekentta, nollaa, undo, sovellus);
    }

	@Override
    public void suorita() {
		super.edellinenArvo = Integer.parseInt(super.tuloskentta.getText());
		super.tuloskentta.setText("" + (Integer.parseInt(super.tuloskentta.getText()) + Integer.parseInt(super.syotekentta.getText())));
		super.nollaa.disableProperty().set(false);
    }
	
}
