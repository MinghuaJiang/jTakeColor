package dialog;

import org.eclipse.jface.preference.*;
import org.eclipse.swt.widgets.Display;

import util.PreferenceUtil;

public class Preference {
	public static void showPreference() {
		Display display = Display.getCurrent();
		PreferenceManager mgr = new PreferenceManager();
		PreferenceNode hotkey = new PreferenceNode("hotkey", "Hot Key", null,
				PreferencePageOne.class.getName());
		PreferenceNode amplified = new PreferenceNode("amplify", "Amplify", null,
				PreferencePageTwo.class.getName());
		mgr.addToRoot(hotkey);
		mgr.addToRoot(amplified);
		PreferenceUtil pUtil = PreferenceUtil.getInstance();
		pUtil.load();
		PreferenceDialog pDialog = new PreferenceDialog(
				display.getActiveShell(), mgr);
		pDialog.setPreferenceStore(pUtil.getPreferenceStore());
		pDialog.open();
		pUtil.save();

	}
}
