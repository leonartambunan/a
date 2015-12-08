package com.pinpos.bo.ui.explorer;

import com.pinpos.POSConstants;
import com.pinpos.bo.ui.BOMessageDialog;
import com.pinpos.model.DrawerPullReport;
import com.pinpos.model.dao.DrawerPullReportDAO;
import com.pinpos.model.util.DateUtil;
import com.pinpos.swing.TransparentPanel;
import com.pinpos.ui.PosTableRenderer;
import com.pinpos.ui.util.UiUtil;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DrawerPullReportExplorer extends TransparentPanel {
	private JXDatePicker fromDatePicker = UiUtil.getCurrentMonthStart();
	private JXDatePicker toDatePicker = UiUtil.getCurrentMonthEnd();
	private JButton btnGo = new JButton(com.pinpos.POSConstants.GO);
	private JButton btnEditActualAmount = new JButton(com.pinpos.POSConstants.EDIT_ACTUAL_AMOUNT);
	
	private static SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("dd MMM, yyyy hh:mm a"); //$NON-NLS-1$

	private JTable table;

	public DrawerPullReportExplorer() {
		super(new BorderLayout());

		JPanel topPanel = new JPanel(new MigLayout());

		topPanel.add(new JLabel(com.pinpos.POSConstants.FROM), "grow"); //$NON-NLS-1$
//		topPanel.add(fromDatePicker, "wrap"); //$NON-NLS-1$
		topPanel.add(fromDatePicker); //$NON-NLS-1$
		topPanel.add(new JLabel(com.pinpos.POSConstants.TO), "grow"); //$NON-NLS-1$
//		topPanel.add(toDatePicker, "wrap"); //$NON-NLS-1$
		topPanel.add(toDatePicker); //$NON-NLS-1$
//		topPanel.add(btnGo, "skip 1, al right"); //$NON-NLS-1$
		topPanel.add(new JLabel("   "));
		topPanel.add(btnGo); //$NON-NLS-1$
		add(topPanel, BorderLayout.NORTH);

		add(new JScrollPane(table = new JTable(new DrawerPullExplorerTableModel(null))));
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setDefaultRenderer(Object.class, new PosTableRenderer());
		
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		bottomPanel.add(btnEditActualAmount);
		add(bottomPanel, BorderLayout.SOUTH);

		btnGo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					viewReport();
				} catch (Exception e1) {
					BOMessageDialog.showError(DrawerPullReportExplorer.this, POSConstants.ERROR_MESSAGE, e1);
				}
			}

		});
		btnEditActualAmount.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
					int selectedRow = table.getSelectedRow();
					if(selectedRow < 0) {
						BOMessageDialog.showError(DrawerPullReportExplorer.this, com.pinpos.POSConstants.SELECT_DRAWER_PULL_TO_EDIT);
						return;
					}
					
					String amountString = JOptionPane.showInputDialog(DrawerPullReportExplorer.this, com.pinpos.POSConstants.ENTER_ACTUAL_AMOUNT + ":"); //$NON-NLS-1$
					if(amountString == null) {
						return;
					}
					double amount = 0;
					try {
						amount = Double.parseDouble(amountString);
					}catch(Exception x) {
						BOMessageDialog.showError(DrawerPullReportExplorer.this, com.pinpos.POSConstants.INVALID_AMOUNT);
						return;
					}
					
					DrawerPullExplorerTableModel model = (DrawerPullExplorerTableModel) table.getModel();
					DrawerPullReport report = (DrawerPullReport) model.getRowData(selectedRow);
					report.setCashToDeposit(amount);
					
					DrawerPullReportDAO dao = new DrawerPullReportDAO();
					dao.saveOrUpdate(report);
					model.updateItem(selectedRow);
				} catch (Exception e1) {
					BOMessageDialog.showError(DrawerPullReportExplorer.this, POSConstants.ERROR_MESSAGE, e1);
				}
			}
			
		});
	}

	private void viewReport() {
		try {
			Date fromDate = fromDatePicker.getDate();
			Date toDate = toDatePicker.getDate();

			fromDate = DateUtil.startOfDay(fromDate);
			toDate = DateUtil.endOfDay(toDate);

			List<DrawerPullReport> list = new DrawerPullReportDAO().findReports(fromDate, toDate);
			table.setModel(new DrawerPullExplorerTableModel(list));
		} catch (Exception e) {
			BOMessageDialog.showError(this, POSConstants.ERROR_MESSAGE, e);
		}
	}

	class DrawerPullExplorerTableModel extends ListTableModel {
		String[] columnNames = { POSConstants.ID, POSConstants.TIME, POSConstants.DRAWER_PULL_AMOUNT, POSConstants.ACTUAL_AMOUNT };
		
		
		DrawerPullExplorerTableModel(List<DrawerPullReport> list) {
			setRows(list);
			setColumnNames(columnNames);
		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			DrawerPullReport report = (DrawerPullReport) rows.get(rowIndex);

			switch (columnIndex) {
			case 0:
				return report.getId().toString();

			case 1:
				return dateTimeFormatter.format(report.getReportTime());

			case 2:
				return report.getDrawerAccountable();

			case 3:
				return report.getCashToDeposit();

			}
			return null;
		}
	}
}
