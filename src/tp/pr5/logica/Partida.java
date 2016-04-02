package tp.pr5.logica;

import java.util.ArrayList;

import tp.pr5.obs.Observable;
import tp.pr5.obs.ObserverPartida;

/**
 * Clase para representar la información de una partida. Se encarga de almacenar
 * el estado del tablero, a quién le toca, si ya hay un ganador, etc., así como
 * la lista de movimientos que se han ido realizando para poder deshacerlos. La
 * partida guarda al menos los 10 últimos movimientos.
 * 
 * @author Javier Martin Villarreal
 * 
 */
public class Partida implements Observable<ObserverPartida> {
	private Tablero tablero;
	private Ficha turno;
	private boolean terminada;
	private Ficha ganador;
	private UndoStack pila;
	private ReglasJuego reglas;
	private ArrayList<ObserverPartida> observadores;

	/**
	 * Construye una partida nueva.
	 * 
	 * @param reglas
	 *            Reglas del juego que se utilizarán durante la partida (al
	 *            menos hasta que se ejecute reset).
	 */
	public Partida(ReglasJuego reglas) {
		observadores = new ArrayList<ObserverPartida>();
		this.reglas = reglas;
		tablero = this.reglas.iniciaTablero();
		turno = this.reglas.jugadorInicial();
		terminada = false;
		ganador = Ficha.VACIA;
		pila = new UndoStack();

	}

	/**
	 * Reinicia la partida en curso. Esta operación no puede deshacerse. Gracias
	 * al parámetro, permite cambiar el tipo de juego al que se juega.
	 * 
	 * @param reglas
	 *            Las reglas del juego a utilizar a partir de ahora.
	 */
	public void reset(ReglasJuego reglas) {
		this.reglas = reglas;
		tablero = this.reglas.iniciaTablero();
		turno = this.reglas.jugadorInicial();
		terminada = false;
		ganador = Ficha.VACIA;
		pila.rst();

		for (ObserverPartida o : observadores) {
			o.onReset(tablero, turno);
		}
	}

	/**
	 * Deshace el último movimiento ejecutado.
	 * 
	 * @return true si se pudo deshacer.
	 */
	public void undo() {
		if (pila.getNumUndo() > -1) {
			pila.getCimaPila().undo(tablero);
			cambioTurno();
			pila.subPila();
			for (ObserverPartida o : observadores) {
				o.onDeshacer(tablero);
			}
		} else
			deshacerError();
	}

	/**
	 * Ejecuta el movimiento indicado.
	 * 
	 * @param mov
	 *            Movimiento a ejecutar. Se asume que el movimiento es
	 *            compatible con las reglas de la partida que se está jugando
	 *            (si se está jugando a Conecta 4, el tipo de movimiento es
	 *            Conecta 4, etc.). En caso contrario, el comportamiento es
	 *            indeterminado.
	 * @throws MovimientoInvalido
	 *             si no se puede efectuar el movimiento. Es un error intentar
	 *             colocar una ficha del jugador que no tiene el turno, cuando
	 *             la partida está terminada, columna llena, etc.
	 */
	public boolean ejecutaMovimiento(Movimiento mov) throws MovimientoInvalido {
		boolean ejecucionCorrecta = false;
		if (mov.color == turno && !terminada) {
			try {
				ejecucionCorrecta = mov.ejecutaMovimiento(tablero);
				pila.addPila(mov);
				ganador = reglas.hayGanador(mov, tablero);
			} catch (MovimientoInvalido e) {
				for (ObserverPartida o : observadores) {
					o.onError(e.getMessage());
				}
				throw e;
			}

			if (reglas.tablas(turno, tablero) || ganador != Ficha.VACIA) {
				terminada = true;
			} else {
				turno = reglas.siguienteTurno(turno, tablero);
				for (ObserverPartida o : observadores) {
					o.onCambioTurno(turno);
				}
			}
			ejecucionCorrecta = true;
		} else {
			throw new MovimientoInvalido("No es tu turno");
		}
		for (ObserverPartida o : observadores) {
			o.onPoner(tablero);
		}
		ganador = reglas.hayGanador(mov, tablero);
		if (ganador != Ficha.VACIA) {
			hayGanador();
		} else if (reglas.tablas(turno, tablero) || turno == Ficha.VACIA) { /* */
			hayTablas();
		}
		return ejecucionCorrecta;
	}

