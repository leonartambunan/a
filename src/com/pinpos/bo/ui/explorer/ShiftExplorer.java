package com.pinpos.bo.ui.explorer;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.pinpos.bo.ui.BOMessageDialog;
import com.pinpos.model.Shift;
import com.pinpos.model.User;
import com.pinpos.model.dao.ShiftDAO;
import com.pinpos.model.dao.UserDAO;
import com.pinpos.swing.TransparentPanel;
import com.pinpos.ui.PosTableRenderer;
import com.pinpos.ui.dialog.ConfirmDeleteDialog;
import com.pinpos.ui.model.ShiftEntryDialog;
import com.pinpos.util.ShiftUtil;

public class ShiftExplorer extends TransparentPanel {
	
	private JTable table;
	private ShiftTableModel tableModel;
	
	public ShiftExplorer() {
		List<Shift> shifts = new ShiftDAO().findAll();
		
		tableModel = new ShiftTableModel(shifts);
		table = new JTable(tableModel);
		table.setDefaultRenderer(Object.class, new PosTableRenderer());
		
		setLayout(new BorderLayout(5,5));
		add(new JScrollPane(table));
		
		JButton addButton = new JButton(com.pinpos.POSConstants.ADD);
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ShiftEntryDialog dialog = new ShiftEntryDialog();
					dialog.open();
					if (dialog.isCanceled())
						return;
					Shift shift = dialog.getShift();
					tableModel.addItem(shift);
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

					Shift shift = (Shift) tableModel.getRowData(index);
					ShiftEntryDialog dialog = new ShiftEntryDialog();
					dialog.setShift(shift);
					dialog.open();
					if (dialog.isCanceled())
						return;

					tableModel.updateItem(index);
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

					if (ConfirmDeleteDialog.showMessage(ShiftExplorer.this, com.pinpos.POSConstants.CONFIRM_DELETE, com.pinpos.POSConstants.DELETE) == ConfirmDeleteDialog.YES) {
						User user = (User) tableModel.getRowData(index);
						UserDAO.getInstance().delete(user);
						tableModel.deleteItem(index);
					}
				} catch (Exception x) {
				BOMessageDialog.showError(com.pinpos.POSConstants.ERROR_MESSAGE, x);
				}
			}
			
		});

		TransparentPanel panel = new TransparentPanel();
		panel.add(addButton);
		panel.add(editButton);
		//panel.add(deleteButton);
		add(panel, BorderLayout.SOUTH);
	}
	
	class ShiftTableModel extends ListTableModel {
		
		ShiftTableModel(List list){
			super(new String[] {com.pinpos.POSConstants.ID, com.pinpos.POSConstants.NAME,
					com.pinpos.POSConstants.START_TIME, com.pinpos.POSConstants.END_TIME}, list);
		}
		

		public Object getValueAt(int rowIndex, int columnIndex) {
			Shift shift = (Shift) rows.get(rowIndex);
			
			switch(columnIndex) {
				case 0:
					return String.valueOf(shift.getId());
					
				case 1:
					return shift.getName();
					
				case 2:
					return ShiftUtil.buildShiftTimeRepresentation(shift.getStartTime());
					
				case 3:
					return ShiftUtil.buildShiftTimeRepresentation(shift.getEndTime());
			}
			return null;
		}
	}
}
