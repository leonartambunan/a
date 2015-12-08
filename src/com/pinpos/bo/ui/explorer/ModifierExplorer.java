package com.pinpos.bo.ui.explorer;

import com.pinpos.POSConstants;
import com.pinpos.bo.ui.BOMessageDialog;
import com.pinpos.bo.ui.BackOfficeWindow;
import com.pinpos.main.Application;
import com.pinpos.model.MenuModifier;
import com.pinpos.model.dao.ModifierDAO;
import com.pinpos.swing.TransparentPanel;
import com.pinpos.ui.PosTableRenderer;
import com.pinpos.ui.dialog.BeanEditorDialog;
import com.pinpos.ui.dialog.ConfirmDeleteDialog;
import com.pinpos.ui.model.MenuModifierForm;
import org.jdesktop.swingx.JXTable;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ModifierExplorer extends TransparentPanel {
	private List<MenuModifier> modifierList;
	ModifierExplorerTableModel tableModel;
	private String currencySymbol;
	private JTable table;

	public ModifierExplorer() {
		currencySymbol = Application.getCurrencySymbol();
		
		ModifierDAO dao = new ModifierDAO();
		modifierList = dao.findAll();

		tableModel = new ModifierExplorerTableModel();
		table = new JTable(tableModel);
		table.setAutoResizeMode(JXTable.AUTO_RESIZE_OFF);
		table.setDefaultRenderer(Object.class, new PosTableRenderer());
		//table.packAll();
		
		setLayout(new BorderLayout(5, 5));
		add(new JScrollPane(table));

		TransparentPanel panel = new TransparentPanel();
		ExplorerButtonPanel explorerButton = new ExplorerButtonPanel();
		JButton editButton = explorerButton.getEditButton();
		JButton addButton = explorerButton.getAddButton();
		JButton deleteButton = explorerButton.getDeleteButton();

		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int index = table.getSelectedRow();
					if (index < 0)
						return;
					MenuModifier modifier = modifierList.get(index);

					MenuModifierForm editor = new MenuModifierForm(modifier);
					BeanEditorDialog dialog = new BeanEditorDialog(editor, BackOfficeWindow.getInstance(), true);
					dialog.open();
					if (dialog.isCanceled())
						return;

					table.repaint();
				} catch (Throwable x) {
				BOMessageDialog.showError(com.pinpos.POSConstants.ERROR_MESSAGE, x);
				}
			}
		});

		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MenuModifierForm editor = new MenuModifierForm();
					BeanEditorDialog dialog = new BeanEditorDialog(editor, BackOfficeWindow.getInstance(), true);
					dialog.open();
					if (dialog.isCanceled())
						return;
					MenuModifier modifier = (MenuModifier) editor.getBean();
					tableModel.addModifier(modifier);
				} catch (Throwable x) {
				BOMessageDialog.showError(com.pinpos.POSConstants.ERROR_MESSAGE, x);
				}
			}

		});

		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int index = table.getSelectedRow();
					if (index < 0)
						return;
					if (ConfirmDeleteDialog.showMessage(ModifierExplorer.this, com.pinpos.POSConstants.CONFIRM_DELETE, com.pinpos.POSConstants.DELETE) != ConfirmDeleteDialog.NO) {
						MenuModifier category = modifierList.get(index);
						ModifierDAO modifierDAO = new ModifierDAO();
						modifierDAO.delete(category);
						tableModel.deleteModifier(category, index);
					}
				} catch (Throwable x) {
				BOMessageDialog.showError(com.pinpos.POSConstants.ERROR_MESSAGE, x);
				}

			}

		});

		panel.add(addButton);
		panel.add(editButton);
		panel.add(deleteButton);
		add(panel, BorderLayout.SOUTH);
	}

	class ModifierExplorerTableModel extends AbstractTableModel {
		String[] columnNames = {POSConstants.ID, POSConstants.NAME, POSConstants.PRICE + " (" + currencySymbol + ")", com.pinpos.POSConstants.EXTRA_PRICE, POSConstants.TAX + "(%)", com.pinpos.POSConstants.MODIFIER_GROUP }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		public int getRowCount() {
			if (modifierList == null) {
				return 0;
			}
			return modifierList.size();
		}

		public int getColumnCount() {
			return columnNames.length;
		}

		@Override
		public String getColumnName(int column) {
			return columnNames[column];
		}

		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			if (modifierList == null)
				return ""; //$NON-NLS-1$

			MenuModifier modifier = modifierList.get(rowIndex);

			switch (columnIndex) {
				case 0:
					return String.valueOf(modifier.getId());

				case 1:
					return (modifier.getName());

				case 2:
					return (modifier.getPrice());
					
				case 3:
					return (modifier.getExtraPrice());
					
				case 4:
					if(modifier.getTax() == null) {
						return ""; //$NON-NLS-1$
					}
					return (modifier.getTax().getRate());
					
				case 5:
					if(modifier.getModifierGroup() == null) {
						return ""; //$NON-NLS-1$
					}
					return modifier.getModifierGroup().getName();
			}
			return null;
		}

		public void addModifier(MenuModifier category) {
			int size = modifierList.size();
			modifierList.add(category);
			fireTableRowsInserted(size, size);

		}

		public void deleteModifier(MenuModifier category, int index) {
			modifierList.remove(category);
			fireTableRowsDeleted(index, index);
		}
	}
}
