package ui.event;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import model.CaptureType;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.dialogs.PreferencesUtil;

import ui.JTakeColor;
import ui.composite.CaptureComposite;
import ui.composite.ColorComposite;
import util.CaptureImageUtil;
import util.ImageUtil;
import util.PreferenceUtil;

public class EventManager {
	public static void addMouseTrackListener(Control control) {
		int ratio = PreferenceUtil.getInstance().getIntegerPreference("");
		doAddMouseTrackListener(control);
	}

	private static void doAddMouseTrackListener(Control control) {
		ColorComposite colorComposite = JTakeColor.getTakeColorWindow()
				.getShellManager().getColorComposite();
		final Label colorBox = colorComposite.getColorBox();
		final Label positionText = colorComposite.getPositionText();
		final Label colorText = colorComposite.getColorText();
		final Combo typeCombo = colorComposite.getTypeCombo();
		
		CaptureComposite captureComposite = JTakeColor.getTakeColorWindow()
				.getShellManager().getCaptureComposite();
		final Label captureBox = captureComposite.getCaptureBox();
		control.addMouseTrackListener(new MouseTrackAdapter() {
			public void mouseHover(MouseEvent e) {
				int x = e.x;
				int y = e.y;
				String type = typeCombo.getItem(typeCombo.getSelectionIndex());
				CaptureType captureType = CaptureType.valueOf(type);
				positionText.setText("[" + x + ", " + y + "]");

				Point mouseLocation = Display.getDefault().getCursorLocation();

				int currentWidth = captureBox.getBounds().width;
				int currentHeight = captureBox.getBounds().height;
				Rectangle sampleRectangle = new Rectangle(mouseLocation.x
						- (currentWidth / 2), mouseLocation.y
						- (currentHeight / 2), currentWidth, currentHeight);

				BufferedImage bImage = CaptureImageUtil.getInstance()
						.getCaptureImage(sampleRectangle);
				BufferedImage doublebuffer = new BufferedImage(bImage.getWidth(),
						bImage.getHeight(), BufferedImage.TYPE_INT_RGB);
				// java.awt.Image img =
				// bImage.getScaledInstance(bImage.getWidth() * 3,
				// bImage.getHeight() * 3, BufferedImage.SCALE_SMOOTH);

				Graphics2D graphics2d = (Graphics2D) doublebuffer.getGraphics();
				graphics2d.drawImage(bImage, -bImage.getWidth(), -bImage.getHeight(),
						bImage.getWidth() * 4, bImage.getHeight() * 4, null);
				// g2.drawImage(bImage, 0, 0, bImage.getWidth(), bImage
				// .getHeight(), null);
				// g2.scale(3f, 3f);
				ImageData imageData = ImageUtil.getImageData(doublebuffer);
				// id = id.scaledTo(id.width*3, id.height*3);
				if (doublebuffer != null) {
					int rgb = doublebuffer.getRGB(doublebuffer.getWidth() / 2,
							doublebuffer.getHeight() / 2);
					int R = (rgb & 0xff0000) >> 16;
					int G = (rgb & 0xff00) >> 8;
					int B = (rgb & 0xff);
					Color color = new Color(Display.getDefault(), R, G, B);
					colorBox.setBackground(color);
					RGB rgbColor = color.getRGB();
					colorText.setText(captureType.getColorText(rgbColor));
					if (color != null)
						color.dispose();
					Image image = new Image(Display.getCurrent(), imageData);
					captureBox.setBackgroundImage(image);
				}
				// image.dispose();
			}

		});
	}

	public static void addMouseMotionListener(Control control) {
		doAddMouseMotionListener(control);
	}

	private static void doAddMouseMotionListener(Control control) {
		doAddMouseTrackListener(control);
	}
}
