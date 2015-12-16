package com.pinpos.bo.ui.explorer;

import com.pinpos.POSConstants;
import com.pinpos.bo.ui.BOMessageDialog;
import com.pinpos.bo.ui.BackOfficeWindow;
import com.pinpos.model.MenuCategory;
import com.pinpos.model.dao.MenuCategoryDAO;
import com.pinpos.swing.TransparentPanel;
import com.pinpos.ui.PosTableRenderer;
import com.pinpos.ui.dialog.BeanEditorDialog;
import com.pinpos.ui.dialog.ConfirmDeleteDialog;
import com.pinpos.ui.model.MenuCategoryForm;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CategoryExplorer extends TransparentPanel {
	private List<MenuCategory> categoryList;
	
	private JTable table;

	private CategoryExplorerTableModel tableModel;
	
	public CategoryExplorer() {
		MenuCategoryDAO dao = new MenuCategoryDAO();
		categoryList = dao.findAll();
		
		tableModel = new CategoryExplorerTableModel();
		table = new JTable(tableModel);
		table.setDefaultRenderer(Object.class, new PosTableRenderer());
		
		setLayout(new BorderLayout(5,5));
		add(new JScrollPane(table));
		
		JButton addButton = new JButton(com.pinpos.POSConstants.ADD);
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MenuCategoryForm editor = new MenuCategoryForm();
					BeanEditorDialog dialog = new BeanEditorDialog(editor, BackOfficeWindow.getInstance(), true);
					dialog.open();
					if (dialog.isCanceled())
						return;
					MenuCategory foodCategory = (MenuCategory) editor.getBean();
					tableModel.addCategory(foodCategory);
				} catch (Exception x) {
					BOMessageDialog.showError(com.pinpos.POSConstants.ERROR_MESSAGE, x);
				}
			}
			
		});
		
		JButton editButton = new JButton(com.pinpos.POSConstants.EDIT);
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int index = table.getSelectedRow();
					if (index < 0)
						return;

					MenuCategory category = categoryList.get(index);

					MenuCategoryForm editor = new MenuCategoryForm(category);
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
		JButton deleteButton = new JButton(com.pinpos.POSConstants.DELETE);
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int index = table.getSelectedRow();
					if (index < 0)
						return;

					if (ConfirmDeleteDialog.showMessage(CategoryExplorer.this, com.pinpos.POSConstants.CONFIRM_DELETE, com.pinpos.POSConstants.DELETE) == ConfirmDeleteDialog.YES) {
						MenuCategory category = categoryList.get(index);
						MenuCategoryDAO dao = new MenuCategoryDAO();
						dao.delete(category);
						tableModel.deleteCategory(category, index);
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
	
	class CategoryExplorerTableModel extends AbstractTableModel {
		String[] columnNames = {
				POSConstants.ID,
				POSConstants.NAME,
				POSConstants.BEVERAGE,
				POSConstants.VISIBLE};
		
		public int getRowCount() {
			if(categoryList == null) {
				return 0;
			}
			return categoryList.size();
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
			if(categoryList == null)
				return ""; //$NON-NLS-1$
			
			MenuCategory category = categoryList.get(rowIndex);
			
			switch(columnIndex) {
				case 0:
					return String.valueOf(category.getId());
					
				case 1:
					return category.getName();
					
				case 2:
					return (category.isBeverage());
					
				case 3:
					return (category.isVisible());
			}
			return null;
		}

		public void addCategory(MenuCategory category) {
			int size = categoryList.size();
			categoryList.add(category);
			fireTableRowsInserted(size, size);
		}
		
		public void deleteCategory(MenuCategory category, int index) {
			categoryList.remove(category);
			fireTableRowsDeleted(index, index);
		}
	}
}
