package com.pinpos.ui.report.actions;

import com.pinpos.bo.ui.BackOfficeWindow;
import com.pinpos.ui.report.JournalReportView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class JournalReportAction extends AbstractAction {

	public JournalReportAction() {
		super(com.pinpos.POSConstants.JOURNAL_REPORT);
	}

	public JournalReportAction(String name) {
		super(name);
	}

	public JournalReportAction(String name, Icon icon) {
		super(name, icon);
	}

	public void actionPerformed(ActionEvent e) {
		BackOfficeWindow window = BackOfficeWindow.getInstance();
		JTabbedPane tabbedPane = window.getTabbedPane();
		
		JournalReportView reportView = null;
		int index = tabbedPane.indexOfTab(com.pinpos.POSConstants.JOURNAL_REPORT);
		if (index == -1) {
			reportView = new JournalReportView();
			tabbedPane.addTab(com.pinpos.POSConstants.JOURNAL_REPORT, reportView);
		}
		else {
			reportView = (JournalReportView) tabbedPane.getComponentAt(index);
		}
		tabbedPane.setSelectedComponent(reportView);
	}

}
