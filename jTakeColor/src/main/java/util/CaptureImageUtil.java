package util;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;

public class CaptureImageUtil {
	private static CaptureImageUtil cImageUtil = new CaptureImageUtil();
	private Robot robot;

	private CaptureImageUtil() {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static CaptureImageUtil getInstance() {
		return cImageUtil;
	}

	public BufferedImage getCaptureImage(Rectangle area) {
		return robot.createScreenCapture(area);
	}
}
