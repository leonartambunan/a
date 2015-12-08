package com.pinpos.bo.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JTabbedPane;

import com.pinpos.bo.ui.BackOfficeWindow;
import com.pinpos.bo.ui.explorer.GroupExplorer;

public class GroupExplorerAction extends AbstractAction {

	public GroupExplorerAction() {
		super(com.pinpos.POSConstants.MENU_GROUPS);
	}

	public GroupExplorerAction(String name) {
		super(name);
	}

	public GroupExplorerAction(String name, Icon icon) {
		super(name, icon);
	}

	public void actionPerformed(ActionEvent e) {
		BackOfficeWindow backOfficeWindow = BackOfficeWindow.getInstance();
		JTabbedPane tabbedPane;
		GroupExplorer group;
		tabbedPane = backOfficeWindow.getTabbedPane();
		int index = tabbedPane.indexOfTab(com.pinpos.POSConstants.GROUP_EXPLORER);
		if (index == -1) {
			group = new GroupExplorer();
			tabbedPane.addTab(com.pinpos.POSConstants.GROUP_EXPLORER, group);
		}
		else {
			group = (GroupExplorer) tabbedPane.getComponentAt(index);
		}
		tabbedPane.setSelectedComponent(group);

	}

}
