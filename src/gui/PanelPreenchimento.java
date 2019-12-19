package gui;
import javax.swing.*;

public class PanelPreenchimento extends JPanel {
	public PanelPreenchimento() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(new GridPecas());
		add(new GridTabuleiro());
	}
}
