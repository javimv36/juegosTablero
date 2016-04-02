package tp.pr5.gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

import tp.pr5.control.Controlador;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.Tablero;
import tp.pr5.vistas.VistaVentana;
/**
 * 
 * @author Javier Martin Villarreal
 * 
 */
public class IzquierdaPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private TableroPanel tableroPanel;
	private AleatorioPanel jugadorAleatorioPanel;
	private TurnoPanel turnoPanel;
	private Controlador ctrl;

	public IzquierdaPanel(Controlador c) {
		super();

		ctrl = c;
		tableroPanel = new TableroPanel(ctrl);
		jugadorAleatorioPanel = new AleatorioPanel(ctrl);
		turnoPanel = new TurnoPanel(ctrl);

		this.setLayout(new BorderLayout());
		this.setBackground(Color.WHITE);
		this.add(tableroPanel, BorderLayout.NORTH, 0);
		this.add(turnoPanel, BorderLayout.CENTER);
		this.add(jugadorAleatorioPanel, BorderLayout.SOUTH);
		
	}

	public void pintaTablero(Tablero t) {
		tableroPanel.pintaTablero(t);
	}

	public void cambioTurno(Ficha turn) {
		turnoPanel.cambioTurno(turn);
		tableroPanel.cambioTurno(turn);
	}

	public void cambioJuego(Tablero t) {
		tableroPanel.cambioJuego(t);
		validate();
	}

	public void desactivarBotones() {
		tableroPanel.desactivarBotones();
		jugadorAleatorioPanel.desactivarBotones();
	}

	public void activarBotones() {
		tableroPanel.activarBotones();
		jugadorAleatorioPanel.activarBotones();
	}

	public void terminaPartida() {
		tableroPanel.terminaTurno();
		
	}

	public void bloqueaBotonesQueDebas(VistaVentana vistaVentana) {
		tableroPanel.bloqueaBotonesQueDebas(vistaVentana);
		
	}

	public void boqueaAleatorio() {
		jugadorAleatorioPanel.desactivarBotones();
	}
}
