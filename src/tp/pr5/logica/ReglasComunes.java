package tp.pr5.logica;

/**
 * 
 * @author Javier Martin Villarreal
 * 
 */
public abstract class ReglasComunes {
	/**
	 * Metodo que revisa si la ultima ficha introducida ha ganado o no
	 * 
	 * @return true si ha ganado
	 */
	public Ficha checkWinner(int width, int height, Tablero t) {

		if (t.getCasilla(width, height) != Ficha.VACIA) {

			return checkTable(width, height, t);

		} else
			return Ficha.VACIA;
	}

	/**
	 * Este metodo se encarga de comprobar si hay 4 en raya desde la ficha en la
	 * posicion dada, hacia todas las direcciones posibles, de manera radial,
	 * dividiendolo en 4 comprobaciones, una por cada una de las direcciones
	 * 
	 * @param width
	 *            posicion de la columna de la ficha introducida
	 * @param height
	 *            posicion de la fila de la ficha introducida
	 * @param t
	 * @return true si en alguna de las 2 diagonales hay 4 en raya
	 */
	public Ficha checkTable(int width, int height, Tablero t) {

		int count = 1;

		if (count < 4) { // diagonal \
			count = justCheck(width, height, 1, 1, count, t);
			count = justCheck(width, height, -1, -1, count, t);
		}
		if (count < 4) { // diagonal /
			count = 1;
			count = justCheck(width, height, -1, 1, count, t);
			count = justCheck(width, height, 1, -1, count, t);
		}
		if (count < 4) { // horizontal
			count = 1;
			count = justCheck(width, height, -1, 0, count, t);
			count = justCheck(width, height, 1, 0, count, t);
		}
		if (count < 4) { // vertical (solo hacia abajo)
			count = 1;
			count = justCheck(width, height, 0, 1, count, t);
		}
		if (count >= 4)
			return t.getCasilla(width, height);
		else
			return Ficha.VACIA;
	}

	/**
	 * Metodo que cuenta cuantas fichas del color de la ficha dada hay, desde la
	 * ficha dada, hacia una direccion
	 * 
	 * @param width
	 *            ancho, columna en la que se encuentra la ficha
	 * @param height
	 *            alto, altura a la que se encuentra la ficha
	 * @param incrementWidth
	 *            incremento horizontal (sera -1, 0 o 1 siendo izquierda, ningun
	 *            incremento o derecha respectivamente)
	 * @param incrementHeight
	 *            incremento vertical (sera -1, 0 o 1 siendo arriba, ningun
	 *            incremento o abajo respectivamente)
	 * @param count
	 *            cuenta cuantas fichas del color de la de la posicion dada, hay
	 *            seguidas, en la direccion dada
	 * @param t
	 *            tablero de la partida
	 * @return count devuelve la cuenta en el estado que se encuentre
	 */
	public int justCheck(int width, int height, int incrementWidth,
			int incrementHeight, int count, Tablero t) {

		Ficha ficha = t.getCasilla(width, height);
		int columna = width + incrementWidth;
		int fila = height + incrementHeight;

		while (ficha == t.getCasilla(columna, fila) && count < 4) {
			count++;
			columna += incrementWidth;
			fila += incrementHeight;

		}

		return count;
	}

	protected int findFicha(int col, Tablero t) {
		return findFila(col, t) + 1;
	}

	private int findFila(int col, Tablero t) {
		int fila = t.getAlto();
		while (t.getCasilla(col, fila) != Ficha.VACIA && fila > 0) {
			fila--;
		}
		return fila;
	}
}
