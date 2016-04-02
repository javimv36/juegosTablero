package tp.pr5.control;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoGravity;
import tp.pr5.logica.Tablero;

/**
 * Clase que implementa el movimiento para el juego del Gravity. Se deben
 * implementar los métodos abstractos de la clase padre.
 * 
 * @author Javier Martin Villarreal
 * 
 */
public class JugadorAleatorioGravity implements Jugador {

	public Movimiento getMovimiento(Tablero tab, Ficha color) {

		int col = (int) (Math.random() * tab.getAncho()) + 1;
		int fila = (int) (Math.random() * tab.getAlto()) + 1;
		Movimiento mov = null;

		while (tab.getCasilla(col, fila) != Ficha.VACIA) {
			col = (int) (Math.random() * tab.getAncho()) + 1;
			fila = (int) (Math.random() * tab.getAlto()) + 1;
		}
		mov = new MovimientoGravity(col, fila, color);
		return mov;
	}
}
