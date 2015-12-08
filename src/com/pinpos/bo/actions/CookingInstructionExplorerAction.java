package com.pinpos.bo.actions;

import com.pinpos.bo.ui.BackOfficeWindow;
import com.pinpos.bo.ui.explorer.CookingInstructionExplorer;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CookingInstructionExplorerAction extends AbstractAction {

	public CookingInstructionExplorerAction() {
		super(com.pinpos.POSConstants.COOKING_INSTRUCTIONS);
	}

	public CookingInstructionExplorerAction(String name) {
		super(name);
	}

	public CookingInstructionExplorerAction(String name, Icon icon) {
		super(name, icon);
	}

	public void actionPerformed(ActionEvent e) {
		BackOfficeWindow backOfficeWindow = BackOfficeWindow.getInstance();
		
		CookingInstructionExplorer explorer;
		JTabbedPane tabbedPane = backOfficeWindow.getTabbedPane();
		int index = tabbedPane.indexOfTab(com.pinpos.POSConstants.COOKING_INSTRUCTIONS);
		if (index == -1) {
			explorer = new CookingInstructionExplorer();
			tabbedPane.addTab(com.pinpos.POSConstants.COOKING_INSTRUCTIONS, explorer);
		}
		else {
			explorer = (CookingInstructionExplorer) tabbedPane.getComponentAt(index);
		}
		tabbedPane.setSelectedComponent(explorer);
	}

}
