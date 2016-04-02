package tp.pr5.logica;


/**
 * Clase punto con dos atributos: columna y fila.
 * @author Javier Martín Villarreal.
 *
 */
public class Punto {
	private int columna;
	private int fila;
	
	/**
	 * Constructor con parámetros.
	 * @param col columna del punto.
	 * @param fil fila del punto.
	 */
	public Punto(int col, int fil){
		columna = col;
		fila = fil;
	}
	
	/**
	 * Getter de la columna.
	 * @return columna del punto.
	 */
	public int getColumna(){
		return columna;
	}
	
	/**
	 * Getter de la fila.
	 * @return fila del punto.
	 */
	public int getFila(){
		return fila;
	}
	
}
