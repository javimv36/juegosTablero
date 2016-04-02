package tp.pr5.logica;

/**
 * 
 * @author Javier Martin Villarreal
 * 
 */
@SuppressWarnings("serial")
public class MovimientoInvalido extends Exception {

	/**
	 * Constructor sin parámetros.
	 */
	public MovimientoInvalido() {
		super();
	}

	/**
	 * Constructor con un parámetro para el mensaje.
	 */
	public MovimientoInvalido(String msg) {
		super(msg);
	}

	/**
	 * Constructor con un parámetro para el mensaje y otro para la causa.
	 */
	public MovimientoInvalido(String msg, Throwable arg) {
		super(msg, arg);
	}

	/**
	 * Constructor con un parámetro para la causa inicial que provocó la
	 * excepción.
	 */
	public MovimientoInvalido(Throwable arg) {
		super(arg);
	}
}
