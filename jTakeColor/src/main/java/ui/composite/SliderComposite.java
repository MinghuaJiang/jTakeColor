package ui.composite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Slider;

public class SliderComposite extends Composite {

	private Slider rSlider;
	private Slider gSlider;
	private Slider bSlider;
	private Label rText;
	private Label gText;
	private Label bText;

	public SliderComposite(Composite parent, int style) {
		super(parent, style);
		GridData gridData = new GridData(GridData.FILL_VERTICAL);
		gridData.widthHint = 265;
		this.setLayoutData(gridData);
		this.setLayout(new GridLayout(3, false));
		Label rLabel = new Label(this, SWT.None);
		rLabel.setText("R: ");
		rText = new Label(this, SWT.NONE);
		rText.setText("0");
		gridData = new GridData();
		gridData.widthHint = 20;
		rText.setLayoutData(gridData);
		rSlider = new Slider(this, SWT.None);
		gridData = new GridData();
		gridData.widthHint = 212;
		rSlider.setLayoutData(gridData);
		rSlider.setMinimum(0);
		rSlider.setMaximum(265);
		rSlider.setIncrement(1);
		rSlider.setPageIncrement(1);
		rSlider.setSelection(0);

		Label gLabel = new Label(this, SWT.None);
		gLabel.setText("G: ");
		gText = new Label(this, SWT.NONE);
		gText.setText("0");
		gridData = new GridData();
		gridData.widthHint = 20;
		gText.setLayoutData(gridData);
		gSlider = new Slider(this, SWT.None);
		gridData = new GridData();
		gridData.widthHint = 212;
		gSlider.setLayoutData(gridData);
		gSlider.setMinimum(0);
		gSlider.setMaximum(265);
		gSlider.setIncrement(1);
		gSlider.setPageIncrement(1);
		gSlider.setSelection(0);

		Label bLabel = new Label(this, SWT.None);
		bLabel.setText("B: ");
		bText = new Label(this, SWT.NONE);
		bText.setText("0");
		gridData = new GridData();
		gridData.widthHint = 20;
		bText.setLayoutData(gridData);
		bSlider = new Slider(this, SWT.None);
		gridData = new GridData();
		gridData.widthHint = 212;
		bSlider.setLayoutData(gridData);
		bSlider.setMinimum(0);
		bSlider.setMaximum(265);
		bSlider.setIncrement(1);
		bSlider.setPageIncrement(1);
		bSlider.setSelection(0);

	}

	public Slider getrSlider() {
		return rSlider;
	}

	public Slider getgSlider() {
		return gSlider;
	}

	public Slider getbSlider() {
		return bSlider;
	}

	public Label getrText() {
		return rText;
	}

	public void setrText(Label rText) {
		this.rText = rText;
	}

	public Label getgText() {
		return gText;
	}

	public void setgText(Label gText) {
		this.gText = gText;
	}

	public Label getbText() {
		return bText;
	}

	public void setbText(Label bText) {
		this.bText = bText;
	}

}
