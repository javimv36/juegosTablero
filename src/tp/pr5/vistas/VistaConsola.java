package tp.pr5.vistas;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Tablero;
import tp.pr5.obs.Observable;
import tp.pr5.obs.ObserverPartida;
/**
 * 
 * @author Javier Martin Villarreal
 * 
 */
public class VistaConsola implements ObserverPartida {

	public VistaConsola(Observable<ObserverPartida> partida) {
		partida.addObs(this);
	}

	@Override
	public void onReset(Tablero t, Ficha turno) {
		System.out.println(t);
		System.out.println("Partida reiniciada.");
	}

	@Override
	public void onPoner(Tablero t) {
		System.out.println(t);

	}

	@Override
	public void onError(String s) {
		System.err.println(s);

	}

	@Override
	public void onTerminada(String s) {
		System.out.println("Partida terminada.");
		System.out.println(s);

	}

	@Override
	public void onDeshacer(Tablero t) {
		System.out.println(t);
	}

	@Override
	public void onSalir() {
		System.out.println("Fin de la partida");
		System.exit(0);
	}

	@Override
	public void onCambioTurno(Ficha turno) {
	}

	@Override
	public void onCambioJuego(Tablero t) {
		System.out.println(t);
	}

	@Override
	public void onJugadorAleatorio(Tablero t) {
		System.out.println(t);
	}

}
