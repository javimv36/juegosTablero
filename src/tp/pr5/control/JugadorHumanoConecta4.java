package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoConecta4;
import tp.pr5.logica.Tablero;

/**
 * 
 * @author Javier Martin Villarreal
 *
 */
public class JugadorHumanoConecta4 implements Jugador {
	private Scanner in;

	public JugadorHumanoConecta4(Scanner in) {
		this.in = in;
	}

	public Movimiento getMovimiento(Tablero tab, Ficha color) {
		Movimiento mov = null;
		int col = in.nextInt();
		String s = in.nextLine(); // para que no lea cadena = "";
		mov = new MovimientoConecta4(col, color);
		return mov;
	}

}
