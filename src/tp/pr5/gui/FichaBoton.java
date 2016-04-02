package tp.pr5.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import tp.pr5.control.Controlador;
import tp.pr5.logica.Ficha;

/**
 * 
 * @author Javier Martin Villarreal
 * 
 */
public class FichaBoton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int fila;
	private int columna;
	private Ficha colr;
	private Controlador ctrl;
	private JugadorSwing jugador;

	private static ImageIcon vacia = new ImageIcon("imagenes/vaciap.png");
	private ImageIcon blanca = new ImageIcon("imagenes/blancap.png");
	private ImageIcon negra = new ImageIcon("imagenes/negrap.png");

	public FichaBoton(Ficha color, int fil, int col, Controlador c) {
		super(vacia);
		colr = color;
		columna = col;
		fila = fil;
		ctrl = c;
		
		setJugador(new JugadorSwingHumano(ctrl));
		setFicha(colr);
		
		this.setBorder(null);
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jugador.casillaPulsada(columna, fila);
			}
		});
		
	}

	public void setJugador(JugadorSwing j) {
		jugador = j;
	}

	public int getColumna() {
		return columna;
	}

	public int getFila() {
		return fila;
	}

	public void desactivar() {
		this.setEnabled(false);
		this.validate();
	}

	public void activar() {
		this.setEnabled(true);
		this.validate();
	}

	public void setFicha(Ficha color) {
		colr = color;
		if (color == Ficha.BLANCA) {
			this.setIcon(blanca);
		} else if (color == Ficha.NEGRA) {
			this.setIcon(negra);
		} else {
			this.setIcon(vacia);
		}
	}
}
