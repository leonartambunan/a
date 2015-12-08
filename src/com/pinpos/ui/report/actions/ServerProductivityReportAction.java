package com.pinpos.ui.report.actions;

import com.pinpos.bo.ui.BackOfficeWindow;
import com.pinpos.ui.report.ServerProductivityReportView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ServerProductivityReportAction extends AbstractAction {

	public ServerProductivityReportAction() {
		super(com.pinpos.POSConstants.SERVER_PRODUCTIVITY_REPORT);
	}

	public ServerProductivityReportAction(String name) {
		super(name);
	}

	public ServerProductivityReportAction(String name, Icon icon) {
		super(name, icon);
	}

	public void actionPerformed(ActionEvent e) {
		BackOfficeWindow window = BackOfficeWindow.getInstance();
		JTabbedPane tabbedPane = window.getTabbedPane();
		
		ServerProductivityReportView reportView = null;
		int index = tabbedPane.indexOfTab(com.pinpos.POSConstants.SERVER_PRODUCTIVITY_REPORT);
		if (index == -1) {
			reportView = new ServerProductivityReportView();
			tabbedPane.addTab(com.pinpos.POSConstants.SERVER_PRODUCTIVITY_REPORT, reportView);
		}
		else {
			reportView = (ServerProductivityReportView) tabbedPane.getComponentAt(index);
		}
		tabbedPane.setSelectedComponent(reportView);
	}

}
