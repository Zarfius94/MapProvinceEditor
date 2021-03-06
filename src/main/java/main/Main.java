package main;

import java.io.File;

import imageMapping.Mapper;
import imageMapping.helper.BitmapImage;
import imageMapping.helper.ProvinceInformation;
import io.IoHandler;
import io.ProvincesReader;

public class Main {

	final static String mapfolder = "A:\\ck2alternatemaps\\randommpmap\\RandomMpMap\\map";
	//final static String mapfolder = "D:\\Steam\\SteamApps\\common\\Crusader Kings II\\map";
	
	public static void main(String[] args) {
		
		File f = new File(mapfolder);
		IoHandler ih = new IoHandler(f.toPath());
		ProvinceInformation[] pia = ProvincesReader.readProvinces(ih.getCsvDefinition().toPath(), ih.getMaxProvinces());
		BitmapImage bi = BitmapImage.instance(ih.getBmpProvinces().toPath());
		Mapper mapper = new Mapper(bi, pia);
		mapper.output(f.toPath());
	}

}
