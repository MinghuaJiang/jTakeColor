package ui.composite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Slider;

public class PaletteComposite extends Composite {

	private	SliderComposite sliderComposite;
	public PaletteComposite(Composite parent, int style) {
		super(parent, style);
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan = 3;
		gridData.heightHint = 90;
		this.setLayoutData(gridData);
		this.setLayout(new GridLayout(2, false));
		addSlider(this);
	}

	private void addSlider(Composite parent) {
		Display display = Display.getCurrent();
		GridData gridData = new GridData(GridData.FILL_VERTICAL);
		gridData.widthHint = 90;

		Label colorLabel = new Label(parent, SWT.NONE);

		colorLabel.setLayoutData(gridData);
		colorLabel.setBackground(display.getSystemColor(SWT.COLOR_BLACK));
		// addMouseTrackListener(colorLabel, positionLabel, colorText,
		// captureLabel, colorLabel2);
		// addMouseMoveListener(colorLabel, positionLabel, colorText,
		// captureLabel, colorLabel2);
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
				e.gc.drawRectangle(0, 0, capture.getSize().x - 1, capture.getSize().y - 1);
			}
		});
		sliderComposite = new SliderComposite(parent, SWT.None);
		addSelectionListenerForSlider(colorLabel);
		/*addMouseTrackListener(colorLabel, positionLabel, colorText, captureLabel, sliderComposite);
		addMouseMoveListener(colorLabel, positionLabel, colorText, captureLabel, sliderComposite);
		Control[] control = sliderComposite.getChildren();
		for (int i = 0; i < control.length; i++) {
			addMouseTrackListener(colorLabel, positionLabel, colorText, captureLabel, control[i]);
			addMouseMoveListener(colorLabel, positionLabel, colorText, captureLabel, control[i]);
		}*/
	}
	
	private void addSelectionListenerForSlider(final Label colorLabel){
		final Display display = Display.getCurrent();
		final Slider rSlider = sliderComposite.getrSlider();
		final Slider gSlider = sliderComposite.getgSlider();
		final Slider bSlider = sliderComposite.getbSlider();
		final Label rText = sliderComposite.getrText();
		final Label gText = sliderComposite.getgText();
		final Label bText = sliderComposite.getbText();
		
		rSlider.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				rText.setText(Integer.valueOf(rSlider.getSelection()).toString());
				Color color = new Color(display, new RGB(rSlider.getSelection(), gSlider.getSelection(), bSlider
						.getSelection()));
				colorLabel.setBackground(color);
				color.dispose();
			}
		});
		gSlider.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				gText.setText(Integer.valueOf(gSlider.getSelection()).toString());
				Color color = new Color(display, new RGB(rSlider.getSelection(), gSlider.getSelection(), bSlider
						.getSelection()));
				colorLabel.setBackground(color);
				color.dispose();
			}
		});
		bSlider.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				bText.setText(Integer.valueOf(bSlider.getSelection()).toString());
				Color color = new Color(display, new RGB(rSlider.getSelection(), gSlider.getSelection(), bSlider
						.getSelection()));
				colorLabel.setBackground(color);
				color.dispose();
			}
		});
	}
}
