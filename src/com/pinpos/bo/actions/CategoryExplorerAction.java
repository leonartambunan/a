package com.pinpos.bo.actions;

import com.pinpos.bo.ui.BackOfficeWindow;
import com.pinpos.bo.ui.explorer.CategoryExplorer;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CategoryExplorerAction extends AbstractAction {

	public CategoryExplorerAction() {
		super(com.pinpos.POSConstants.MENU_CATEGORIES);
	}

	public CategoryExplorerAction(String name) {
		super(name);
	}

	public CategoryExplorerAction(String name, Icon icon) {
		super(name, icon);
	}

	public void actionPerformed(ActionEvent e) {
		BackOfficeWindow backOfficeWindow = BackOfficeWindow.getInstance();
		
		CategoryExplorer explorer;
		JTabbedPane tabbedPane = backOfficeWindow.getTabbedPane();
		int index = tabbedPane.indexOfTab(com.pinpos.POSConstants.CATEGORY_EXPLORER);
		if (index == -1) {
			explorer = new CategoryExplorer();
			tabbedPane.addTab(com.pinpos.POSConstants.CATEGORY_EXPLORER, explorer);
		}
		else {
			explorer = (CategoryExplorer) tabbedPane.getComponentAt(index);
		}
		tabbedPane.setSelectedComponent(explorer);
	}

}
