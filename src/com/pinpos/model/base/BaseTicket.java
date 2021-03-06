package com.pinpos.model.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the TICKET table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="TICKET"
 */

public abstract class BaseTicket  implements Comparable, Serializable {

	public static String REF = "Ticket";
	public static String PROP_RE_OPENED = "reOpened";
	public static String PROP_BAR_CODE = "barCode";
	public static String PROP_VOID_REASON = "voidReason";
	public static String PROP_DUE_AMOUNT = "dueAmount";
	public static String PROP_TRANSACTION_TYPE = "transactionType";
	public static String PROP_DISCOUNT_AMOUNT = "discountAmount";
	public static String PROP_CREATE_DATE = "createDate";
	public static String PROP_DELIVERY_CHARGE = "deliveryCharge";
	public static String PROP_NUMBER_OF_GUESTS = "numberOfGuests";
	public static String PROP_PAID = "paid";
	public static String PROP_ACTIVE_DATE = "activeDate";
	public static String PROP_CARD_TYPE = "cardType";
	public static String PROP_ASSIGNED_DRIVER = "assignedDriver";
	public static String PROP_CREATION_HOUR = "creationHour";
	public static String PROP_CUSTOMER_WILL_PICKUP = "customerWillPickup";
	public static String PROP_DRAWER_RESETTED = "drawerResetted";
	public static String PROP_CUSTOMER = "customer";
	public static String PROP_CARD_NUMBER = "cardNumber";
	public static String PROP_OWNER = "owner";
	public static String PROP_DELIVERY_DATE = "deliveryDate";
	public static String PROP_GRATUITY = "gratuity";
	public static String PROP_TABLE_NUMBER = "tableNumber";
	public static String PROP_TERMINAL = "terminal";
	public static String PROP_CLOSED = "closed";
	public static String PROP_CLOSING_DATE = "closingDate";
	public static String PROP_TRANSACTION_CODE = "transactionCode";
	public static String PROP_DELIVERY_ADDRESS = "deliveryAddress";
	public static String PROP_SHIFT = "shift";
	public static String PROP_TAX_AMOUNT = "taxAmount";
	public static String PROP_TENDERED_AMOUNT = "tenderedAmount";
	public static String PROP_SUBTOTAL_AMOUNT = "subtotalAmount";
	public static String PROP_VOIDED_BY = "voidedBy";
	public static String PROP_TICKET_TYPE = "ticketType";
	public static String PROP_TAX_EXEMPT = "taxExempt";
	public static String PROP_ID = "id";
	public static String PROP_WASTED = "wasted";
	public static String PROP_VOIDED = "voided";
	public static String PROP_TOTAL_AMOUNT = "totalAmount";
	public static String PROP_PAID_AMOUNT = "paidAmount";
	public static String PROP_EXTRA_DELIVERY_INFO = "extraDeliveryInfo";
	public static String PROP_SERVICE_CHARGE = "serviceCharge";


