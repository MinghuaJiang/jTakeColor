package dialog;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;

public class PreferencePageOne extends FieldEditorPreferencePage {

	@Override
	protected void createFieldEditors() {
		// TODO Auto-generated method stub
		StringFieldEditor sfe = new StringFieldEditor("hotkey", "ÈÈ¼ü",
				getFieldEditorParent());
		addField(sfe);

	}

}
