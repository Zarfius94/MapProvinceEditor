package io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import com.opencsv.CSVReader;

import imageMapping.helper.ProvinceInformation;

public class ProvincesReader {

	public static ProvinceInformation[] readProvinces(Path path, int ProvinceNumbers) {
		CSVReader reader;
		List<String[]> readerList = null;
		try {
			reader = new CSVReader(new FileReader(path.toFile()), ';');
			readerList = reader.readAll();
		} catch (FileNotFoundException e) {
			System.err.println("No file found on " + path.toAbsolutePath().toString());
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Reading the file cause an error.");
			e.printStackTrace();
		}
		ProvinceInformation[] ret = null;
		if (readerList != null) {
			if (readerList.size() == ProvinceNumbers) {
				ret = new ProvinceInformation[readerList.size()];
				// first Element should be null so that the id's in the file match the indices.
				ret[0] = null;
				String[] sa;
				for (int i = 1; i < ProvinceNumbers; i++) {
					sa = readerList.get(i);
					int id = Integer.parseInt(sa[0]);
					String pname = sa[4];
					if (pname.compareToIgnoreCase("x") == 0) {
						pname = "";
					}
					ProvinceInformation pi = new ProvinceInformation(id, Integer.parseInt(sa[1]),
							Integer.parseInt(sa[2]), Integer.parseInt(sa[3]), pname);
					ret[id] = pi;
				}
			} else {
				throw new IllegalArgumentException("max_provinces needs to be last province + 1");
			}
		} else {
			throw new IllegalArgumentException("Nothing to read in the provinces file");
		}
		return ret;
	}

}
