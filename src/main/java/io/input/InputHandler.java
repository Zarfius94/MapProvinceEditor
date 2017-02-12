package io.input;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
//import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import imageMapping.helper.Tuple;

/**
 * Handles the various files needed to successfully run the map in game.
 * @author Zarfius
 * @version 1.0
 *
 */
public class InputHandler {

	private File mapDefault;

	// private HashMap<String, File> hmap;

	private File csvDefinition;
	private File bmpProvinces;
	private File txtPositions;
	private File bmpTerrain;
	private File bmpRivers;
	private File txtTerrain;
	private File bmpHeightmap;
	private File bmpTrees;
	private File txtContinents;
	private File csvAdjacencies;
	private File txtClimate;
	private File txtIsland_region;
	private File txtGeographical_region;
	private File txtSeasons;

	private int maxProvinces = -1;

	private int checksum = (int) Math.pow(2, 14) - 1;
	
	private Path mapfolder;

	private List<Tuple<Integer, Integer>> sea_prov = new LinkedList<Tuple<Integer, Integer>>();
	private List<Integer> seaprov = new LinkedList<Integer>();
	private List<List<Integer>> ocean = new LinkedList<List<Integer>>();
	private List<String> missingFiles;

	/**
	 * Creates a new handler for the given path.
	 * @param mapfolder path to the directory the files are in.
	 */
	public InputHandler(Path mapfolder) {
		if(mapfolder == null){
			throw new IllegalArgumentException("Path is needed");
		} else {
			this.mapfolder = mapfolder;
		}

		this.mapDefault = new File(mapfolder.toFile(), "default.map");
		if (!this.mapDefault.exists()) {
			throw new IllegalArgumentException("default.map needs to exist for a functionable map");
		} else {
			this.missingFiles = new LinkedList<String>();
			evaluateDefault(mapfolder);
		}

	}

