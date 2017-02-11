package imageMapping.helper;

/**
 * Simple class to combine two classes in one.
 * @author Zarfius
 *
 * @param <X> first class
 * @param <Y> second class
 */
public class Tuple<X, Y> {

	public final X x;
	public final Y y;

	/**
	 * New Tuple Instance
	 * @param x first value
	 * @param y second value
	 */
	public Tuple(X x, Y y) {
		this.x = x;
		this.y = y;
	}
}
