package observer;

import java.util.ArrayList;
/**
 * @author Chernyj Dmitry
 *
 */
public interface RegisterSubject {
	public void register(RegisterObserver o);
	public void unregister(RegisterObserver o);
	public void notifyObserver(String userName, ArrayList<Integer> numbers);
}
