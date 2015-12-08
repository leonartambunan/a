package com.pinpos.ui.report.actions;

import com.pinpos.bo.ui.BackOfficeWindow;
import com.pinpos.ui.report.OpenTicketSummaryReport;
import com.pinpos.ui.report.ReportViewer;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class OpenTicketSummaryReportAction extends AbstractAction {

	public OpenTicketSummaryReportAction() {
		super(com.pinpos.POSConstants.OPEN_TICKET_SUMMARY);
	}

	public OpenTicketSummaryReportAction(String name) {
		super(name);
	}

	public OpenTicketSummaryReportAction(String name, Icon icon) {
		super(name, icon);
	}

	public void actionPerformed(ActionEvent e) {
		BackOfficeWindow window = BackOfficeWindow.getInstance();
		JTabbedPane tabbedPane = window.getTabbedPane();
		
		ReportViewer viewer;
		int index = tabbedPane.indexOfTab(com.pinpos.POSConstants.OPEN_TICKET_SUMMARY);
		if (index == -1) {
			viewer = new ReportViewer(new OpenTicketSummaryReport());
			tabbedPane.addTab(com.pinpos.POSConstants.OPEN_TICKET_SUMMARY_REPORT, viewer);
		}
		else {
			viewer = (ReportViewer) tabbedPane.getComponentAt(index);
		}
		tabbedPane.setSelectedComponent(viewer);
	}

}
