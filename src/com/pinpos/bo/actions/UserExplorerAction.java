package com.pinpos.bo.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JTabbedPane;

import com.pinpos.bo.ui.BackOfficeWindow;
import com.pinpos.bo.ui.explorer.UserExplorer;

public class UserExplorerAction extends AbstractAction {

	public UserExplorerAction() {
		super(com.pinpos.POSConstants.USERS);
	}

	public UserExplorerAction(String name) {
		super(name);
	}

	public UserExplorerAction(String name, Icon icon) {
		super(name, icon);
	}

	public void actionPerformed(ActionEvent e) {
		BackOfficeWindow backOfficeWindow = BackOfficeWindow.getInstance();
		
		UserExplorer explorer = null;
		JTabbedPane tabbedPane = backOfficeWindow.getTabbedPane();
		int index = tabbedPane.indexOfTab(com.pinpos.POSConstants.USERS);
		if (index == -1) {
			explorer = new UserExplorer();
			tabbedPane.addTab(com.pinpos.POSConstants.USERS, explorer);
		}
		else {
			explorer = (UserExplorer) tabbedPane.getComponentAt(index);
		}
		tabbedPane.setSelectedComponent(explorer);
	}

}
