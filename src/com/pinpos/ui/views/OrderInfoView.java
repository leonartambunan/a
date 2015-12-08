package com.pinpos.ui.views;

import com.pinpos.config.PrintConfig;
import com.pinpos.model.Ticket;
import com.pinpos.report.JReportPrintService;
import com.pinpos.report.TicketPrintProperties;
import net.miginfocom.swing.MigLayout;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.List;

public class OrderInfoView extends JPanel {
	private List<Ticket> tickets;

	public OrderInfoView(List<Ticket> tickets) throws Exception {
		super();
		this.tickets = tickets;
		
		createUI();
	}
	
	private void createUI() throws Exception {
		JPanel reportPanel = new JPanel(new MigLayout("wrap 1, ax 50%", "", ""));
		JScrollPane scrollPane = new JScrollPane(reportPanel);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);

		for (int i = 0; i < tickets.size(); i++) {
			Ticket ticket = tickets.get(i);
			
			TicketPrintProperties printProperties = new TicketPrintProperties("*** ORDER " + ticket.getId() + " ***", false, true, true);
			JasperPrint jasperPrint = JReportPrintService.createPrint(ticket, printProperties);

			TicketReceiptView receiptView = new TicketReceiptView(jasperPrint);
			reportPanel.add(receiptView.getReportPanel());
		}
		
		setLayout(new BorderLayout());
		add(scrollPane);
	}

	public void print() throws Exception {
		for (Iterator iter = tickets.iterator(); iter.hasNext();) {
			Ticket ticket = (Ticket) iter.next();
			
			TicketPrintProperties printProperties = new TicketPrintProperties("*** ORDER " + ticket.getId() + " ***", false, true, true);
			JasperPrint jasperPrint = JReportPrintService.createPrint(ticket, printProperties);
			jasperPrint.setProperty("printerName", PrintConfig.getReceiptPrinterName());
			JasperPrintManager.printReport(jasperPrint, false);
		}
	}
}
