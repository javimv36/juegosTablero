package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoComplica;
import tp.pr5.logica.Tablero;
/**
 * 
 * @author Javier Martin Villarreal
 * 
 */
public class JugadorHumanoComplica implements Jugador {

	private Scanner in;

	public JugadorHumanoComplica(Scanner in) {
		this.in = in;
	}

	public Movimiento getMovimiento(Tablero tab, Ficha color) {
		Movimiento mov = null;
		int col = in.nextInt();
		String s = in.nextLine();
		mov = new MovimientoComplica(col, color);
		return mov;
	}

}
