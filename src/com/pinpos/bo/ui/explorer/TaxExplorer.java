package com.pinpos.bo.ui.explorer;

import com.pinpos.POSConstants;
import com.pinpos.bo.ui.BOMessageDialog;
import com.pinpos.bo.ui.BackOfficeWindow;
import com.pinpos.model.Tax;
import com.pinpos.model.dao.TaxDAO;
import com.pinpos.swing.TransparentPanel;
import com.pinpos.ui.PosTableRenderer;
import com.pinpos.ui.dialog.BeanEditorDialog;
import com.pinpos.ui.dialog.ConfirmDeleteDialog;
import com.pinpos.ui.model.MenuCategoryForm;
import com.pinpos.ui.model.TaxForm;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TaxExplorer extends TransparentPanel {
	private List<Tax> taxList;
	
	private JTable table;

	private TaxExplorerTableModel tableModel;
	
	public TaxExplorer() {
		taxList = TaxDAO.getInstance().findAll();
		
		tableModel = new TaxExplorerTableModel();
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

					tableModel.addCategory((Tax) editor.getBean());
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

					Tax tax = taxList.get(index);

					BeanEditorDialog dialog = new BeanEditorDialog(new TaxForm(tax), BackOfficeWindow.getInstance(), true);
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

					if (ConfirmDeleteDialog.showMessage(TaxExplorer.this, com.pinpos.POSConstants.CONFIRM_DELETE, com.pinpos.POSConstants.DELETE) == ConfirmDeleteDialog.YES) {
						Tax tax = taxList.get(index);
						TaxDAO.getInstance().delete(tax);
						tableModel.deleteCategory(tax, index);
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
	
	class TaxExplorerTableModel extends AbstractTableModel {
		String[] columnNames = {
				POSConstants.ID,
				POSConstants.NAME,
				POSConstants.RATE};
		
		public int getRowCount() {
			if(taxList == null) {
				return 0;
			}
			return taxList.size();
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
			if(taxList == null)
				return ""; //$NON-NLS-1$
			
			Tax tax = taxList.get(rowIndex);
			
			switch(columnIndex) {
				case 0:
					return String.valueOf(tax.getId());
					
				case 1:
					return tax.getName();
					
				case 2:
					return tax.getRate();
					
			}

			return null;
		}

		public void addCategory(Tax tax) {
			int size = taxList.size();
			taxList.add(tax);
			fireTableRowsInserted(size, size);
		}
		
		public void deleteCategory(Tax tax, int index) {
			taxList.remove(tax);
			fireTableRowsDeleted(index, index);
		}
	}
}
