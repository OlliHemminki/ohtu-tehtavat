package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Nollaa extends Komento {

	public Nollaa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo) {
		super(tuloskentta, syotekentta, nollaa, undo);
    }

	@Override
    public void suorita() {
		super.edellinenArvo = Integer.parseInt(super.tuloskentta.getText());
		super.tuloskentta.setText("" + 0);
		super.nollaa.disableProperty().set(true);
		super.undo.disableProperty().set(false);
    }
	
}
