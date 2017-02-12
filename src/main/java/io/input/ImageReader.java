package io.input;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * IO Operations for specific images.
 * @author Zarfius
 * @version 1.0
 */
public class ImageReader {

	/**
	 * Reads a bitmap.
	 * 
	 * @param image
	 *            The bitmap file.
	 * @return a BufferedImage of the bitmap.
	 * @throws IllegalArgumentException
	 *             If the input isn't a .bmp file or null.
	 */
	public static BufferedImage readBitmap(File image) throws IllegalArgumentException {
		// Checks if the parameter is set
		if (image == null) {
			throw new IllegalArgumentException("Parameter cannot be null!");
		}
		// Check if image is a bitmap
		String filename = image.getName();
		if (!filename.substring(filename.lastIndexOf(".") + 1).equalsIgnoreCase("bmp")) {
			throw new IllegalArgumentException("A bitmap file is required!");
		}

		try {
			BufferedImage bi = ImageIO.read(image);
			return bi;
		} catch (IOException ioe) {
			System.err.println("Could not read image at" + image.getAbsolutePath());
			ioe.printStackTrace();
		}
		return null;
	}

}
