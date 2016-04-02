package tp.pr5.logica;

import java.util.ArrayList;

public class MovimientoReversi extends Movimiento {

	private int fila;
	private ArrayList<Punto> fichasCambiadas;

	public MovimientoReversi(int columna, int fila, Ficha color) {
		this.columna = columna;
		this.fila = fila;
		this.color = color;
		fichasCambiadas = new ArrayList<Punto>();
	}

	@Override
	public boolean ejecutaMovimiento(Tablero tab) throws MovimientoInvalido {
		if (tab.isCasillaValida(columna, fila)) {
			if (tab.getCasilla(columna, fila) == Ficha.VACIA) {
				tab = tableroConCambiosRealizados(tab, columna, fila, color);
			} else {
				throw new MovimientoInvalido(
						"No se puede poner. Casilla ocupada");
			}
		}
		if (!(tab.getCasilla(columna, fila) == color)) {
			throw new MovimientoInvalido(
					"No se puede poner. Tienes que encerrar al oponente entre tus fichas");
		}
		return (tab.getCasilla(columna, fila) == color);
	}

	/**
	 * Metodo que devuelve un tablero con los cambios realizados sobre el
	 * tablero y guardando todas las fichas cambiadas en un array de puntos.
	 * 
	 * @param tab
	 *            tablero sobre e que se realizarán los cambios.
	 * @param col
	 *            columna sobre la que partiremos para realizar los cambios.
	 * @param fil
	 *            fila sobre la que partiremos para realizar los cambios.
	 * @param turno
	 *            color del jugador que pone.
	 * @return tablero modificado.
	 */
	private Tablero tableroConCambiosRealizados(Tablero tab, int col, int fil,
			Ficha turno) {
		Ficha otroTurno;
		if (turno == Ficha.BLANCA)
			otroTurno = Ficha.NEGRA;
		else
			otroTurno = Ficha.BLANCA;
		Tablero t = tab;

		// Sentido de las agujas del reloj |/_

		t = siEncierraFichaVoltea(t, col, fil, turno, otroTurno, 0, -1);
		t = siEncierraFichaVoltea(t, col, fil, turno, otroTurno, 1, -1);
		t = siEncierraFichaVoltea(t, col, fil, turno, otroTurno, 1, 0);
		t = siEncierraFichaVoltea(t, col, fil, turno, otroTurno, 1, 1);
		t = siEncierraFichaVoltea(t, col, fil, turno, otroTurno, 0, 1);
		t = siEncierraFichaVoltea(t, col, fil, turno, otroTurno, -1, 1);
		t = siEncierraFichaVoltea(t, col, fil, turno, otroTurno, -1, 0);
		t = siEncierraFichaVoltea(t, col, fil, turno, otroTurno, -1, -1);

		return t;
	}

	/**
	 * Metodo que comprueba si en la dirección dada se crea un movimiento válido
	 * y si es así voltea las fichas guardando los puntos en los que se ha hecho
	 * algún cambio.
	 * 
	 * @param tab
	 *            tablero sobre el que se harán los cálculos.
	 * @param col
	 *            columna desde la que se parte
	 * @param fil
	 *            fila desde la que se parte.
	 * @param turno
	 *            color para el que se va a mirar si es una dirección de
	 *            movimiento válida.
	 * @param otroTurno
	 *            color contrario.
	 * @param incrementoCol
	 *            incremento horizontal.
	 * @param incrementoFil
	 *            incremento vertical.
	 * @return
	 */
	private Tablero siEncierraFichaVoltea(Tablero tab, int col, int fil,
			Ficha turno, Ficha otroTurno, int incrementoCol, int incrementoFil) {

		int columna = col + incrementoCol;
		int fila = fil + incrementoFil;
		int cuenta = 0;

		while (otroTurno == tab.getCasilla(columna, fila)) {
			cuenta++;
			columna += incrementoCol;
			fila += incrementoFil;
		}
		if (turno == tab.getCasilla(columna, fila) && cuenta > 0) {
			while (cuenta >= 0) {
				cuenta--;
				columna -= incrementoCol;
				fila -= incrementoFil;
				tab.setCasilla(columna, fila, turno);
				fichasCambiadas.add(new Punto(columna, fila));
			}
		}
		return tab;
	}

	@Override
	public void undo(Tablero tab) {
		for (Punto p : fichasCambiadas) {
			tab.setCasilla(p.getColumna(), p.getFila(),
					colorContrario(tab, p.getColumna(), p.getFila()));
		}
		tab.setCasilla(columna, fila, Ficha.VACIA);
	}

	private Ficha colorContrario(Tablero tab, int columna, int fila) {
		if (tab.getCasilla(columna, fila) == Ficha.BLANCA) {
			return Ficha.NEGRA;
		} else
			return Ficha.BLANCA;
	}

}