	/*
	 * Use this method only when you have validated default.map exists and is set to the variable.
	 */
	private void evaluateDefault(Path mapfolder) {
		if (mapDefault == null) {
			throw new IllegalArgumentException("default.map must be set");
		}

		try (BufferedReader reader = Files.newBufferedReader(mapDefault.toPath())) {
			String line = null;
			String[] stra = null;
			while ((line = reader.readLine()) != null) {
				stra = line.split("=");
				if (stra.length == 2) {
					line = stra[1].replace("\"", "").trim();
					switch (stra[0].trim()) {
					case "definitions":
						this.csvDefinition = new File(mapfolder.toFile(), line);
						if (!this.csvDefinition.exists()) {
							this.missingFiles.add(line);
						}
						checksum -= (int) Math.pow(2, 0);
						break;
					case "provinces":
						this.bmpProvinces = new File(mapfolder.toFile(), line);
						if (!this.bmpProvinces.exists()) {
							this.missingFiles.add(line);
						}
						checksum -= (int) Math.pow(2, 1);
						break;
					case "positions":
						this.txtPositions = new File(mapfolder.toFile(), line);
						if (!this.txtPositions.exists()) {
							this.missingFiles.add(line);
						}
						checksum -= (int) Math.pow(2, 2);
						break;
					case "terrain":
						this.bmpTerrain = new File(mapfolder.toFile(), line);
						if (!this.bmpTerrain.exists()) {
							this.missingFiles.add(line);
						}
						checksum -= (int) Math.pow(2, 3);
						break;
					case "rivers":
						this.bmpRivers = new File(mapfolder.toFile(), line);
						if (!this.bmpRivers.exists()) {
							this.missingFiles.add(line);
						}
						checksum -= (int) Math.pow(2, 4);
						break;
					case "terrain_definition":
						this.txtTerrain = new File(mapfolder.toFile(), line);
						if (!this.txtTerrain.exists()) {
							this.missingFiles.add(line);
						}
						checksum -= (int) Math.pow(2, 5);
						break;
					case "heightmap":
						this.bmpHeightmap = new File(mapfolder.toFile(), line);
						if (!this.bmpHeightmap.exists()) {
							this.missingFiles.add(line);
						}
						checksum -= (int) Math.pow(2, 6);
						break;
					case "tree_definition":
						this.bmpTrees = new File(mapfolder.toFile(), line);
						if (!this.bmpTrees.exists()) {
							this.missingFiles.add(line);
						}
						checksum -= (int) Math.pow(2, 7);
						break;
					case "continent":
						this.txtContinents = new File(mapfolder.toFile(), line);
						if (!this.txtContinents.exists()) {
							this.missingFiles.add(line);
						}
						checksum -= (int) Math.pow(2, 8);
						break;
					case "adjacencies":
						this.csvAdjacencies = new File(mapfolder.toFile(), line);
						if (!this.csvAdjacencies.exists()) {
							this.missingFiles.add(line);
						}
						checksum -= (int) Math.pow(2, 9);
						break;
					case "climate":
						this.txtClimate = new File(mapfolder.toFile(), line);
						if (!this.txtClimate.exists()) {
							this.missingFiles.add(line);
						}
						checksum -= (int) Math.pow(2, 10);
						break;
					case "region":
						this.txtIsland_region = new File(mapfolder.toFile(), line);
						if (!this.txtIsland_region.exists()) {
							this.missingFiles.add(line);
						}
						checksum -= (int) Math.pow(2, 11);
						break;
					case "geographical_region":
						this.txtGeographical_region = new File(mapfolder.toFile(), line);
						if (!this.txtGeographical_region.exists()) {
							this.missingFiles.add(line);
						}
						checksum -= (int) Math.pow(2, 12);
						break;
					case "seasons":
						this.txtSeasons = new File(mapfolder.toFile(), line);
						if (!this.txtSeasons.exists()) {
							this.missingFiles.add(line);
						}
						checksum -= (int) Math.pow(2, 13);
						break;
					case "max_provinces":
						this.maxProvinces = Integer.parseInt(line);
						break;
					case "sea_zones":
						line = line.substring(2, line.length() - 1);
						stra = line.split(" ");
						int start = Integer.parseInt(stra[0]);
						int end = Integer.parseInt(stra[1]);
						sea_prov.add(new Tuple<Integer, Integer>(start, end));
						for (int i = start; i <= end; i++) {
							seaprov.add(i);
						}
						break;
					case "ocean_region":
						line = reader.readLine();
						Pattern p = Pattern.compile("-?\\d+");
						Matcher m = p.matcher(line);
						List<Integer> tl = new ArrayList<Integer>();
						while (m.find()) {
							tl.add(Integer.parseInt(m.group()));
						}
						Collections.sort(tl);
						ocean.add(tl);
						break;
					default:
						System.out.println(stra[0] + stra[1]);
						break;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (this.missingFiles.size() > 0) {
			String error = "The following files are missing in the 'map' folder: ";
			for (String s : this.missingFiles) {
				error = error.concat(" " + s);
			}
			System.out.println(error);
			System.out.println("If you want to use the default ones then you can ignore this.");
			// throw new IllegalArgumentException(error);
		}

		if (!validateChecksum() && this.csvDefinition != null && this.maxProvinces > 0) {
			throw new IllegalArgumentException("Minimum requirements not met");
		}
	}

	private boolean validateChecksum() {
		if (checksum == 0) {
			// Everything is defined.
			return true;
		} else {
			String bin = Integer.toBinaryString(checksum);
			System.out.print("Missing definitions: ");
			for (int i = 0; i < bin.length(); i++) {
				if (bin.charAt(i) == '1') {
					System.out.print(i + " ");
				}
			}
			System.out.println(
					"0: definitions, 1: provinces, 2: positions, 3: terrain, 4: rivers, 5: terrain_definition, 6: heightmap"
							+ " 7: tree_definition, 8: continent, 9: adjacencies, 10: climate, 11: region, 12: geographical_region, 13: seasons");
			return false;
		}
	}

	public File getMapDefault() {
		return this.mapDefault;
	}

	/**
	 * @return the csvDefinition
	 */
	public final File getCsvDefinition() {
		return csvDefinition;
	}

	/**
	 * @return the bmpProvinces
	 */
	public final File getBmpProvinces() {
		return bmpProvinces;
	}

	/**
	 * @return the txtPositions
	 */
	public final File getTxtPositions() {
		return txtPositions;
	}

	/**
	 * @return the bmpTerrain
	 */
	public final File getBmpTerrain() {
		return bmpTerrain;
	}

	/**
	 * @return the bmpRivers
	 */
	public final File getBmpRivers() {
		return bmpRivers;
	}

	/**
	 * @return the txtTerrain
	 */
	public final File getTxtTerrain() {
		return txtTerrain;
	}

	/**
	 * @return the bmpHeightmap
	 */
	public final File getBmpHeightmap() {
		return bmpHeightmap;
	}

	/**
	 * @return the bmpTrees
	 */
	public final File getBmpTrees() {
		return bmpTrees;
	}

	/**
	 * @return the txtContinents
	 */
	public final File getTxtContinents() {
		return txtContinents;
	}

	/**
	 * @return the csvAdjacencies
	 */
	public final File getCsvAdjacencies() {
		return csvAdjacencies;
	}

	/**
	 * @return the txtClimate
	 */
	public final File getTxtClimate() {
		return txtClimate;
	}

	/**
	 * @return the txtIsland_region
	 */
	public final File getTxtIsland_region() {
		return txtIsland_region;
	}

	/**
	 * @return the txtGeographical_region
	 */
	public final File getTxtGeographical_region() {
		return txtGeographical_region;
	}

	/**
	 * @return the txtSeasons
	 */
	public final File getTxtSeasons() {
		return txtSeasons;
	}

	/**
	 * @return the missingFiles
	 */
	public final List<String> getMissingFiles() {
		return missingFiles;
	}

	/**
	 * @return the maxProvinces
	 */
	public final int getMaxProvinces() {
		return maxProvinces;
	}

	/**
	 * @return the sea_prov
	 */
	public final List<Tuple<Integer, Integer>> getSea_prov() {
		return sea_prov;
	}

	/**
	 * @return the seaprov
	 */
	public final List<Integer> getSeaprov() {
		return seaprov;
	}

	/**
	 * @return the ocean
	 */
	public final List<List<Integer>> getOcean() {
		return ocean;
	}
	
	/**
	 * @return the mapfolder
	 */
	public final Path getMapfolder() {
		return mapfolder;
	}

}