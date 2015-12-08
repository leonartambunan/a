package com.pinpos.services;

import com.pinpos.POSConstants;
import com.pinpos.PosException;
import com.pinpos.model.Ticket;
import com.pinpos.model.dao.TicketDAO;

public class TicketService {
	public static Ticket getTicket(int ticketId) {
		TicketDAO dao = new TicketDAO();
		Ticket ticket = dao.get(ticketId);

		if (ticket == null) {
			throw new PosException(POSConstants.NO_TICKET_WITH_ID + " " + ticketId + " " + POSConstants.FOUND);
		}
		
		return ticket;
	}
	
	public static void refundTicket(Ticket ticket) throws Exception {
		PosTransactionService service = PosTransactionService.getInstance();
		service.refundTicket(ticket);
		ticket.setDrawerResetted(false);
	}
}
