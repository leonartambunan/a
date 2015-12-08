package com.pinpos.ui.report;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JRViewer;

import org.jdesktop.swingx.JXDatePicker;

import com.pinpos.POSConstants;
import com.pinpos.bo.ui.BackOfficeWindow;
import com.pinpos.model.util.DateUtil;
import com.pinpos.report.MenuUsageReport;
import com.pinpos.report.service.ReportService;
import com.pinpos.ui.dialog.POSMessageDialog;
import com.pinpos.ui.util.UiUtil;

public class MenuUsageReportView extends JPanel {
	private JXDatePicker fromDatePicker = UiUtil.getCurrentMonthStart();
	private JXDatePicker toDatePicker = UiUtil.getCurrentMonthEnd();
	private JButton btnGo = new JButton(com.pinpos.POSConstants.GO);
	private JPanel reportContainer;
	
	public MenuUsageReportView() {
		super(new BorderLayout());
		
		JPanel topPanel = new JPanel(new MigLayout());
		
		topPanel.add(new JLabel(com.pinpos.POSConstants.FROM + ":"), "grow");
//		topPanel.add(fromDatePicker,"wrap");
		topPanel.add(fromDatePicker);
		topPanel.add(new JLabel(com.pinpos.POSConstants.TO + ":"), "grow");
//		topPanel.add(toDatePicker,"wrap");
		topPanel.add(toDatePicker);
//		topPanel.add(btnGo, "skip 1, al right");
		topPanel.add(new JLabel("  "));
		topPanel.add(btnGo);
		add(topPanel, BorderLayout.NORTH);
		
		JPanel centerPanel = new JPanel(new BorderLayout());
		centerPanel.setBorder(new EmptyBorder(0, 10,10,10));
		centerPanel.add(new JSeparator(), BorderLayout.NORTH);
		
		reportContainer = new JPanel(new BorderLayout());
		centerPanel.add(reportContainer);
		
		add(centerPanel);
		
		btnGo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					viewReport();
				} catch (Exception e1) {
					POSMessageDialog.showError(MenuUsageReportView.this, POSConstants.ERROR_MESSAGE, e1);
				}
			}
			
		});
	}
	
	private void viewReport() throws Exception {
		Date fromDate = fromDatePicker.getDate();
		Date toDate = toDatePicker.getDate();
		
		if(fromDate.after(toDate)) {
			POSMessageDialog.showError(BackOfficeWindow.getInstance(), com.pinpos.POSConstants.FROM_DATE_CANNOT_BE_GREATER_THAN_TO_DATE_);
			return;
		}
		
		fromDate = DateUtil.startOfDay(fromDate);
		toDate = DateUtil.endOfDay(toDate);
		
		ReportService reportService = new ReportService();
		MenuUsageReport report = reportService.getMenuUsageReport(fromDate, toDate);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("reportTitle", "========= LAPORAN PENJUALAN PER MENU ==========");
		map.put("fromDate", ReportService.formatShortDate(fromDate));
		map.put("toDate", ReportService.formatShortDate(toDate));
		map.put("reportTime", ReportService.formatFullDate(new Date()));
		
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(getClass().getResource("/com/pinpos/ui/report/menu_usage_report.jasper"));
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, new JRTableModelDataSource(report.getTableModel()));
		JRViewer viewer = new JRViewer(jasperPrint);
		reportContainer.removeAll();
		reportContainer.add(viewer);
		reportContainer.revalidate();
		
	}
}
