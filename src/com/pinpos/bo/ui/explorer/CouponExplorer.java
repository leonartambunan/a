package com.pinpos.bo.ui.explorer;

import com.pinpos.POSConstants;
import com.pinpos.bo.ui.BOMessageDialog;
import com.pinpos.bo.ui.BackOfficeWindow;
import com.pinpos.model.CouponAndDiscount;
import com.pinpos.model.dao.CouponAndDiscountDAO;
import com.pinpos.swing.TransparentPanel;
import com.pinpos.ui.PosTableRenderer;
import com.pinpos.ui.dialog.BeanEditorDialog;
import com.pinpos.ui.dialog.ConfirmDeleteDialog;
import com.pinpos.ui.model.CouponForm;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CouponExplorer extends TransparentPanel implements ActionListener {
	private JTable explorerView;
	private CouponExplorerTableModel explorerModel;
	
	public CouponExplorer() {
		
		explorerView = new JTable();
		explorerView.setDefaultRenderer(Object.class, new PosTableRenderer());
		
		setLayout(new BorderLayout(5,5));
		add(new JScrollPane(explorerView));
		
		JButton addButton = new JButton(com.pinpos.POSConstants.NEW.toUpperCase());
		addButton.setActionCommand(com.pinpos.POSConstants.ADD);
		addButton.addActionListener(this);
		
		JButton editButton = new JButton(com.pinpos.POSConstants.EDIT.toUpperCase());
		editButton.setActionCommand(com.pinpos.POSConstants.EDIT);
		editButton.addActionListener(this);

		JButton deleteButton = new JButton(com.pinpos.POSConstants.DELETE.toUpperCase());
		deleteButton.setActionCommand(com.pinpos.POSConstants.DELETE);
		deleteButton.addActionListener(this);

		TransparentPanel panel = new TransparentPanel();
		panel.add(addButton);
		panel.add(editButton);
		panel.add(deleteButton);
		add(panel, BorderLayout.SOUTH);
	}
	
	public void initData() throws Exception {
		CouponAndDiscountDAO dao = new CouponAndDiscountDAO();
		List<CouponAndDiscount> couponList = dao.findAll();
		explorerModel = new CouponExplorerTableModel(couponList);
		explorerView.setModel(explorerModel);
	}
	
	private void addNewCoupon() {
		try {
			CouponForm editor = new CouponForm();
			BeanEditorDialog dialog = new BeanEditorDialog(editor, BackOfficeWindow.getInstance(), true);
			dialog.open();
			
			if (dialog.isCanceled())
				return;
			CouponAndDiscount coupon = (CouponAndDiscount) editor.getBean();
			explorerModel.addCoupon(coupon);
		} catch (Exception x) {
		BOMessageDialog.showError(com.pinpos.POSConstants.ERROR_MESSAGE, x);
		}
	}
	
	private void editCoupon(CouponAndDiscount coupon) {
		try {
			CouponForm editor = new CouponForm(coupon);
			BeanEditorDialog dialog = new BeanEditorDialog(editor, BackOfficeWindow.getInstance(), true);
			dialog.open();
			if (dialog.isCanceled())
				return;

			explorerView.repaint();
		} catch (Throwable x) {
		BOMessageDialog.showError(com.pinpos.POSConstants.ERROR_MESSAGE, x);
		}
	}
	
	private void deleteCoupon(int index, CouponAndDiscount coupon) {
		try {
			if (ConfirmDeleteDialog.showMessage(this, com.pinpos.POSConstants.CONFIRM_DELETE, com.pinpos.POSConstants.DELETE) == ConfirmDeleteDialog.YES) {
				CouponAndDiscountDAO dao = new CouponAndDiscountDAO();
				dao.delete(coupon);
				explorerModel.deleteCoupon(coupon, index);
			}
		} catch (Exception x) {
		BOMessageDialog.showError(com.pinpos.POSConstants.ERROR_MESSAGE, x);
		}
	}
	
	private class CouponExplorerTableModel extends AbstractTableModel {

		String[] columnNames = {
				POSConstants.NAME,
				POSConstants.COUPON_TYPE,
				POSConstants.COUPON_VALUE,
				POSConstants.EXPIRY_DATE,
				POSConstants.DISABLED,
				POSConstants.NEVER_EXPIRE
        };

        List<CouponAndDiscount> couponList;
		
		CouponExplorerTableModel(List<CouponAndDiscount> list) {
			this.couponList = list;
		}
		
		public int getRowCount() {
			if(couponList == null) return 0;
			
			return couponList.size();
		}

		public int getColumnCount() {
			return columnNames.length;
		}
		
		@Override
		public String getColumnName(int index) {
			return columnNames[index];
		}

		public Object getValueAt(int row, int column) {
			if(couponList == null) return ""; //$NON-NLS-1$
			
			CouponAndDiscount coupon = couponList.get(row);
			switch(column) {
				case 0:
					return coupon.getName();
					
				case 1:
					return CouponAndDiscount.COUPON_TYPE_NAMES[coupon.getType()];
					
				case 2:
					return (coupon.getValue());
					
				case 3:
					return coupon.getExpiryDate();
					
				case 4:
					return (coupon.isDisabled());
					
				case 5:
					return (coupon.isNeverExpire());
			}
			
			return null;
		}
		
		public void addCoupon(CouponAndDiscount coupon) {
			int size = couponList.size();
			couponList.add(coupon);
			fireTableRowsInserted(size, size);
		}
		
		public void deleteCoupon(CouponAndDiscount coupon, int index) {
			couponList.remove(coupon);
			fireTableRowsDeleted(index, index);
		}
		
		public CouponAndDiscount getCoupon(int index) {
			return couponList.get(index);
		}
	}

	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		if(com.pinpos.POSConstants.ADD.equals(actionCommand)) {
			addNewCoupon();
		}
		else if(com.pinpos.POSConstants.EDIT.equals(actionCommand)) {
			int index = explorerView.getSelectedRow();
			if(index < 0) {
			BOMessageDialog.showError(com.pinpos.POSConstants.SELECT_COUPON_TO_EDIT);
				return;
			}
			CouponAndDiscount coupon = explorerModel.getCoupon(index);
			editCoupon(coupon);
		}
		else if(com.pinpos.POSConstants.DELETE.equals(actionCommand)) {
			int index = explorerView.getSelectedRow();
			if(index < 0) {
			BOMessageDialog.showError(com.pinpos.POSConstants.SELECT_COUPON_TO_DELETE);
				return;
			}
			CouponAndDiscount coupon = explorerModel.getCoupon(index);
			deleteCoupon(index, coupon);
		}
	}
}
