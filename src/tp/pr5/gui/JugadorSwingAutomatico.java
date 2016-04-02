package tp.pr5.gui;

import javax.swing.SwingUtilities;

import tp.pr5.control.Controlador;

public class JugadorSwingAutomatico implements JugadorSwing {

	private Controlador ctrl;
	private HebraJugador h;

	public JugadorSwingAutomatico(Controlador c) {
		ctrl = c;
	}

	/**
	 * Al recibir el turno, debe lanzar la hebra
	 */
	public void teToca() {
		h = new HebraJugador();
		h.start();
	}

	/**
	 * No hacer nada, el jugador automático no debe hacer nada si se pulsa una
	 * casilla
	 */
	public void casillaPulsada(int columna, int fila) {

	}

	@Override
	public void yaNoTeToca() {
		if (h != null) {
			h.interrupt();
		}
		h = null;
	}

	/**
	 * Clase que implementa la hebra que deberá lanzar el JugadorSwingAleatorio
	 * cuando reciba el turno
	 * 
	 * @author Javi
	 * 
	 */
	class HebraJugador extends Thread {

		public void run() {
			try {
				sleep(20);
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						ctrl.ponerAleatorio();
					}
				});
			} catch (InterruptedException e) {

			}

		}
	}

}
