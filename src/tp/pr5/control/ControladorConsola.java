package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.comandos.GestorComando;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.Partida;

/**
 * Clase que controla la ejecución de la partida, pidiendo al usuario qué quiere
 * ir haciendo, hasta que la partida termina.
 * 
 * @author Javier Martin Villarreal
 * 
 */
public class ControladorConsola extends Controlador {

	private Partida partida;
	private Scanner in;

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
	public ControladorConsola(FactoriaTipoJuego f, Partida p, Scanner in) {
		super(f, p, in);
		partida = p;
		this.in = in;
	}

	/**
	 * Ejecuta la partida hasta que ésta termina. Se deberá hacer una
	 * única invocación a este método; tras ella, si se vuelve a llamar a run()
	 * el comportamiento será indeterminado.
	 */
	public void run() {
		setJuego();
		String string;
		boolean exit = false;

		while (!exit) {
			GestorComando gC = new GestorComando(partida, this, in);
			string = "";
			System.out.print("Juegan " + partida.getTurno()
					+ "s\nQué quieres hacer? ");

			string = in.nextLine();

			String s[] = string.split(" ");

			if (!gC.parsearComando(s))
				System.err.println("No te entiendo.");
			else if (!exit) {
				exit = partida.isTerminada();
			}
		}
	}

	/**
	 * Cambia el jugador dado por la ficha por el tipo de jugador dado.
	 * 
	 * @param j
	 *            jugador del tipo que queremos cambiar.
	 * @param color
	 *            color del jugador sobre el que realizaremos los cambios.
	 */
	public void cambioJugador(Jugador j, Ficha color) {
		if (color == Ficha.NEGRA) {
			setJugadorNegras(j);
		} else if (color == Ficha.BLANCA) {
			setJugadorBlancas(j);
		}
		partida.cambioJugador();
	}

}
