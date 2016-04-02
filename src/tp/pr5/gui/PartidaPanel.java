package tp.pr5.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import tp.pr5.control.Controlador;

/**
 * 
 * @author Javier Martin Villarreal
 * 
 */
public class PartidaPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JButton deshacer;
	private JButton reiniciar;
	private Controlador ctrl;

	public PartidaPanel(Controlador c) {
		super();

		ctrl = c;

		ImageIcon deshacerImg = new ImageIcon("imagenes/undop.png");
		deshacer = new JButton("Deshacer", deshacerImg);
		deshacer.setBackground(Color.WHITE);
		deshacer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ctrl.undo();
			}
		});
		ImageIcon reiniciarImg = new ImageIcon("imagenes/reiniciarp.png");
		reiniciar = new JButton("Reiniciar", reiniciarImg);
		reiniciar.setBackground(Color.WHITE);
		reiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.reset();
			}
		});

		this.setLayout(new FlowLayout());
		this.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.gray, 1),
				"Partida"));
		this.setBackground(Color.WHITE);
		this.add(deshacer);
		this.add(reiniciar);
	}

	public void desactivarBotones() {
		deshacer.setEnabled(false);
	}

	public void activarBotones() {
		deshacer.setEnabled(true);
	}

}
