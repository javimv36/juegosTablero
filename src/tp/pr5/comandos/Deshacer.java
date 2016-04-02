package tp.pr5.comandos;

import tp.pr5.control.ControladorConsola;

/**
 * 
 * @author Javier Martin Villarreal
 * 
 */
public class Deshacer implements Comando {

	private ControladorConsola ctrl;

	public Deshacer(ControladorConsola c) {
		ctrl = c;
	}

	public boolean meParseo(String s[]) {
		boolean correcto = false;
		if (s.length == 1) {
			if (s[0].equalsIgnoreCase("DESHACER")) {
				correcto = true;
			}
		}
		return correcto;
	}

	public String ayuda() {
		return "DESHACER: deshace el último movimiento hecho en la partida.";
	}

	public boolean meEjecuto() {
		ctrl.undo();
		return true;
	}
}
