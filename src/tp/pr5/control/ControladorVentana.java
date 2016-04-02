package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logica.Partida;

public class ControladorVentana extends Controlador {

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
	public ControladorVentana(FactoriaTipoJuego f, Partida p, Scanner in) {
		super(f, p, in);
	}

	@Override
	public void run() {

	}
}
