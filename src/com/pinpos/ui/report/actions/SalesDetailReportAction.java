package com.pinpos.ui.report.actions;

import com.pinpos.bo.ui.BackOfficeWindow;
import com.pinpos.ui.report.SalesDetailReportView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SalesDetailReportAction extends AbstractAction {

	public SalesDetailReportAction() {
		super(com.pinpos.POSConstants.SALES_DETAILED_REPORT);
	}

	public SalesDetailReportAction(String name) {
		super(name);
	}

	public SalesDetailReportAction(String name, Icon icon) {
		super(name, icon);
	}

	public void actionPerformed(ActionEvent e) {
		BackOfficeWindow window = BackOfficeWindow.getInstance();
		JTabbedPane tabbedPane = window.getTabbedPane();
		
		SalesDetailReportView reportView = null;
		int index = tabbedPane.indexOfTab(com.pinpos.POSConstants.SALES_DETAILED_REPORT);
		if (index == -1) {
			reportView = new SalesDetailReportView();
			tabbedPane.addTab(com.pinpos.POSConstants.SALES_DETAILED_REPORT, reportView);
		}
		else {
			reportView = (SalesDetailReportView) tabbedPane.getComponentAt(index);
		}
		tabbedPane.setSelectedComponent(reportView);
	}

}
