package tp.pr5.obs;

/**
 * Interfaz observable.
 * @author Javier Martin Villarreal
 * 
 */
public interface Observable<T> {

	public void addObs(T o);

	public void removeObs(T o);
}
