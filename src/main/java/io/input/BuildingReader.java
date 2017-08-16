package io.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import imageMapping.helper.Tuple;
import titles.HoldingType;
import titles.buildings.Building;
import titles.buildings.CategorizedBuildings;

/**
 * Reader for the buildings.
 * 
 * @author Zarfius
 *
 */
public class BuildingReader {

	/**
	 * Parses a buildings txt file to get the buildings for the different holding types.
	 * 
	 * Works at the moment only if the document is in a specific formatting style. Closing brackets need to be in a
	 * separate line if the opening is not in the same line. Also after the opening bracket no text is allowed.
	 * 
	 * @param path
	 *            Path to the file
	 * @return A list of buildings categorized into the holdings
	 */
	public static List<CategorizedBuildings> readBuildings(Path path) {
		// TODO Make it compatible with messy text.
		List<CategorizedBuildings> ret = new ArrayList<CategorizedBuildings>();

		try (BufferedReader reader = Files.newBufferedReader(path)) {
			String line;
			int depth = 0;
			String[] split;
			CategorizedBuildings catb = null;
			Building b = null;
			String value = "";
			String identifier = "";

			while ((line = reader.readLine()) != null) {
				line = line.trim();

				if (line.length() > 0 && line.charAt(0) != '#') {
					//System.out.println(line);

					if (line.length() >= 1) {
						split = line.split("=");
						if (depth == 0 && split.length == 2) {
							catb = new CategorizedBuildings(HoldingType.matchString(split[0].trim()));
							ret.add(catb);
							depth += 1;
						} else if (depth == 1 && split.length == 2) {
							if (catb != null) {
								b = new Building(split[0].trim());
								catb.addBuilding(b);
								depth += 1;
							} else {
								throw new InputMismatchException(
										"Something is wrong in the file. Cannot add a building to a non excisting holding type");
							}
						} else if (depth == 2 && split.length >= 2) {
							identifier = split[0].trim();
							value = split[1].trim();
							if (value.length() == 1 && value.charAt(0) == '{') {
								value = "";
								depth += 1;
							} else {
								for (int i = 2; i < split.length; i++) {
									value = value.concat(" = " + split[i].trim());
								}
								b.addParameter(new Tuple<String, String>(identifier, value));
							}
						} else if (depth >= 3 && split.length >= 2) {
							value = value.concat(line);
							String s = split[1].trim();
							if (s.length() == 1 && s.charAt(0) == '{') {
								depth += 1;
							}

						} else if (split[0].trim().length() == 1 && split[0].charAt(0) == '}') {
							if (depth >= 3) {
								value = value.concat(line);
								if (depth == 3) {
									b.addParameter(new Tuple<String, String>(identifier, value));
								}
							}
							depth -= 1;
						} else {
							throw new InputMismatchException("Something went wrong while parsing the input: " + line);
						}
					}
				} else {

				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

}
