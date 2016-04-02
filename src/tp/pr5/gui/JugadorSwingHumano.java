package tp.pr5.gui;

import tp.pr5.control.Controlador;

public class JugadorSwingHumano implements JugadorSwing {

	private Controlador ctrl;

	public JugadorSwingHumano(Controlador c) {
		ctrl = c;
	}

	/**
	 * No hacer nada, el jugador humano no hace nada si le toca el turno
	 */
	public void teToca() {
	}

	public void casillaPulsada(int columna, int fila) {
		try {
			ctrl.poner(ctrl.getFactoria().creaMovimiento(columna, fila,
					ctrl.quienJuega()));
		} catch (NumberFormatException _e) {
		}
	}

	@Override
	/**
	 * No hacer nada, el jugador humano no hace nada si pierde el turno
	 */
	public void yaNoTeToca() {
	}
}
