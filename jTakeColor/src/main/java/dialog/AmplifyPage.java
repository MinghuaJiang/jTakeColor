package dialog;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.RadioGroupFieldEditor;

public class AmplifyPage extends FieldEditorPreferencePage {
	@Override
	protected void createFieldEditors() {
		// TODO Auto-generated method stub
		RadioGroupFieldEditor rgfe = new RadioGroupFieldEditor("amplify", "Ratio", 3, new String[][] { { "2", "2" },
				{ "3", "3" }, { "4", "4" } }, getFieldEditorParent(), true);
		addField(rgfe);
	}
}
