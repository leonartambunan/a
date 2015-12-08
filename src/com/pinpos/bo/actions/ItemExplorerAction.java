package com.pinpos.bo.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JTabbedPane;

import com.pinpos.bo.ui.BackOfficeWindow;
import com.pinpos.bo.ui.explorer.MenuItemExplorer;

public class ItemExplorerAction extends AbstractAction {

	public ItemExplorerAction() {
		super(com.pinpos.POSConstants.MENU_ITEMS);
	}

	public ItemExplorerAction(String name) {
		super(name);
	}

	public ItemExplorerAction(String name, Icon icon) {
		super(name, icon);
	}

	public void actionPerformed(ActionEvent e) {
		BackOfficeWindow backOfficeWindow = BackOfficeWindow.getInstance();
		JTabbedPane tabbedPane;
		MenuItemExplorer item;
		tabbedPane = backOfficeWindow.getTabbedPane();
		int index = tabbedPane.indexOfTab(com.pinpos.POSConstants.ITEM_EXPLORER);
		if (index == -1) {
			item = new MenuItemExplorer();
			tabbedPane.addTab(com.pinpos.POSConstants.ITEM_EXPLORER, item);
		}
		else {
			item = (MenuItemExplorer) tabbedPane.getComponentAt(index);
		}
		tabbedPane.setSelectedComponent(item);
	}

}