	/**
	 * metodo que cambia el truno de blancas a negras y viceversa
	 * 
	 */
	private void cambioTurno() {
		if (turno == Ficha.BLANCA)
			turno = Ficha.NEGRA;
		else
			turno = Ficha.BLANCA;

		for (ObserverPartida o : observadores) {
			o.onCambioTurno(turno);
		}

	}

	/**
	 * Devuelve el color del jugador al que le toca poner.
	 * 
	 * @return Color del jugador, o Ficha.VACIA si la partida ha terminado.
	 */
	public Ficha getTurno() {
		if (terminada)
			turno = Ficha.VACIA;
		return turno;
	}

	/**
	 * Devuelve el objeto reglas del juego en curso
	 * 
	 * @return reglas del juego en curso
	 */
	public ReglasJuego getReglas() {
		return reglas;
	}

	/**
	 * Devuelve el color del ganador. Sólo válido si la partida ya ha terminado
	 * (isTerminada() == true).
	 * 
	 * @return Color del ganador. Si la partida terminó en tablas, Ficha.VACIA.
	 *         Si la partida no ha terminado aún, el resultado es indeterminado.
	 */
	public Ficha getGanador() {
		return ganador;
	}

	/**
	 * Método para saber si la partida ha conluído ya o no.
	 * 
	 * @return true si la partida ha acabado.
	 */
	public boolean isTerminada() {
		return terminada;
	}

	public void salir() {
		for (ObserverPartida o : observadores) {
			o.onSalir();
		}
	}

	/**
	 * Método de acceso al tablero. Dependiendo de cómo se haga la
	 * implementación del resto de clases (principalmente de la clase
	 * Controlador), es posible que no se utilice para nada en la práctica. Sin
	 * embargo, es necesario implementarlo para poder ejecutar los test de
	 * unidad.
	 * 
	 * @return Estado del tablero actual.
	 */
	public Tablero getTablero() {
		return tablero;
	}

	@Override
	public void addObs(ObserverPartida o) {
		observadores.add(o);
	}

	@Override
	public void removeObs(ObserverPartida o) {
		observadores.remove(o);
	}

	/**
	 * Llama el metodo onTerminada de todos los observadores de la partida
	 * pasándoles el mensaje con el ganandor.
	 */
	public void hayGanador() {
		for (ObserverPartida o : observadores) {
			o.onTerminada("Ganan las " + ganador + "s");
		}
	}

	/**
	 * Llama el metodo onTerminada de todos los observadores de la partida
	 * pasándoles el mensaje de que ha habido tablas.
	 */
	public void hayTablas() {
		for (ObserverPartida o : observadores) {
			o.onTerminada("Partida terminada en tablas.");
		}
	}

	/**
	 * Llama el metodo onError de todos los observadores de la partida
	 * pasándoles el mensaje de error al deshacer.
	 */
	public void deshacerError() {
		for (ObserverPartida o : observadores) {
			o.onError("No se pudo deshacer.");
		}

	}

	/**
	 * Llama el metodo onCambioJuego de todos los observadores de la partida
	 */
	public void cambioJuego() {
		for (ObserverPartida o : observadores) {
			o.onCambioJuego(tablero);
		}
	}

	/**
	 * Establece el atributo terminada a verdadero.
	 */
	public void setTerminada() {
		terminada = true;
	}

	public void cambioJugador() {
		for (ObserverPartida o : observadores) {
			o.onJugadorAleatorio(tablero);
		}
	}
}
