package com.pinpos.ui.report.actions;

import com.pinpos.Messages;
import com.pinpos.bo.ui.BackOfficeWindow;
import com.pinpos.ui.report.DailyReportViewer;
import com.pinpos.ui.report.DailyTxnReport;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DailyTxnReportAction extends AbstractAction {

	public DailyTxnReportAction() {
		super(Messages.getString("Daily.Txn"));
	}

	public DailyTxnReportAction(String name) {
		super(name);
	}

	public DailyTxnReportAction(String name, Icon icon) {
		super(name, icon);
	}

	public void actionPerformed(ActionEvent e) {
		BackOfficeWindow window = BackOfficeWindow.getInstance();
		JTabbedPane tabbedPane = window.getTabbedPane();

        DailyReportViewer viewer;
		int index = tabbedPane.indexOfTab(Messages.getString("Daily.Txn"));
		if (index == -1) {
			viewer = new DailyReportViewer(new DailyTxnReport());
			tabbedPane.addTab(Messages.getString("Daily.Txn"), viewer);
		}
		else {
			viewer = (DailyReportViewer) tabbedPane.getComponentAt(index);
		}

        tabbedPane.setSelectedComponent(viewer);

    }

}
