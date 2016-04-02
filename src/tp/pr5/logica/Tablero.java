package tp.pr5.logica;

/**
 * Almacena la información de un tablero rectangular. El tamaño se fija en el
 * momento de la construcción, y contiene métodos para acceder a la información
 * de cada celda y para colocar fichas.
 * 
 * @author Javier Martin Villarreal
 * 
 */
public class Tablero {

	private Ficha[][] tablero;
	private int ancho;
	private int alto;

	/**
	 * Construye un tablero vacío.
	 * 
	 * @param tx
	 *            Tamaño en la coordenada x (o número de columnas).
	 * @param ty
	 *            Tamaño en la coordenada y y (o número de filas).
	 */
	public Tablero(int tx, int ty) {

		if (dimensionesCorrectas(tx, ty)) {
			ancho = tx;
			alto = ty;
		} else {
			ancho = 1;
			alto = 1;
		}
		tablero = new Ficha[ancho][alto];
		reset();
	}

	/**
	 * Devuelve true si el tamaño dado para el tablero es correcto
	 * 
	 * @param tx
	 *            numero de columnas
	 * @param ty
	 *            numero de filas
	 * @return true si es un tamaño de tablero correcto
	 */
	private boolean dimensionesCorrectas(int tx, int ty) {
		return (!(tx <= 0 || ty <= 0));
	}

	/**
	 * Resetea el tablero vaciandolo (pone todas las fichas Ficha.VACIA)
	 */
	public void reset() {
		for (int i = 1; i <= alto; i++) {
			for (int k = 1; k <= ancho; k++) {
				setCasilla(k, i, Ficha.VACIA);
			}
		}
	}

	/**
	 * Método para obtener el alto del tablero.
	 * 
	 * @return Número de filas del tablero.
	 */
	public int getAlto() {
		return alto;
	}

	/**
	 * Método para obtener el ancho del tablero.
	 * 
	 * @return Número de columnas del tablero.
	 */
	public int getAncho() {
		return ancho;
	}

	/**
	 * Método para acceder a la información de una casilla o celda concreta.
	 * 
	 * @param x
	 *            Número de columna (1..ancho)
	 * @param y
	 *            Número de fila (1..alto)
	 * @return Información de la casilla. Si la casilla no es válida, devuelve
	 *         Ficha.VACIA
	 */
	public Ficha getCasilla(int x, int y) {
		if (isCasillaValida(x, y))
			return tablero[x - 1][y - 1];
		else
			return Ficha.VACIA;
	}

	public boolean isCasillaValida(int x, int y) {
		return (x >= 1 && x <= ancho && y >= 1 && y <= alto);

	}

	/**
	 * Permite especificar el nuevo contenido de una casilla. También puede
	 * utilizarse para quitar una ficha
	 * 
	 * @param x
	 *            Número de columna (1..ancho)
	 * @param y
	 *            Número de fila (1..alto)
	 * @param color
	 *            Color de la casilla. Si se indica Ficha.VACIA, la celda
	 *            quedará sin ficha.
	 */
	public void setCasilla(int x, int y, Ficha color) {
		if (isCasillaValida(x, y)) {
			tablero[x - 1][y - 1] = color;
		}
	}

	public boolean columnaLlena(int c) {
		if (tablero[c - 1][0] == Ficha.VACIA)
			return false;
		else
			return true;
	}

	/**
	 * Metodo que devuelve una polla String con el tablero
	 * 
	 * @return String del tablero
	 */
	public String toString() {
		String cadenaTablero = "";
		for (int i = 1; i <= alto; i++) {
			cadenaTablero += "|";
			for (int k = 1; k <= ancho; k++) {
				if (getCasilla(k, i) == Ficha.NEGRA)
					cadenaTablero += "X";
				else if (getCasilla(k, i) == Ficha.BLANCA)
					cadenaTablero += "O";
				else
					cadenaTablero += " ";
			}
			cadenaTablero += "|\n";
		}
		cadenaTablero += "+";
		for (int l = 1; l <= ancho; l++)
			cadenaTablero += "-";
		cadenaTablero += "+\n ";
		for (int m = 1; m <= ancho; m++)
			cadenaTablero += m % 10;
		cadenaTablero += "\n";
		return cadenaTablero;
	}
}
