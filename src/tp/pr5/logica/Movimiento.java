package tp.pr5.logica;

/**
 * Clase que representa el movimiento de un jugador. Tiene un método para
 * ejecutar el movimiento sobre la partida, y otro para deshacerlo. Es una clase
 * abstracta; habrá una clase no abstracta por cada tipo de juego soportado.
 * 
 * @author Javier Martin Villarreal
 * 
 */
public abstract class Movimiento {
	int columna;
	Ficha color;

	/**
	 * 
	 * Metodo publico que devuelve el jugador al que pertenece el movimiento
	 * actual
	 * 
	 * @return color Ficha del color del actual movimiento
	 */
	public Ficha getJugador() {
		return color;
	};

	/**
	 * 
	 * Metodo abstracto que ejecuta el movimiento sobre el tablero que se pasa
	 * como parametro. el tablero debera atender a unas reglas, sino su
	 * resultado sera indeterminado
	 * 
	 * @param tab
	 *            Tablero sobre el que se efectuara el movimiento
	 * @return true si se efectuo el movimiento correctamente
	 * @throws MovimientoInvalido
	 * 
	 */
	public abstract boolean ejecutaMovimiento(Tablero tab)
			throws MovimientoInvalido;

	/**
	 * 
	 * Metodo abstracto que deshace el ultimo movimiento sobre el tablero que se
	 * pasa como parametro el tablero debera atender a unas reglas, sino su
	 * resultado sera indeterminado
	 * 
	 * @param tab
	 *            Tablero sobre el que se efectuara el movimiento de deshacer.
	 * 
	 */
	public abstract void undo(Tablero tab);

	/**
	 * Metodo que recore la columna de arriba a abajo hasta llegar a la primera
	 * casilla en la que haya una ficha
	 * 
	 * @param tab
	 *            Tablero donde se buscara la ficha
	 * 
	 * @return fila en la que se encuentra la ficha
	 */
	public int buscaFicha(Tablero tab) {
		int fila = 1;
		while (tab.getCasilla(columna, fila) == Ficha.VACIA
				&& fila <= tab.getAlto())
			fila++;

		return --fila;
	}
}
