package com.pinpos.report;

import com.pinpos.main.Application;
import com.pinpos.model.PayOutTransaction;
import com.pinpos.model.Terminal;
import com.pinpos.model.dao.PayOutTransactionDAO;
import org.jdesktop.swingx.calendar.DateUtils;

import java.util.Date;
import java.util.List;

public class PayoutReportModelFactory {
	private Date startDate;
	private Date endDate;
	private boolean settled = true;

	private PayoutReportModel itemReportModel;

	public PayoutReportModelFactory() {
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

        PayOutTransactionDAO dao = new PayOutTransactionDAO();

        Terminal terminal = Application.getInstance().getTerminal();
        List<PayOutTransaction> list = dao.findPayouts(startDate, endDate);

		itemReportModel = new PayoutReportModel();
		itemReportModel.setItems(list);
	}



//	public static void main(String[] args) throws Exception {
//		PayoutReportModelFactory factory = new PayoutReportModelFactory();
//		factory.createModels();
//
//        PayoutReportModel itemReportModel = factory.getItemReportModel();
//
//		JasperReport itemReport = (JasperReport) JRLoader.loadObject(PayoutReportModelFactory.class.getResource("/com/pinpos/ui/report/SalesSubReport.jasper"));
//
//		HashMap map = new HashMap();
//		map.put("itemDataSource", new JRTableModelDataSource(itemReportModel));
//		map.put("currencySymbol", Application.getCurrencySymbol());
//		map.put("itemGrandTotal", itemReportModel.getGrandTotalAsString());
//		map.put("itemReport", itemReport);
//
//		JasperReport masterReport = (JasperReport) JRLoader.loadObject(PayoutReportModelFactory.class.getResource("/com/pinpos/ui/report/SalesReport.jasper"));
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

	public PayoutReportModel getItemReportModel() {
		return itemReportModel;
	}

}
