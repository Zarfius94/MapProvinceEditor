package titles;

import java.util.InputMismatchException;

/**
 * Holding types of the game.
 * @author Zarfius
 *
 */
public enum HoldingType {
	CASTLE, CITY, TEMPLE, TRIBE, NOMAD, PALACE, TRADEPOST, HOSPITAL;
	
	/**
	 * Mapps a input string to the matching holding enum.
	 * @param s String to be matched
	 * @return matched holding enum.
	 */
	public static HoldingType matchString(String s) {
		switch (s.toLowerCase()) {
		case "castle":
			return HoldingType.CASTLE;
		case "city":
			return HoldingType.CITY;
		case "temple":
			return HoldingType.TEMPLE;
		case "tribe":
			return HoldingType.TRIBE;
		case "nomade":
			return HoldingType.NOMAD;
		case "family_palace":
		case "palace":
			return HoldingType.PALACE;
			
		case "tradepost":
			return HoldingType.TRADEPOST;
		case "hospital":
			return HoldingType.HOSPITAL;
			
		default:
			throw new InputMismatchException("the input " + s + " could not get matched to an existing holding type.");
		}
	}
}


