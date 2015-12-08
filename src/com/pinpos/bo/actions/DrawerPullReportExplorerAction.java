package com.pinpos.bo.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JTabbedPane;

import com.pinpos.bo.ui.BackOfficeWindow;
import com.pinpos.bo.ui.explorer.DrawerPullReportExplorer;

public class DrawerPullReportExplorerAction extends AbstractAction {

	public DrawerPullReportExplorerAction() {
		super(com.pinpos.POSConstants.DRAWER_PULL_REPORTS);
	}

	public DrawerPullReportExplorerAction(String name) {
		super(name);
	}

	public DrawerPullReportExplorerAction(String name, Icon icon) {
		super(name, icon);
	}

	public void actionPerformed(ActionEvent e) {
		BackOfficeWindow backOfficeWindow = BackOfficeWindow.getInstance();
		
		DrawerPullReportExplorer explorer = null;
		JTabbedPane tabbedPane = backOfficeWindow.getTabbedPane();
		int index = tabbedPane.indexOfTab(com.pinpos.POSConstants.DRAWER_PULL_REPORTS);
		if (index == -1) {
			explorer = new DrawerPullReportExplorer();
			tabbedPane.addTab(com.pinpos.POSConstants.DRAWER_PULL_REPORTS, explorer);
		}
		else {
			explorer = (DrawerPullReportExplorer) tabbedPane.getComponentAt(index);
		}
		tabbedPane.setSelectedComponent(explorer);
	}

}
