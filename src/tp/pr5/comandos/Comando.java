package tp.pr5.comandos;

/**
 * Interfaz para crear comandos, nos permitirá añadir nuevos comandos de manera
 * sencilla.
 * 
 * @author Javier Martin Villarreal
 * 
 */
public interface Comando {
	public boolean meParseo(String s[]);

	public boolean meEjecuto();

	public String ayuda();
}
