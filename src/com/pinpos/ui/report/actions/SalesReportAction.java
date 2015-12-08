package com.pinpos.ui.report.actions;

import com.pinpos.bo.ui.BackOfficeWindow;
import com.pinpos.ui.report.ReportViewer;
import com.pinpos.ui.report.SalesReport;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SalesReportAction extends AbstractAction {

	public SalesReportAction() {
		super(com.pinpos.POSConstants.SALES_REPORT);
	}

	public SalesReportAction(String name) {
		super(name);
	}

	public SalesReportAction(String name, Icon icon) {
		super(name, icon);
	}

	public void actionPerformed(ActionEvent e) {
		BackOfficeWindow window = BackOfficeWindow.getInstance();
		JTabbedPane tabbedPane = window.getTabbedPane();
		
		ReportViewer viewer = null;
		int index = tabbedPane.indexOfTab(com.pinpos.POSConstants.SALES_REPORT);
		if (index == -1) {
			viewer = new ReportViewer(new SalesReport());
			tabbedPane.addTab(com.pinpos.POSConstants.SALES_REPORT, viewer);
		}
		else {
			viewer = (ReportViewer) tabbedPane.getComponentAt(index);
		}
		tabbedPane.setSelectedComponent(viewer);
	}

}
