package imageMapping.helper;

import java.awt.image.BufferedImage;
import java.nio.file.Path;

import io.ImageReader;

/**
 * Bitmap wrapper.
 * @author Zarfius
 * @version 1.0
 *
 */
public class BitmapImage {

	private BufferedImage bitmap;
	
	private BitmapImage(BufferedImage bi){
		this.bitmap = bi;
	}
	
	/**
	 * Returns the stored bitmap
	 * @return bitmap
	 */
	public BufferedImage getBitmap() {
		return this.bitmap;
	}
	
	/**
	 * Creates a new bitmap instance from the path.
	 * @param path path of the bitmap
	 * @return bitmap instance
	 */
	public static BitmapImage instance(Path path) {
		return new BitmapImage(ImageReader.readBitmap(path.toFile()));
	}
}
