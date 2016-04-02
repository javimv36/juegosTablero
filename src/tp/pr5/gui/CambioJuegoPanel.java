package tp.pr5.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tp.pr5.control.Controlador;
import tp.pr5.control.FactoriaComplica;
import tp.pr5.control.FactoriaConecta4;
import tp.pr5.control.FactoriaGravity;
import tp.pr5.control.FactoriaReversi;

/**
 * 
 * @author Javier Martin Villarreal
 * 
 */
public class CambioJuegoPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JButton nuevoJuego;
	private JComboBox cajaJuegos;
	private JTextField filaText;
	private JTextField coluText;
	private JLabel filaLabel;
	private JLabel coluLabel;
	private JPanel columnaFila;
	private Controlador ctrl;

	public CambioJuegoPanel(Controlador c) {
		super();
		ctrl = c;

		columnaFila = new JPanel();

		coluLabel = new JLabel("Columna:");
		coluLabel.setBackground(Color.WHITE);
		coluText = new JTextField(5);
		coluText.setText("10");
		coluText.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		coluText.setBackground(Color.WHITE);

		filaLabel = new JLabel("Fila:");
		filaLabel.setBackground(Color.WHITE);
		filaText = new JTextField(5);
		filaText.setText("10");
		filaText.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		filaText.setBackground(Color.WHITE);

		columnaFila.setLayout(new FlowLayout());
		columnaFila.add(coluLabel);
		columnaFila.add(coluText);
		columnaFila.add(filaLabel);
		columnaFila.add(filaText);
		columnaFila.setBackground(Color.WHITE);
		columnaFila.setVisible(false);

		String[] juegos = { "Conecta 4", "Complica", "Gravity", "Reversi" };
		cajaJuegos = new JComboBox(juegos);
		cajaJuegos.setBackground(Color.WHITE);
		cajaJuegos.setSelectedItem(1);
		cajaJuegos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cajaJuegos.getSelectedItem().toString() == "Gravity") {
					columnaFila.setVisible(true);
				} else
					columnaFila.setVisible(false);
			}
		});

		ImageIcon nuevoJuegoImg = new ImageIcon("imagenes/nuevop.png");
		nuevoJuego = new JButton("Nuevo juego", nuevoJuegoImg);
		nuevoJuego.setBackground(Color.WHITE);
		nuevoJuego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cajaJuegos.getSelectedItem().toString() == "Gravity") {
					if (coluText.getText().trim().length() == 0
							|| filaText.getText().trim().length() == 0) {
						JOptionPane
								.showMessageDialog(
										null,
										"Creando partida por defecto de dimensiones 10x10",
										"Faltan argumentos", 1, null);
						ctrl.cambioJuego(new FactoriaGravity(10, 10));
					} else
						ctrl.cambioJuego(new FactoriaGravity(Integer
								.parseInt(coluText.getText()), Integer
								.parseInt(filaText.getText())));
				} else if (cajaJuegos.getSelectedItem().toString() == "Complica") {
					ctrl.cambioJuego(new FactoriaComplica());
				} else if (cajaJuegos.getSelectedItem().toString() == "Conecta 4") {
					ctrl.cambioJuego(new FactoriaConecta4());
				} else if (cajaJuegos.getSelectedItem().toString() == "Reversi") {
					ctrl.cambioJuego(new FactoriaReversi());
				}
			}
		});

		this.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.gray, 1),
				"Cambio de juego"));
		this.setLayout(new BorderLayout());
		this.add(cajaJuegos, BorderLayout.NORTH);
		this.add(columnaFila, BorderLayout.CENTER);
		this.add(nuevoJuego, BorderLayout.SOUTH);
		this.setBackground(Color.WHITE);
	}

	public void desactivarBotones() {
		nuevoJuego.setEnabled(false);
		cajaJuegos.setEnabled(false);

	}

	public void activarBotones() {
		nuevoJuego.setEnabled(true);
		cajaJuegos.setEnabled(true);

	}

}
