package com.pinpos.model;

public interface ITicketItem {
	boolean canAddCookingInstruction();
	Boolean isPrintedToKitchen();
	
	String getNameDisplay();

	Double getUnitPriceDisplay();

	Integer getItemCountDisplay();

	Double getTaxAmountWithoutModifiersDisplay();

	Double getTotalAmountWithoutModifiersDisplay();
}
