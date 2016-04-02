package tp.pr5.logica;

/**
 * 
 * @author Javier Martin Villarreal
 * 
 */
public class MovimientoComplica extends Movimiento {

	private Ficha fichaQuitada;

	/**
	 * Constructora de la clase
	 * 
	 * @param donde
	 *            columna donde se efectuara el movimiento
	 * @param color
	 *            Ficha del color a colocar
	 */
	public MovimientoComplica(int donde, Ficha color) {
		columna = donde;
		super.color = color;
		fichaQuitada = Ficha.VACIA;
	}

	public boolean ejecutaMovimiento(Tablero tab) throws MovimientoInvalido {
		int fila;

		if (tab.getCasilla(columna, 1) != Ficha.VACIA) {
			desplazaColumna(tab);
		}
		fila = buscaFicha(tab);

		if (tab.isCasillaValida(columna, fila)) {
			if (tab.getCasilla(columna, fila) == Ficha.VACIA)
				tab.setCasilla(columna, fila, color);
		} else
			throw new MovimientoInvalido(
					"Columna incorrecta. Debe estar entre 1 y "
							+ tab.getAncho() + ".");
		return (tab.getCasilla(columna, fila) == color);
	}

	public void undo(Tablero tab) {
		int fila = buscaFicha(tab);

		if (fichaQuitada == Ficha.VACIA)
			tab.setCasilla(columna, ++fila, Ficha.VACIA);

		else {
			desplazaUndo(tab);
			tab.setCasilla(columna, tab.getAlto(), fichaQuitada);
		}

	}

	private void desplazaUndo(Tablero tab) {
		for (int fila = 1; fila <= tab.getAlto(); fila++) {
			tab.setCasilla(columna, fila, tab.getCasilla(columna, fila + 1));
		}
	}

	private void desplazaColumna(Tablero tab) {

		fichaQuitada = tab.getCasilla(columna, tab.getAlto());
		for (int fila = tab.getAlto(); fila > 1; fila--) {
			tab.setCasilla(columna, fila, tab.getCasilla(columna, fila - 1));
		}
		tab.setCasilla(columna, 1, Ficha.VACIA);
	}

}
