package com.pinpos.ui.report;

import com.pinpos.Messages;
import com.pinpos.main.Application;
import com.pinpos.model.PayOutTransaction;
import com.pinpos.model.dao.PayOutTransactionDAO;
import com.pinpos.report.DailyTxnReportModelFactory;
import com.pinpos.report.PayoutReportModel;
import com.pinpos.report.service.ReportService;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JRViewer;
import org.jdesktop.swingx.calendar.DateUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class PayoutReport extends Report {

	private PayoutReportModel itemReportModel;

	public PayoutReport() {
		super();
	}

	@Override
	public void refresh() throws Exception {
		createModels();

        PayoutReportModel itemReportModel = this.itemReportModel;

		JasperReport itemReport = (JasperReport) JRLoader.loadObject(DailyTxnReportModelFactory.class.getResource("/com/pinpos/ui/report/PayOutDetail.jasper"));
//		JasperReport modifierReport = (JasperReport) JRLoader.loadObject(DailyTxnReportModelFactory.class.getResource("/com/pinpos/ui/report/sales_sub_report.jasper"));

		HashMap map = new HashMap();
		ReportUtil.populateRestaurantProperties(map);
		map.put("reportTitle", Messages.getString("PayOutReport"));
		map.put("reportTime", ReportService.formatFullDate(new Date()));
		map.put("dateRange", ReportService.formatShortDate(getStartDate()) + " - " + ReportService.formatShortDate(getEndDate()));
		map.put("terminalName", com.pinpos.POSConstants.ALL);
		map.put("itemDataSource", new JRTableModelDataSource(itemReportModel));
		map.put("currencySymbol", Application.getCurrencySymbol());
		map.put("itemGrandTotal", itemReportModel.getGrandTotalAsString());
		map.put("itemReport", itemReport);

		JasperReport masterReport = (JasperReport) JRLoader.loadObject(PayoutReport.class.getResource("/com/pinpos/ui/report/PayOut.jasper"));

		JasperPrint print = JasperFillManager.fillReport(masterReport, map, new JREmptyDataSource());
		viewer = new JRViewer(print);
	}

	@Override
	public boolean isDateRangeSupported() {
		return true;
	}

	@Override
	public boolean isTypeSupported() {
		return true;
	}

	public void createModels() {
		Date date1 = DateUtils.startOfDay(getStartDate());
		Date date2 = DateUtils.endOfDay(getEndDate());

		List<PayOutTransaction> txns = PayOutTransactionDAO.getInstance().findPayouts(date1, date2);

        itemReportModel = new PayoutReportModel();
		itemReportModel.setItems(txns);
        itemReportModel.calculateGrandTotal();
	}
}
