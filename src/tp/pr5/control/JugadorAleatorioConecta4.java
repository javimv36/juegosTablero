package tp.pr5.control;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoConecta4;
import tp.pr5.logica.Tablero;

/**
 * Jugador que juega de forma aleatoria a Conecta 4. Simplemente elige una
 * columna aleatoria en el tablero, comprobando antes que se podrá colocar en
 * ella (no está llena).
 * 
 * @author Javier Martin Villarreal
 * 
 */
public class JugadorAleatorioConecta4 implements Jugador {

	public Movimiento getMovimiento(Tablero tab, Ficha color) {
		Movimiento mov = null;

		int col = (int) (Math.random() * tab.getAncho()) + 1;

		while (tab.columnaLlena(col)) {
			col = (int) (Math.random() * tab.getAncho()) + 1;
		}
		mov = new MovimientoConecta4(col, color);
		return mov;
	}
}
