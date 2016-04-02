package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoGravity;
import tp.pr5.logica.Tablero;

/**
 * 
 * @author Javier Martin Villarreal
 * 
 */
public class JugadorHumanoGravity implements Jugador {

	private Scanner in;

	public JugadorHumanoGravity(Scanner in) {
		this.in = in;
	}

	public Movimiento getMovimiento(Tablero tab, Ficha color) {
		Movimiento mov = null;
		int col = in.nextInt();
		String s = in.nextLine(); // para que no lea cadena = "";
		System.out.print("Introduce la fila: ");
		int fila = in.nextInt();
		s = in.nextLine();
		mov = new MovimientoGravity(col, fila, color);
		return mov;
	}

}
