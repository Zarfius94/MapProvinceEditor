package io.output;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVWriter;

import imageMapping.helper.ProvinceInformation;
import main.Helper;
import titles.Title;
//import titles.TitleRank;

/**
 * Generates or overrides the following map files:<br>
 * - provinces.csv in the map directory<br>
 * - all province files in the history/provinces directory<br>
 * 
 * @author Zarfius
 * @version 1.0
 *
 */
public class ProvincePrinter {
	private final static String[] csvFirstLine = { "province", "red", "green", "blue", "x", "x" };

	public static void printProvinces(Path moddir, ProvinceInformation[] pia) {
		Path mapdir = Paths.get(moddir.toString(), "map");
		if (Files.isDirectory(mapdir)) {
			try {
				Files.createDirectories(mapdir);
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
		}

		File provinces = new File(mapdir.toFile(), "provinces.csv");
		try (CSVWriter writer = new CSVWriter(new FileWriter(provinces, false), ';')) {
			writer.writeNext(csvFirstLine);
			for (ProvinceInformation pi : pia) {
				if (pi != null) {
					writer.writeNext(generateCsvLineOutput(pi));
					generateProvinceFile(pi, moddir);
				}
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// Create parameters for a line in the csv file
	private static String[] generateCsvLineOutput(ProvinceInformation pi) {
		// Input validation
		if (pi == null) {
			throw new IllegalArgumentException("No null parameters allowed");
		}
		String[] ret = new String[6];
		ret[0] = "" + pi.getId();
		int color = pi.getColor();
		ret[1] = "" + ((color >> 16) & 0x000000FF);
		ret[2] = "" + ((color >> 8) & 0x000000FF);
		ret[3] = "" + (color & 0x000000FF);
		ret[4] = pi.getNameCsv();
		ret[5] = "x";

		return ret;
	}

	// Create the province file
	private static List<Title> generateProvinceFile(ProvinceInformation pi, Path moddir) {
		// Input validation
		if (pi == null || moddir == null) {
			throw new IllegalArgumentException("No null parameters allowed");
		}
		// Check if the valid directory exists and if not create it.
		Path outputdir = Paths.get(moddir.toString(), "history", "provinces");
		if (!Files.isDirectory(outputdir)) {
			try {
				Files.createDirectories(outputdir);
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
		List<Title> neededTitles = new ArrayList<Title>();
		String filename = pi.getId() + " - " + pi.getName();
		Path provFile = Paths.get(outputdir.toString(), filename);
		try (BufferedWriter writer = Files.newBufferedWriter(provFile, StandardOpenOption.CREATE)) {
			Helper.writeLineBufferedWriter(writer, "# " + pi.getId() + " - " + pi.getName());
			writer.newLine();
			Helper.writeLineBufferedWriter(writer, "# County Title");
			//Title county_title = new Title(TitleRank.COUNTY, pi.getName(), null);
			Helper.writeLineBufferedWriter(writer, "title = c_" + pi.getName().toLowerCase());

		} catch (IOException e) {
			e.printStackTrace();
		}
		// TODO writing to the file.
		return neededTitles;
	}
}
