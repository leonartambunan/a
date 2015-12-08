package com.pinpos.model.inventory.base;

import java.lang.Comparable;
import java.io.Serializable;


/**
 * This is an object that contains data related to the INVENTORY_ITEM table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="INVENTORY_ITEM"
 */

public abstract class BaseInventoryItem  implements Comparable, Serializable {

	public static String REF = "InventoryItem";
	public static String PROP_PACKAGE_BARCODE = "packageBarcode";
	public static String PROP_DESCRIPTION = "description";
	public static String PROP_ITEM_VENDOR = "itemVendor";
	public static String PROP_ITEM_GROUP = "itemGroup";
	public static String PROP_VISIBLE = "visible";
	public static String PROP_AVERAGE_PACKAGE_PRICE = "averagePackagePrice";
	public static String PROP_SORT_ORDER = "sortOrder";
	public static String PROP_UNIT_BARCODE = "unitBarcode";
	public static String PROP_PACKAGE_REPLENISH_LEVEL = "packageReplenishLevel";
	public static String PROP_PACKAGE_DESCRIPTION = "packageDescription";
	public static String PROP_NAME = "name";
	public static String PROP_LAST_UPDATE_DATE = "lastUpdateDate";
	public static String PROP_TOTAL_PACKAGES = "totalPackages";
	public static String PROP_ITEM_LOCATION = "itemLocation";
	public static String PROP_CREATE_TIME = "createTime";
	public static String PROP_TOTAL_RECEPIE_UNITS = "totalRecepieUnits";
	public static String PROP_ID = "id";
	public static String PROP_UNIT_PER_PACKAGE = "unitPerPackage";
	public static String PROP_PACKAGE_REORDER_LEVEL = "packageReorderLevel";
	public static String PROP_UNIT_SELLING_PRICE = "unitSellingPrice";
	public static String PROP_UNIT_PURCHASE_PRICE = "unitPurchasePrice";


