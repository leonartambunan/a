package com.pinpos.bo.ui.explorer;

import com.pinpos.POSConstants;
import com.pinpos.bo.ui.BOMessageDialog;
import com.pinpos.bo.ui.BackOfficeWindow;
import com.pinpos.model.CookingInstruction;
import com.pinpos.model.dao.CookingInstructionDAO;
import com.pinpos.swing.TransparentPanel;
import com.pinpos.ui.PosTableRenderer;
import com.pinpos.ui.dialog.ConfirmDeleteDialog;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CookingInstructionExplorer extends TransparentPanel {
	private List<CookingInstruction> categoryList;
	
	private JTable table;

	private CookingInstructionTableModel tableModel;
	CookingInstructionDAO dao = new CookingInstructionDAO();
	
	public CookingInstructionExplorer() {
		categoryList = dao.findAll();
		
		tableModel = new CookingInstructionTableModel();
		table = new JTable(tableModel);
		table.setDefaultRenderer(Object.class, new PosTableRenderer());
		
		setLayout(new BorderLayout(5,5));
		add(new JScrollPane(table));
		
		JButton addButton = new JButton(com.pinpos.POSConstants.ADD);
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String instruction = JOptionPane.showInputDialog(BackOfficeWindow.getInstance(), com.pinpos.POSConstants.ENTER_INSTRUCTION_DESCRIPTION);
					if(instruction == null) {
						BOMessageDialog.showError(BackOfficeWindow.getInstance(), com.pinpos.POSConstants.INSTRUCTION_CANNOT_BE_EMPTY);
						return;
					}
					if(instruction.length() > 60) {
						BOMessageDialog.showError(BackOfficeWindow.getInstance(), com.pinpos.POSConstants.LONG_INSTRUCTION_ERROR);
						return;
					}
					
					CookingInstruction cookingInstruction = new CookingInstruction();
					cookingInstruction.setDescription(instruction);
					dao.save(cookingInstruction);
					
					tableModel.add(cookingInstruction);
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
					
					CookingInstruction cookingInstruction = categoryList.get(index);
					String instruction = JOptionPane.showInputDialog(BackOfficeWindow.getInstance(), com.pinpos.POSConstants.ENTER_INSTRUCTION_DESCRIPTION, cookingInstruction.getDescription());

					if(instruction == null) {
						BOMessageDialog.showError(BackOfficeWindow.getInstance(), com.pinpos.POSConstants.INSTRUCTION_CANNOT_BE_EMPTY);
						return;
					}
					if(instruction.length() > 60) {
						BOMessageDialog.showError(BackOfficeWindow.getInstance(), com.pinpos.POSConstants.LONG_INSTRUCTION_ERROR);
						return;
					}
					cookingInstruction.setDescription(instruction);
					dao.saveOrUpdate(cookingInstruction);
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

					if (ConfirmDeleteDialog.showMessage(CookingInstructionExplorer.this, com.pinpos.POSConstants.CONFIRM_DELETE, com.pinpos.POSConstants.DELETE) == ConfirmDeleteDialog.YES) {
						CookingInstruction cookingInstruction = categoryList.get(index);
						dao.delete(cookingInstruction);
						tableModel.delete(cookingInstruction, index);
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
	
	class CookingInstructionTableModel extends AbstractTableModel {
		String[] columnNames = {POSConstants.ID, POSConstants.DESCRIPTION};
		
		public int getRowCount() {
			if(categoryList == null) {
				return 0;
			}
			return categoryList.size();
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
			if(categoryList == null)
				return ""; //$NON-NLS-1$
			
			CookingInstruction cookingInstruction = categoryList.get(rowIndex);
			
			switch(columnIndex) {
				case 0:
					return String.valueOf(cookingInstruction.getId());
					
				case 1:
					return cookingInstruction.getDescription();
					
			}
			return null;
		}

		public void add(CookingInstruction instruction) {
			int size = categoryList.size();
			categoryList.add(instruction);
			fireTableRowsInserted(size, size);
		}
		
		public void delete(CookingInstruction instruction, int index) {
			categoryList.remove(instruction);
			fireTableRowsDeleted(index, index);
		}
	}
}
