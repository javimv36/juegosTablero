package tp.pr5.logica;

/**
 * 
 * @author Javier Martin Villarreal
 * 
 */
public class UndoStack {
	private Movimiento[] undoStack;
	private int numUndo;
	public static final int NUMDESHACER = 10;

	/**
	 * constructora de la pila
	 */
	public UndoStack() {
		undoStack = new Movimiento[NUMDESHACER];
		numUndo = -1;
	}

	/**
	 * Metodo para resetear la pila.
	 */
	public void rst() {
		undoStack = new Movimiento[NUMDESHACER];
		numUndo = -1;
	}

	/**
	 * comprueba si la pila esta llena y en ese caso desplaza uno a la izquierda
	 * para hacer un hueco
	 */
	private void desplazar() {
		if (llena()) {
			for (int i = 0; i < NUMDESHACER - 1; i++)
				undoStack[i] = undoStack[i + 1];
			numUndo--;
		}
	}

	/**
	 * metodo que devuelve true si la pila esta llena
	 * 
	 * @return true si pila esta llena
	 */
	private boolean llena() {
		return numUndo == NUMDESHACER - 1;
	}

	/**
	 * metodo que devuelve true si apilo correctamente el parametro col en la
	 * pila en la posicion correspondiente (contador + 1)
	 * 
	 * @param col
	 *            columna que se añadira a la pila en la posicion contador + 1
	 * @return true si se añadio correctamente
	 */
	public void addPila(Movimiento mov) {
		desplazar();
		numUndo++;
		undoStack[numUndo] = mov;
	}

	/**
	 * Metodo para obtener la cima de la pila (posicion indicada por el
	 * contador)
	 * 
	 * @return Devuelve la columna situada en la cima de la pila
	 */
	public Movimiento getCimaPila() {
		return undoStack[numUndo];
	}

	/**
	 * Metodo para obtener el contador de la pila en su estado actual
	 * 
	 * @return contador de la pila
	 */
	public int getNumUndo() {
		return numUndo;
	}

	/**
	 * metodo que debidamente quita la cima de la pila
	 */
	public void subPila() {
		if (numUndo >= 0)
			numUndo--;
	}

	/**
	 * metodo que devuelve un string que contiene la pila en su estado actual
	 * para formato consola. muy util para comprobar esta clase individualmente
	 * con el main comentado abajo
	 */
	public String toString() {
		return "Pila [tam= " + numUndo + "] ";
	}

}
