package ui;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import ui.event.EventManager;
import util.PreferenceUtil;
import action.AboutAction;
import action.HelpAction;
import action.SettingAction;

public class JTakeColor extends ApplicationWindow {

	private ShellManager shellManager;
	private static JTakeColor app;
	private Shell parent;

	public JTakeColor(Shell parent) {
		super(parent);
		this.parent = parent;
		addMenuBar();
		app = this;
	}

	public static JTakeColor getTakeColorWindow() {
		return app;
	}

	public void run() {
		PreferenceUtil.getInstance().load();
		setBlockOnOpen(true);
		parent.open();
		open();
		Display.getCurrent().dispose();
	}

	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		Image image = new Image(shell.getDisplay(), JTakeColor.class.getClassLoader().getResourceAsStream("icon.jpg"));
		shell.setText("JTakeColor");
		shell.setBounds(100, 100, 431, 350);
		shell.setImage(image);
		shellManager = new ShellManager();
		shellManager.configureShell(shell);
		shell.addDisposeListener(new DisposeListener(){
			@Override
			public void widgetDisposed(DisposeEvent e) {
				parent.dispose();
			}
		});
		EventManager.addMouseTrackListener(parent);
		EventManager.addMouseMotionListener(parent);
		EventManager.addKeyListener(parent);
	}

	protected MenuManager createMenuManager() {
		MenuManager mm = new MenuManager();
		MenuManager setMenu = new MenuManager("Setting");
		MenuManager aboutMenu = new MenuManager("About");
		MenuManager helpMenu = new MenuManager("Help");
		mm.add(setMenu);
		mm.add(aboutMenu);
		mm.add(helpMenu);
		setMenu.add(new SettingAction());
		aboutMenu.add(new AboutAction());
		helpMenu.add(new HelpAction());
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
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setBounds(0, 0, display.getBounds().width, display.getBounds().width);
		shell.setAlpha(1);
		Image image = new Image(display, JTakeColor.class.getClassLoader().getResourceAsStream("icon.jpg"));
		shell.setImage(image);
		new JTakeColor(shell).run();
	}

}
