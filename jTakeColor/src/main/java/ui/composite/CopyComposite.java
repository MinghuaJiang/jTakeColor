package ui.composite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;

public class CopyComposite extends Composite {
	private List copyList;
	private java.util.List<RGB> rgbList;
	private Button palette;
	private Button copy;
	private boolean isPaletteOpen;
	private PaletteComposite paletteComposite;

	public CopyComposite(Composite parent, int style) {
		super(parent, style);
		isPaletteOpen = false;
		GridData gridData = new GridData();
		gridData.heightHint = 200;
		gridData.widthHint = 115;
		this.setLayoutData(gridData);
		this.setLayout(new FormLayout());
		FormData formData = new FormData();
		formData.left = new FormAttachment(0, 10);
		formData.right = new FormAttachment(100, -10);
		formData.top = new FormAttachment(0, 10);
		formData.bottom = new FormAttachment(80, -10);
		copyList = new List(this, SWT.MULTI | SWT.V_SCROLL | SWT.BORDER);
		copyList.setLayoutData(formData);
		palette = new Button(this, SWT.PUSH);
		palette.setText("Palette");
		formData = new FormData();
		formData.right = new FormAttachment(100, -10);
		formData.top = new FormAttachment(copyList, 10);
		formData.bottom = new FormAttachment(100, -10);
		palette.setLayoutData(formData);
		addClickListenerForPalette(palette);

		copy = new Button(this, SWT.PUSH);
		copy.setText("Copy");
		formData = new FormData();
		formData.left = new FormAttachment(0, 10);
		formData.right = new FormAttachment(palette, -10);
		formData.top = new FormAttachment(copyList, 10);
		formData.bottom = new FormAttachment(100, -10);
		copy.setLayoutData(formData);
	}

	private void addClickListenerForPalette(Button palette) {
		// final Composite tempParent = copyComposite;

		palette.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Shell shell = CopyComposite.this.getShell();
				if (!isPaletteOpen) {
					paletteComposite = new PaletteComposite(shell, SWT.None);
					isPaletteOpen = true;
				} else {
					paletteComposite.dispose();
					isPaletteOpen = false;
				}
				shell.pack();
			}
		});
	}

	public List getCopyList() {
		return copyList;
	}
	
	public java.util.List<RGB> getRgbList() {
		return rgbList;
	}

	public Button getPalette() {
		return palette;
	}

	public Button getCopy() {
		return copy;
	}

	public boolean isPaletteOpen() {
		return isPaletteOpen;
	}

	public PaletteComposite getPaletteComposite() {
		return paletteComposite;
	}
	
}
