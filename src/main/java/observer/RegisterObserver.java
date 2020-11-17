package observer;

import java.util.ArrayList;
/**
 * @author Chernyj Dmitry
 *
 */
public interface RegisterObserver {
	public void update(String userName, ArrayList<Integer> numbers);
}
