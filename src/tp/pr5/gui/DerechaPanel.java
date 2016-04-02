package tp.pr5.gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

import tp.pr5.control.Controlador;

/**
 * 
 * @author Javier Martin Villarreal
 * 
 */
public class DerechaPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private PartidaPanel partida;
	private CambioJuegoPanel cambioJuego;
	private SalirPanel salir;
	private Controlador ctrl;

	public DerechaPanel(Controlador c) {

		ctrl = c;

		partida = new PartidaPanel(ctrl);
		
		cambioJuego = new CambioJuegoPanel(ctrl);
		salir = new SalirPanel(ctrl);

		// this.setBorder(BorderFactory.createTitledBorder("Derecha"));
		this.setLayout(new BorderLayout());
		this.setBackground(Color.WHITE);
		this.add(partida, BorderLayout.NORTH); //north
		this.add(cambioJuego, BorderLayout.CENTER); //center
		this.add(salir, BorderLayout.SOUTH);//south
	}

	public void desactivarBotones() {
		partida.desactivarBotones();
		cambioJuego.desactivarBotones();
	}

	public void activarBotones() {
		partida.activarBotones();
		cambioJuego.activarBotones();
	}

	public void bloqueaDeshacer() {
		partida.desactivarBotones();
		
	}
}
