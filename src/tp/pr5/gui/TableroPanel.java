package tp.pr5.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import tp.pr5.control.Controlador;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.Tablero;
import tp.pr5.vistas.VistaVentana;

/**
 * Clase que extiende a JPanel con un panel con el tablero y otro con los
 * comboboxes de los jugadores que jugarán la partida.
 * 
 * El Jpanel con los comboboxes lo implementa como clase anónima dentro de esta
 * misma clase.
 * 
 * @author Javier Martín Villarreal
 *
 */
public class TableroPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private FichaBoton[][] tableroBoton;
	private JPanel tableroPanel;
	private int columna;
	private int fila;
	private Border borde;
	private Controlador ctrl;
	private JugadoresPanel jugadores;
	private JugadorSwing[] jugadorSwing;
	private JugadorSwing jugadorConTurno;

	public TableroPanel(Controlador c) {
		super();

		ctrl = c;

		// ****ARRAY DE JUGADORES {NEGRAS, BLANCAS}******
		jugadores = new JugadoresPanel(ctrl);
		jugadorSwing = new JugadorSwing[2];
		inicializaJugadores();

		tableroPanel = new JPanel();
		tableroPanel.setBackground(Color.WHITE);

		creaTablero(ctrl.getTablero());

		this.setLayout(new BorderLayout());
		this.setBorder(borde);
		this.setBackground(Color.WHITE);
		this.add(tableroPanel, BorderLayout.WEST);
		this.add(jugadores, BorderLayout.SOUTH);
	}

	private void creaTablero(Tablero t) {
		columna = t.getAncho();
		fila = t.getAlto();
		tableroPanel.setLayout(new GridLayout(0, columna));
		tableroBoton = new FichaBoton[fila][columna];
		for (int j = 0; j < fila; j++) {
			for (int i = 0; i < columna; i++) {
				tableroBoton[j][i] = new FichaBoton(t.getCasilla(i + 1, j + 1),
						j + 1, i + 1, ctrl);
				tableroPanel.add(tableroBoton[j][i]);
			}
		}
	}

	public void setJugadorActual() {
		switch (ctrl.quienJuega()) {
		case NEGRA:
			jugadorConTurno = jugadorSwing[0];
			break;
		case BLANCA:
			jugadorConTurno = jugadorSwing[1];
			break;
		default:
			break;
		}
	}

	public void cambioJuego(Tablero t) {
		jugadorConTurno.yaNoTeToca();
		inicializaJugadores();
		this.removeAll();
		tableroPanel.removeAll();
		creaTablero(t);
		this.add(tableroPanel, BorderLayout.WEST);
		this.add(jugadores, BorderLayout.SOUTH);
		tableroPanel.revalidate();
		this.revalidate();
	}

	private void inicializaJugadores() {
		jugadorSwing[0] = new JugadorSwingHumano(ctrl); // NEGRAS
		jugadorSwing[1] = new JugadorSwingHumano(ctrl); // BLANCAS
		jugadorConTurno = new JugadorSwingHumano(ctrl);
		jugadores.inicializaComboboxJugadores();
	}

	public void pintaTablero(Tablero t) {
		for (int j = 0; j < fila; j++) {
			for (int i = 0; i < columna; i++) {
				tableroBoton[j][i].setFicha(t.getCasilla(i + 1, j + 1));
				tableroBoton[j][i].setJugador(jugadorConTurno);
			}
		}
	}

	public void desactivarBotones() {
		for (int j = 0; j < fila; j++) {
			for (int i = 0; i < columna; i++) {
				tableroBoton[j][i].desactivar();
			}
		}
	}

	public void activarBotones() {
		for (int j = 0; j < fila; j++) {
			for (int i = 0; i < columna; i++) {
				tableroBoton[j][i].activar();
			}
		}
	}

	public void cambioTurno(Ficha turn) {
		jugadorConTurno.yaNoTeToca();

		switch (turn) {
		case NEGRA:
			jugadorConTurno = jugadorSwing[0];
			break;
		case BLANCA:
			jugadorConTurno = jugadorSwing[1];
			break;
		default:
			break;
		}
		jugadorConTurno.teToca();
	}

	public void terminaTurno() {
		jugadorConTurno.yaNoTeToca();
	}

	public void bloqueaBotonesQueDebas(VistaVentana vistaVentana) {
		if (jugadorConTurno instanceof JugadorSwingAutomatico) {
			vistaVentana.bloqueaAutomatico();
		}
	}

	/**
	 * Clase que extinde de JPanel para crear un panel con dos JCombobox creados
	 * a partir de una clase anónima creada dentro de esta misma y dos JLabels
	 * para controlar el cambio de jugadores en la vista.
	 * 
	 * @author javi
	 * 
	 */
	public class JugadoresPanel extends JPanel {

		private static final long serialVersionUID = 1L;

		private CajaTiposJugador cajaBlancas;
		private CajaTiposJugador cajaNegras;
		private JLabel blancasLabel;
		private JLabel negrasLabel;
		private Controlador ctrl;

		public JugadoresPanel(Controlador c) {
			super();
			ctrl = c;

			// ****COMBOBOX NEGRAS******
			cajaNegras = new CajaTiposJugador(0, ctrl);

			// ****COMBOBOX BLANCAS******
			cajaBlancas = new CajaTiposJugador(1, ctrl);
			// cajaBlancas.setSelectedIndex(1);

			// ****ETIQUETA NEGRAS******
			negrasLabel = new JLabel("Negras", 0);
			negrasLabel.setBorder(BorderFactory.createLineBorder(
					Color.DARK_GRAY, 1));
			negrasLabel.setForeground(Color.WHITE);
			negrasLabel.setOpaque(true);
			negrasLabel.setBackground(Color.DARK_GRAY);

			// ****ETIQUETA BLANCAS******
			blancasLabel = new JLabel("Blancas", 0);
			blancasLabel.setBorder(BorderFactory.createLineBorder(
					Color.DARK_GRAY, 1));
			blancasLabel.setOpaque(true);
			blancasLabel.setForeground(Color.DARK_GRAY);
			blancasLabel.setBackground(Color.WHITE);

			// ****COLOCAMOS EL PANEL******
			this.setLayout(new GridLayout(2, 0));
			this.add(negrasLabel);
			this.add(blancasLabel);
			this.add(cajaNegras);
			this.add(cajaBlancas);
			this.setBackground(Color.WHITE);
		}

		public void inicializaComboboxJugadores() {
			cajaNegras.setSelectedIndex(0);
			cajaBlancas.setSelectedIndex(0);
		}

		/**
		 * Clase que hereda de JComboBox e implementa el action listener para
		 * las dos cajas de seleccion
		 * 
		 * @author malditasea
		 */
		class CajaTiposJugador extends JComboBox {

			private static final long serialVersionUID = 1L;

			public CajaTiposJugador(int jugador, Controlador c) {
				super();
				final int j = jugador;
				final Controlador ctrl = c;
				this.addItem("Humano");
				this.addItem("Automatico");
				this.setBackground(Color.WHITE);
				this.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						if (getSelectedItem().toString() == "Humano") {
							jugadorSwing[j] = new JugadorSwingHumano(ctrl);
						} else {
							jugadorSwing[j] = new JugadorSwingAutomatico(ctrl);
						}
						compruebaTurno(j, jugadorSwing[j]);
					}
				});
			}

			private void compruebaTurno(int jugador, JugadorSwing jSwing) {
				switch (jugador) {
				case 0:
					if (ctrl.quienJuega() == Ficha.NEGRA) {
						jugadorConTurno.yaNoTeToca();
						jugadorConTurno = jSwing;
						
					}
					break;
				case 1:
					if (ctrl.quienJuega() == Ficha.BLANCA) {
						jugadorConTurno.yaNoTeToca();
						jugadorConTurno = jSwing;
					}
					break;
				default:
					break;
				}
				pintaTablero(ctrl.getTablero());
				jugadorConTurno.teToca();
			}
		}
	}
}
