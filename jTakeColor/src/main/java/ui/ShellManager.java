package ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import ui.composite.CaptureComposite;
import ui.composite.ColorComposite;
import ui.composite.CopyComposite;

public class ShellManager {

	private ColorComposite colorComposite;
	private CaptureComposite captureComposite;
	private CopyComposite copyComposite;
	private RGB currentRGB;
	
	public void configureShell(Shell shell) {
		createContent(shell);
	}
	
	public void createContent(Shell shell){
		shell.setLayout(new GridLayout(3, false));
		colorComposite = createColorComposite(shell);
		captureComposite = createCaptureComposite(shell);
		copyComposite = createCopyComposite(shell);
		createSeperator(shell);
	}
	
	private ColorComposite createColorComposite(Shell shell){
		return new ColorComposite(shell, SWT.None);
	}
	
	private CaptureComposite createCaptureComposite(Shell shell){
		return new CaptureComposite(shell, SWT.None);
	}
	
	private CopyComposite createCopyComposite(Shell shell){
		return new CopyComposite(shell, SWT.None);
	}
	
	private void createSeperator(Shell shell){
		Label label = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan = 3;
		label.setLayoutData(gridData);
	}

	public ColorComposite getColorComposite() {
		return colorComposite;
	}

	public CaptureComposite getCaptureComposite() {
		return captureComposite;
	}

	public CopyComposite getCopyComposite() {
		return copyComposite;
	}

	public RGB getCurrentRGB() {
		return currentRGB;
	}

	public void setCurrentRGB(RGB currentRGB) {
		this.currentRGB = currentRGB;
	}
	
}
	
