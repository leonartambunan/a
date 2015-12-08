package com.pinpos.ui.report.actions;

import com.pinpos.bo.ui.BackOfficeWindow;
import com.pinpos.ui.report.CreditCardReportView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CreditCardReportAction extends AbstractAction {

	public CreditCardReportAction() {
		super(com.pinpos.POSConstants.CREDIT_CARD_REPORT);
	}

	public CreditCardReportAction(String name) {
		super(name);
	}

	public CreditCardReportAction(String name, Icon icon) {
		super(name, icon);
	}

	public void actionPerformed(ActionEvent e) {
		BackOfficeWindow window = BackOfficeWindow.getInstance();
		JTabbedPane tabbedPane = window.getTabbedPane();
		
		CreditCardReportView reportView;
		int index = tabbedPane.indexOfTab(com.pinpos.POSConstants.CREDIT_CARD_REPORT);
		if (index == -1) {
			reportView = new CreditCardReportView();
			tabbedPane.addTab(com.pinpos.POSConstants.CREDIT_CARD_REPORT, reportView);
		}
		else {
			reportView = (CreditCardReportView) tabbedPane.getComponentAt(index);
		}
		tabbedPane.setSelectedComponent(reportView);
	}

}
