package com.pinpos.ui.dialog;

import com.pinpos.bo.ui.BackOfficeWindow;


public class POSBackofficeDialog extends POSDialog {
	public POSBackofficeDialog() {
		super(BackOfficeWindow.getInstance(), true);
	}
}
