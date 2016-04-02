package tp.pr5.comandos;

import tp.pr5.control.ControladorConsola;

/**
 * 
 * @author Javier Martin Villarreal
 * 
 */
public class Reiniciar implements Comando {
	private ControladorConsola ctrl;

	public Reiniciar(ControladorConsola c) {
		ctrl = c;
	}

	public boolean meParseo(String s[]) {
		boolean correcto = false;
		if (s.length == 1) {
			if (s[0].equalsIgnoreCase("REINICIAR")) {
				correcto = true;
			}
		}
		return correcto;
	}

	public String ayuda() {
		return "REINICIAR: reinicia la partida.";
	}

	public boolean meEjecuto() {
		ctrl.reset();
		return true;
	}
}
