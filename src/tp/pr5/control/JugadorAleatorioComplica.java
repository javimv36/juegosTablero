package tp.pr5.control;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoComplica;
import tp.pr5.logica.Tablero;

/**
 * Jugador que juega de forma aleatoria a Complica. En este caso cualquier
 * columna es válida, pues si está llena, se hará hueco.
 * 
 * @author Javier Martin Villarreal
 * 
 */
public class JugadorAleatorioComplica implements Jugador {

	public Movimiento getMovimiento(Tablero tab, Ficha color) {
		Movimiento mov = null;
		int col = (int) (Math.random() * tab.getAncho()) + 1;
		mov = new MovimientoComplica(col, color);
		return mov;
	}
}
