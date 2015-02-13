package action;

import org.eclipse.jface.action.Action;

import org.eclipse.swt.SWT;

public class AboutAction extends Action {
	public AboutAction() {
		super();
		this.setText("About");
		this.setAccelerator(SWT.CTRL + 'A');
		this.setToolTipText("About");
	}

	public void run() {
		AboutDialog.showAboutMessageBox();
	}
}
