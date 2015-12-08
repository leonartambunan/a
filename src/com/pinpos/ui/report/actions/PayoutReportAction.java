package com.pinpos.ui.report.actions;

import com.pinpos.Messages;
import com.pinpos.bo.ui.BackOfficeWindow;
import com.pinpos.ui.report.DailyReportViewer;
import com.pinpos.ui.report.PayoutReport;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class PayoutReportAction extends AbstractAction {

	public PayoutReportAction() {
		super(Messages.getString("PayOutReport"));
	}

	public PayoutReportAction(String name) {
		super(name);
	}

	public PayoutReportAction(String name, Icon icon) {
		super(name, icon);
	}

	public void actionPerformed(ActionEvent e) {
		BackOfficeWindow window = BackOfficeWindow.getInstance();
		JTabbedPane tabbedPane = window.getTabbedPane();

        DailyReportViewer viewer = null;

		int index = tabbedPane.indexOfTab(Messages.getString("PayOutReport"));
		if (index == -1) {
			viewer = new DailyReportViewer(new PayoutReport());
			tabbedPane.addTab(Messages.getString("PayOutReport"), viewer);
		}
		else {
			viewer = (DailyReportViewer) tabbedPane.getComponentAt(index);
		}

        tabbedPane.setSelectedComponent(viewer);

    }

}
