package tp.pr5.comandos;

/**
 * 
 * @author Javier Martin Villarreal
 * 
 */
public class Ayuda implements Comando {

	private String s;

	public Ayuda(String s) {
		this.s = s;
	}

	public boolean meParseo(String[] s) {
		boolean correcto = false;
		if (s.length == 1) {
			if (s[0].equalsIgnoreCase("AYUDA")) {
				correcto = true;
			}
		}
		return correcto;
	}

	public boolean meEjecuto() {
		System.out.println("Los comandos disponibles son:" + "\n" + "\n" + s
				+ ayuda() + "\n");
		return true;
	}

	public String ayuda() {
		return "AYUDA: muestra esta ayuda.";
	}

}
