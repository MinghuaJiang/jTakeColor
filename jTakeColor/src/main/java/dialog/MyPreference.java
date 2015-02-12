package dialog;

import org.eclipse.jface.preference.*;
import org.eclipse.swt.widgets.Display;

import util.PreferenceUtil;

public class MyPreference {
	public static void showPreference() {
		Display display = Display.getCurrent();
		PreferenceManager mgr = new PreferenceManager();
		PreferenceNode hotkey = new PreferenceNode("hotkey", "ÈÈ¼ü", null,
				PreferencePageOne.class.getName());
		PreferenceNode amplified = new PreferenceNode("amplify", "·Å´ó", null,
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
