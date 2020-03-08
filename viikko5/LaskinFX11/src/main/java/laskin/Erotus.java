package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Erotus extends Komento {

	public Erotus(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo) {
		super(tuloskentta, syotekentta, nollaa, undo);
    }

	@Override
    public void suorita() {
		super.edellinenArvo = Integer.parseInt(super.tuloskentta.getText());
		super.tuloskentta.setText("" + (Integer.parseInt(super.tuloskentta.getText()) - Integer.parseInt(super.syotekentta.getText())));
		super.nollaa.disableProperty().set(false);
		super.undo.disableProperty().set(false);
    }	
}
