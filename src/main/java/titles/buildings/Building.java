package titles.buildings;

import java.util.ArrayList;
import java.util.List;

import imageMapping.helper.Tuple;

/**
 * This class represents a building
 * @author Zarfius
 *
 */
public class Building {

	private List<Tuple<String, String>> parameters;
	private String tag;
	
	public Building(String tag) {
		this.tag = tag;
		this.parameters = new ArrayList<Tuple<String, String>>();
	}
	
	/**
	 * Add a parameter.
	 * @param parameter A parameter consisting of a identifier and a value tuple.
	 */
	public void addParameter(Tuple<String, String> parameter) {
		this.parameters.add(parameter);
	}

	/**
	 * @return the parameters
	 */
	public final List<Tuple<String, String>> getParameters() {
		return parameters;
	}

	/**
	 * @return the tag
	 */
	public final String getTag() {
		return tag;
	}
	
}
