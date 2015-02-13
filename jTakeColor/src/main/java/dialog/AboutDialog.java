package dialog;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class AboutDialog {
	public static void showAboutMessageBox() {
		Display display = Display.getCurrent();
		Shell shell = display.getActiveShell();
		MessageBox messageBox = new MessageBox(shell, SWT.ICON_INFORMATION);

		messageBox.setText("Author");
		messageBox.setMessage("Copy Right CuteBen 1.0");
		messageBox.open();
	}
}
