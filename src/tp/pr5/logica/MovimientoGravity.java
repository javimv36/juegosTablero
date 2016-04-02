package tp.pr5.logica;

/**
 * 
 * @author Javier Martin Villarreal
 * 
 */
public class MovimientoGravity extends Movimiento {

	private int fila;

	public MovimientoGravity(int columna, int fila, Ficha color) {
		this.columna = columna;
		this.fila = fila;
		this.color = color;
	}

	@Override
	public boolean ejecutaMovimiento(Tablero tab) throws MovimientoInvalido {
		if (tab.isCasillaValida(columna, fila)) {
			if (tab.getCasilla(columna, fila) == Ficha.VACIA) {
				tab = buscaSitioYColoca(tab, columna, fila);
			} else
				throw new MovimientoInvalido("Casilla ocupada.");
		} else
			throw new MovimientoInvalido("Posición incorrecta.");

		return (tab.getCasilla(columna, fila) == color);
	}

	@Override
	public void undo(Tablero tab) {
		tab.setCasilla(columna, fila, Ficha.VACIA);
	}

	/**
	 * Calcula las distancias desde la ficha hasta las 4 paredes y desplaza la
	 * ficha hacia el menor de los 4 lados. Si los dos son iguales o la ficha
	 * esta en el centro, no se moverá
	 * 
	 * @param t
	 *            Tablero de la partida
	 * @param c
	 *            Columna sobre la que se quiere colocar la ficha
	 * @param f
	 *            Fila sobre la que se quiere colocar la ficha
	 * @return Tablero con la ficha colocada
	 */
	private Tablero buscaSitioYColoca(Tablero t, int c, int f) {
		int fichaDcha = distanciaHastaElBorde(t, c, f, 1, 0);
		int fichaIzda = distanciaHastaElBorde(t, c, f, -1, 0);
		int fichaArriba = distanciaHastaElBorde(t, c, f, 0, -1);
		int fichaAbajo = distanciaHastaElBorde(t, c, f, 0, 1);

		if (fichaDcha != 0 && fichaIzda != 0 && fichaArriba != 0
				&& fichaAbajo != 0) {
			return desplazaHaciaElMenorDe(c, f,
					elMenorDe(fichaIzda, fichaDcha),
					elMenorDe(fichaArriba, fichaAbajo), t);
		} else
			return desplaza(c, f, 0, 0, t);

	}

	/**
	 * Calcula la distancia desde la posicion dada por la columna y la fila
	 * hasta una direccion dada por dos incrementos
	 * 
	 * @param t
	 *            Tablero en el que calculamos la distacia
	 * @param c
	 *            Columna origen a calcular
	 * @param f
	 *            Fila origen a calcular
	 * @param direccionC
	 *            incremento sobre las columnas
	 * @param direccionF
	 *            incremento sobre las filas
	 * @return distancia desde la posición dada hasta la pared en la direccion
	 *         dada
	 */
	private int distanciaHastaElBorde(Tablero t, int c, int f, int direccionC,
			int direccionF) {
		int distancia = 0;

		int columna = c + direccionC;
		int fila = f + direccionF;

		while (columna != t.getAncho() + 1 && columna != 0
				&& fila != t.getAlto() + 1 && fila != 0) {
			distancia++;
			columna += direccionC;
			fila += direccionF;
		}
		return distancia;
	}

	/**
	 * Calcula el menor de dos numeros. Si el primero es más pequeño, devuelve
	 * su inverso. Si son iguales devuelve 0.
	 * 
	 * @param a
	 *            Primer numero a evaluar
	 * @param b
	 *            Segundo numero a evaluar
	 * @return El menor de a y b. Si el menor es a, devuelve -a. Si son iguales
	 *         devuelve 0.
	 */

	private int elMenorDe(int a, int b) {
		if (a > b)
			return b;
		else if (b > a)
			return -a;
		else
			return 0;
	}

	private Tablero desplazaHaciaElMenorDe(int c, int f, int h, int v, Tablero t) {
		int dirH, dirV;
		dirH = 0;
		dirV = 0;
		if (h != 0 && v != 0) {
			if (Math.abs(v) < Math.abs(h)) {
				if (v < 0)
					dirV = -1;
				else if (v > 0)
					dirV = 1;

			} else if (Math.abs(h) < Math.abs(v)) {
				if (h < 0)
					dirH = -1;
				else if (h > 0)
					dirH = 1;

			} else if (Math.abs(h) == Math.abs(v)) {
				if (h > 0 && v > 0) {
					dirV = 1;
					dirH = 1;
				} else if (h > 0 && v < 0) {
					dirH = 1;
					dirV = -1;
				} else if (h < 0 && v > 0) {
					dirH = -1;
					dirV = 1;
				} else if (h < 0 && v < 0) {
					dirH = -1;
					dirV = -1;
				}
			}
		}
		if (h == 0) {
			if (v < 0)
				dirV = -1;
			else if (v > 0)
				dirV = 1;
		}
		if (v == 0) {
			if (h < 0)
				dirH = -1;
			else if (h > 0)
				dirH = 1;
		}
		desplaza(c, f, dirH, dirV, t);
		return t;
	}

	private Tablero desplaza(int c, int f, int a, int b, Tablero t) {
		int columna = c + a;
		int fila = f + b;
		if (a != 0 || b != 0) {
			while (t.getCasilla(columna, fila) == Ficha.VACIA
					&& t.isCasillaValida(columna, fila)) {
				columna += a;
				fila += b;
			}
			columna -= a;
			fila -= b;
		}
		this.columna = columna;
		this.fila = fila;

		t.setCasilla(columna, fila, color);
		return t;
	}

}
