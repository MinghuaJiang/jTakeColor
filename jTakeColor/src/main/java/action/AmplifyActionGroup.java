package action;

import java.util.List;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.MenuManager;

import util.PreferenceUtil;

import com.google.common.collect.Lists;

public class AmplifyActionGroup {
	private List<IAction> actions;

	public AmplifyActionGroup(MenuManager menuManager) {
		actions = Lists.newArrayList();
		String text = PreferenceUtil.getInstance().getProperty(
				PreferenceUtil.AMPLIFY);
		actions.add(new AmplifyAction(this, "2", text == null ? true : false));
		actions.add(new AmplifyAction(this, "3", false));
		actions.add(new AmplifyAction(this, "4", false));
		for (IAction action : actions) {
			menuManager.add(action);
		}
	}

	public List<IAction> getActions() {
		return actions;
	}

	public void setActions(List<IAction> actions) {
		this.actions = actions;
	}

}
