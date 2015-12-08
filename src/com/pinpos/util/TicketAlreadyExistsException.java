package com.pinpos.util;

import com.pinpos.model.Ticket;

public class TicketAlreadyExistsException extends Exception {
	private Ticket ticket;
	
	public TicketAlreadyExistsException(Ticket ticket) {
		this.ticket = ticket;
	}

	public Ticket getTicket() {
		return ticket;
	}

}
