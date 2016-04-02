package tp.pr5.comandos;

import java.util.Scanner;

import tp.pr5.control.ControladorConsola;
import tp.pr5.control.FactoriaTipoJuego;
import tp.pr5.control.Jugador;
import tp.pr5.logica.Ficha;

/**
 * 
 * @author Javier Martin Villarreal
 * 
 */
public class CambiarJugador implements Comando {

	private Jugador j;
	private FactoriaTipoJuego f;
	private Scanner in;
	private Ficha color;
	private ControladorConsola ctrl;

	public CambiarJugador(ControladorConsola c) {
		this.f = c.getFactoria();
		this.in = c.getEntrada();
		ctrl = c;
		color = Ficha.VACIA;
	}

	public boolean meParseo(String[] s) {
		boolean correcto = false;
		if (s.length == 3) {
			if (s[0].equalsIgnoreCase("JUGADOR")) {
				if (s[2].equalsIgnoreCase("ALEATORIO")) {
					correcto = true;
					j = f.creaJugadorAleatorio();
				} else if (s[2].equalsIgnoreCase("HUMANO")) {
					j = f.creaJugadorHumanoConsola(in);
					correcto = true;
				}
			}
			if (s[1].equalsIgnoreCase("NEGRAS")) {
				color = Ficha.NEGRA;
			} else if (s[1].equalsIgnoreCase("BLANCAS")) {
				color = Ficha.BLANCA;
			}
		}
		return correcto;
	}

	public String ayuda() {
		return "JUGADOR [blancas|negras] [humano|aleatorio]: cambia el tipo de jugador.";
	}

	public boolean meEjecuto() {
		ctrl.cambioJugador(j, color);
		return true;
	}
}
