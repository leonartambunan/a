package com.pinpos.ui.report.actions;

import com.pinpos.bo.ui.BackOfficeWindow;
import com.pinpos.ui.report.HourlyLaborReportView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class HourlyLaborReportAction extends AbstractAction {

	public HourlyLaborReportAction() {
		super(com.pinpos.POSConstants.HOURLY_LABOR_REPORT);
	}

	public HourlyLaborReportAction(String name) {
		super(name);
	}

	public HourlyLaborReportAction(String name, Icon icon) {
		super(name, icon);
	}

	public void actionPerformed(ActionEvent e) {
		BackOfficeWindow window = BackOfficeWindow.getInstance();
		JTabbedPane tabbedPane = window.getTabbedPane();
		
		HourlyLaborReportView reportView = null;
		int index = tabbedPane.indexOfTab(com.pinpos.POSConstants.HOURLY_LABOR_REPORT);
		if (index == -1) {
			reportView = new HourlyLaborReportView();
			tabbedPane.addTab(com.pinpos.POSConstants.HOURLY_LABOR_REPORT, reportView);
		}
		else {
			reportView = (HourlyLaborReportView) tabbedPane.getComponentAt(index);
		}
		tabbedPane.setSelectedComponent(reportView);
	}

}
