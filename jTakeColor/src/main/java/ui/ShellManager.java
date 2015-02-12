package ui;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.image.BufferedImage;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import util.CaptureImageUtil;

public class ShellManager {
	private Shell subshell;
	public void configureShell(Shell shell) {
		Display display = shell.getDisplay();
		shell.setBounds(0, 0, display.getBounds().width,
				display.getBounds().width);
		shell.setAlpha(1);
		subshell = new Shell(shell, SWT.CLOSE | SWT.MAX | SWT.MIN);
		subshell.setText("JTakeColor");
		subshell.setLocation(100, 100);	
		createContent(subshell);
	}
	
	private void createContent(Shell parent){
		final Display display = parent.getDisplay();
		parent.setLayout(new GridLayout(3, false));
		// canvas = new Canvas(parent,SWT.NONE);
		// canvas.setVisible(false);
		Composite backComposite = new Composite(parent, SWT.None);
		GridData gd = new GridData();
		// gd.minimumWidth = 100;
		gd.widthHint = 100;
		gd.heightHint = 200;
		backComposite.setLayoutData(gd);
		backComposite.setLayout(new FormLayout());
		FormData fd = new FormData();
		fd.left = new FormAttachment(0, 10);
		fd.right = new FormAttachment(100, -10);
		fd.top = new FormAttachment(0, 10);
		fd.bottom = new FormAttachment(50, -10);
		final Label colorLabel = new Label(backComposite, SWT.NONE);
		// colorLabel.setText("sss");
		colorLabel.setLayoutData(fd);
		colorLabel.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				// Do some drawing
				Label capture = (Label) e.widget;
				// e.gc.setForeground(display.getSystemColor(SWT.COLOR_RED));
				// e.gc.drawLine(0, capture.getSize().y/2,
				// capture.getSize().x,capture.getSize().y/2);
				// e.gc.drawLine(capture.getSize().x/2, 0,
				// capture.getSize().x/2, capture.getSize().y);
				// e.gc.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
				e.gc.drawRectangle(0, 0, capture.getSize().x - 1, capture
						.getSize().y - 1);
			}
		});
		final Label positionLabel = new Label(backComposite, SWT.NONE);
		fd = new FormData();
		fd.left = new FormAttachment(0, 10);
		fd.right = new FormAttachment(100, -10);
		fd.top = new FormAttachment(colorLabel, 10);

		positionLabel.setLayoutData(fd);
		final Label colorText = new Label(backComposite, SWT.NONE);
		fd = new FormData();
		fd.left = new FormAttachment(0, 10);
		fd.right = new FormAttachment(100, -10);
		fd.top = new FormAttachment(positionLabel, 15);
		fd.bottom = new FormAttachment(80, -10);
		colorText.setLayoutData(fd);
		Combo combo = new Combo(backComposite, SWT.READ_ONLY);
		fd = new FormData();
		fd.left = new FormAttachment(0, 10);
		fd.right = new FormAttachment(100, -10);
		fd.top = new FormAttachment(colorText, 10);
		fd.bottom = new FormAttachment(100, -10);
		combo.setLayoutData(fd);
		combo.setItems(Mode.items);
		combo.select(0);

		// parent.layout();
		Composite captureComposite = new Composite(parent, SWT.None);
		gd = new GridData();
		// gd.minimumWidth = 100;
		gd.heightHint = 200;
		gd.widthHint = 150;
		captureComposite.setLayoutData(gd);
		captureComposite.setLayout(new FormLayout());
		fd = new FormData();
		fd.left = new FormAttachment(0, 10);
		fd.right = new FormAttachment(100, -10);
		fd.top = new FormAttachment(0, 10);
		fd.bottom = new FormAttachment(80, -10);

		Label captureLabel = new Label(captureComposite, SWT.NONE);
		captureLabel.setLayoutData(fd);
		captureLabel.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				// Do some drawing
				Label capture = (Label) e.widget;
				e.gc.setForeground(display.getSystemColor(SWT.COLOR_RED));
				e.gc.drawLine(0, capture.getSize().y / 2, capture.getSize().x,
						capture.getSize().y / 2);
				e.gc.drawLine(capture.getSize().x / 2, 0,
						capture.getSize().x / 2, capture.getSize().y);
				e.gc.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
				e.gc.drawRectangle(0, 0, capture.getSize().x - 1, capture
						.getSize().y - 1);
			}
		});
		final Text captureText = new Text(captureComposite, SWT.BORDER
				| SWT.SINGLE);
		fd = new FormData();
		fd.left = new FormAttachment(0, 10);
		fd.right = new FormAttachment(100, -10);
		fd.top = new FormAttachment(captureLabel, 10);
		fd.bottom = new FormAttachment(100, -10);
		captureText.setLayoutData(fd);

		Composite copyComposite = new Composite(parent, SWT.None);
		gd = new GridData();
		// gd.minimumWidth = 100;
		gd.heightHint = 200;
		gd.widthHint = 115;
		copyComposite.setLayoutData(gd);
		copyComposite.setLayout(new FormLayout());
		fd = new FormData();
		fd.left = new FormAttachment(0, 10);
		fd.right = new FormAttachment(100, -10);
		fd.top = new FormAttachment(0, 10);
		fd.bottom = new FormAttachment(80, -10);
		final List list = new List(copyComposite, SWT.MULTI | SWT.V_SCROLL
				| SWT.BORDER);
		list.setLayoutData(fd);
		Button chooser = new Button(copyComposite, SWT.PUSH);
		chooser.setText("µ÷É«°å");
		fd = new FormData();
		fd.right = new FormAttachment(100, -10);
		fd.top = new FormAttachment(list, 10);
		fd.bottom = new FormAttachment(100, -10);
		chooser.setLayoutData(fd);

		Button copy = new Button(copyComposite, SWT.PUSH);
		copy.setText("¸´ÖÆ");
		fd = new FormData();
		fd.left = new FormAttachment(0, 10);
		fd.right = new FormAttachment(chooser, -10);
		fd.top = new FormAttachment(list, 10);
		fd.bottom = new FormAttachment(100, -10);
		copy.setLayoutData(fd);
		copy.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				String color = list.getSelection()[0];
				Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
				StringSelection select = new StringSelection(color);
				cb.setContents(select, null);
				subshell.forceFocus();
			}
		});

	
		subshell.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				// System.out.println(e.keyCode);
				System.out.println(e.stateMask);
				if ((e.keyCode == 'z' && (e.stateMask & SWT.ALT) != 0)) {

					captureText.setText(setColorText(rgb));
					// System.out.println(e.stateMask);
					currentRGB = rgb;
					list.add(captureText.getText());
					
					mylist.add(rgb);
					if (list.getSelectionIndex() == -1) {
						// System.out.println("here");
						list.select(0);
					}
					// shell.setFocus();
				}
			}
		});
		combo.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				mode = ((Combo) e.widget).getSelectionIndex();
				if (rgb != null) {
					colorText.setText(setColorText(rgb));
					if (captureText.getText() != "") {

						captureText.setText(setColorText(currentRGB));

						// list.pack();
					}
					RGB[] temp = new RGB[mylist.size()];
					temp = mylist.toArray(temp);
					for (int i = 0; i < mylist.size(); i++) {
						list.setItem(i, setColorText(temp[i]));
					}
				}
				subshell.forceFocus();
			}
		});
		list.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {

				// shell.forceFocus();
				subshell.forceFocus();
			}
		});
		Label label = new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 3;
		label.setLayoutData(gd);
		final Composite tempParent = parent;

		chooser.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (count % 2 == 0) {
					if ((currentComposite != null)
							&& (!currentComposite.isDisposed())) {
						currentComposite.dispose();
					}
					final Composite chooserComposite = new Composite(
							tempParent, SWT.None);
					GridData gd = new GridData(GridData.FILL_HORIZONTAL);
					gd.horizontalSpan = 3;
					gd.heightHint = 90;
					chooserComposite.setLayoutData(gd);
					chooserComposite.setLayout(new GridLayout(2, false));
					setSlider(chooserComposite);
					currentComposite = chooserComposite;

				} else {
					if ((currentComposite != null)
							&& (!currentComposite.isDisposed())) {
						currentComposite.dispose();
					}
				}
				// parent.layout();
				tempParent.pack();
				count++;
				subshell.forceFocus();
			}
		});
		addMouseTrackListener(colorLabel, positionLabel, colorText,
				captureLabel, backComposite);
		addMouseMoveListener(colorLabel, positionLabel, colorText,
				captureLabel, backComposite);
		Control[] control = backComposite.getChildren();
		for(int i = 0;i < control.length;i++){
			addMouseTrackListener(colorLabel, positionLabel, colorText,
					captureLabel, control[i]);
			addMouseMoveListener(colorLabel, positionLabel, colorText,
					captureLabel, control[i]);
		}
		addMouseTrackListener(colorLabel, positionLabel, colorText,
				captureLabel, captureComposite);
		addMouseMoveListener(colorLabel, positionLabel, colorText,
				captureLabel, captureComposite);
		control = captureComposite.getChildren();
		for(int i = 0;i < control.length;i++){
			addMouseTrackListener(colorLabel, positionLabel, colorText,
					captureLabel, control[i]);
			addMouseMoveListener(colorLabel, positionLabel, colorText,
					captureLabel, control[i]);
		}
		addMouseTrackListener(colorLabel, positionLabel, colorText,
				captureLabel, copyComposite);
		addMouseMoveListener(colorLabel, positionLabel, colorText,
				captureLabel, copyComposite);
		control = copyComposite.getChildren();
		for(int i = 0;i < control.length;i++){
			addMouseTrackListener(colorLabel, positionLabel, colorText,
					captureLabel, control[i]);
			addMouseMoveListener(colorLabel, positionLabel, colorText,
					captureLabel, control[i]);
		}
		addMouseTrackListener(colorLabel, positionLabel, colorText,
				captureLabel, parent);
		addMouseMoveListener(colorLabel, positionLabel, colorText,
				captureLabel, parent);
		addMouseTrackListener(colorLabel, positionLabel, colorText,
				captureLabel, shell);
		addMouseMoveListener(colorLabel, positionLabel, colorText,
				captureLabel, shell);
		
		// chooserComposite.setVisible(false);
		subshell.pack();
		subshell.forceFocus();
	}
	
	private void addMouseMoveListener(final Label colorLabel,
			final Label positionLabel, final Label colorText,
			final Label captureLabel, Control control) {
		control.addMouseMoveListener(new MouseMoveListener() {
			public void mouseMove(MouseEvent e) {
				// TODO Auto-generated method stub

				int x = e.x;
				int y = e.y;

				// ImageData imageData = image.getImageData();
				// BufferedImage bi = ColorManager.convertToAWT(imageData);

				positionLabel.setText("[" + x + ", " + y + "]");

				Point mouseLocation = Display.getDefault().getCursorLocation();

				int currentWidth = captureLabel.getBounds().width;
				int currentHeight = captureLabel.getBounds().height;
				Rectangle sampleRectangle = new Rectangle(mouseLocation.x
						- (currentWidth / 2), mouseLocation.y
						- (currentHeight / 2), currentWidth, currentHeight);
				// System.out.println(sampleRectangle);
				// this.getShell().setVisible( false );
				BufferedImage bImage = CaptureImageUtil
						.createScreenCapture(sampleRectangle);
				// bImage =
				// (BufferedImage)bImage.getScaledInstance(bImage.getWidth() *
				// 3, bImage.getHeight() * 3, BufferedImage.SCALE_SMOOTH);
				BufferedImage temp = new BufferedImage(bImage.getWidth(),
						bImage.getHeight(), BufferedImage.TYPE_INT_RGB);
				// java.awt.Image img =
				// bImage.getScaledInstance(bImage.getWidth() * 3,
				// bImage.getHeight() * 3, BufferedImage.SCALE_SMOOTH);

				Graphics2D g2 = (Graphics2D) temp.getGraphics();

				 g2.drawImage(bImage, -bImage.getWidth(), -bImage.getHeight(),
				 bImage.getWidth() * 3, bImage.getHeight() * 3, null);
				// g2.scale(3f, 3f);
				//g2.drawImage(bImage, 0, 0, bImage.getWidth(), bImage
						//.getHeight(), null);
				// g2.scale(3f, 3f);
				ImageData id = ColorManager.getImageData(temp);
				// System.out.println(bImage.getRGB(10, 10));
				// ImageData id = ColorManager.getImageData(bImage);
				// id = id.scaledTo(id.width*3, id.height*3);

				Image image = new Image(display, id);
				 
				captureLabel.setBackgroundImage(image);
				if (temp != null) {

					int rgb = temp.getRGB(temp.getWidth() / 2,
							temp.getHeight() / 2);
					int R = (rgb & 0xff0000) >> 16;
					int G = (rgb & 0xff00) >> 8;
					int B = (rgb & 0xff);
					color = new Color(Display.getDefault(), R, G, B);
				}
				colorLabel.setBackground(color);
				// colorLabel.redraw();
				rgb = color.getRGB();
				colorText.setText(setColorText(rgb));
				if (color != null)
					color.dispose();
				// image.dispose();
				// gc.dispose();
			}

		});
	}
	private void addMouseTrackListener(final Label colorLabel,
			final Label positionLabel, final Label colorText,
			final Label captureLabel, Control control) {
		control.addMouseTrackListener(new MouseTrackAdapter() {
			public void mouseHover(MouseEvent e) {
				// TODO Auto-generated method stub

				int x = e.x;
				int y = e.y;
				// ImageData imageData = image.getImageData();
				// BufferedImage bi = ColorManager.convertToAWT(imageData);

				positionLabel.setText("[" + x + ", " + y + "]");
				
				Point mouseLocation = Display.getDefault().getCursorLocation();

				int currentWidth = captureLabel.getBounds().width;
				int currentHeight = captureLabel.getBounds().height;
				Rectangle sampleRectangle = new Rectangle(mouseLocation.x
						- (currentWidth / 2), mouseLocation.y
						- (currentHeight / 2), currentWidth, currentHeight);

				// this.getShell().setVisible( false );
				BufferedImage bImage = robot
						.createScreenCapture(sampleRectangle);
				BufferedImage temp = new BufferedImage(bImage.getWidth(),
						bImage.getHeight(), BufferedImage.TYPE_INT_RGB);
				// java.awt.Image img =
				// bImage.getScaledInstance(bImage.getWidth() * 3,
				// bImage.getHeight() * 3, BufferedImage.SCALE_SMOOTH);

				Graphics2D g2 = (Graphics2D) temp.getGraphics();
				 g2.drawImage(bImage, -bImage.getWidth(), -bImage.getHeight(),
				 bImage.getWidth() * 3, bImage.getHeight() * 3, null);
				//g2.drawImage(bImage, 0, 0, bImage.getWidth(), bImage
						//.getHeight(), null);
				// g2.scale(3f, 3f);
				ImageData id = ColorManager.getImageData(temp);
				// id = id.scaledTo(id.width*3, id.height*3);
				if (temp != null) {
					int rgb = temp.getRGB(temp.getWidth() / 2,
							temp.getHeight() / 2);
					int R = (rgb & 0xff0000) >> 16;
					int G = (rgb & 0xff00) >> 8;
					int B = (rgb & 0xff);
					color = new Color(Display.getDefault(), R, G, B);
				}
				colorLabel.setBackground(color);
				rgb = color.getRGB();
				colorText.setText(setColorText(rgb));
				if (color != null)
					color.dispose();
				Image image = new Image(display, id);
				captureLabel.setBackgroundImage(image);

				// image.dispose();
			}

		});
}
	