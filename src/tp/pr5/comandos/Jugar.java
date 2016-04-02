package tp.pr5.comandos;

import tp.pr5.control.ControladorConsola;
import tp.pr5.control.FactoriaComplica;
import tp.pr5.control.FactoriaConecta4;
import tp.pr5.control.FactoriaGravity;
import tp.pr5.control.FactoriaReversi;
import tp.pr5.control.FactoriaTipoJuego;

/**
 * 
 * @author Javier Martin Villarreal
 * 
 */
public class Jugar implements Comando {

	private FactoriaTipoJuego f;
	private ControladorConsola ctrl;

	public Jugar(ControladorConsola c) {
		this.f = c.getFactoria();
		ctrl = c;
	}

	public boolean meParseo(String s[]) {
		boolean correcto = false;
		if (s.length == 2) {
			if (s[0].equalsIgnoreCase("JUGAR")) {
				if (s[1].equalsIgnoreCase("C4")) {
					f = new FactoriaConecta4();
					correcto = true;
				} else if (s[1].equalsIgnoreCase("CO")) {
					f = new FactoriaComplica();
					correcto = true;
				} else if (s[1].equalsIgnoreCase("RV")) {
					f = new FactoriaReversi();
					correcto = true;
				}
			}
		} else if (s.length == 4) {
			if (s[0].equalsIgnoreCase("JUGAR")) {
				if (s[1].equalsIgnoreCase("GR")) {
					f = new FactoriaGravity(Integer.parseInt(s[2]),
							Integer.parseInt(s[3]));
					correcto = true;
				}
			}
		}
		return correcto;
	}

	public String ayuda() {
		return "JUGAR [c4|co|gr] [tamX tamY]: cambia el tipo de juego.";
	}

	public boolean meEjecuto() {
		ctrl.cambioJuego(f);
		return true;
	}
}
