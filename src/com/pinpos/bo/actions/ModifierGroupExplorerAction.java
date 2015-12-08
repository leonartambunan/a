package com.pinpos.bo.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JTabbedPane;

import com.pinpos.bo.ui.BackOfficeWindow;
import com.pinpos.bo.ui.explorer.ModifierGroupExplorer;

public class ModifierGroupExplorerAction extends AbstractAction {

	public ModifierGroupExplorerAction() {
		super(com.pinpos.POSConstants.MENU_MODIFIER_GROUPS);
	}

	public ModifierGroupExplorerAction(String name) {
		super(name);
	}

	public ModifierGroupExplorerAction(String name, Icon icon) {
		super(name, icon);
	}

	public void actionPerformed(ActionEvent e) {
		BackOfficeWindow backOfficeWindow = BackOfficeWindow.getInstance();
		JTabbedPane tabbedPane;
		ModifierGroupExplorer mGroup;
		tabbedPane = backOfficeWindow.getTabbedPane();
		int index = tabbedPane.indexOfTab(com.pinpos.POSConstants.MODIFIER_GROUP_EXPLORER);
		if (index == -1) {
			mGroup = new ModifierGroupExplorer();
			tabbedPane.addTab(com.pinpos.POSConstants.MODIFIER_GROUP_EXPLORER, mGroup);
		}
		else {
			mGroup = (ModifierGroupExplorer) tabbedPane.getComponentAt(index);
		}
		tabbedPane.setSelectedComponent(mGroup);
	}

}
