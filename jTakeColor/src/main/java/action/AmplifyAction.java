package action;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.swt.SWT;

import util.PreferenceUtil;

public class AmplifyAction extends Action {
	private AmplifyActionGroup group;

	public AmplifyAction(AmplifyActionGroup group, String text, boolean checked) {
		super(text, AS_CHECK_BOX);
		this.group = group;
		this.setAccelerator(SWT.CTRL + text.charAt(0));
		this.setChecked(checked);
		initCheckStatus();
		
	}

	public void run() {
		updateCheckStatus();
		PreferenceUtil.getInstance().setProperty(PreferenceUtil.AMPLIFY,
				getText());
	}

	private void initCheckStatus() {
		String text = PreferenceUtil.getInstance().getProperty(
				PreferenceUtil.AMPLIFY);
		if (getText().equals(text)) {
			updateCheckStatus();
		}
		if (this.isChecked()) {
			PreferenceUtil.getInstance().setProperty(PreferenceUtil.AMPLIFY,
					getText());
		}
	}

	private void updateCheckStatus() {
		for (IAction action : group.getActions()) {
			action.setChecked(false);
		}
		this.setChecked(true);

	}
}
