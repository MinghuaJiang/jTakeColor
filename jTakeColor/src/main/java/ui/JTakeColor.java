package ui;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import action.AboutAction;
import action.HotAction;

public class JTakeColor extends ApplicationWindow {

	public JTakeColor() {
		super(null);
		addMenuBar();
	}

	public void run() {
		setBlockOnOpen(true);
		open();
		Display.getCurrent().dispose();
	}

	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setBounds(100, 100, 400, 350);
		shell.setText("JTakeColor");	
		ShellManager shellManager = new ShellManager();
		shellManager.configureShell(shell);
	}

	protected MenuManager createMenuManager() {
		MenuManager mm = new MenuManager();
		MenuManager setMenu = new MenuManager("Settings");
		MenuManager aboutMenu = new MenuManager("About");
		mm.add(setMenu);
		mm.add(aboutMenu);
		setMenu.add(new HotAction());
		aboutMenu.add(new AboutAction());

		return mm;
	}

	protected boolean showTopSeperator() {
		return false;
	}
	public static void main(String[] args) {
		new JTakeColor().run();
	}

}
