package observer;
/**
 * @author Chernyj Dmitry
 *
 */
public interface LogFileSubject {
	public void register(LogFileObserver o);
	public void unregister(LogFileObserver o);
	public void notifyObserver(String[] params);
}
