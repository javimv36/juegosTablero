package tp.pr5.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tp.pr5.control.Controlador;
import tp.pr5.logica.Ficha;

public class TurnoPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel etiqueta;
	private Controlador ctrl;

	public TurnoPanel(Controlador c) {
		super();

		ctrl = c;

		etiqueta = new JLabel("", 0);
		cambioTurno(ctrl.quienJuega());
		etiqueta.setFont(new Font("Arial Black", Font.BOLD, 12));
		etiqueta.setForeground(Color.WHITE);

		this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
		this.setLayout(new FlowLayout());
		this.setBackground(Color.DARK_GRAY);
		this.add(etiqueta);
	}

	public void cambioTurno(Ficha turno) {
		if (turno == Ficha.BLANCA)
			etiqueta.setText("Juegan Blancas");
		else
			etiqueta.setText("Juegan Negras");
		etiqueta.validate();
	}
}
