package com.pinpos.ui.report;

import com.pinpos.bo.ui.BackOfficeWindow;
import com.pinpos.model.Terminal;
import com.pinpos.model.UserType;
import com.pinpos.model.dao.SalesSummaryDAO;
import com.pinpos.model.dao.TerminalDAO;
import com.pinpos.model.dao.UserTypeDAO;
import com.pinpos.report.SalesAnalysisReportModel;
import com.pinpos.report.SalesAnalysisReportModel.SalesAnalysisData;
import com.pinpos.report.SalesStatistics;
import com.pinpos.report.SalesStatistics.ShiftwiseDataTableModel;
import com.pinpos.swing.ListComboBoxModel;
import com.pinpos.ui.dialog.POSMessageDialog;
import com.pinpos.ui.util.UiUtil;
import com.pinpos.util.NumberUtil;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JRViewer;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class SalesSummaryReportView extends javax.swing.JPanel {
	public static final int REPORT_KEY_STATISTICS = 1;
	public static final int REPORT_SALES_ANALYSIS = 2;

	private int reportType;

	public SalesSummaryReportView() {
		initComponents();

		UserTypeDAO dao = new UserTypeDAO();
		List<UserType> userTypes = dao.findAll();
		
		Vector list = new Vector();
		list.add(null);
		list.addAll(userTypes);
		
		cbUserType.setModel(new DefaultComboBoxModel(list));

		TerminalDAO terminalDAO = new TerminalDAO();
		List terminals = terminalDAO.findAll();
		terminals.add(0, com.pinpos.POSConstants.ALL);
		cbTerminal.setModel(new ListComboBoxModel(terminals));
	}

	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		fromDatePicker = UiUtil.getCurrentMonthStart();
		toDatePicker = UiUtil.getCurrentMonthEnd();
		cbUserType = new javax.swing.JComboBox();
		cbTerminal = new javax.swing.JComboBox();
		btnGo = new javax.swing.JButton();
		jSeparator1 = new javax.swing.JSeparator();
		reportPanel = new javax.swing.JPanel();

		jLabel1.setText(com.pinpos.POSConstants.FROM + ":");

		jLabel2.setText(com.pinpos.POSConstants.TO + ":");

		jLabel3.setText(com.pinpos.POSConstants.USER_TYPE + ":");

		jLabel4.setText(com.pinpos.POSConstants.TERMINAL_LABEL + ":");

		btnGo.setText(com.pinpos.POSConstants.GO);
		btnGo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				showReport(evt);
			}
		});

		reportPanel.setLayout(new java.awt.BorderLayout());

		org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(
				layout.createSequentialGroup().addContainerGap().add(
						layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE).add(
								layout.createSequentialGroup().add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(jLabel1).add(jLabel2)).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(
										layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false).add(toDatePicker, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).add(fromDatePicker, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).add(20, 20, 20).add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(jLabel3).add(jLabel4)).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(
										layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false).add(cbTerminal, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).add(cbUserType, 0, 137, Short.MAX_VALUE)).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(btnGo,
										org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 72, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)).add(reportPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)).addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(
				layout.createSequentialGroup().addContainerGap().add(
						layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(jLabel3).add(
								layout.createSequentialGroup().add(cbUserType, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(
										layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE).add(jLabel4).add(cbTerminal, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).add(btnGo))).add(
								layout.createSequentialGroup().add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(jLabel1).add(fromDatePicker, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(
												layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(jLabel2).add(toDatePicker, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))).addPreferredGap(
						org.jdesktop.layout.LayoutStyle.RELATED).add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(reportPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 303,
						Short.MAX_VALUE).addContainerGap()));

		layout.linkSize(new java.awt.Component[] { cbTerminal, cbUserType, jLabel3, jLabel4 }, org.jdesktop.layout.GroupLayout.VERTICAL);

		layout.linkSize(new java.awt.Component[] { fromDatePicker, jLabel1, jLabel2, toDatePicker }, org.jdesktop.layout.GroupLayout.VERTICAL);

	}

	private boolean initCriteria() {
		fromDate = fromDatePicker.getDate();
		toDate = toDatePicker.getDate();

		if (fromDate.after(toDate)) {
			POSMessageDialog.showError(BackOfficeWindow.getInstance(), com.pinpos.POSConstants.FROM_DATE_CANNOT_BE_GREATER_THAN_TO_DATE_);
			return false;
		}

		dateDiff = (int) ((toDate.getTime() - fromDate.getTime()) * (1.15740741 * Math.pow(10, -8))) + 1;
		userType = (UserType) cbUserType.getSelectedItem();
//		if (userType.equalsIgnoreCase(com.pinpos.POSConstants.ALL)) {
//			userType = null;
//		}
		terminal = null;
		if (cbTerminal.getSelectedItem() instanceof Terminal) {
			terminal = (Terminal) cbTerminal.getSelectedItem();
		}

		Calendar calendar = Calendar.getInstance();
		calendar.clear();

		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(fromDate);

		calendar.set(Calendar.YEAR, calendar2.get(Calendar.YEAR));
		calendar.set(Calendar.MONTH, calendar2.get(Calendar.MONTH));
		calendar.set(Calendar.DATE, calendar2.get(Calendar.DATE));
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		fromDate = calendar.getTime();

		calendar.clear();
		calendar2.setTime(toDate);
		calendar.set(Calendar.YEAR, calendar2.get(Calendar.YEAR));
		calendar.set(Calendar.MONTH, calendar2.get(Calendar.MONTH));
		calendar.set(Calendar.DATE, calendar2.get(Calendar.DATE));
		calendar.set(Calendar.HOUR, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		toDate = calendar.getTime();

		return true;
	}

	private void showReport(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showReport
		try {
			if (!initCriteria()) {
				return;
			}

			if (reportType == REPORT_KEY_STATISTICS) {
				showKeyStatisticsReport();
			}
			else if (reportType == REPORT_SALES_ANALYSIS) {
				showSalesAnalysisReport();
			}
		} catch (Exception e) {
			POSMessageDialog.showError(this, com.pinpos.POSConstants.ERROR_MESSAGE, e);
		}
	}//GEN-LAST:event_showReport

	private void showSalesAnalysisReport() throws Exception {
		SalesSummaryDAO dao = new SalesSummaryDAO();
		List<SalesAnalysisData> datas = dao.findSalesAnalysis(fromDate, toDate, userType, terminal);

		Map properties = new HashMap();
		ReportUtil.populateRestaurantProperties(properties);
		properties.put("subtitle", com.pinpos.POSConstants.SALES_SUMMARY_REPORT);
		properties.put("reportTime", fullDateFormatter.format(new Date()));
		properties.put("fromDate", shortDateFormatter.format(fromDate));
		properties.put("toDate", shortDateFormatter.format(toDate));
		if (userType == null) {
			properties.put("reportType", com.pinpos.POSConstants.SYSTEM_TOTAL);
		}
		else {
			properties.put("reportType", userType.getName());
		}
		properties.put("shift", com.pinpos.POSConstants.ALL);
		properties.put("centre", terminal == null ? com.pinpos.POSConstants.ALL : terminal.getName());
		properties.put("days", String.valueOf(dateDiff));

		JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("/com/pinpos/ui/report/sales_summary_report2.jasper"));
		JasperPrint print = JasperFillManager.fillReport(report, properties, new JRTableModelDataSource(new SalesAnalysisReportModel(datas)));
		openReport(print);
	}

	private void showKeyStatisticsReport() throws Exception {

		SalesSummaryDAO dao = new SalesSummaryDAO();
		SalesStatistics summary = dao.findKeyStatistics(fromDate, toDate, userType, terminal);

		Map properties = new HashMap();
		ReportUtil.populateRestaurantProperties(properties);
		properties.put("subtitle", com.pinpos.POSConstants.SALES_SUMMARY_REPORT);
		properties.put("Capacity", String.valueOf(summary.getCapacity()));
		properties.put("GuestCount", String.valueOf(Math.abs(summary.getGuestCount())));
		properties.put("GuestPerSeat", NumberUtil.formatNumber(summary.getGuestPerSeat()));
		properties.put("reportTime", fullDateFormatter.format(new Date()));
		properties.put("fromDate", shortDateFormatter.format(fromDate));
		properties.put("toDate", shortDateFormatter.format(toDate));
		if (userType == null) {
			properties.put("reportType", com.pinpos.POSConstants.SYSTEM_TOTAL);
		}
		else {
			properties.put("reportType", userType.getName());
		}
		properties.put("shift", com.pinpos.POSConstants.ALL);
		properties.put("centre", terminal == null ? com.pinpos.POSConstants.ALL : terminal.getName());
		properties.put("days", String.valueOf(dateDiff));

		properties.put("Capacity", String.valueOf(summary.getCapacity()));
		properties.put("GuestCount", String.valueOf(Math.abs(summary.getGuestCount())));
		properties.put("GuestPerSeat", NumberUtil.formatNumber(summary.getGuestPerCheck()));
		properties.put("TableTrnOvr", NumberUtil.formatNumber(summary.getTableTurnOver()));
		properties.put("AVGGuest", NumberUtil.formatNumber(summary.getAvgGuest()));
		properties.put("OpenChecks", String.valueOf(summary.getOpenChecks()));
		properties.put("VOIDChecks", String.valueOf(summary.getVoidChecks()));
		properties.put("OPPDChecks", String.valueOf(" "));
		properties.put("TRNGChecks", String.valueOf(" "));
		properties.put("ROPNChecks", String.valueOf(summary.getRopnChecks()));
		properties.put("MergeChecks", String.valueOf(" "));
		properties.put("LaborHour", NumberUtil.formatNumber(summary.getLaborHour()));
		properties.put("LaborSales", NumberUtil.formatNumber(summary.getGrossSale()));
		properties.put("Tables", String.valueOf(summary.getTables()));
		properties.put("CheckCount", String.valueOf(summary.getCheckCount()));
		properties.put("GuestPerChecks", NumberUtil.formatNumber(summary.getGuestPerCheck()));
		properties.put("TrnOvrTime", String.valueOf(" "));
		properties.put("AVGChecks", NumberUtil.formatNumber(summary.getAvgCheck()));
		properties.put("OPENAmount", NumberUtil.formatNumber(summary.getOpenAmount()));
		properties.put("VOIDAmount", NumberUtil.formatNumber(summary.getVoidAmount()));
		properties.put("PAIDChecks", String.valueOf(summary.getPaidChecks()));
		properties.put("TRNGAmount", String.valueOf(" "));
		properties.put("ROPNAmount", NumberUtil.formatNumber(summary.getRopnAmount()));
		properties.put("NTaxChecks", String.valueOf(summary.getNtaxChecks()));
		properties.put("NTaxAmount", NumberUtil.formatNumber(summary.getNtaxAmount()));
		properties.put("MergeAmount", String.valueOf(" "));
		properties.put("Labor", NumberUtil.formatNumber(summary.getLaborCost()));
		properties.put("LaborCost", NumberUtil.formatNumber((summary.getLaborCost() / summary.getGrossSale()) * 100));

		JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("/com/pinpos/ui/report/sales_summary_report1.jasper"));
		JasperPrint print = JasperFillManager.fillReport(report, properties, new JRTableModelDataSource(new ShiftwiseDataTableModel(summary.getSalesTableDataList())));
		openReport(print);

	}

	private void openReport(JasperPrint print) {
		JRViewer viewer = new JRViewer(print);
		reportPanel.removeAll();
		reportPanel.add(viewer);
		reportPanel.revalidate();
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton btnGo;
	private javax.swing.JComboBox cbTerminal;
	private javax.swing.JComboBox cbUserType;
	private org.jdesktop.swingx.JXDatePicker fromDatePicker;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JSeparator jSeparator1;
	private javax.swing.JPanel reportPanel;
	private org.jdesktop.swingx.JXDatePicker toDatePicker;
	// End of variables declaration//GEN-END:variables
	private SimpleDateFormat fullDateFormatter = new SimpleDateFormat("dd MMM yyyy, hh:mm a");
	private SimpleDateFormat shortDateFormatter = new SimpleDateFormat("dd MMM yyyy");
	
	private Date fromDate;
	private Date toDate;
	private int dateDiff;
	private UserType userType;
	private Terminal terminal;

	public int getReportType() {
		return reportType;
	}

	public void setReportType(int reportType) {
		this.reportType = reportType;
	}

}
