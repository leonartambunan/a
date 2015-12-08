package com.pinpos.ui.report.actions;

import com.pinpos.bo.ui.BackOfficeWindow;
import com.pinpos.ui.report.MenuUsageReportView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MenuUsageReportAction extends AbstractAction {

	public MenuUsageReportAction() {
		super(com.pinpos.POSConstants.MENU_USAGE_REPORT);
	}

	public MenuUsageReportAction(String name) {
		super(name);
	}

	public MenuUsageReportAction(String name, Icon icon) {
		super(name, icon);
	}

	public void actionPerformed(ActionEvent e) {
		BackOfficeWindow window = BackOfficeWindow.getInstance();
		JTabbedPane tabbedPane = window.getTabbedPane();
		
		MenuUsageReportView reportView = null;
		int index = tabbedPane.indexOfTab(com.pinpos.POSConstants.MENU_USAGE_REPORT);
		if (index == -1) {
			reportView = new MenuUsageReportView();
			tabbedPane.addTab(com.pinpos.POSConstants.MENU_USAGE_REPORT, reportView);
		}
		else {
			reportView = (MenuUsageReportView) tabbedPane.getComponentAt(index);
		}
		tabbedPane.setSelectedComponent(reportView);
	}

}
