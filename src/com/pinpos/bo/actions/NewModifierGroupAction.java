package com.pinpos.bo.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import com.pinpos.bo.ui.BOMessageDialog;
import com.pinpos.bo.ui.BackOfficeWindow;
import com.pinpos.ui.dialog.BeanEditorDialog;
import com.pinpos.ui.model.MenuModifierGroupForm;

public class NewModifierGroupAction extends AbstractAction {

	public NewModifierGroupAction() {
		super();
	}

	public NewModifierGroupAction(String name) {
		super(name);
	}

	public NewModifierGroupAction(String name, Icon icon) {
		super(name, icon);
	}

	public void actionPerformed(ActionEvent e) {
		try {
			MenuModifierGroupForm editor = new MenuModifierGroupForm();
			BeanEditorDialog dialog = new BeanEditorDialog(editor, BackOfficeWindow.getInstance(), true);
			dialog.open();
		} catch (Exception x) {
			BOMessageDialog.showError(com.pinpos.POSConstants.ERROR_MESSAGE, x);
		}
	}

}
