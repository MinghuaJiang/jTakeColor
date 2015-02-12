package action;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;

public class ExitAction extends Action {
	public ExitAction() {
		super();
		this.setText("Exit");
		this.setAccelerator(SWT.CTRL + 'Q');
		this.setToolTipText("Exit the program");
	}

	public void run() {

	}
}