	// constructors
	public BaseTicket () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseTicket (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	 java.util.Date modifiedTime;

	// fields
	private java.util.Date createDate;
	private java.util.Date closingDate;
	private java.util.Date activeDate;
	private java.util.Date deliveryDate;
	private java.lang.Integer creationHour;
	private java.lang.Boolean paid;
	private java.lang.Boolean voided;
	private java.lang.String voidReason;
	private java.lang.Boolean wasted;
	private java.lang.Boolean closed;
	private java.lang.Boolean drawerResetted;
	private java.lang.Double subtotalAmount;
	private java.lang.Double discountAmount;
	private java.lang.Double taxAmount;
	private java.lang.Double totalAmount;
	private java.lang.Double tenderedAmount;
	private java.lang.Double paidAmount;
	private java.lang.Double dueAmount;
	private java.lang.Integer tableNumber;
	private java.lang.Integer numberOfGuests;
	private java.lang.String transactionType;
	private java.lang.String transactionCode;
	private java.lang.String barCode;
	private java.lang.String cardType;
	private java.lang.String cardNumber;
	private java.lang.Boolean taxExempt;
	private java.lang.Boolean reOpened;
	private java.lang.Double serviceCharge;
	private java.lang.Double deliveryCharge;
	private java.lang.String deliveryAddress;
	private java.lang.Boolean customerWillPickup;
	private java.lang.String extraDeliveryInfo;
	private java.lang.String ticketType;

	// many to one
	private com.pinpos.model.Shift shift;
	private com.pinpos.model.User owner;
	private com.pinpos.model.User assignedDriver;
	private com.pinpos.model.Gratuity gratuity;
	private com.pinpos.model.User voidedBy;
	private com.pinpos.model.Terminal terminal;
	private com.pinpos.model.Customer customer;

	// collections
	private java.util.Map<String, Object> properties;
	private java.util.List<com.pinpos.model.TicketItem> ticketItems;
	private java.util.List<com.pinpos.model.TicketCouponAndDiscount> couponAndDiscounts;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="identity"
     *  column="ID"
     */
	public java.lang.Integer getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (java.lang.Integer id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}



	/**
	 * Return the value associated with the column: MODIFIED_TIME
	 */
	public java.util.Date getModifiedTime () {
		return modifiedTime;
	}

	/**
	 * Set the value related to the column: MODIFIED_TIME
	 * @param modifiedTime the MODIFIED_TIME value
	 */
	public void setModifiedTime (java.util.Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}




	/**
	 * Return the value associated with the column: CREATE_DATE
	 */
	public java.util.Date getCreateDate () {
		return createDate;
	}

	/**
	 * Set the value related to the column: CREATE_DATE
	 * @param createDate the CREATE_DATE value
	 */
	public void setCreateDate (java.util.Date createDate) {
		this.createDate = createDate;
	}



	/**
	 * Return the value associated with the column: CLOSING_DATE
	 */
	public java.util.Date getClosingDate () {
		return closingDate;
	}

	/**
	 * Set the value related to the column: CLOSING_DATE
	 * @param closingDate the CLOSING_DATE value
	 */
	public void setClosingDate (java.util.Date closingDate) {
		this.closingDate = closingDate;
	}



	/**
	 * Return the value associated with the column: ACTIVE_DATE
	 */
	public java.util.Date getActiveDate () {
		return activeDate;
	}

	/**
	 * Set the value related to the column: ACTIVE_DATE
	 * @param activeDate the ACTIVE_DATE value
	 */
	public void setActiveDate (java.util.Date activeDate) {
		this.activeDate = activeDate;
	}



	/**
	 * Return the value associated with the column: DELIVEERY_DATE
	 */
	public java.util.Date getDeliveryDate () {
		return deliveryDate;
	}

	/**
	 * Set the value related to the column: DELIVEERY_DATE
	 * @param deliveryDate the DELIVEERY_DATE value
	 */
	public void setDeliveryDate (java.util.Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}



	/**
	 * Return the value associated with the column: CREATION_HOUR
	 */
	public java.lang.Integer getCreationHour () {
		return creationHour;
	}

	/**
	 * Set the value related to the column: CREATION_HOUR
	 * @param creationHour the CREATION_HOUR value
	 */
	public void setCreationHour (java.lang.Integer creationHour) {
		this.creationHour = creationHour;
	}



	/**
	 * Return the value associated with the column: PAID
	 */
	public java.lang.Boolean isPaid () {
		return paid;
	}

	/**
	 * Set the value related to the column: PAID
	 * @param paid the PAID value
	 */
	public void setPaid (java.lang.Boolean paid) {
		this.paid = paid;
	}



	/**
	 * Return the value associated with the column: VOIDED
	 */
	public java.lang.Boolean isVoided () {
		return voided;
	}

	/**
	 * Set the value related to the column: VOIDED
	 * @param voided the VOIDED value
	 */
	public void setVoided (java.lang.Boolean voided) {
		this.voided = voided;
	}



	/**
	 * Return the value associated with the column: VOID_REASON
	 */
	public java.lang.String getVoidReason () {
		return voidReason;
	}

	/**
	 * Set the value related to the column: VOID_REASON
	 * @param voidReason the VOID_REASON value
	 */
	public void setVoidReason (java.lang.String voidReason) {
		this.voidReason = voidReason;
	}

	/**
	 * Return the value associated with the column: WASTED
	 */
	public java.lang.Boolean isWasted () {
		return wasted;
	}

	/**
	 * Set the value related to the column: WASTED
	 * @param wasted the WASTED value
	 */
	public void setWasted (java.lang.Boolean wasted) {
		this.wasted = wasted;
	}



	/**
	 * Return the value associated with the column: SETTLED
	 */
	public java.lang.Boolean isClosed () {
		return closed;
	}

	/**
	 * Set the value related to the column: SETTLED
	 * @param closed the SETTLED value
	 */
	public void setClosed (java.lang.Boolean closed) {
		this.closed = closed;
	}



	/**
	 * Return the value associated with the column: DRAWER_RESETTED
	 */
	public java.lang.Boolean isDrawerResetted () {
		return drawerResetted;
	}

	/**
	 * Set the value related to the column: DRAWER_RESETTED
	 * @param drawerResetted the DRAWER_RESETTED value
	 */
	public void setDrawerResetted (java.lang.Boolean drawerResetted) {
		this.drawerResetted = drawerResetted;
	}



	/**
	 * Return the value associated with the column: SUB_TOTAL
	 */
	public java.lang.Double getSubtotalAmount () {
		return subtotalAmount;
	}

	/**
	 * Set the value related to the column: SUB_TOTAL
	 * @param subtotalAmount the SUB_TOTAL value
	 */
	public void setSubtotalAmount (java.lang.Double subtotalAmount) {
		this.subtotalAmount = subtotalAmount;
	}



	/**
	 * Return the value associated with the column: TOTAL_DISCOUNT
	 */
	public java.lang.Double getDiscountAmount () {
		return discountAmount;
	}

	/**
	 * Set the value related to the column: TOTAL_DISCOUNT
	 * @param discountAmount the TOTAL_DISCOUNT value
	 */
	public void setDiscountAmount (java.lang.Double discountAmount) {
		this.discountAmount = discountAmount;
	}



	/**
	 * Return the value associated with the column: TOTAL_TAX
	 */
	public java.lang.Double getTaxAmount () {
		return taxAmount;
	}

	/**
	 * Set the value related to the column: TOTAL_TAX
	 * @param taxAmount the TOTAL_TAX value
	 */
	public void setTaxAmount (java.lang.Double taxAmount) {
		this.taxAmount = taxAmount;
	}



	/**
	 * Return the value associated with the column: TOTAL_PRICE
	 */
	public java.lang.Double getTotalAmount () {
		return totalAmount;
	}

	/**
	 * Set the value related to the column: TOTAL_PRICE
	 * @param totalAmount the TOTAL_PRICE value
	 */
	public void setTotalAmount (java.lang.Double totalAmount) {
		this.totalAmount = totalAmount;
	}



	/**
	 * Return the value associated with the column: TENDERED_AMOUNT
	 */
	public java.lang.Double getTenderedAmount () {
		return tenderedAmount;
	}

	/**
	 * Set the value related to the column: TENDERED_AMOUNT
	 * @param tenderedAmount the TENDERED_AMOUNT value
	 */
	public void setTenderedAmount (java.lang.Double tenderedAmount) {
		this.tenderedAmount = tenderedAmount;
	}



	/**
	 * Return the value associated with the column: PAID_AMOUNT
	 */
	public java.lang.Double getPaidAmount () {
		return paidAmount;
	}

	/**
	 * Set the value related to the column: PAID_AMOUNT
	 * @param paidAmount the PAID_AMOUNT value
	 */
	public void setPaidAmount (java.lang.Double paidAmount) {
		this.paidAmount = paidAmount;
	}



	/**
	 * Return the value associated with the column: DUE_AMOUNT
	 */
	public java.lang.Double getDueAmount () {
		return dueAmount;
	}

	/**
	 * Set the value related to the column: DUE_AMOUNT
	 * @param dueAmount the DUE_AMOUNT value
	 */
	public void setDueAmount (java.lang.Double dueAmount) {
		this.dueAmount = dueAmount;
	}



	/**
	 * Return the value associated with the column: TABLE_NUMBER
	 */
	public java.lang.Integer getTableNumber () {
		return tableNumber;
	}

	/**
	 * Set the value related to the column: TABLE_NUMBER
	 * @param tableNumber the TABLE_NUMBER value
	 */
	public void setTableNumber (java.lang.Integer tableNumber) {
		this.tableNumber = tableNumber;
	}



	/**
	 * Return the value associated with the column: NUMBER_OF_GUESTS
	 */
	public java.lang.Integer getNumberOfGuests () {
		return numberOfGuests;
	}

	/**
	 * Set the value related to the column: NUMBER_OF_GUESTS
	 * @param numberOfGuests the NUMBER_OF_GUESTS value
	 */
	public void setNumberOfGuests (java.lang.Integer numberOfGuests) {
		this.numberOfGuests = numberOfGuests;
	}



	/**
	 * Return the value associated with the column: TRANSACTION_TYPE
	 */
	public java.lang.String getTransactionType () {
		return transactionType;
	}

	/**
	 * Set the value related to the column: TRANSACTION_TYPE
	 * @param transactionType the TRANSACTION_TYPE value
	 */
	public void setTransactionType (java.lang.String transactionType) {
		this.transactionType = transactionType;
	}



	/**
	 * Return the value associated with the column: TRANSACTION_CODE
	 */
	public java.lang.String getTransactionCode () {
		return transactionCode;
	}

	/**
	 * Set the value related to the column: TRANSACTION_CODE
	 * @param transactionCode the TRANSACTION_CODE value
	 */
	public void setTransactionCode (java.lang.String transactionCode) {
		this.transactionCode = transactionCode;
	}



	/**
	 * Return the value associated with the column: BAR_CODE
	 */
	public java.lang.String getBarCode () {
		return barCode;
	}

	/**
	 * Set the value related to the column: BAR_CODE
	 * @param barCode the BAR_CODE value
	 */
	public void setBarCode (java.lang.String barCode) {
		this.barCode = barCode;
	}



	/**
	 * Return the value associated with the column: CARD_TYPE
	 */
	public java.lang.String getCardType () {
		return cardType;
	}

	/**
	 * Set the value related to the column: CARD_TYPE
	 * @param cardType the CARD_TYPE value
	 */
	public void setCardType (java.lang.String cardType) {
		this.cardType = cardType;
	}



	/**
	 * Return the value associated with the column: CARD_NUMBER
	 */
	public java.lang.String getCardNumber () {
		return cardNumber;
	}

	/**
	 * Set the value related to the column: CARD_NUMBER
	 * @param cardNumber the CARD_NUMBER value
	 */
	public void setCardNumber (java.lang.String cardNumber) {
		this.cardNumber = cardNumber;
	}



	/**
	 * Return the value associated with the column: IS_TAX_EXEMPT
	 */
	public java.lang.Boolean isTaxExempt () {
		return taxExempt;
	}

	/**
	 * Set the value related to the column: IS_TAX_EXEMPT
	 * @param taxExempt the IS_TAX_EXEMPT value
	 */
	public void setTaxExempt (java.lang.Boolean taxExempt) {
		this.taxExempt = taxExempt;
	}



	/**
	 * Return the value associated with the column: IS_RE_OPENED
	 */
	public java.lang.Boolean isReOpened () {
		return reOpened;
	}

	/**
	 * Set the value related to the column: IS_RE_OPENED
	 * @param reOpened the IS_RE_OPENED value
	 */
	public void setReOpened (java.lang.Boolean reOpened) {
		this.reOpened = reOpened;
	}



	/**
	 * Return the value associated with the column: SERVICE_CHARGE
	 */
	public java.lang.Double getServiceCharge () {
		return serviceCharge;
	}

	/**
	 * Set the value related to the column: SERVICE_CHARGE
	 * @param serviceCharge the SERVICE_CHARGE value
	 */
	public void setServiceCharge (java.lang.Double serviceCharge) {
		this.serviceCharge = serviceCharge;
	}



	/**
	 * Return the value associated with the column: DELIVERY_CHARGE
	 */
	public java.lang.Double getDeliveryCharge () {
		return deliveryCharge;
	}

	/**
	 * Set the value related to the column: DELIVERY_CHARGE
	 * @param deliveryCharge the DELIVERY_CHARGE value
	 */
	public void setDeliveryCharge (java.lang.Double deliveryCharge) {
		this.deliveryCharge = deliveryCharge;
	}



	/**
	 * Return the value associated with the column: DELIVERY_ADDRESS
	 */
	public java.lang.String getDeliveryAddress () {
		return deliveryAddress;
	}

	/**
	 * Set the value related to the column: DELIVERY_ADDRESS
	 * @param deliveryAddress the DELIVERY_ADDRESS value
	 */
	public void setDeliveryAddress (java.lang.String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}



	/**
	 * Return the value associated with the column: CUSTOMER_PICKEUP
	 */
	public java.lang.Boolean isCustomerWillPickup () {
		return customerWillPickup;
	}

	/**
	 * Set the value related to the column: CUSTOMER_PICKEUP
	 * @param customerWillPickup the CUSTOMER_PICKEUP value
	 */
	public void setCustomerWillPickup (java.lang.Boolean customerWillPickup) {
		this.customerWillPickup = customerWillPickup;
	}



	/**
	 * Return the value associated with the column: DELIVERY_EXTRA_INFO
	 */
	public java.lang.String getExtraDeliveryInfo () {
		return extraDeliveryInfo;
	}

	/**
	 * Set the value related to the column: DELIVERY_EXTRA_INFO
	 * @param extraDeliveryInfo the DELIVERY_EXTRA_INFO value
	 */
	public void setExtraDeliveryInfo (java.lang.String extraDeliveryInfo) {
		this.extraDeliveryInfo = extraDeliveryInfo;
	}



	/**
	 * Return the value associated with the column: TICKET_TYPE
	 */
	public java.lang.String getTicketType () {
		return ticketType;
	}

	/**
	 * Set the value related to the column: TICKET_TYPE
	 * @param ticketType the TICKET_TYPE value
	 */
	public void setTicketType (java.lang.String ticketType) {
		this.ticketType = ticketType;
	}



	/**
	 * Return the value associated with the column: SHIFT_ID
	 */
	public com.pinpos.model.Shift getShift () {
		return shift;
	}

	/**
	 * Set the value related to the column: SHIFT_ID
	 * @param shift the SHIFT_ID value
	 */
	public void setShift (com.pinpos.model.Shift shift) {
		this.shift = shift;
	}



	/**
	 * Return the value associated with the column: OWNER_ID
	 */
	public com.pinpos.model.User getOwner () {
		return owner;
	}

	/**
	 * Set the value related to the column: OWNER_ID
	 * @param owner the OWNER_ID value
	 */
	public void setOwner (com.pinpos.model.User owner) {
		this.owner = owner;
	}



	/**
	 * Return the value associated with the column: DRIVER_ID
	 */
	public com.pinpos.model.User getAssignedDriver () {
		return assignedDriver;
	}

	/**
	 * Set the value related to the column: DRIVER_ID
	 * @param assignedDriver the DRIVER_ID value
	 */
	public void setAssignedDriver (com.pinpos.model.User assignedDriver) {
		this.assignedDriver = assignedDriver;
	}



	/**
	 * Return the value associated with the column: GRATUITY_ID
	 */
	public com.pinpos.model.Gratuity getGratuity () {
		return gratuity;
	}

	/**
	 * Set the value related to the column: GRATUITY_ID
	 * @param gratuity the GRATUITY_ID value
	 */
	public void setGratuity (com.pinpos.model.Gratuity gratuity) {
		this.gratuity = gratuity;
	}



	/**
	 * Return the value associated with the column: VOID_BY_USER
	 */
	public com.pinpos.model.User getVoidedBy () {
		return voidedBy;
	}

	/**
	 * Set the value related to the column: VOID_BY_USER
	 * @param voidedBy the VOID_BY_USER value
	 */
	public void setVoidedBy (com.pinpos.model.User voidedBy) {
		this.voidedBy = voidedBy;
	}



	/**
	 * Return the value associated with the column: TERMINAL_ID
	 */
	public com.pinpos.model.Terminal getTerminal () {
		return terminal;
	}

	/**
	 * Set the value related to the column: TERMINAL_ID
	 * @param terminal the TERMINAL_ID value
	 */
	public void setTerminal (com.pinpos.model.Terminal terminal) {
		this.terminal = terminal;
	}



	/**
	 * Return the value associated with the column: CUSTOMER_ID
	 */
	public com.pinpos.model.Customer getCustomer () {
		return customer;
	}

	/**
	 * Set the value related to the column: CUSTOMER_ID
	 * @param customer the CUSTOMER_ID value
	 */
	public void setCustomer (com.pinpos.model.Customer customer) {
		this.customer = customer;
	}



	/**
	 * Return the value associated with the column: properties
	 */
	public java.util.Map<String, Object> getProperties () {
		return properties;
	}

	/**
	 * Set the value related to the column: properties
	 * @param properties the properties value
	 */
	public void setProperties (java.util.Map<String, Object> properties) {
		this.properties = properties;
	}



	/**
	 * Return the value associated with the column: ticketItems
	 */
	public java.util.List<com.pinpos.model.TicketItem> getTicketItems () {
		return ticketItems;
	}

	/**
	 * Set the value related to the column: ticketItems
	 * @param ticketItems the ticketItems value
	 */
	public void setTicketItems (java.util.List<com.pinpos.model.TicketItem> ticketItems) {
		this.ticketItems = ticketItems;
	}

	public void addToticketItems (com.pinpos.model.TicketItem ticketItem) {
		if (null == getTicketItems()) setTicketItems(new java.util.ArrayList<com.pinpos.model.TicketItem>());
		getTicketItems().add(ticketItem);
	}



	/**
	 * Return the value associated with the column: couponAndDiscounts
	 */
	public java.util.List<com.pinpos.model.TicketCouponAndDiscount> getCouponAndDiscounts () {
		return couponAndDiscounts;
	}

	/**
	 * Set the value related to the column: couponAndDiscounts
	 * @param couponAndDiscounts the couponAndDiscounts value
	 */
	public void setCouponAndDiscounts (java.util.List<com.pinpos.model.TicketCouponAndDiscount> couponAndDiscounts) {
		this.couponAndDiscounts = couponAndDiscounts;
	}

	public void addTocouponAndDiscounts (com.pinpos.model.TicketCouponAndDiscount ticketCouponAndDiscount) {
		if (null == getCouponAndDiscounts()) setCouponAndDiscounts(new java.util.ArrayList<com.pinpos.model.TicketCouponAndDiscount>());
		getCouponAndDiscounts().add(ticketCouponAndDiscount);
	}





	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.pinpos.model.Ticket)) return false;
		else {
			com.pinpos.model.Ticket ticket = (com.pinpos.model.Ticket) obj;
			if (null == this.getId() || null == ticket.getId()) return false;
			else return (this.getId().equals(ticket.getId()));
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}

	public int compareTo (Object obj) {
		if (obj.hashCode() > hashCode()) return 1;
		else if (obj.hashCode() < hashCode()) return -1;
		else return 0;
	}

	public String toString () {
		return super.toString();
	}


}