package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoReversi;
import tp.pr5.logica.Tablero;

public class JugadorHumanoReversi implements Jugador {

	private Scanner in;
	
	public JugadorHumanoReversi(Scanner in) {
		this.in = in;
	}

	@Override
	public Movimiento getMovimiento(Tablero tab, Ficha color) {
		Movimiento mov = null;
		int col = in.nextInt();
		String s = in.nextLine(); // para que no lea cadena = "";
		System.out.print("Introduce la fila: ");
		int fila = in.nextInt();
		s = in.nextLine(); // para que no lea cadena = "";
		mov = new MovimientoReversi(col, fila, color);
		return mov;
	}

}
