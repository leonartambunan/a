package com.pinpos.ui.report.actions;

import com.pinpos.bo.ui.BackOfficeWindow;
import com.pinpos.ui.report.SalesBalanceReportView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SalesBalanceReportAction extends AbstractAction {

	public SalesBalanceReportAction() {
		super(com.pinpos.POSConstants.SALES_BALANCE_REPORT);
	}

	public SalesBalanceReportAction(String name) {
		super(name);
	}

	public SalesBalanceReportAction(String name, Icon icon) {
		super(name, icon);
	}

	public void actionPerformed(ActionEvent e) {
		BackOfficeWindow window = BackOfficeWindow.getInstance();
		JTabbedPane tabbedPane = window.getTabbedPane();
		
		SalesBalanceReportView reportView = null;
		int index = tabbedPane.indexOfTab(com.pinpos.POSConstants.SALES_BALANCE_REPORT);
		if (index == -1) {
			reportView = new SalesBalanceReportView();
			tabbedPane.addTab(com.pinpos.POSConstants.SALES_BALANCE_REPORT, reportView);
		}
		else {
			reportView = (SalesBalanceReportView) tabbedPane.getComponentAt(index);
		}
		tabbedPane.setSelectedComponent(reportView);
	}

}
