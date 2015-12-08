package com.pinpos.bo.actions;

import com.pinpos.POSConstants;
import com.pinpos.bo.ui.BackOfficeWindow;
import com.pinpos.bo.ui.explorer.TaxExplorer;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class TaxExplorerAction extends AbstractAction {

	public TaxExplorerAction() {
		super(POSConstants.TAX);
	}

	public TaxExplorerAction(String name) {
		super(name);
	}

	public TaxExplorerAction(String name, Icon icon) {
		super(name, icon);
	}

	public void actionPerformed(ActionEvent e) {
		BackOfficeWindow backOfficeWindow = BackOfficeWindow.getInstance();
		
		TaxExplorer explorer = null;
		JTabbedPane tabbedPane = backOfficeWindow.getTabbedPane();
		int index = tabbedPane.indexOfTab(POSConstants.TAX_EXPLORER);
		if (index == -1) {
			explorer = new TaxExplorer();
			tabbedPane.addTab(POSConstants.TAX_EXPLORER, explorer);
		}
		else {
			explorer = (TaxExplorer) tabbedPane.getComponentAt(index);
		}
		tabbedPane.setSelectedComponent(explorer);
	}

}
