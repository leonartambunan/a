package com.pinpos.ui.report.actions;

import com.pinpos.bo.ui.BackOfficeWindow;
import com.pinpos.ui.report.SalesSummaryReportView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SalesAnalysisReportAction extends AbstractAction {
	
	public SalesAnalysisReportAction() {
		super(com.pinpos.POSConstants.SALES_ANALYSIS);
	}

	public SalesAnalysisReportAction(String name) {
		super(name);
	}

	public SalesAnalysisReportAction(String name, Icon icon) {
		super(name, icon);
	}

	public void actionPerformed(ActionEvent e) {
		BackOfficeWindow window = BackOfficeWindow.getInstance();
		JTabbedPane tabbedPane = window.getTabbedPane();
		
		SalesSummaryReportView reportView = null;
		int index = tabbedPane.indexOfTab(com.pinpos.POSConstants.SALES_ANALYSIS);
		if (index == -1) {
			reportView = new SalesSummaryReportView();
			reportView.setReportType(SalesSummaryReportView.REPORT_SALES_ANALYSIS);
			tabbedPane.addTab(com.pinpos.POSConstants.SALES_ANALYSIS, reportView);
		}
		else {
			reportView = (SalesSummaryReportView) tabbedPane.getComponentAt(index);
		}
		tabbedPane.setSelectedComponent(reportView);
	}

}
