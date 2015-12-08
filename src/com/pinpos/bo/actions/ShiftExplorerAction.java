package com.pinpos.bo.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JTabbedPane;

import com.pinpos.bo.ui.BackOfficeWindow;
import com.pinpos.bo.ui.explorer.ShiftExplorer;

public class ShiftExplorerAction extends AbstractAction {

	public ShiftExplorerAction() {
		super(com.pinpos.POSConstants.SHIFTS);
	}

	public ShiftExplorerAction(String name) {
		super(name);
	}

	public ShiftExplorerAction(String name, Icon icon) {
		super(name, icon);
	}

	public void actionPerformed(ActionEvent e) {
		BackOfficeWindow backOfficeWindow = BackOfficeWindow.getInstance();
		
		ShiftExplorer explorer = null;
		JTabbedPane tabbedPane = backOfficeWindow.getTabbedPane();
		int index = tabbedPane.indexOfTab(com.pinpos.POSConstants.SHIFTS);
		if (index == -1) {
			explorer = new ShiftExplorer();
			tabbedPane.addTab(com.pinpos.POSConstants.SHIFTS, explorer);
		}
		else {
			explorer = (ShiftExplorer) tabbedPane.getComponentAt(index);
		}
		tabbedPane.setSelectedComponent(explorer);
	}

}
