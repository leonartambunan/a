package com.pinpos.ui.views.order;

import com.pinpos.extension.OrderServiceExtension;
import com.pinpos.main.Application;
import com.pinpos.model.Ticket;
import com.pinpos.model.dao.TicketDAO;
import com.pinpos.util.PosGuiUtil;
import com.pinpos.util.TicketAlreadyExistsException;

import java.util.Calendar;

public class DefaultOrderServiceExtension implements OrderServiceExtension {

	@Override
	public String getName() {
		return "Order Handler";
	}

	@Override
	public String getDescription() {
		return "Default order handler";
	}
	
	@Override
	public void init() {
	}

	@Override
	public void createNewTicket(String ticketType) throws TicketAlreadyExistsException {
		int tableNumber = PosGuiUtil.captureTableNumber();
		if (tableNumber == -1) {
			return;
		}

		TicketDAO dao = TicketDAO.getInstance();

		Ticket ticket = dao.findTicketByTableNumber(tableNumber);
		if (ticket != null) {
			throw new TicketAlreadyExistsException(ticket);
		}

		int numberOfGuests = 1;

		Application application = Application.getInstance();

		ticket = new Ticket();
		ticket.setTicketType(ticketType);
		ticket.setTableNumber(tableNumber);
		ticket.setNumberOfGuests(numberOfGuests);
		ticket.setTerminal(application.getTerminal());
		ticket.setOwner(Application.getCurrentUser());
		ticket.setShift(application.getCurrentShift());

		Calendar currentTime = Calendar.getInstance();
		ticket.setCreateDate(currentTime.getTime());
		ticket.setCreationHour(currentTime.get(Calendar.HOUR_OF_DAY));

		OrderView.getInstance().setCurrentTicket(ticket);
		RootView.getInstance().showView(OrderView.VIEW_NAME);
	}

	@Override
	public void setCustomerToTicket(int ticketId) {
	}
	
	public void setDeliveryDate(int ticketId) {}

	@Override
	public void assignDriver(int ticketId) {
		
	};
	
	@Override
	public boolean finishOrder(int ticketId) {
		return false;
	}
}