	// constructors
	public BaseInventoryItem () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseInventoryItem (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	 java.util.Date modifiedTime;

	// fields
		protected java.util.Date createTime;
		protected java.util.Date lastUpdateDate;
		protected java.lang.String name;
		protected java.lang.String packageBarcode;
		protected java.lang.String unitBarcode;
		protected java.lang.String packageDescription;
		protected java.lang.Float unitPerPackage;
		protected java.lang.Integer sortOrder;
		protected int packageReorderLevel;
		protected int packageReplenishLevel;
		protected java.lang.String description;
		protected double averagePackagePrice;
		protected int totalPackages;
		protected float totalRecepieUnits;
		protected double unitPurchasePrice;
		protected double unitSellingPrice;
		protected java.lang.Boolean visible;

	// many to one
	private com.pinpos.model.inventory.InventoryGroup itemGroup;
	private com.pinpos.model.inventory.InventoryLocation itemLocation;
	private com.pinpos.model.inventory.InventoryVendor itemVendor;



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
	 * Return the value associated with the column: CREATE_TIME
	 */
	public java.util.Date getCreateTime () {
					return createTime;
			}

	/**
	 * Set the value related to the column: CREATE_TIME
	 * @param createTime the CREATE_TIME value
	 */
	public void setCreateTime (java.util.Date createTime) {
		this.createTime = createTime;
	}



	/**
	 * Return the value associated with the column: LAST_UPDATE_DATE
	 */
	public java.util.Date getLastUpdateDate () {
					return lastUpdateDate;
			}

	/**
	 * Set the value related to the column: LAST_UPDATE_DATE
	 * @param lastUpdateDate the LAST_UPDATE_DATE value
	 */
	public void setLastUpdateDate (java.util.Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}



	/**
	 * Return the value associated with the column: NAME
	 */
	public java.lang.String getName () {
					return name;
			}

	/**
	 * Set the value related to the column: NAME
	 * @param name the NAME value
	 */
	public void setName (java.lang.String name) {
		this.name = name;
	}



	/**
	 * Return the value associated with the column: PACKAGE_BARCODE
	 */
	public java.lang.String getPackageBarcode () {
					return packageBarcode;
			}

	/**
	 * Set the value related to the column: PACKAGE_BARCODE
	 * @param packageBarcode the PACKAGE_BARCODE value
	 */
	public void setPackageBarcode (java.lang.String packageBarcode) {
		this.packageBarcode = packageBarcode;
	}



	/**
	 * Return the value associated with the column: UNIT_BARCODE
	 */
	public java.lang.String getUnitBarcode () {
					return unitBarcode;
			}

	/**
	 * Set the value related to the column: UNIT_BARCODE
	 * @param unitBarcode the UNIT_BARCODE value
	 */
	public void setUnitBarcode (java.lang.String unitBarcode) {
		this.unitBarcode = unitBarcode;
	}



	/**
	 * Return the value associated with the column: PACKAGE_DESC
	 */
	public java.lang.String getPackageDescription () {
					return packageDescription;
			}

	/**
	 * Set the value related to the column: PACKAGE_DESC
	 * @param packageDescription the PACKAGE_DESC value
	 */
	public void setPackageDescription (java.lang.String packageDescription) {
		this.packageDescription = packageDescription;
	}



	/**
	 * Return the value associated with the column: UNIT_PER_PACKAGE
	 */
	public java.lang.Float getUnitPerPackage () {
					return unitPerPackage;
			}

	/**
	 * Set the value related to the column: UNIT_PER_PACKAGE
	 * @param unitPerPackage the UNIT_PER_PACKAGE value
	 */
	public void setUnitPerPackage (java.lang.Float unitPerPackage) {
		this.unitPerPackage = unitPerPackage;
	}



	/**
	 * Return the value associated with the column: SORT_ORDER
	 */
	public java.lang.Integer getSortOrder () {
					return sortOrder == null ? Integer.valueOf(0) : sortOrder;
			}

	/**
	 * Set the value related to the column: SORT_ORDER
	 * @param sortOrder the SORT_ORDER value
	 */
	public void setSortOrder (java.lang.Integer sortOrder) {
		this.sortOrder = sortOrder;
	}



	/**
	 * Return the value associated with the column: PACKAGE_REORDER_LEVEL
	 */
	public int getPackageReorderLevel () {
					return packageReorderLevel;
			}

	/**
	 * Set the value related to the column: PACKAGE_REORDER_LEVEL
	 * @param packageReorderLevel the PACKAGE_REORDER_LEVEL value
	 */
	public void setPackageReorderLevel (int packageReorderLevel) {
		this.packageReorderLevel = packageReorderLevel;
	}



	/**
	 * Return the value associated with the column: PACKAGE_REPLENISH_LEVEL
	 */
	public int getPackageReplenishLevel () {
					return packageReplenishLevel;
			}

	/**
	 * Set the value related to the column: PACKAGE_REPLENISH_LEVEL
	 * @param packageReplenishLevel the PACKAGE_REPLENISH_LEVEL value
	 */
	public void setPackageReplenishLevel (int packageReplenishLevel) {
		this.packageReplenishLevel = packageReplenishLevel;
	}



	/**
	 * Return the value associated with the column: DESCRIPTION
	 */
	public java.lang.String getDescription () {
					return description;
			}

	/**
	 * Set the value related to the column: DESCRIPTION
	 * @param description the DESCRIPTION value
	 */
	public void setDescription (java.lang.String description) {
		this.description = description;
	}



	/**
	 * Return the value associated with the column: AVERAGE_PACKAGE_PRICE
	 */
	public double getAveragePackagePrice () {
					return averagePackagePrice;
			}

	/**
	 * Set the value related to the column: AVERAGE_PACKAGE_PRICE
	 * @param averagePackagePrice the AVERAGE_PACKAGE_PRICE value
	 */
	public void setAveragePackagePrice (double averagePackagePrice) {
		this.averagePackagePrice = averagePackagePrice;
	}



	/**
	 * Return the value associated with the column: TOTAL_PACKAGES
	 */
	public int getTotalPackages () {
					return totalPackages;
			}

	/**
	 * Set the value related to the column: TOTAL_PACKAGES
	 * @param totalPackages the TOTAL_PACKAGES value
	 */
	public void setTotalPackages (int totalPackages) {
		this.totalPackages = totalPackages;
	}



	/**
	 * Return the value associated with the column: TOTAL_RECEPIE_UNITS
	 */
	public float getTotalRecepieUnits () {
					return totalRecepieUnits;
			}

	/**
	 * Set the value related to the column: TOTAL_RECEPIE_UNITS
	 * @param totalRecepieUnits the TOTAL_RECEPIE_UNITS value
	 */
	public void setTotalRecepieUnits (float totalRecepieUnits) {
		this.totalRecepieUnits = totalRecepieUnits;
	}



	/**
	 * Return the value associated with the column: UNIT_PURCHASE_PRICE
	 */
	public double getUnitPurchasePrice () {
					return unitPurchasePrice;
			}

	/**
	 * Set the value related to the column: UNIT_PURCHASE_PRICE
	 * @param unitPurchasePrice the UNIT_PURCHASE_PRICE value
	 */
	public void setUnitPurchasePrice (double unitPurchasePrice) {
		this.unitPurchasePrice = unitPurchasePrice;
	}



	/**
	 * Return the value associated with the column: UNIT_SELLING_PRICE
	 */
	public double getUnitSellingPrice () {
					return unitSellingPrice;
			}

	/**
	 * Set the value related to the column: UNIT_SELLING_PRICE
	 * @param unitSellingPrice the UNIT_SELLING_PRICE value
	 */
	public void setUnitSellingPrice (double unitSellingPrice) {
		this.unitSellingPrice = unitSellingPrice;
	}



	/**
	 * Return the value associated with the column: VISIBLE
	 */
	public java.lang.Boolean isVisible () {
								return visible == null ? Boolean.FALSE : visible;
					}

	/**
	 * Set the value related to the column: VISIBLE
	 * @param visible the VISIBLE value
	 */
	public void setVisible (java.lang.Boolean visible) {
		this.visible = visible;
	}



	/**
	 * Return the value associated with the column: ITEM_GROUP_ID
	 */
	public com.pinpos.model.inventory.InventoryGroup getItemGroup () {
					return itemGroup;
			}

	/**
	 * Set the value related to the column: ITEM_GROUP_ID
	 * @param itemGroup the ITEM_GROUP_ID value
	 */
	public void setItemGroup (com.pinpos.model.inventory.InventoryGroup itemGroup) {
		this.itemGroup = itemGroup;
	}



	/**
	 * Return the value associated with the column: ITEM_LOCATION_ID
	 */
	public com.pinpos.model.inventory.InventoryLocation getItemLocation () {
					return itemLocation;
			}

	/**
	 * Set the value related to the column: ITEM_LOCATION_ID
	 * @param itemLocation the ITEM_LOCATION_ID value
	 */
	public void setItemLocation (com.pinpos.model.inventory.InventoryLocation itemLocation) {
		this.itemLocation = itemLocation;
	}



	/**
	 * Return the value associated with the column: ITEM_VENDOR_ID
	 */
	public com.pinpos.model.inventory.InventoryVendor getItemVendor () {
					return itemVendor;
			}

	/**
	 * Set the value related to the column: ITEM_VENDOR_ID
	 * @param itemVendor the ITEM_VENDOR_ID value
	 */
	public void setItemVendor (com.pinpos.model.inventory.InventoryVendor itemVendor) {
		this.itemVendor = itemVendor;
	}





	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.pinpos.model.inventory.InventoryItem)) return false;
		else {
			com.pinpos.model.inventory.InventoryItem inventoryItem = (com.pinpos.model.inventory.InventoryItem) obj;
			if (null == this.getId() || null == inventoryItem.getId()) return false;
			else return (this.getId().equals(inventoryItem.getId()));
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