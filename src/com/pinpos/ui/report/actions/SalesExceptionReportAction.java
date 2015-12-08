package com.pinpos.ui.report.actions;

import com.pinpos.bo.ui.BackOfficeWindow;
import com.pinpos.ui.report.SalesExceptionReportView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SalesExceptionReportAction extends AbstractAction {

	public SalesExceptionReportAction() {
		super(com.pinpos.POSConstants.SALES_EXCEPTION_REPORT);
	}

	public SalesExceptionReportAction(String name) {
		super(name);
	}

	public SalesExceptionReportAction(String name, Icon icon) {
		super(name, icon);
	}

	public void actionPerformed(ActionEvent e) {
		BackOfficeWindow window = BackOfficeWindow.getInstance();
		JTabbedPane tabbedPane = window.getTabbedPane();
		
		SalesExceptionReportView reportView = null;
		int index = tabbedPane.indexOfTab(com.pinpos.POSConstants.SALES_EXCEPTION_REPORT);
		if (index == -1) {
			reportView = new SalesExceptionReportView();
			tabbedPane.addTab(com.pinpos.POSConstants.SALES_EXCEPTION_REPORT, reportView);
		}
		else {
			reportView = (SalesExceptionReportView) tabbedPane.getComponentAt(index);
		}
		tabbedPane.setSelectedComponent(reportView);
	}

}
