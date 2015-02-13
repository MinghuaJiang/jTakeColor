package ui;

import org.eclipse.core.commands.common.EventManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import action.AboutAction;
import action.HotAction;

public class JTakeColor extends ApplicationWindow {

	private ShellManager shellManager;
	private static JTakeColor app;

	public JTakeColor() {
		super(null);
		addMenuBar();
		app = this;
	}

	public static JTakeColor getTakeColorWindow() {
		return app;
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
		shellManager = new ShellManager();
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

	public ShellManager getShellManager() {
		return shellManager;
	}

	public void setShellManager(ShellManager shellManager) {
		this.shellManager = shellManager;
	}

	protected boolean showTopSeperator() {
		return false;
	}

	public static void main(String[] args) {
		new JTakeColor().run();
	}

}
