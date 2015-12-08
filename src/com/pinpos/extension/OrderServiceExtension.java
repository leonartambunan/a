package com.pinpos.extension;

import net.xeoh.plugins.base.Plugin;

import com.pinpos.util.TicketAlreadyExistsException;

public interface OrderServiceExtension extends Plugin {
	String getName();
	String getDescription();
	
	void init();
	void createNewTicket(String ticketType) throws TicketAlreadyExistsException;
	void setCustomerToTicket(int ticketId);
	void setDeliveryDate(int ticketId);
	void assignDriver(int ticketId);
	boolean finishOrder(int ticketId);
}
