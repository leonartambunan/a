package com.pinpos.bo.ui.explorer;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.hibernate.exception.ConstraintViolationException;

import com.pinpos.bo.ui.BOMessageDialog;
import com.pinpos.bo.ui.BackOfficeWindow;
import com.pinpos.model.User;
import com.pinpos.model.dao.UserDAO;
import com.pinpos.swing.TransparentPanel;
import com.pinpos.ui.PosTableRenderer;
import com.pinpos.ui.dialog.BeanEditorDialog;
import com.pinpos.ui.dialog.ConfirmDeleteDialog;
import com.pinpos.ui.forms.UserForm;

public class UserExplorer extends TransparentPanel {
	
	private JTable table;
	private UserTableModel tableModel;
	
	public UserExplorer() {
		List<User> users = UserDAO.getInstance().findAll();
		
		tableModel = new UserTableModel(users);
		table = new JTable(tableModel);
		table.setDefaultRenderer(Object.class, new PosTableRenderer());
		
		setLayout(new BorderLayout(5,5));
		add(new JScrollPane(table));
		
		JButton addButton = new JButton(com.pinpos.POSConstants.ADD);
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Integer userWithMaxId = UserDAO.getInstance().findUserWithMaxId();
					
					UserForm editor = new UserForm();
					if(userWithMaxId != null) {
						editor.setId(userWithMaxId + 1);
					}
					BeanEditorDialog dialog = new BeanEditorDialog(editor, BackOfficeWindow.getInstance(), true);
					dialog.open();
					if (dialog.isCanceled())
						return;
					User user = (User) editor.getBean();
					tableModel.addItem(user);
				} catch (Exception x) {
					x.printStackTrace();
				BOMessageDialog.showError(com.pinpos.POSConstants.ERROR_MESSAGE, x);
				}
			}
			
		});
		JButton copyButton = new JButton(com.pinpos.POSConstants.COPY);
		copyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int index = table.getSelectedRow();
					if (index < 0)
						return;

					User user = (User) tableModel.getRowData(index);
					
					User user2 = new User();
					user2.setUserId(user.getUserId());
					user2.setNewUserType(user.getNewUserType());
					user2.setFirstName(user.getFirstName());
					user2.setLastName(user.getLastName());
					user2.setPassword(user.getPassword());
					user2.setSsn(user.getSsn());
					
					UserForm editor = new UserForm();
					editor.setEditMode(false);
					editor.setBean(user2);
					BeanEditorDialog dialog = new BeanEditorDialog(editor, BackOfficeWindow.getInstance(), true);
					dialog.open();
					if (dialog.isCanceled())
						return;

					User newUser = (User) editor.getBean();
					tableModel.addItem(newUser);
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

					User user = (User) tableModel.getRowData(index);
					UserForm editor = new UserForm();
					editor.setEditMode(true);
					editor.setBean(user);
					BeanEditorDialog dialog = new BeanEditorDialog(editor, BackOfficeWindow.getInstance(), true);
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
				int index = table.getSelectedRow();
				if (index < 0)
					return;
				
				User user = (User) tableModel.getRowData(index);
				if(user == null) {
					return;
				}
				
				try {
					if (ConfirmDeleteDialog.showMessage(UserExplorer.this, com.pinpos.POSConstants.CONFIRM_DELETE, com.pinpos.POSConstants.DELETE) == ConfirmDeleteDialog.YES) {
						UserDAO.getInstance().delete(user);
						tableModel.deleteItem(index);
					}
				} catch(ConstraintViolationException x) {
					String message = com.pinpos.POSConstants.USER + " " + user.getFirstName() + " " + user.getLastName() + " (" + user.getNewUserType() + ") " + com.pinpos.POSConstants.ERROR_MESSAGE; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				BOMessageDialog.showError(message, x);
				} catch (Exception x) {
				BOMessageDialog.showError(com.pinpos.POSConstants.ERROR_MESSAGE, x);
				}
			}
			
		});

		TransparentPanel panel = new TransparentPanel();
		panel.add(addButton);
		panel.add(copyButton);
		panel.add(editButton);
		panel.add(deleteButton);
		add(panel, BorderLayout.SOUTH);
	}
	
	class UserTableModel extends ListTableModel {
		
		UserTableModel(List list){
			super(new String[] {com.pinpos.POSConstants.ID, com.pinpos.POSConstants.FIRST_NAME, com.pinpos.POSConstants.LAST_NAME, com.pinpos.POSConstants.TYPE}, list);
		}
		

		public Object getValueAt(int rowIndex, int columnIndex) {
			User user = (User) rows.get(rowIndex);
			
			switch(columnIndex) {
				case 0:
					return String.valueOf(user.getUserId());
					
				case 1:
					return user.getFirstName();
					
				case 2:
					return user.getLastName();
					
				case 3:
					return user.getNewUserType();
			}
			return null;
		}
	}
}
