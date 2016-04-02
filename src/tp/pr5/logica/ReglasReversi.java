package tp.pr5.logica;

import java.util.ArrayList;

import tp.pr5.control.UtilsReversi;

public class ReglasReversi extends ReglasComunes implements ReglasJuego {

	private int fichasNegras;
	private int fichasBlancas;
	private int fichasVacias;

	@Override
	public Tablero iniciaTablero() {
		Tablero t = new Tablero(8, 8);
		// 44, 45, 54, 55
		t.setCasilla(4, 4, Ficha.BLANCA);
		t.setCasilla(4, 5, Ficha.NEGRA);
		t.setCasilla(5, 4, Ficha.NEGRA);
		t.setCasilla(5, 5, Ficha.BLANCA);
		return t;
	}

	@Override
	public Ficha jugadorInicial() {
		return Ficha.NEGRA;
	}

	@Override
	/**
	 * Hay ganador si el tablero esta lleno, gana el que tenga mas fichas o si todas las fichas son del mismo color
	 */
	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t) {

		puntosTablero(t);

		if (fichasNegras == 0) { // NO HAY NEGRAS
			return Ficha.BLANCA;

		} else if (fichasBlancas == 0) { // NO HAY BLANCAS
			return Ficha.NEGRA;

		} else if (fichasVacias == 0) { // TABLERO LLENO
			if (fichasBlancas > fichasNegras) {
				return Ficha.BLANCA;

			} else if (fichasNegras > fichasBlancas) {
				return Ficha.NEGRA;
			} else
				return Ficha.VACIA;
		} else
			return Ficha.VACIA;
	}

	/**
	 * Método que cuenta las fichas que hay de cada tipo en un tablero dado.
	 * 
	 * @param t
	 *            tablero para contar fichas.
	 */
	private void puntosTablero(Tablero t) {
		fichasNegras = 0;
		fichasBlancas = 0;
		fichasVacias = 0;
		for (int i = 1; i <= t.getAlto(); i++) {
			for (int k = 1; k <= t.getAncho(); k++) {
				switch (t.getCasilla(k, i)) {
				case BLANCA:
					fichasBlancas++;
					break;
				case NEGRA:
					fichasNegras++;
					break;
				case VACIA:
					fichasVacias++;
					break;
				default:
					break;
				}
			}
		}
	}

	@Override
	public boolean tablas(Ficha ultimoEnPoner, Tablero t) {
		puntosTablero(t);
		if (fichasNegras == fichasBlancas && fichasVacias == 0) {
			return true;
		} else
			return false;
	}

	@Override
	public Ficha siguienteTurno(Ficha ultimoEnPoner, Tablero t) {

		ArrayList<Punto> blancasPosibles = UtilsReversi.puedoPoner(t,
				Ficha.BLANCA);
		ArrayList<Punto> negrasPosibles = UtilsReversi.puedoPoner(t,
				Ficha.NEGRA);

		if (ultimoEnPoner == Ficha.BLANCA && negrasPosibles.size() > 0) {
			return Ficha.NEGRA;
		} else if (blancasPosibles.size() > 0) {
			return Ficha.BLANCA;
		}

		if (ultimoEnPoner == Ficha.NEGRA && blancasPosibles.size() > 0) {
			return Ficha.BLANCA;
		} else if (negrasPosibles.size() > 0) {
			return Ficha.NEGRA;
		} else
			return Ficha.VACIA;

	}

}
