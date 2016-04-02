package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoComplica;
import tp.pr5.logica.ReglasComplica;
import tp.pr5.logica.ReglasJuego;

/**
 * 
 * @author Javier Martin Villarreal
 * 
 */
public class FactoriaComplica implements FactoriaTipoJuego {

	public ReglasJuego creaReglas() {
		return new ReglasComplica();
	}

	public Movimiento creaMovimiento(int col, int fila, Ficha color) {
		return new MovimientoComplica(col, color);
	}

	public Jugador creaJugadorHumanoConsola(Scanner in) {
		return new JugadorHumanoComplica(in);
	}

	public Jugador creaJugadorAleatorio() {
		return new JugadorAleatorioComplica();
	}

}
