package com.pinpos.report;

import com.pinpos.main.Application;
import com.pinpos.model.PayOutTransaction;
import com.pinpos.util.NumberUtil;

import javax.swing.table.AbstractTableModel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class PayoutReportModel extends AbstractTableModel {

    private DateFormat sdf = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");

	private String currencySymbol;

	private String[] columnNames = {"ID", "TIME", "USER","AMOUNT","RECIPIENT","REASON","NOTE"};
	private List<PayOutTransaction> items;
	private double grandTotal;

	public PayoutReportModel() {
		super();
		currencySymbol = Application.getCurrencySymbol();
	}

	public int getRowCount() {
		if(items == null) {
			return 0;
		}
		
		return items.size();
	}

	public int getColumnCount() {
		return columnNames.length;
	}
	
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
        PayOutTransaction item = items.get(rowIndex);
		
		switch(columnIndex) {
			case 0:
				return String.valueOf(item.getId());

            case 1:
                return sdf.format(item.getTransactionTime());

            case 2:
                return item.getUser().getFirstName() + " " + item.getUser().getLastName();

			case 3:
                return " " + NumberUtil.formatToCurrency(item.getTotalAmount());

            case 4:
                return (item.getRecepient()==null?"":item.getRecepient().getName());

            case 5:
                return (item.getReason()==null?"":item.getReason().getReason());
            case 6:
                return (item.getNote()==null?"":item.getNote());
		}

		return null;
	}

	public List<PayOutTransaction> getItems() {
		return items;
	}

	public void setItems(List<PayOutTransaction> items) {
		this.items = items;
	}

	public double getGrandTotal() {
		return grandTotal;
	}

	public String getGrandTotalAsString() {
		return currencySymbol + " " + NumberUtil.formatToCurrency(grandTotal);
	}

	public void setGrandTotal(double grandTotal) {
		this.grandTotal = grandTotal;
	}

    public void calculateGrandTotal() {
        grandTotal = 0;
        if(items == null) {
            return;
        }

        for (PayOutTransaction item : items) {
            grandTotal += item.getTotalAmount();
        }
    }
}
