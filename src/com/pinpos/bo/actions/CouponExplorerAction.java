package com.pinpos.bo.actions;

import com.pinpos.bo.ui.BOMessageDialog;
import com.pinpos.bo.ui.BackOfficeWindow;
import com.pinpos.bo.ui.explorer.CouponExplorer;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CouponExplorerAction extends AbstractAction {

	public CouponExplorerAction() {
		super(com.pinpos.POSConstants.COUPONS_AND_DISCOUNTS);
	}

	public CouponExplorerAction(String name) {
		super(name);
	}

	public CouponExplorerAction(String name, Icon icon) {
		super(name, icon);
	}

	public void actionPerformed(ActionEvent e) {
		try {
			BackOfficeWindow backOfficeWindow = BackOfficeWindow.getInstance();

			CouponExplorer explorer;
			JTabbedPane tabbedPane = backOfficeWindow.getTabbedPane();
			int index = tabbedPane.indexOfTab(com.pinpos.POSConstants.COUPON_DISCOUNT_EXPLORER);
			if (index == -1) {
				explorer = new CouponExplorer();
				explorer.initData();
				tabbedPane.addTab(com.pinpos.POSConstants.COUPON_DISCOUNT_EXPLORER, explorer);
			}
			else {
				explorer = (CouponExplorer) tabbedPane.getComponentAt(index);
			}
			tabbedPane.setSelectedComponent(explorer);
		} catch (Exception x) {
			BOMessageDialog.showError(com.pinpos.POSConstants.ERROR_MESSAGE, x);
		}
	}

}
