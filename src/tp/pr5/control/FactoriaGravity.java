package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoGravity;
import tp.pr5.logica.ReglasGravity;
import tp.pr5.logica.ReglasJuego;

/**
 * 
 * @author Javier Martin Villarreal
 */
public class FactoriaGravity implements FactoriaTipoJuego {

	private int i;
	private int j;

	/**
	 * Consrtuctor de la clase con parámetros, tamanio IxJ.
	 * @param i ancho del tablero.
	 * @param j alto del tablero.
	 */
	public FactoriaGravity(int i, int j) {
		this.i = i;
		this.j = j;
	}

	/**
	 * Constructor de la clase sin parámetros, tablero por defecto 10x10
	 */
	public FactoriaGravity() {
		this.i = 10;
		this.j = 10;
	}

	public ReglasJuego creaReglas() {
		return new ReglasGravity(i, j);
	}

	public Movimiento creaMovimiento(int col, int fila, Ficha color) {
		return new MovimientoGravity(col, fila, color);
	}

	public Jugador creaJugadorHumanoConsola(Scanner in) {
		return new JugadorHumanoGravity(in);
	}

	public Jugador creaJugadorAleatorio() {
		return new JugadorAleatorioGravity();
	}
}
