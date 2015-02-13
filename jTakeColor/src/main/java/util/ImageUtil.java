package util;

import java.awt.image.BufferedImage;
import java.awt.image.DirectColorModel;
import java.awt.image.WritableRaster;

import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;

public class ImageUtil {
	public static ImageData getImageData(BufferedImage bufferedImage) {
		DirectColorModel colorModel = (DirectColorModel) bufferedImage.getColorModel();
		PaletteData palette = new PaletteData(colorModel.getRedMask(), colorModel.getGreenMask(),
				colorModel.getBlueMask());
		ImageData data = new ImageData(bufferedImage.getWidth(), bufferedImage.getHeight(), colorModel.getPixelSize(),
				palette);
		WritableRaster raster = bufferedImage.getRaster();
		int[] pixelArray = new int[3];
		for (int y = 0; y < data.height; y++) {
			for (int x = 0; x < data.width; x++) {
				raster.getPixel(x, y, pixelArray);
				int pixel = palette.getPixel(new RGB(pixelArray[0], pixelArray[1], pixelArray[2]));
				data.setPixel(x, y, pixel);
			}
		}
		return data;
	}
}
