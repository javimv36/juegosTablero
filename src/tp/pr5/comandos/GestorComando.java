package tp.pr5.comandos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import tp.pr5.control.ControladorConsola;
import tp.pr5.logica.Partida;

/**
 * Clase que se encarga de gestionar un vector de comandos que se parsearan a si
 * mismos
 * 
 * @author Javier Martin Villarreal
 * 
 */
public class GestorComando {

	private ControladorConsola c;
	private List<Comando> comandos = new ArrayList<Comando>();
	private String s;

	public GestorComando(Partida p, ControladorConsola c, Scanner in) {
		this.c = c;

		comandos.add(new Poner(this.c));
		comandos.add(new Deshacer(this.c));
		comandos.add(new Reiniciar(this.c));
		comandos.add(new Jugar(this.c));
		comandos.add(new CambiarJugador(this.c));
		comandos.add(new Salir(this.c));

		s = cadenaAyuda();
		comandos.add(new Ayuda(s));
	}

	public boolean parsearComando(String s[]) {
		boolean comandoCorrecto = false;
		for (Comando comando : comandos) {
			if (comando.meParseo(s)) {
				comandoCorrecto = comando.meEjecuto();
			}
		}
		return comandoCorrecto;
	}

	public String cadenaAyuda() {
		String s = "";
		for (Comando comando : comandos) {
			s = s + comando.ayuda() + "\n";
		}
		return s;
	}
}
