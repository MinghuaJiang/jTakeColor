package dialog;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.RadioGroupFieldEditor;

public class PreferencePageTwo extends FieldEditorPreferencePage {

	@Override
	protected void createFieldEditors() {
		// TODO Auto-generated method stub
		RadioGroupFieldEditor rgfe = new RadioGroupFieldEditor("amplify", "Ratio",
				3,
				new String[][] { { "2", "2" }, { "4", "4" }, { "6", "6" } },
				getFieldEditorParent(), true);
		addField(rgfe);
	}

}
