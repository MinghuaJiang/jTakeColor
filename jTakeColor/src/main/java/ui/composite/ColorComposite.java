package ui.composite;

import java.util.Arrays;

import model.CaptureType;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;

import ui.JTakeColor;
import ui.ShellManager;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

public class ColorComposite extends Composite {

	private Label colorBox;
	private Label positionText;
	private Label colorText;
	private Combo typeCombo;

	public ColorComposite(Composite parent, int style) {
		super(parent, style);
		GridData gridData = new GridData();
		gridData.widthHint = 100;
		gridData.heightHint = 200;
		this.setLayoutData(gridData);
		this.setLayout(new FormLayout());
		FormData formData = new FormData();
		formData.left = new FormAttachment(0, 10);
		formData.right = new FormAttachment(100, -10);
		formData.top = new FormAttachment(0, 10);
		formData.bottom = new FormAttachment(50, -10);
		colorBox = new Label(this, SWT.NONE);
		colorBox.setLayoutData(formData);
		colorBox.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				Label capture = (Label) e.widget;
				e.gc.drawRectangle(0, 0, capture.getSize().x - 1,
						capture.getSize().y - 1);
			}
		});
		positionText = new Label(this, SWT.NONE);
		formData = new FormData();
		formData.left = new FormAttachment(0, 10);
		formData.right = new FormAttachment(100, -10);
		formData.top = new FormAttachment(colorBox, 10);

		positionText.setLayoutData(formData);
		colorText = new Label(this, SWT.NONE);
		formData = new FormData();
		formData.left = new FormAttachment(0, 10);
		formData.right = new FormAttachment(100, -10);
		formData.top = new FormAttachment(positionText, 15);
		formData.bottom = new FormAttachment(80, -10);
		colorText.setLayoutData(formData);
		typeCombo = new Combo(this, SWT.READ_ONLY);
		formData = new FormData();
		formData.left = new FormAttachment(0, 10);
		formData.right = new FormAttachment(100, -10);
		formData.top = new FormAttachment(colorText, 10);
		formData.bottom = new FormAttachment(100, -10);
		typeCombo.setLayoutData(formData);
		typeCombo.setItems(Lists.transform(Arrays.asList(CaptureType.values()),
				new Function<CaptureType, String>() {
					@Override
					public String apply(CaptureType input) {
						return input.name();
					}

				}).toArray(new String[0]));
		typeCombo.select(0);
		typeCombo.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {

				String type = typeCombo.getItem(((Combo) e.widget)
						.getSelectionIndex());
				CaptureType captureType = CaptureType.valueOf(type);
				ShellManager shellManager = JTakeColor.getTakeColorWindow()
						.getShellManager();
				CopyComposite copyComposite = shellManager.getCopyComposite();
				List copyList = copyComposite.getCopyList();
				java.util.List<RGB> rgbList = copyComposite.getRgbList();
				Text captureText = shellManager.getCaptureComposite()
						.getCaptureText();
				if (shellManager.getCurrentRGB() != null) {
					colorText.setText(captureType.getColorText(shellManager
							.getCurrentRGB()));
					int selectIndex = copyList.getSelectionIndex();
					copyList.removeAll();
					for (int i = 0; i < rgbList.size(); i++) {
						copyList.add(captureType.getColorText(rgbList.get(i)));
					}
					if (rgbList.size() > 0) {
						if (selectIndex >= 0) {
							captureText.setText(captureType
									.getColorText(rgbList.get(selectIndex)));
							copyList.select(selectIndex);
						} else {
							captureText.setText(captureType
									.getColorText(rgbList.get(0)));
						}
						captureText.setText(captureType.getColorText(rgbList
								.get(selectIndex >= 0 ? selectIndex : 0)));
					}
				}
			}
		});
	}

	public Label getColorBox() {
		return colorBox;
	}

	public Label getPositionText() {
		return positionText;
	}

	public Label getColorText() {
		return colorText;
	}

	public Combo getTypeCombo() {
		return typeCombo;
	}

}
