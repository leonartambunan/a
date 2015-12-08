package com.pinpos.report;

import com.pinpos.model.ITicketItem;
import com.pinpos.model.Ticket;
import com.pinpos.ui.ticket.TicketItemRowCreator;
import com.pinpos.util.NumberUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class TicketDataSource extends AbstractReportDataSource {
	
	boolean includeModifiers = true;
	boolean includeCookingInstructions = true;
	boolean kitchenPrint = true;
	
	public TicketDataSource() {
		super(new String[] { "itemName", "itemQty", "itemSubtotal" });
	}

	public TicketDataSource(Ticket ticket) {
		super(new String[] { "itemName", "itemQty", "itemSubtotal" });

		setTicket(ticket);
	}
	
	public TicketDataSource(Ticket ticket, boolean kitchenPrint, boolean includeModifiers, boolean includeCookingInstructions) {
		super(new String[] { "itemName", "itemQty", "itemSubtotal" });
		
		this.kitchenPrint = kitchenPrint;
		this.includeModifiers = includeModifiers;
		this.includeCookingInstructions = includeCookingInstructions;
		
		setTicket(ticket);
		
	}

	private void setTicket(Ticket ticket) {
		ArrayList<ITicketItem> rows = new ArrayList<ITicketItem>();

		LinkedHashMap<String, ITicketItem> tableRows = new LinkedHashMap<String, ITicketItem>();
		TicketItemRowCreator.calculateTicketRows(ticket, tableRows, kitchenPrint, includeModifiers, includeCookingInstructions);

		rows.addAll(tableRows.values());
		setRows(rows);
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		ITicketItem item = (ITicketItem) rows.get(rowIndex);

		switch (columnIndex) {
			case 0:
				return item.getNameDisplay();

			case 1:
				Integer itemCountDisplay = item.getItemCountDisplay();
				
				if(itemCountDisplay == null) {
					return null;
				}
				
				return String.valueOf(itemCountDisplay);

			case 2:
				Double total = item.getTotalAmountWithoutModifiersDisplay();
				if(total == null) {
					return null;
				}

                return NumberUtil.formatToCurrency(total);
		}

		return null;
	}
}
