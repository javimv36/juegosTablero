package tp.pr5.logica;

/**
 * 
 * @author Javier Martin Villarreal
 * 
 */
public class ReglasComplica extends ReglasComunes implements ReglasJuego {

	public ReglasComplica() {

	}
	
	@Override
	public Tablero iniciaTablero() {
		return new Tablero(4, 7);
	}

	@Override
	public Ficha jugadorInicial() {
		return Ficha.BLANCA;
	}

	@Override
	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t) {

		boolean ganaTurno = false;
		boolean ganaOtro = false;
		Ficha turno = ultimoMovimiento.color;
		Ficha otro;
		Ficha ficha;

		if (turno == Ficha.NEGRA)
			otro = Ficha.BLANCA;
		else
			otro = Ficha.NEGRA;

		for (int i = 1; i <= t.getAlto(); i++) {
			ficha = hayGanador2(ultimoMovimiento, t, i);
			if (ficha == turno)
				ganaTurno = true;
			else if (ficha == otro)
				ganaOtro = true;
		}

		if (ganaTurno && !ganaOtro) {
			return turno;
		} else if (ganaOtro && !ganaTurno) {
			return otro;
		} else
			return Ficha.VACIA;
	}

	@Override
	public boolean tablas(Ficha ultimoEnPoner, Tablero t) {
		return false;
	}

	@Override
	public Ficha siguienteTurno(Ficha ultimoEnPoner, Tablero t) {

		if (ultimoEnPoner == Ficha.BLANCA)
			return Ficha.NEGRA;
		else
			return Ficha.BLANCA;
	}

	private Ficha hayGanador2(Movimiento ultimoMovimiento, Tablero t, int fila) {
		Ficha ficha = Ficha.VACIA;
		int width = ultimoMovimiento.columna;
		int height = fila;

		ficha = checkWinner(width, height, t);
		return ficha;
	}

}
