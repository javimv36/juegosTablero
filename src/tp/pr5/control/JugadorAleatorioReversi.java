package tp.pr5.control;

import java.util.ArrayList;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoReversi;
import tp.pr5.logica.Punto;
import tp.pr5.logica.Tablero;

public class JugadorAleatorioReversi implements Jugador {

	private ArrayList<Punto> movimientosPosibles; // ArrayList de los puntos
													// donde se podría realizar
													// el movimiento.

	@Override
	public Movimiento getMovimiento(Tablero tab, Ficha color) {

		movimientosPosibles = new ArrayList<Punto>();

		buscaEnElTablero(tab, color);

		if (movimientosPosibles.size() > 0) {
			int movimientoAleatorioElegido = (int) (Math.random() * (movimientosPosibles
					.size()));
			Punto p = movimientosPosibles.get(movimientoAleatorioElegido);
			Movimiento mov = new MovimientoReversi(p.getColumna(), p.getFila(),
					color);
			return mov;
		} else
			return null;

	}

	/**
	 * Comprueba en todas las casillas vacias del tablero si es un movimiento
	 * posible, si lo es, lo añade al array de movimientosPosibles.
	 * 
	 * @param t
	 *            tablero sobre el que mirar los movimientosPosibles.
	 * @param turno
	 *            color del jugador que queremos saber donde puede poner.
	 */
	private void buscaEnElTablero(Tablero t, Ficha turno) {
		movimientosPosibles = UtilsReversi.puedoPoner(t, turno);
	}
}
