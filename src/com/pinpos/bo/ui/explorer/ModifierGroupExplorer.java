package com.pinpos.bo.ui.explorer;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import com.pinpos.bo.ui.BOMessageDialog;
import com.pinpos.bo.ui.BackOfficeWindow;
import com.pinpos.model.MenuModifierGroup;
import com.pinpos.model.dao.ModifierGroupDAO;
import com.pinpos.swing.TransparentPanel;
import com.pinpos.ui.PosTableRenderer;
import com.pinpos.ui.dialog.BeanEditorDialog;
import com.pinpos.ui.dialog.ConfirmDeleteDialog;
import com.pinpos.ui.model.MenuModifierGroupForm;

public class ModifierGroupExplorer extends TransparentPanel {
	private List<MenuModifierGroup> mGroupList;

	private JTable table;
	private ModifierGroupExplorerTableModel tableModel;

	public ModifierGroupExplorer() {
		ModifierGroupDAO dao = new ModifierGroupDAO();
		mGroupList = dao.findAll();

		tableModel = new ModifierGroupExplorerTableModel();
		table = new JTable(tableModel);
		table.setDefaultRenderer(Object.class, new PosTableRenderer());

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
					MenuModifierGroup category = mGroupList.get(index);

					MenuModifierGroupForm editor = new MenuModifierGroupForm(category);
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
					MenuModifierGroupForm editor = new MenuModifierGroupForm();
					BeanEditorDialog dialog = new BeanEditorDialog(editor, BackOfficeWindow.getInstance(), true);
					dialog.open();
					if (dialog.isCanceled())
						return;
					MenuModifierGroup modifierGroup = (MenuModifierGroup) editor.getBean();
					tableModel.addModifierGroup(modifierGroup);
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
					if (ConfirmDeleteDialog.showMessage(ModifierGroupExplorer.this, com.pinpos.POSConstants.CONFIRM_DELETE, com.pinpos.POSConstants.DELETE) != ConfirmDeleteDialog.NO) {
						MenuModifierGroup category = mGroupList.get(index);
						ModifierGroupDAO modifierCategoryDAO = new ModifierGroupDAO();
						modifierCategoryDAO.delete(category);
						tableModel.deleteModifierGroup(category, index);
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

	class ModifierGroupExplorerTableModel extends AbstractTableModel {
		String[] columnNames = { com.pinpos.POSConstants.ID, com.pinpos.POSConstants.NAME };

		public int getRowCount() {
			if (mGroupList == null) {
				return 0;
			}
			return mGroupList.size();
		}

		public int getColumnCount() {
			return 2;
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
			if (mGroupList == null)
				return ""; //$NON-NLS-1$

			MenuModifierGroup mgroup = mGroupList.get(rowIndex);

			switch (columnIndex) {
				case 0:
					return String.valueOf(mgroup.getId());

				case 1:
					return mgroup.getName();

			}
			return null;
		}

		public void addModifierGroup(MenuModifierGroup category) {
			int size = mGroupList.size();
			mGroupList.add(category);
			fireTableRowsInserted(size, size);

		}

		public void deleteModifierGroup(MenuModifierGroup category, int index) {
			mGroupList.remove(category);
			fireTableRowsDeleted(index, index);
		}
	}
}
