package com.pinpos.ui.report.actions;

import com.pinpos.bo.ui.BackOfficeWindow;
import com.pinpos.ui.report.SalesSummaryReportView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class KeyStatisticsSalesReportAction extends AbstractAction {

	public KeyStatisticsSalesReportAction() {
		super(com.pinpos.POSConstants.SALES_SUMMARY_KEY_STATISTICS_REPORT);
	}

	public KeyStatisticsSalesReportAction(String name) {
		super(name);
	}

	public KeyStatisticsSalesReportAction(String name, Icon icon) {
		super(name, icon);
	}

	public void actionPerformed(ActionEvent e) {
		BackOfficeWindow window = BackOfficeWindow.getInstance();
		JTabbedPane tabbedPane = window.getTabbedPane();
		
		SalesSummaryReportView reportView = null;
		int index = tabbedPane.indexOfTab(com.pinpos.POSConstants.SALES_SUMMARY_KEY_STATISTICS);
		if (index == -1) {
			reportView = new SalesSummaryReportView();
			reportView.setReportType(SalesSummaryReportView.REPORT_KEY_STATISTICS);
			tabbedPane.addTab(com.pinpos.POSConstants.SALES_SUMMARY_KEY_STATISTICS, reportView);
		}
		else {
			reportView = (SalesSummaryReportView) tabbedPane.getComponentAt(index);
		}
		tabbedPane.setSelectedComponent(reportView);
	}

}
