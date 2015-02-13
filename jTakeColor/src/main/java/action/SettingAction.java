package action;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;

import dialog.Preference;

public class SettingAction extends Action {
	public SettingAction() {
		super();
		this.setText("Preference");
		this.setAccelerator(SWT.CTRL + 'P');
		this.setToolTipText("Preference");
	}

	public void run() {
		Preference.showPreference();
	}
}
