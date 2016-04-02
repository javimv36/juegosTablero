package tp.pr5.logica;

/**
 * 
 * @author Javier Martin Villarreal
 * 
 */
public class ReglasGravity extends ReglasComunes implements ReglasJuego {
	private int i;
	private int j;

	public ReglasGravity(int i, int j) {
		this.i = i;
		this.j = j;
	}

	@Override
	public Tablero iniciaTablero() {
		return new Tablero(i, j);
	}


	public Tablero iniciaTablero(int x, int y) {
		return new Tablero(x, y);
	}

	@Override
	public Ficha jugadorInicial() {
		return Ficha.BLANCA;
	}

	@Override
	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t) {
		Ficha ficha = Ficha.VACIA;
		int width = t.getAncho();
		int height = t.getAlto();

		for (int i = 1; i <= width; i++) {
			for (int j = 1; j <= height; j++) {
				ficha = checkWinner(i, j, t);
				if (ficha != Ficha.VACIA)
					return ficha;
			}
		}
		return ficha;
	}

	@Override
	public boolean tablas(Ficha ultimoEnPoner, Tablero t) {
		int ancho = t.getAncho();
		int alto = t.getAlto();

		for (int i = 1; i <= ancho; i++) {
			for (int j = 1; j <= alto; j++) {
				if (t.getCasilla(i, j) == Ficha.VACIA)
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
