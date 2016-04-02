package tp.pr5.logica;

/**
 * 
 * @author Javier Martin Villarreal
 * 
 */
public class ReglasConecta4 extends ReglasComunes implements ReglasJuego {

	public ReglasConecta4() {

	}

	@Override
	public Tablero iniciaTablero() {
		return new Tablero(7, 6);
	}

	@Override
	public Ficha jugadorInicial() {
		return Ficha.BLANCA;
	}

	@Override
	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t) {
		Ficha ficha = Ficha.VACIA;
		int width = ultimoMovimiento.columna;
		int height = findFicha(width, t);

		ficha = checkWinner(width, height, t);
		return ficha;
	}

	@Override
	public boolean tablas(Ficha ultimoEnPoner, Tablero t) {
		for (int i = 1; i <= t.getAncho(); i++) {
			if (t.getCasilla(i, 1) == Ficha.VACIA)
				return false;
			else if (t.getCasilla(i, 1) == ultimoEnPoner) {
				Movimiento mov = new MovimientoConecta4(i, ultimoEnPoner);
				if (hayGanador(mov, t) == ultimoEnPoner)
					return false;
			}
		}
		return true;
	}

	@Override
	public Ficha siguienteTurno(Ficha ultimoEnPoner, Tablero t) {
		if (ultimoEnPoner == Ficha.BLANCA)
			return Ficha.NEGRA;
		else
			return Ficha.BLANCA;
	}

}
