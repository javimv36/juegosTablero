package tp.pr5.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import tp.pr5.control.Controlador;

public class SalirPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JButton salir;
	private Controlador ctrl;

	public SalirPanel(Controlador c) {
		super();
		ctrl = c;

		ImageIcon salirImg = new ImageIcon("imagenes/salirp.png");
		salir = new JButton(salirImg);
		salir.setBackground(Color.WHITE);
		salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.salir();
			}
		});
		this.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.gray, 1),
				"Pulse para salir"));
		this.setLayout(new BorderLayout());
		this.add(salir, BorderLayout.CENTER);
	}
}
