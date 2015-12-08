package com.pinpos.ui.report.actions;

import com.pinpos.Messages;
import com.pinpos.bo.ui.BackOfficeWindow;
import com.pinpos.ui.report.DailyReportViewer;
import com.pinpos.ui.report.DrawerBleedReport;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DrawerBleedReportAction extends AbstractAction {

	public DrawerBleedReportAction() {
		super(Messages.getString("DrawerBleedReport"));
	}

	public DrawerBleedReportAction(String name) {
		super(name);
	}

	public DrawerBleedReportAction(String name, Icon icon) {
		super(name, icon);
	}

	public void actionPerformed(ActionEvent e) {
		BackOfficeWindow window = BackOfficeWindow.getInstance();
		JTabbedPane tabbedPane = window.getTabbedPane();

        DailyReportViewer viewer;

		int index = tabbedPane.indexOfTab(Messages.getString("DrawerBleedReport"));
		if (index == -1) {
			viewer = new DailyReportViewer(new DrawerBleedReport());
			tabbedPane.addTab(Messages.getString("DrawerBleedReport"), viewer);
		}
		else {
			viewer = (DailyReportViewer) tabbedPane.getComponentAt(index);
		}

        tabbedPane.setSelectedComponent(viewer);

    }

}
