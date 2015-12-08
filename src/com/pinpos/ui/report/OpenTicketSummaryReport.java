package com.pinpos.ui.report;

import com.pinpos.model.Ticket;
import com.pinpos.model.dao.TicketDAO;
import com.pinpos.report.TicketReportModel;
import com.pinpos.report.service.ReportService;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JRViewer;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class OpenTicketSummaryReport extends Report {

	public OpenTicketSummaryReport() {
		super();
	}

	@Override
	public void refresh() throws Exception {

		List<Ticket> tickets = TicketDAO.getInstance().findOpenTickets();
		TicketReportModel reportModel = new TicketReportModel();
		reportModel.setItems(tickets);
		
		HashMap map = new HashMap();
		ReportUtil.populateRestaurantProperties(map);
		map.put("reportTitle", "============================ Tiket Aktif =============================");
		map.put("reportTime", ReportService.formatFullDate(new Date()));
		//map.put("dateRange", Application.formatDate(date1) + " to " + Application.formatDate(date2));
		map.put("terminalName", com.pinpos.POSConstants.ALL);
		
		JasperReport masterReport = (JasperReport) JRLoader.loadObject(OpenTicketSummaryReport.class.getResource("/com/pinpos/ui/report/open_ticket_summary_report.jasper"));
		JasperPrint print = JasperFillManager.fillReport(masterReport, map, new JRTableModelDataSource(reportModel));
		viewer = new JRViewer(print);
	}

	@Override
	public boolean isDateRangeSupported() {
		return false;
	}

	@Override
	public boolean isTypeSupported() {
		return false;
	}

}
