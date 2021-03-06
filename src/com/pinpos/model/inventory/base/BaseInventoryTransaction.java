package com.pinpos.model.inventory.base;

import java.lang.Comparable;
import java.io.Serializable;


/**
 * This is an object that contains data related to the INVENTORY_TRANSACTION table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="INVENTORY_TRANSACTION"
 */

public abstract class BaseInventoryTransaction  implements Comparable, Serializable {

	public static String REF = "InventoryTransaction";
	public static String PROP_INVENTORY_ITEM = "inventoryItem";
	public static String PROP_QUANTITY = "quantity";
	public static String PROP_TO_WAREHOUSE = "toWarehouse";
	public static String PROP_VENDOR = "vendor";
	public static String PROP_TRANSACTION_DATE = "transactionDate";
	public static String PROP_FROM_WAREHOUSE = "fromWarehouse";
	public static String PROP_ID = "id";
	public static String PROP_TRANSACTION_TYPE = "transactionType";
	public static String PROP_UNIT_PRICE = "unitPrice";
	public static String PROP_REMARK = "remark";
	public static String PROP_REFERENCE_NO = "referenceNo";


	// constructors
	public BaseInventoryTransaction () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseInventoryTransaction (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	 java.util.Date modifiedTime;

	// fields
		protected java.util.Date transactionDate;
		protected float quantity;
		protected double unitPrice;
		protected java.lang.String remark;

	// many to one
	private com.pinpos.model.inventory.InventoryTransactionType transactionType;
	private com.pinpos.model.inventory.PurchaseOrder referenceNo;
	private com.pinpos.model.inventory.InventoryItem inventoryItem;
	private com.pinpos.model.inventory.InventoryVendor vendor;
	private com.pinpos.model.inventory.InventoryWarehouse fromWarehouse;
	private com.pinpos.model.inventory.InventoryWarehouse toWarehouse;



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
	 * Return the value associated with the column: TRANSACTION_DATE
	 */
	public java.util.Date getTransactionDate () {
					return transactionDate;
			}

	/**
	 * Set the value related to the column: TRANSACTION_DATE
	 * @param transactionDate the TRANSACTION_DATE value
	 */
	public void setTransactionDate (java.util.Date transactionDate) {
		this.transactionDate = transactionDate;
	}



	/**
	 * Return the value associated with the column: QUANTITY
	 */
	public float getQuantity () {
					return quantity;
			}

	/**
	 * Set the value related to the column: QUANTITY
	 * @param quantity the QUANTITY value
	 */
	public void setQuantity (float quantity) {
		this.quantity = quantity;
	}



	/**
	 * Return the value associated with the column: UNIT_PRICE
	 */
	public double getUnitPrice () {
					return unitPrice;
			}

	/**
	 * Set the value related to the column: UNIT_PRICE
	 * @param unitPrice the UNIT_PRICE value
	 */
	public void setUnitPrice (double unitPrice) {
		this.unitPrice = unitPrice;
	}



	/**
	 * Return the value associated with the column: REMARK
	 */
	public java.lang.String getRemark () {
					return remark;
			}

	/**
	 * Set the value related to the column: REMARK
	 * @param remark the REMARK value
	 */
	public void setRemark (java.lang.String remark) {
		this.remark = remark;
	}



	/**
	 * Return the value associated with the column: TRANSACTION_TYPE_ID
	 */
	public com.pinpos.model.inventory.InventoryTransactionType getTransactionType () {
					return transactionType;
			}

	/**
	 * Set the value related to the column: TRANSACTION_TYPE_ID
	 * @param transactionType the TRANSACTION_TYPE_ID value
	 */
	public void setTransactionType (com.pinpos.model.inventory.InventoryTransactionType transactionType) {
		this.transactionType = transactionType;
	}



	/**
	 * Return the value associated with the column: REFERENCE_ID
	 */
	public com.pinpos.model.inventory.PurchaseOrder getReferenceNo () {
					return referenceNo;
			}

	/**
	 * Set the value related to the column: REFERENCE_ID
	 * @param referenceNo the REFERENCE_ID value
	 */
	public void setReferenceNo (com.pinpos.model.inventory.PurchaseOrder referenceNo) {
		this.referenceNo = referenceNo;
	}



	/**
	 * Return the value associated with the column: ITEM_ID
	 */
	public com.pinpos.model.inventory.InventoryItem getInventoryItem () {
					return inventoryItem;
			}

	/**
	 * Set the value related to the column: ITEM_ID
	 * @param inventoryItem the ITEM_ID value
	 */
	public void setInventoryItem (com.pinpos.model.inventory.InventoryItem inventoryItem) {
		this.inventoryItem = inventoryItem;
	}



	/**
	 * Return the value associated with the column: VENDOR_ID
	 */
	public com.pinpos.model.inventory.InventoryVendor getVendor () {
					return vendor;
			}

	/**
	 * Set the value related to the column: VENDOR_ID
	 * @param vendor the VENDOR_ID value
	 */
	public void setVendor (com.pinpos.model.inventory.InventoryVendor vendor) {
		this.vendor = vendor;
	}



	/**
	 * Return the value associated with the column: FROM_WAREHOUSE_ID
	 */
	public com.pinpos.model.inventory.InventoryWarehouse getFromWarehouse () {
					return fromWarehouse;
			}

	/**
	 * Set the value related to the column: FROM_WAREHOUSE_ID
	 * @param fromWarehouse the FROM_WAREHOUSE_ID value
	 */
	public void setFromWarehouse (com.pinpos.model.inventory.InventoryWarehouse fromWarehouse) {
		this.fromWarehouse = fromWarehouse;
	}



	/**
	 * Return the value associated with the column: TO_WAREHOUSE_ID
	 */
	public com.pinpos.model.inventory.InventoryWarehouse getToWarehouse () {
					return toWarehouse;
			}

	/**
	 * Set the value related to the column: TO_WAREHOUSE_ID
	 * @param toWarehouse the TO_WAREHOUSE_ID value
	 */
	public void setToWarehouse (com.pinpos.model.inventory.InventoryWarehouse toWarehouse) {
		this.toWarehouse = toWarehouse;
	}





	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.pinpos.model.inventory.InventoryTransaction)) return false;
		else {
			com.pinpos.model.inventory.InventoryTransaction inventoryTransaction = (com.pinpos.model.inventory.InventoryTransaction) obj;
			if (null == this.getId() || null == inventoryTransaction.getId()) return false;
			else return (this.getId().equals(inventoryTransaction.getId()));
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