package tp.pr5.control;

import java.util.ArrayList;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Punto;
import tp.pr5.logica.Tablero;

public abstract class UtilsReversi {

	private static ArrayList<Punto> movimientosPosibles;

	/**
	 * Método para calcular todos los puntos del tablero donde puedo poner una
	 * ficha según un turno. Mira en todas las casillas vacías del tablero para
	 * comprobarlo.
	 * 
	 * @param t
	 *            tablero a calcular los puntos donde puedo poner.
	 * @param turno
	 *            color de la ficha que hay que mirar si puedo poner.
	 * @return un arrayList de puntos con todos aquellos puntos en los que el
	 *         jugador del color dado puede realizar un movimiento para un
	 *         tablero dado.
	 */
	public static ArrayList<Punto> puedoPoner(Tablero t, Ficha turno) {
		movimientosPosibles = new ArrayList<Punto>();
		for (int i = 1; i <= t.getAlto(); i++) {
			for (int k = 1; k <= t.getAncho(); k++) {
				if (t.getCasilla(k, i) == Ficha.VACIA) {
					buscaMovimientos(t, turno, k, i, movimientosPosibles);
				}
			}
		}
		return movimientosPosibles;
	}

	/**
	 * Busca si un movimiento es posible sobre una casilla de un tablero dada.
	 * Para ello mira en todas las direcciones en el sentido de las agujas del
	 * reloj si en alguna dirección desde la ficha, se cumple que pueda poner el
	 * color dado. Si es un movimiento posible, añade este punto a la lista de
	 * puntos posibles.
	 * 
	 * @param tab
	 *            tablero sobre el que comprobaremos si un punto, es un
	 *            movimiento posible.
	 * @param turno
	 *            color del jugador que queremos comprobar.
	 * @param col
	 *            columna de la casilla.
	 * @param fil
	 *            fila de la casilla.
	 * @param movimientosPosibles
	 *            ArrayList de movimientosPosibles.
	 */
	public static void buscaMovimientos(Tablero tab, Ficha turno, int col,
			int fil, ArrayList<Punto> movimientosPosibles) {

		Ficha otroTurno;
		if (turno == Ficha.BLANCA)
			otroTurno = Ficha.NEGRA;
		else
			otroTurno = Ficha.BLANCA;

		Tablero t = tab;
		// Sentido de
		// las agujas
		// del reloj |/_
		if (encierraFicha(t, col, fil, turno, otroTurno, 0, -1)
				|| encierraFicha(t, col, fil, turno, otroTurno, 1, -1)
				|| encierraFicha(t, col, fil, turno, otroTurno, 1, 0)
				|| encierraFicha(t, col, fil, turno, otroTurno, 1, 1)
				|| encierraFicha(t, col, fil, turno, otroTurno, 0, 1)
				|| encierraFicha(t, col, fil, turno, otroTurno, -1, 1)
				|| encierraFicha(t, col, fil, turno, otroTurno, -1, 0)
				|| encierraFicha(t, col, fil, turno, otroTurno, -1, -1)) {

			movimientosPosibles.add(new Punto(col, fil));
		}
	}

	/**
	 * Método estático booleano para calcular si en un punto del tablero, un
	 * color encierra fichas del color contrario entre otra de sus fichas en la
	 * dirección dada.
	 * 
	 * @param t
	 *            tablero sobre el que miraremos si una ficha del color dado
	 *            encerraria a otras del contrario.
	 * @param col
	 *            columna de la casilla.
	 * @param fil
	 *            fila de la casilla.
	 * @param turno
	 *            color del jugador que queremos saber si encierra a otra ficha.
	 * @param otroTurno
	 *            color del jugador contrario.
	 * @param incrementoCol
	 *            incremento direccion horizontal.
	 * @param incrementoFil
	 *            incremento direccion vertical.
	 * @return true si encierra fichas del otro color, false en caso contrario.
	 */
	private static boolean encierraFicha(Tablero t, int col, int fil,
			Ficha turno, Ficha otroTurno, int incrementoCol, int incrementoFil) {
		int columna = col + incrementoCol;
		int fila = fil + incrementoFil;
		int cuenta = 0;

		while (otroTurno == t.getCasilla(columna, fila)) {
			cuenta++;
			columna += incrementoCol;
			fila += incrementoFil;
		}
		if (turno == t.getCasilla(columna, fila) && cuenta > 0) {
			return true;
		} else
			return false;
	}
}
