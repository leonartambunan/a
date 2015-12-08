package com.pinpos.bo.ui.explorer;

import com.pinpos.POSConstants;
import com.pinpos.bo.ui.BOMessageDialog;
import com.pinpos.bo.ui.BackOfficeWindow;
import com.pinpos.model.MenuGroup;
import com.pinpos.model.dao.MenuGroupDAO;
import com.pinpos.swing.TransparentPanel;
import com.pinpos.ui.PosTableRenderer;
import com.pinpos.ui.dialog.BeanEditorDialog;
import com.pinpos.ui.dialog.ConfirmDeleteDialog;
import com.pinpos.ui.model.MenuGroupForm;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GroupExplorer extends TransparentPanel {
	private List<MenuGroup> groupList;

	private JTable table;
	private GroupExplorerTableModel tableModel;

	public GroupExplorer() {
		MenuGroupDAO dao = new MenuGroupDAO();
		groupList = dao.findAll();

		tableModel = new GroupExplorerTableModel();
		table = new JTable(tableModel);
		table.setDefaultRenderer(Object.class, new PosTableRenderer());

		setLayout(new BorderLayout(5, 5));
		add(new JScrollPane(table));

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

					MenuGroup category = groupList.get(index);

					MenuGroupForm editor = new MenuGroupForm(category);
					BeanEditorDialog dialog = new BeanEditorDialog(editor, BackOfficeWindow.getInstance(), true);
					dialog.open();
					if (dialog.isCanceled())
						return;
					table.repaint();
				} catch (Exception x) {
				BOMessageDialog.showError(com.pinpos.POSConstants.ERROR_MESSAGE, x);
				}
			}

		});

		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MenuGroupForm editor = new MenuGroupForm();
					BeanEditorDialog dialog = new BeanEditorDialog(editor, BackOfficeWindow.getInstance(), true);
					dialog.open();
					if (dialog.isCanceled())
						return;
					MenuGroup foodGroup = (MenuGroup) editor.getBean();
					tableModel.addGroup(foodGroup);
				} catch (Exception x) {
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
					if (ConfirmDeleteDialog.showMessage(GroupExplorer.this, com.pinpos.POSConstants.CONFIRM_DELETE, com.pinpos.POSConstants.DELETE) != ConfirmDeleteDialog.NO) {
						MenuGroup category = groupList.get(index);
						MenuGroupDAO foodGroupDAO = new MenuGroupDAO();
						foodGroupDAO.delete(category);
						tableModel.deleteGroup(category, index);
					}
				} catch (Exception x) {
				BOMessageDialog.showError(com.pinpos.POSConstants.ERROR_MESSAGE, x);
				}
			}

		});

		TransparentPanel panel = new TransparentPanel();

		panel.add(addButton);
		panel.add(editButton);
		panel.add(deleteButton);
		add(panel, BorderLayout.SOUTH);
	}

	class GroupExplorerTableModel extends AbstractTableModel {
		String[] columnNames = { POSConstants.ID, POSConstants.NAME, POSConstants.VISIBLE, POSConstants.MENU_CATEGORY };

		public int getRowCount() {
			if (groupList == null) {
				return 0;
			}
			return groupList.size();
		}

		public int getColumnCount() {
			return 4;
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
			if (groupList == null)
				return ""; //$NON-NLS-1$

			MenuGroup category = groupList.get(rowIndex);

			switch (columnIndex) {
				case 0:
					return String.valueOf(category.getId());

				case 1:
					return category.getName();

				case 2:
					return (category.isVisible());

				case 3:
					return category.getParent().getName();
			}
			return null;
		}

		public void addGroup(MenuGroup category) {
			int size = groupList.size();
			groupList.add(category);
			fireTableRowsInserted(size, size);

		}

		public void deleteGroup(MenuGroup category, int index) {
			groupList.remove(category);
			fireTableRowsDeleted(index, index);
		}
	}
}
