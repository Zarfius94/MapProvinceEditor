package main;

import java.io.File;

import imageMapping.Mapper;
import imageMapping.helper.BitmapImage;
import imageMapping.helper.ProvinceInformation;
import io.input.InputHandler;
import io.input.ProvincesReader;

/**
 * Main class to start the program
 * @author Zarfius
 *
 */
public class Main {
	
	/**
	 * Mapfolder to be edited with this program
	 */
	final static String mapfolder = "A:\\ck2alternatemaps\\randommpmap\\RandomMpMap\\map";
	//final static String mapfolder = "D:\\Steam\\SteamApps\\common\\Crusader Kings II\\map";
	
	/**
	 * Main method
	 * @param args arguments are not needed and will be ignored.
	 */
	public static void main(String[] args) {
		
		File f = new File(mapfolder);
		InputHandler ih = new InputHandler(f.toPath());
		ProvinceInformation[] pia = ProvincesReader.readProvinces(ih.getCsvDefinition().toPath(), ih.getMaxProvinces());
		BitmapImage bi = BitmapImage.instance(ih.getBmpProvinces().toPath());
		Mapper mapper = new Mapper(bi, pia);
		mapper.output(f.toPath());
	}

}
