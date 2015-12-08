package com.pinpos.bo.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import com.pinpos.bo.ui.BOMessageDialog;
import com.pinpos.bo.ui.BackOfficeWindow;
import com.pinpos.ui.dialog.BeanEditorDialog;
import com.pinpos.ui.model.MenuItemForm;

public class NewMenuItemAction extends AbstractAction {

	public NewMenuItemAction() {
		super();
	}

	public NewMenuItemAction(String name) {
		super(name);
	}

	public NewMenuItemAction(String name, Icon icon) {
		super(name, icon);
	}

	public void actionPerformed(ActionEvent e) {
		try {
			MenuItemForm editor = new MenuItemForm();
			BeanEditorDialog dialog = new BeanEditorDialog(editor, BackOfficeWindow.getInstance(), true);
			dialog.open();
		} catch (Exception x) {
			BOMessageDialog.showError(com.pinpos.POSConstants.ERROR_MESSAGE, x);
		}
	}

}
