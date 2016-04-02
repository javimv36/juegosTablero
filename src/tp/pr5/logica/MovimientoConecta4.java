package tp.pr5.logica;

/**
 * 
 * @author Javier Martin Villarreal
 * 
 */
public class MovimientoConecta4 extends Movimiento {

	/**
	 * Constructora de la clase
	 * 
	 * @param donde
	 *            Columna donde se efectuara el movimiento
	 * @param color
	 *            Ficha del color a colocar
	 */
	public MovimientoConecta4(int donde, Ficha color) {
		columna = donde;
		super.color = color;
	}

	/**
	 * 
	 * Metodo sobreescrito de la clase padre Movimiento.
	 * 
	 * @throws MovimientoInvalido
	 * 
	 */
	public boolean ejecutaMovimiento(Tablero tab) throws MovimientoInvalido {
		int fila = 1;

		if (tab.isCasillaValida(columna, fila)) {
			fila = buscaFicha(tab);
			if (!tab.columnaLlena(columna)) {
				tab.setCasilla(columna, fila, color);
			} else {
				throw new MovimientoInvalido("Columna llena.");
			}
		} else
			throw new MovimientoInvalido(
					"Columna incorrecta. Debe estar entre 1 y "
							+ tab.getAncho() + ".");

		return (tab.getCasilla(columna, fila) == color);
	}

	@Override
	public void undo(Tablero tab) {
		int fila = buscaFicha(tab);
		tab.setCasilla(columna, ++fila, Ficha.VACIA);
	}
}
