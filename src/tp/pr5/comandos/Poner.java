package tp.pr5.comandos;

import tp.pr5.control.ControladorConsola;
import tp.pr5.control.Jugador;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.Tablero;

/**
 * 
 * @author Javier Martin Villarreal
 * 
 */
public class Poner implements Comando {

	private Jugador j;
	private Ficha color;
	private Tablero t;
	private ControladorConsola c;

	public Poner(ControladorConsola c) {
		this.color = c.quienJuega();
		this.t = c.getTablero();
		this.c = c;
	}

	public boolean meParseo(String s[]) {
		boolean correcto = false;
		if (s.length == 1) {
			if (s[0].equalsIgnoreCase("PONER")) {
				correcto = true;
			}
		}
		return correcto;
	}

	public String ayuda() {
		return "PONER: utilízalo para poner la siguiente ficha.";
	}

	public boolean meEjecuto() {
		if (color == Ficha.BLANCA)
			j = c.getJugadorBlancas();
		else
			j = c.getJugadorNegras();

		System.out.print("Introduce la columna: ");
		Movimiento movimiento = j.getMovimiento(t, color);
		c.poner(movimiento);
		return true;
	}
}
