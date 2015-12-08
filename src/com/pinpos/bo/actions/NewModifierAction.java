package com.pinpos.bo.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import com.pinpos.bo.ui.BOMessageDialog;
import com.pinpos.bo.ui.BackOfficeWindow;
import com.pinpos.ui.dialog.BeanEditorDialog;
import com.pinpos.ui.model.MenuModifierForm;

public class NewModifierAction extends AbstractAction {

	public NewModifierAction() {
		super();
	}

	public NewModifierAction(String name) {
		super(name);
	}

	public NewModifierAction(String name, Icon icon) {
		super(name, icon);
	}

	public void actionPerformed(ActionEvent e) {
		try {
			MenuModifierForm editor = new MenuModifierForm();
			BeanEditorDialog dialog = new BeanEditorDialog(editor, BackOfficeWindow.getInstance(), true);
			dialog.open();
		} catch (Exception x) {
			BOMessageDialog.showError(com.pinpos.POSConstants.ERROR_MESSAGE, x);
		}
	}

}
