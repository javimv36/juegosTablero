package tp.pr5.vistas;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import tp.pr5.control.Controlador;
import tp.pr5.gui.DerechaPanel;
import tp.pr5.gui.IzquierdaPanel;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.Tablero;
import tp.pr5.obs.Observable;
import tp.pr5.obs.ObserverPartida;

/**
 * 
 * @author Javier Martin Villarreal
 * 
 */
public class VistaVentana extends JFrame implements ObserverPartida {

	private static final long serialVersionUID = 1L;
	
	private Container panelPrincipal;
	private IzquierdaPanel panelIzquierda;
	private DerechaPanel panelDerecha;
	private Controlador ctrl;

	public VistaVentana(Observable<ObserverPartida> partida, Controlador c)
			throws HeadlessException {
		super("TP 2014/15 - Javier Martín Villarreal - 2ºC");
		try {

			ctrl = c;
			panelIzquierda = new IzquierdaPanel(ctrl);
			panelDerecha = new DerechaPanel(ctrl);

			panelPrincipal = this.getContentPane();
			panelPrincipal.add(panelIzquierda);
			panelPrincipal.add(panelDerecha);
			panelPrincipal.setBackground(Color.WHITE);

			this.setLayout(new FlowLayout());
			this.setLocation(20, 20);
			this.setVisible(true);
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			this.pack();

			partida.addObs(this);
			
		} catch (HeadlessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onReset(Tablero tablero, Ficha turno) {
		panelIzquierda.pintaTablero(tablero);
		panelIzquierda.cambioTurno(turno);
		activarBotones();
	}

	@Override
	public void onPoner(Tablero t) {
		panelIzquierda.pintaTablero(t);
	}

	@Override
	public void onError(String s) {
		JOptionPane.showMessageDialog(null, s, "Movimiento inválido", 2, null);
	}

	@Override
	public void onTerminada(String s) {
		panelIzquierda.terminaPartida();
		JOptionPane.showMessageDialog(null, s, "Partida terminada", 0, null);
		desactivarBotones();
	}

	@Override
	public void onDeshacer(Tablero t) {
		panelIzquierda.pintaTablero(t);
	}

	@Override
	public void onSalir() {
		int result = JOptionPane.showConfirmDialog(null,
				"Está seguro de que desea salir?",
				"Saliendo de la Practica 5 de Tecnología de la Programación", JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	@Override
	public void onCambioTurno(Ficha turno) {
		activarBotones();
		panelIzquierda.cambioTurno(turno);
		panelIzquierda.bloqueaBotonesQueDebas(this);
	}

	@Override
	public void onCambioJuego(Tablero t) {
		panelIzquierda.cambioJuego(t);
		this.pack();
	}

	@Override
	public void onJugadorAleatorio(Tablero t) {
		System.out.println(t);
	}

	private void desactivarBotones() {
		panelIzquierda.desactivarBotones();
		panelDerecha.desactivarBotones();
	}

	private void activarBotones() {
		panelIzquierda.activarBotones();
		panelDerecha.activarBotones();
	}

	public void bloqueaAutomatico() {
		panelDerecha.bloqueaDeshacer();
		panelIzquierda.boqueaAleatorio();
	}

}
