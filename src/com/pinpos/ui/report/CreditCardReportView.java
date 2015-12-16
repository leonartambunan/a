package com.pinpos.ui.report;

import com.pinpos.Messages;
import com.pinpos.POSConstants;
import com.pinpos.bo.ui.BackOfficeWindow;
import com.pinpos.model.util.DateUtil;
import com.pinpos.report.CreditCardReport;
import com.pinpos.report.service.ReportService;
import com.pinpos.ui.dialog.POSMessageDialog;
import com.pinpos.ui.util.UiUtil;
import com.pinpos.util.NumberUtil;
import net.miginfocom.swing.MigLayout;
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
import java.util.Date;
import java.util.HashMap;

public class CreditCardReportView extends JPanel {
	private JXDatePicker fromDatePicker = UiUtil.getCurrentDate();
	private JXDatePicker toDatePicker = UiUtil.getCurrentDate();
	private JButton btnGo = new JButton(com.pinpos.POSConstants.GO);
	private JPanel reportContainer;
	
	public CreditCardReportView() {
		super(new BorderLayout());
		
		JPanel topPanel = new JPanel(new MigLayout());
		
		topPanel.add(new JLabel(com.pinpos.POSConstants.FROM + ":"), "grow");
		topPanel.add(fromDatePicker);
		topPanel.add(new JLabel(com.pinpos.POSConstants.TO + ":"), "grow");
		topPanel.add(toDatePicker);
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
					POSMessageDialog.showError(CreditCardReportView.this, POSConstants.ERROR_MESSAGE, e1);
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
		CreditCardReport report = reportService.getCreditCardReport(fromDate, toDate);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		ReportUtil.populateRestaurantProperties(map);
		map.put("reportTitle", "========= "+ Messages.getString("PosMessage.142").toUpperCase()+" ==========");
		map.put("fromDate", ReportService.formatShortDate(fromDate));
		map.put("toDate", ReportService.formatShortDate(toDate));
		map.put("reportTime", ReportService.formatFullDate(new Date()));
		
		map.put("salesCount", String.valueOf(report.getTotalSalesCount()));
		map.put("totalSales", NumberUtil.formatNumber(report.getTotalSales()));
		map.put("netTips", NumberUtil.formatNumber(report.getNetTips()));
		map.put("netTipsPaid", NumberUtil.formatNumber(report.getTipsPaid()));
		map.put("netCharge", NumberUtil.formatNumber(report.getNetCharge()));
		
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(getClass().getResource("/com/pinpos/ui/report/credit_card_report.jasper"));
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, new JRTableModelDataSource(report.getTableModel()));
		JRViewer viewer = new JRViewer(jasperPrint);
		reportContainer.removeAll();
		reportContainer.add(viewer);
		reportContainer.revalidate();
		
	}

//	public static void main(String[] args) {
//		PanelTester.width = 800;
//		PanelTester.height = 500;
//		PanelTester.test(new CreditCardReportView());
//	}
}
