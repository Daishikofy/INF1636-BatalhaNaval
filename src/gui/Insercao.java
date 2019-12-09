package gui;
import javax.swing.*;

public class Insercao extends JPanel {
	public Insercao() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(new GridDeEscolha());
		add(new PanelMatriz());
	}
}
