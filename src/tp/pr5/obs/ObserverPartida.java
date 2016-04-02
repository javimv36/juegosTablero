package tp.pr5.obs;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Tablero;

/**
 * Interfaz Observer que implementarán los observadores de la partida.
 * @author Javier Martin Villarreal
 * 
 */
public interface ObserverPartida {
	public void onReset(Tablero tablero, Ficha turno);

	public void onPoner(Tablero t);

	public void onError(String s);

	public void onTerminada(String s);

	public void onDeshacer(Tablero t);

	public void onCambioJuego(Tablero t);

	public void onSalir();

	public void onCambioTurno(Ficha turno);

	public void onJugadorAleatorio(Tablero t);
}
