package action;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;

public class HelpAction extends Action {
	public HelpAction() {
		super();
		this.setText("Help Contents");
		this.setAccelerator(SWT.CTRL + 'H');
		this.setToolTipText("Help Contents");
	}

	public void run() {
		//AboutDialog.showAboutMessageBox();
	}

}

