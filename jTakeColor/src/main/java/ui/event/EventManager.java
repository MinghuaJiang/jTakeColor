package ui.event;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import model.CaptureType;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
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
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;

import ui.JTakeColor;
import ui.ShellManager;
import ui.composite.CaptureComposite;
import ui.composite.ColorComposite;
import ui.composite.CopyComposite;
import util.CaptureImageUtil;
import util.ImageUtil;
import util.PreferenceUtil;

public class EventManager {
	public static void addMouseTrackListener(Control control) {
		doAddMouseTrackListener(control);
	}

	public static void addKeyListener(Shell shell) {
		ColorComposite colorComposite = JTakeColor.getTakeColorWindow()
				.getShellManager().getColorComposite();
		final Label colorText = colorComposite.getColorText();
		CopyComposite copyComposite = JTakeColor.getTakeColorWindow()
				.getShellManager().getCopyComposite();
		final List copyList = copyComposite.getCopyList();		
		shell.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				shell.forceFocus();
				System.out.println("hello");
				if ((e.keyCode == 'c' && (e.stateMask & SWT.ALT) != 0)) {
					copyList.add(colorText.getText());
				}
			}
		});
	}

	private static void doAddMouseTrackListener(Control control) {
		control.addMouseTrackListener(new MouseTrackAdapter() {
			public void mouseHover(MouseEvent e) {
				handleMouseEvent(e);
			}
		});
	}

	public static void addMouseMotionListener(Control control) {
		doAddMouseMotionListener(control);
	}

	private static void doAddMouseMotionListener(Control control) {
		control.addMouseMoveListener(new MouseMoveListener() {
			public void mouseMove(MouseEvent e) {
				handleMouseEvent(e);
			}
		});
	}

	private static void handleMouseEvent(MouseEvent e) {
		int ratio = Integer.parseInt(PreferenceUtil.getInstance().getProperty(PreferenceUtil.AMPLIFY));
		ShellManager shellManager = JTakeColor.getTakeColorWindow()
				.getShellManager();
		ColorComposite colorComposite = shellManager.getColorComposite();
		final Label colorBox = colorComposite.getColorBox();
		final Label positionText = colorComposite.getPositionText();
		final Label colorText = colorComposite.getColorText();
		final Combo typeCombo = colorComposite.getTypeCombo();

		CaptureComposite captureComposite = shellManager.getCaptureComposite();
		final Label captureBox = captureComposite.getCaptureBox();
		String type = typeCombo.getItem(typeCombo.getSelectionIndex());
		CaptureType captureType = CaptureType.valueOf(type);

		int x = e.x;
		int y = e.y;

		positionText.setText("[" + x + ", " + y + "]");

		Point mouseLocation = Display.getDefault().getCursorLocation();

		int currentWidth = captureBox.getBounds().width;
		int currentHeight = captureBox.getBounds().height;
		Rectangle sampleRectangle = new Rectangle(mouseLocation.x
				- (currentWidth / 2), mouseLocation.y - (currentHeight / 2),
				currentWidth, currentHeight);

		BufferedImage bImage = CaptureImageUtil.getInstance().getCaptureImage(
				sampleRectangle);
		BufferedImage doublebuffer = new BufferedImage(bImage.getWidth(),
				bImage.getHeight(), BufferedImage.TYPE_INT_RGB);

		Graphics2D graphics2d = (Graphics2D) doublebuffer.getGraphics();
		graphics2d.drawImage(bImage, -bImage.getWidth() * (ratio - 1) / 2,
				-bImage.getHeight() * (ratio - 1) / 2, bImage.getWidth()
						* ratio, bImage.getHeight() * ratio, null);

		ImageData imageData = ImageUtil.getImageData(doublebuffer);
		
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
			shellManager.setCurrentRGB(rgbColor);
			if (color != null)
				color.dispose();
			Image image = new Image(Display.getCurrent(), imageData);
			captureBox.setBackgroundImage(image);
		}
	}
}
