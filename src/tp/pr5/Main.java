package tp.pr5;

import java.util.Scanner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

import tp.pr5.control.Controlador;
import tp.pr5.control.ControladorConsola;
import tp.pr5.control.ControladorVentana;
import tp.pr5.control.FactoriaComplica;
import tp.pr5.control.FactoriaConecta4;
import tp.pr5.control.FactoriaGravity;
import tp.pr5.control.FactoriaReversi;
import tp.pr5.control.FactoriaTipoJuego;
import tp.pr5.logica.Partida;
import tp.pr5.logica.ReglasJuego;
import tp.pr5.vistas.VistaConsola;
import tp.pr5.vistas.VistaVentana;

/**
 * Clase que contiene el punto de entrada a la aplicación.
 * 
 * @author Javier Martin Villarreal
 * 
 */
public class Main {

	/**
	 * Metodo principal de la aplicacion.
	 * 
	 * @param args
	 *            Argumentos pasados a la aplicacion. Prepararemos con ellos la
	 *            aplicación (Juego, Interfaz y Dimensiones del tablero inicial)
	 */
	public static void main(String[] args) {
		String argsSobrantes;
		FactoriaTipoJuego f = new FactoriaConecta4();
		ReglasJuego reglas = f.creaReglas();
		Partida partida = new Partida(reglas);
		Controlador control = new ControladorVentana(f, partida, new Scanner(
				System.in));
		try {
			Options options = new Options();
			options.addOption("g", "game", true,
					"Tipo de juego (c4, co, gr, rv). Por defecto, c4.");
			options.getOption("g").setArgName("game");
			options.addOption("h", "help", false, "Muestra esta ayuda.");
			options.addOption("u", "ui", true,
					"Tipo de interfaz (console, window). Por defecto, console.");
			options.getOption("u").setArgName("Tipo");
			options.addOption("x", "tamX", true,
					"Número de columnas del tablero (sólo para Gravity). Por defecto, 10.");
			options.getOption("x").setArgName("columnNumber");
			options.addOption("y", "tamY", true,
					"Número de filas del tablero (sólo para Gravity). Por defecto, 10.");
			options.getOption("y").setArgName("rowNumber");

			CommandLineParser parser = new PosixParser();
			CommandLine cmd = parser.parse(options, args);

			if (cmd.hasOption("h")) {
				HelpFormatter formatter = new HelpFormatter();
				formatter.printHelp(Main.class.getName(), options, true);
			}

	
			if (cmd.hasOption("g")) {
				argsSobrantes = "";
				if (cmd.getArgs().length == 0) {
					if (cmd.getOptionValue("g").equalsIgnoreCase("CO")) {
						f = new FactoriaComplica();
						reglas = f.creaReglas();
						partida = new Partida(reglas);
					} else if (cmd.getOptionValue("g").equalsIgnoreCase("RV")) {
						f = new FactoriaReversi();
						reglas = f.creaReglas();
						partida = new Partida(reglas);
					} else if (cmd.getOptionValue("g").equalsIgnoreCase("GR")) {
						if (cmd.hasOption("x") && cmd.hasOption("y")) {
							f = new FactoriaGravity(Integer.parseInt(cmd
									.getOptionValue("x", "10")),
									Integer.parseInt(cmd.getOptionValue("y",
											"10")));
							reglas = f.creaReglas();
							partida = new Partida(reglas);
						} else {
							f = new FactoriaGravity(10, 10);
							reglas = f.creaReglas();
							partida = new Partida(reglas);
						}
					} else if (cmd.getOptionValue("g").equalsIgnoreCase("C4")) {
						f = new FactoriaConecta4();
						reglas = f.creaReglas();
						partida = new Partida(reglas);
					} else {
						throw new ParseException("Juego '"
								+ cmd.getOptionValue("g") + "' incorrecto.");
					}
				} else {
					for (int i = 0; i <= cmd.getOptionValues("g").length; i++) {
						argsSobrantes += (" " + cmd.getArgs()[i]);
					}
					throw new ParseException("Argumentos no entendidos:"
							+ argsSobrantes);
				}
			}
				if (cmd.hasOption("u")) {
					argsSobrantes = "";
					if (cmd.getArgs().length == 0) {
						if (cmd.getOptionValue("u").equalsIgnoreCase("window")) {
							control = new ControladorVentana(f, partida,
									new Scanner(System.in));
							VistaVentana vistaVentana = new VistaVentana(
									partida, control);
						} else if (cmd.getOptionValue("u").equalsIgnoreCase(
								"console")) {
							control = new ControladorConsola(f, partida,
									new Scanner(System.in));
							VistaConsola vistaConsola = new VistaConsola(
									partida);
						} else {
							throw new ParseException("Tipo '"
									+ cmd.getOptionValue("u")
									+ "' no entendido.");
						}
					} else {
						for (int i = 0; i <= cmd.getOptionValues("u").length; i++) {
							argsSobrantes += (" " + cmd.getArgs()[i]);
						}
						throw new ParseException("Argumentos no entendidos:"
								+ argsSobrantes);
					}
				}
			else if (!cmd.hasOption("h")){
				
			}

		} catch (ParseException e) {
			System.err.println("Uso incorrecto: " + e.getMessage());
			System.err.println("Use -h|--help para más detalles.");
			System.exit(1);
		}
		control.run();
	}
}
