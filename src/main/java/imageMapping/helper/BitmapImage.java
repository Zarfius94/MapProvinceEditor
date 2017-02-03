package imageMapping.helper;

import java.awt.image.BufferedImage;
import java.nio.file.Path;

import io.ImageReader;

public class BitmapImage {

	private BufferedImage bitmap;
	
	private BitmapImage(BufferedImage bi){
		this.bitmap = bi;
	}
	
	public BufferedImage getBitmap() {
		return this.bitmap;
	}
	
	public static BitmapImage instance(Path path) {
		return new BitmapImage(ImageReader.readBitmap(path.toFile()));
	}
}
