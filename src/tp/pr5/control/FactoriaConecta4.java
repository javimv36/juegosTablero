package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoConecta4;
import tp.pr5.logica.ReglasConecta4;
import tp.pr5.logica.ReglasJuego;

/**
 * 
 * @author Javier Martin Villarreal
 * 
 */
public class FactoriaConecta4 implements FactoriaTipoJuego {

	public ReglasJuego creaReglas() {
		return new ReglasConecta4();
	}

	public Movimiento creaMovimiento(int col, int fila, Ficha color) {
		return new MovimientoConecta4(col, color);
	}

	public Jugador creaJugadorHumanoConsola(Scanner in) {
		return new JugadorHumanoConecta4(in);
	}

	public Jugador creaJugadorAleatorio() {
		return new JugadorAleatorioConecta4();
	}

}
