package observer;
/**
 * @author Chernyj Dmitry
 *
 */
public interface ButtonReadySubject {
	
	public void register(ButtonReadyObserver o);
	public void unregister(ButtonReadyObserver o);
	public void notifyObservers();

}
