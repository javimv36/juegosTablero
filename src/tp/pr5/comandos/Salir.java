package tp.pr5.comandos;

import tp.pr5.control.Controlador;
import tp.pr5.control.ControladorConsola;
import tp.pr5.logica.Partida;

/**
 * 
 * @author Javier Martin Villarreal
 * 
 */
public class Salir implements Comando {

	private Controlador ctrl;
	private Partida p;

	public Salir(ControladorConsola c) {
		ctrl = c;
		p = c.getPartida();
	}

	public boolean meParseo(String s[]) {
		boolean correcto = false;
		if (s.length == 1) {
			if (s[0].equalsIgnoreCase("SALIR")) {
				correcto = true;
			}
		}
		return correcto;
	}

	public String ayuda() {
		return ("SALIR: termina la aplicación.");
	}

	public boolean meEjecuto() {
		ctrl.salir();
		p.setTerminada();
		return true;
	}
}
