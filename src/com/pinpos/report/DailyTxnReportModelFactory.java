package com.pinpos.report;

import com.pinpos.model.Ticket;
import com.pinpos.model.dao.TicketDAO;
import org.jdesktop.swingx.calendar.DateUtils;

import java.util.Date;
import java.util.List;

public class DailyTxnReportModelFactory {
	private Date startDate;
	private Date endDate;
	private boolean settled = true;

	private DailyTxnReportModel itemReportModel;

	public DailyTxnReportModelFactory() {
		super();
	}
	
	public void createModels() {
		Date currentDate = new Date();
		if(startDate == null) {
			startDate = DateUtils.startOfDay(currentDate);
		}
		if(endDate == null) {
			endDate = DateUtils.endOfDay(currentDate);
		}
		
		List<Ticket> tickets = TicketDAO.getInstance().findTickets(startDate, endDate, settled);
		
		itemReportModel = new DailyTxnReportModel();
		itemReportModel.setItems(tickets);
	}
		
	
	
//	public static void main(String[] args) throws Exception {
//		DailyTxnReportModelFactory factory = new DailyTxnReportModelFactory();
//		factory.createModels();
//
//        DailyTxnReportModel itemReportModel = factory.getItemReportModel();
//
//		JasperReport itemReport = (JasperReport) JRLoader.loadObject(DailyTxnReportModelFactory.class.getResource("/com/pinpos/ui/report/SalesSubReport.jasper"));
//
//		HashMap map = new HashMap();
//		map.put("itemDataSource", new  JRTableModelDataSource(itemReportModel));
//		map.put("currencySymbol", Application.getCurrencySymbol());
//		map.put("itemGrandTotal", itemReportModel.getGrandTotalAsString());
//		map.put("itemReport", itemReport);
//
//		JasperReport masterReport = (JasperReport) JRLoader.loadObject(DailyTxnReportModelFactory.class.getResource("/com/pinpos/ui/report/SalesReport.jasper"));
//
//		JasperPrint print = JasperFillManager.fillReport(masterReport, map, new JREmptyDataSource());
//		JasperViewer.viewReport(print, false);
//	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public boolean isSettled() {
		return settled;
	}

	public void setSettled(boolean settled) {
		this.settled = settled;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public DailyTxnReportModel getItemReportModel() {
		return itemReportModel;
	}

}
