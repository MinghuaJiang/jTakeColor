package ui.composite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class CaptureComposite extends Composite {
	private Label captureBox;
	private Text captureText;

	public CaptureComposite(Composite parent, int style) {
		super(parent, style);
		final Display display = parent.getDisplay();
		GridData gridData = new GridData();
		gridData.heightHint = 200;
		gridData.widthHint = 150;
		this.setLayoutData(gridData);
		this.setLayout(new FormLayout());
		FormData formData = new FormData();
		formData.left = new FormAttachment(0, 10);
		formData.right = new FormAttachment(100, -10);
		formData.top = new FormAttachment(0, 10);
		formData.bottom = new FormAttachment(80, -10);

		captureBox = new Label(this, SWT.NONE);
		captureBox.setLayoutData(formData);
		captureBox.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				Label capture = (Label) e.widget;
				e.gc.setForeground(display.getSystemColor(SWT.COLOR_RED));
				e.gc.drawLine(0, capture.getSize().y / 2, capture.getSize().x, capture.getSize().y / 2);
				e.gc.drawLine(capture.getSize().x / 2, 0, capture.getSize().x / 2, capture.getSize().y);
				e.gc.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
				e.gc.drawRectangle(0, 0, capture.getSize().x - 1, capture.getSize().y - 1);
			}
		});
		captureText = new Text(this, SWT.BORDER | SWT.SINGLE);
		formData = new FormData();
		formData.left = new FormAttachment(0, 10);
		formData.right = new FormAttachment(100, -10);
		formData.top = new FormAttachment(captureBox, 10);
		formData.bottom = new FormAttachment(100, -10);
		captureText.setLayoutData(formData);
	}

	public Label getCaptureBox() {
		return captureBox;
	}

	public Text getCaptureText() {
		return captureText;
	}

}
