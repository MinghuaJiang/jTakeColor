package dialog;

import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.jface.preference.PreferenceManager;
import org.eclipse.jface.preference.PreferenceNode;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;

import util.PreferenceUtil;

public class Preference {
	public static void showPreference() {
		Display display = Display.getCurrent();
		PreferenceManager mgr = new PreferenceManager();
		PreferenceNode amplified = new PreferenceNode("amplify", "Amplify", null,
				AmplifyPage.class.getName());
		mgr.addToRoot(amplified);
		PreferenceUtil pUtil = PreferenceUtil.getInstance();
		pUtil.load();
		PreferenceDialog pDialog = new PreferenceDialog(
				display.getActiveShell(), mgr);
		pDialog.setPreferenceStore(pUtil.getPreferenceStore());
		int code = pDialog.open();
		if(code == Window.OK){
			pUtil.save();
		}
	}
}

