package com.pinpos.ui.report;

import com.pinpos.POSConstants;
import com.pinpos.bo.ui.BackOfficeWindow;
import com.pinpos.model.util.DateUtil;
import com.pinpos.report.SalesExceptionReport;
import com.pinpos.report.SalesReportModelFactory;
import com.pinpos.report.service.ReportService;
import com.pinpos.ui.dialog.POSMessageDialog;
import com.pinpos.ui.util.UiUtil;
import net.miginfocom.swing.MigLayout;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JRViewer;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class SalesExceptionReportView extends JPanel {
	private SimpleDateFormat fullDateFormatter = new SimpleDateFormat("dd MMM yyyy, hh:mm a");
	private SimpleDateFormat shortDateFormatter = new SimpleDateFormat("dd MMM yyyy");
	
	private JXDatePicker fromDatePicker = UiUtil.getCurrentDate();
	private JXDatePicker toDatePicker = UiUtil.getCurrentDate();
	private JButton btnGo = new JButton(com.pinpos.POSConstants.GO);
	private JPanel reportContainer;
	
	public SalesExceptionReportView() {
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
					POSMessageDialog.showError(SalesExceptionReportView.this, POSConstants.ERROR_MESSAGE, e1);
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
		SalesExceptionReport report = reportService.getSalesExceptionReport(fromDate, toDate);
		
		JasperReport voidReport = (JasperReport) JRLoader.loadObject(SalesReportModelFactory.class.getResource("/com/pinpos/ui/report/sales_summary_exception_voids.jasper"));
		JasperReport discountReport = (JasperReport) JRLoader.loadObject(SalesReportModelFactory.class.getResource("/com/pinpos/ui/report/sales_summary_exception_discounts.jasper"));
		
		HashMap map = new HashMap();
		ReportUtil.populateRestaurantProperties(map);
		map.put("fromDate", shortDateFormatter.format(fromDate));
		map.put("toDate", shortDateFormatter.format(toDate));
		map.put("reportTime", fullDateFormatter.format(new Date()));
		map.put("voidReport", voidReport);
		map.put("voidReportDataSource", new JRTableModelDataSource(report.getVoidTableModel()));
		map.put("discountReport", discountReport);
		map.put("discountReportDataSource", new JRTableModelDataSource(report.getDiscountTableModel()));
		
		
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(getClass().getResource("/com/pinpos/ui/report/sales_summary_exception.jasper"));
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, new JREmptyDataSource());
		JRViewer viewer = new JRViewer(jasperPrint);
		reportContainer.removeAll();
		reportContainer.add(viewer);
		reportContainer.revalidate();
		
	}
}
