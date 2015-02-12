package action;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import dialog.*;

public class HotAction extends Action {
	public HotAction() {
		super();
		this.setText("Preference");
		this.setAccelerator(SWT.CTRL + 'A');
		this.setToolTipText("Preference");
	}

	public void run() {
		MyPreference.showPreference();
	}
}
