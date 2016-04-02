package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.Partida;
import tp.pr5.logica.Tablero;

/**
 * 
 * @author Javier Martin Villarreal
 * 
 */
abstract public class Controlador {

	private Partida partida;
	private Scanner in;
	private FactoriaTipoJuego factoria;
	private Jugador jugadorBlancas;
	private Jugador jugadorNegras;

	/**
	 * Constructor de la clase
	 * 
	 * @param f
	 * @param p
	 *            Partida a la que se jugará, configurada con el juego al que
	 *            hay que jugar en la invocación a run().
	 * @param in
	 *            Scanner que hay que utilizar para pedirle la información al
	 *            usuario.
	 */
	public Controlador(FactoriaTipoJuego f, Partida p, Scanner in) {
		partida = p;
		this.in = in;
		factoria = f;
	}

	/**
	 * Método abstracto que lanzará el Main y que implementarán los hijos del
	 * Controlador
	 */
	abstract public void run();

	/**
	 * Getter de la factoria
	 * 
	 * @return la factoria del controlador
	 */
	public FactoriaTipoJuego getFactoria() {
		return factoria;
	}

	/**
	 * Setter de la factoria del controlador
	 * 
	 * @param f
	 *            parametro por el que queremos cambiar
	 */
	public void setFactoria(FactoriaTipoJuego f) {
		factoria = f;
	}

	/**
	 * Getter de la partida.
	 * 
	 * @return la partida del controlador.
	 */
	public Partida getPartida() {
		return partida;
	}

	public Scanner getEntrada() {
		return in;
	}

	/**
	 * Getters de los jugadores. Devuelve el tipo de jugador del color pedido
	 * 
	 * @return Tipo de Jugador pedido
	 */
	public Jugador getJugadorBlancas() {
		return jugadorBlancas;
	}

	public Jugador getJugadorNegras() {
		return jugadorNegras;
	}

	/**
	 * Setters de los jugadores. Cambia el tipo del jugador indicado por el tipo
	 * pasado
	 * 
	 * @param jugador
	 *            tipo de jugador por el que ha de cambiarse
	 */
	public void setJugadorBlancas(Jugador jugador) {
		jugadorBlancas = jugador;
	}

	public void setJugadorNegras(Jugador jugador) {
		jugadorNegras = jugador;
	}

	/**
	 * Actualiza los jugadores a humanos y las reglas a las que indique la
	 * factoria
	 */
	public void setJuego() {
		jugadorBlancas = factoria.creaJugadorHumanoConsola(in);
		jugadorNegras = factoria.creaJugadorHumanoConsola(in);
		reset();
	}

	/**
	 * Getter del tablero del controlador.
	 * 
	 * @return el tablero del controlador.
	 */
	public Tablero getTablero() {
		return partida.getTablero();
	}

	/**
	 * Método que devuelve una Ficha del tipo del jugador que tiene el turno.
	 * 
	 * @return Ficha del tipo del jugador que tiene el turno.
	 */
	public Ficha quienJuega() {
		return partida.getTurno();
	}

	/**
	 * Método que ejecuta un movimiento en la partida.
	 * 
	 * @param mov
	 *            movimiento dado para ejecutar sobre la partida.
	 * 
	 */
	public void poner(Movimiento mov) {
		try {
			partida.ejecutaMovimiento(mov);
		} catch (MovimientoInvalido e) {
		}
	}

	/**
	 * Manda a la partida resetearse con las reglas de la factoría.
	 */
	public void reset() {
		partida.reset(factoria.creaReglas());
	}

	/**
	 * Manda deshacer a la partida.
	 */
	public void undo() {
		partida.undo();
	}

	/**
	 * A partir de una factoria dada, establece la nueva factoría, crea el nuevo
	 * juego según la factoría y realiza los cambios pertinentes en la partida.
	 * 
	 * @param f
	 *            factoría de nuevo juego al que queremos cambiar.
	 */
	public void cambioJuego(FactoriaTipoJuego f) {
		setFactoria(f);
		setJuego();
		partida.cambioJuego();
		// System.out.println("Partida reiniciada.");
	}

	/**
	 * Manda salir a la partida.
	 */
	public void salir() {
		partida.salir();
	}

	/**
	 * Pone un movimiento aleatorio para el juego que se esté jugando sobre la
	 * partida y según el turno que tenga la partida.
	 * 
	 */
	public void ponerAleatorio() {
		poner(factoria.creaJugadorAleatorio().getMovimiento(
				partida.getTablero(), partida.getTurno()));

	}
}
