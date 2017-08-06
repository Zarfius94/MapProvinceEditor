package imageMapping;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import imageMapping.helper.BitmapImage;
import imageMapping.helper.ProvinceInformation;

/**
 * Maps the different provinces to the actual location on the map and the coordinates on the map to the provinces.
 * 
 * Also the id color combination gets mapped.
 * @author Zarfius
 * @version 1.0
 *
 */
public class Mapper {

	private BufferedImage provinces;
	private int[][] mapped;
	private ProvinceInformation[] provincesInfo;
	private Hashtable<Integer, Integer> colorIdHt;
	private Hashtable<Integer, Integer> IdColorht;
	private List<ProvinceInformation> unknownColors;

	/**
	 * New mapper for the given image and the list of given provinces.
	 * 
	 * @param provinces
	 *            bitmap image of the provinces
	 * @param provinceInfo
	 *            array of all given provinces with their informations.
	 */
	public Mapper(BitmapImage provinces, ProvinceInformation[] provinceInfo) {
		if (provinces == null || provinceInfo == null) {
			throw new IllegalArgumentException("Parameter must be different to null!");
		}
		this.provinces = provinces.getBitmap();

		// 2-dimensional array for mapping the bmp
		mapped = new int[this.provinces.getWidth()][this.provinces.getHeight()];

		// set the information
		this.provincesInfo = provinceInfo;
		this.colorIdHt = new Hashtable<Integer, Integer>();
		this.IdColorht = new Hashtable<Integer, Integer>();
		for (ProvinceInformation pi : this.provincesInfo) {
			if (pi != null) {
				this.colorIdHt.put(pi.getColor(), pi.getId());
				this.IdColorht.put(pi.getId(), pi.getColor());
			}

		}
		// list for unknown colors that may appear (small fail safe)
		this.unknownColors = new ArrayList<ProvinceInformation>();
		this.map();
	}

	/*
	 * extract the provinces through the color in the image and save the coordinates for them.
	 */
	private void map() {
		int height = provinces.getHeight();
		int width = provinces.getWidth();
		int PixelColorRGB = -1;
		Integer hashedId = new Integer(-1);
		int id = -10;

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				PixelColorRGB = provinces.getRGB(x, y);
				hashedId = this.colorIdHt.get(PixelColorRGB);
				if (hashedId != null) {
					this.mapped[x][y] = hashedId;
					this.provincesInfo[hashedId].addCoordinate(x, y);
				} else {
					ProvinceInformation unknownColor = new ProvinceInformation(id, PixelColorRGB);
					unknownColor.addCoordinate(x, y);
					this.unknownColors.add(unknownColor);

					colorIdHt.put(unknownColor.getColor(), unknownColor.getId());
				}
			}
		}
	}

	/**
	 * Generates a text file in which every province id is shown with their color, name if exists and the coordinates on the actual map.
	 * @param output directory for the file to be written to.
	 */
	public void output(Path output) {
		System.out.println("Start output generation:");
		File file = new File(output.toFile(), "output");
		try (BufferedWriter writer = Files.newBufferedWriter(file.toPath(), StandardOpenOption.CREATE)) {
			for (ProvinceInformation pi : provincesInfo) {
				if (pi == null) {
					writer.write("null");
				} else {
					writer.write(pi.toString());
				}
				writer.newLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Finished output generation:");
	}
}
