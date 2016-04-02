package tp.pr5.logica;

/**
 * 
 * @author Javier Martin Villarreal
 * 
 */
public enum Ficha {
	BLANCA, NEGRA, VACIA;

	/**
	 * Devuelve un string con el tipo de ficha en minúsculas.
	 */
	public String toString() {
		return name().toLowerCase();
	}
}
