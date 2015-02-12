package ui;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import action.ExitAction;
import action.HotAction;

public class JTakeColor extends ApplicationWindow {
	private ExitAction exitAction;
	private HotAction hotAction;
	private ShellManager shellManager;

	public JTakeColor() {
		super(null);
		exitAction = new ExitAction();
		hotAction = new HotAction();
		shellManager = new ShellManager();
		addMenuBar();
		
	}

	public void run() {
		// Don't return from open() until window closes
		setBlockOnOpen(true);
		// Open the main window
		open();
		// Dispose the display
		Display.getCurrent().dispose();
	}

	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		// Set the title bar text
		shellManager.configureShell(shell);
	}

	protected MenuManager createMenuManager() {
		MenuManager mm = new MenuManager();
		MenuManager setMenu = new MenuManager("…Ë÷√");
		MenuManager aboutMenu = new MenuManager("πÿ”⁄");
		mm.add(setMenu);
		mm.add(aboutMenu);
		setMenu.add(hotAction);
		setMenu.add(exitAction);

		return mm;
	}

	public static void main(String[] args) {
		new JTakeColor().run();
	}

}
