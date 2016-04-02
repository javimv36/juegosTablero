package tp.pr5.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import tp.pr5.control.Controlador;
/**
 * 
 * @author Javier Martin Villarreal
 * 
 */
public class AleatorioPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JButton aleatorio;
	private Controlador ctrl;

	public AleatorioPanel(Controlador c) {
		super();
		ctrl = c;

		ImageIcon aleatorioImg = new ImageIcon("imagenes/aleatoriop.png");
		aleatorio = new JButton("Poner aleatorio", aleatorioImg);
		aleatorio.setBackground(Color.WHITE);
		aleatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.poner(ctrl.getFactoria().creaJugadorAleatorio()
						.getMovimiento(ctrl.getTablero(), ctrl.quienJuega()));
			}
		});
		this.setLayout(new BorderLayout());
		this.setBackground(Color.WHITE);
		this.add(aleatorio, BorderLayout.CENTER);
	}

	public void desactivarBotones() {
		aleatorio.setEnabled(false);
		aleatorio.validate();
	}

	public void activarBotones() {
		aleatorio.setEnabled(true);
		aleatorio.validate();
	}

}
