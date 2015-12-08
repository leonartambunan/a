package com.pinpos.ui.report.actions;

import com.pinpos.Messages;
import com.pinpos.bo.ui.BackOfficeWindow;
import com.pinpos.ui.report.ReportViewer;
import com.pinpos.ui.report.SalesReportTxnDetail;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SalesReportPerTxnAction extends AbstractAction {

	public SalesReportPerTxnAction() {
		super(Messages.getString("Sales.Detail"));
	}

	public SalesReportPerTxnAction(String name) {
		super(name);
	}

	public SalesReportPerTxnAction(String name, Icon icon) {
		super(name, icon);
	}

	public void actionPerformed(ActionEvent e) {
		BackOfficeWindow window = BackOfficeWindow.getInstance();
		JTabbedPane tabbedPane = window.getTabbedPane();
		
		ReportViewer viewer = null;
		int index = tabbedPane.indexOfTab(Messages.getString("Sales.Detail"));
		if (index == -1) {
			viewer = new ReportViewer(new SalesReportTxnDetail());
			tabbedPane.addTab(Messages.getString("Sales.Detail"), viewer);
		}
		else {
			viewer = (ReportViewer) tabbedPane.getComponentAt(index);
		}
		tabbedPane.setSelectedComponent(viewer);
	}

}
