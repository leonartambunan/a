package com.pinpos.model.inventory.base;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import com.pinpos.model.inventory.dao.InventoryItemDAO;
import org.hibernate.criterion.Order;

/**
 * This is an automatically generated DAO class which should not be edited.
 */
public abstract class BaseInventoryItemDAO extends com.pinpos.model.inventory.dao._RootDAO {

	// query name references


	public static InventoryItemDAO instance;

	/**
	 * Return a singleton of the DAO
	 */
	public static InventoryItemDAO getInstance () {
		if (null == instance) instance = new InventoryItemDAO();
		return instance;
	}

	public Class getReferenceClass () {
		return com.pinpos.model.inventory.InventoryItem.class;
	}

    public Order getDefaultOrder () {
		return Order.asc("name");
    }

	/**
	 * Cast the object as a com.pinpos.model.inventory.InventoryItem
	 */
	public com.pinpos.model.inventory.InventoryItem cast (Object object) {
		return (com.pinpos.model.inventory.InventoryItem) object;
	}

	public com.pinpos.model.inventory.InventoryItem get(java.lang.Integer key)
	{
		return (com.pinpos.model.inventory.InventoryItem) get(getReferenceClass(), key);
	}

	public com.pinpos.model.inventory.InventoryItem get(java.lang.Integer key, Session s)
	{
		return (com.pinpos.model.inventory.InventoryItem) get(getReferenceClass(), key, s);
	}

	public com.pinpos.model.inventory.InventoryItem load(java.lang.Integer key)
	{
		return (com.pinpos.model.inventory.InventoryItem) load(getReferenceClass(), key);
	}

	public com.pinpos.model.inventory.InventoryItem load(java.lang.Integer key, Session s)
	{
		return (com.pinpos.model.inventory.InventoryItem) load(getReferenceClass(), key, s);
	}

	public com.pinpos.model.inventory.InventoryItem loadInitialize(java.lang.Integer key, Session s)
	{ 
		com.pinpos.model.inventory.InventoryItem obj = load(key, s);
		if (!Hibernate.isInitialized(obj)) {
			Hibernate.initialize(obj);
		} 
		return obj; 
	}

/* Generic methods */

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 */
	public java.util.List<com.pinpos.model.inventory.InventoryItem> findAll () {
		return super.findAll();
	}

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 */
	public java.util.List<com.pinpos.model.inventory.InventoryItem> findAll (Order defaultOrder) {
		return super.findAll(defaultOrder);
	}

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 * Use the session given.
	 * @param s the Session
	 */
	public java.util.List<com.pinpos.model.inventory.InventoryItem> findAll (Session s, Order defaultOrder) {
		return super.findAll(s, defaultOrder);
	}

	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.) 
	 * @param inventoryItem a transient instance of a persistent class 
	 * @return the class identifier
	 */
	public java.lang.Integer save(com.pinpos.model.inventory.InventoryItem inventoryItem)
	{
		return (java.lang.Integer) super.save(inventoryItem);
	}

	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.) 
	 * Use the Session given.
	 * @param inventoryItem a transient instance of a persistent class
	 * @param s the Session
	 * @return the class identifier
	 */
	public java.lang.Integer save(com.pinpos.model.inventory.InventoryItem inventoryItem, Session s)
	{
		return (java.lang.Integer) save((Object) inventoryItem, s);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default
	 * the instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the
	 * identifier property mapping. 
	 * @param inventoryItem a transient instance containing new or updated state 
	 */
	public void saveOrUpdate(com.pinpos.model.inventory.InventoryItem inventoryItem)
	{
		saveOrUpdate((Object) inventoryItem);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default the
	 * instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the identifier
	 * property mapping. 
	 * Use the Session given.
	 * @param inventoryItem a transient instance containing new or updated state.
	 * @param s the Session.
	 */
	public void saveOrUpdate(com.pinpos.model.inventory.InventoryItem inventoryItem, Session s)
	{
		saveOrUpdate((Object) inventoryItem, s);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * @param inventoryItem a transient instance containing updated state
	 */
	public void update(com.pinpos.model.inventory.InventoryItem inventoryItem)
	{
		update((Object) inventoryItem);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * Use the Session given.
	 * @param inventoryItem a transient instance containing updated state
	 * @param the Session
	 */
	public void update(com.pinpos.model.inventory.InventoryItem inventoryItem, Session s)
	{
		update((Object) inventoryItem, s);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * @param id the instance ID to be removed
	 */
	public void delete(java.lang.Integer id)
	{
		delete((Object) load(id));
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * Use the Session given.
	 * @param id the instance ID to be removed
	 * @param s the Session
	 */
	public void delete(java.lang.Integer id, Session s)
	{
		delete((Object) load(id, s), s);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * @param inventoryItem the instance to be removed
	 */
	public void delete(com.pinpos.model.inventory.InventoryItem inventoryItem)
	{
		delete((Object) inventoryItem);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * Use the Session given.
	 * @param inventoryItem the instance to be removed
	 * @param s the Session
	 */
	public void delete(com.pinpos.model.inventory.InventoryItem inventoryItem, Session s)
	{
		delete((Object) inventoryItem, s);
	}
	
	/**
	 * Re-read the state of the given instance from the underlying database. It is inadvisable to use this to implement
	 * long-running sessions that span many business tasks. This method is, however, useful in certain special circumstances.
	 * For example 
	 * <ul> 
	 * <li>where a database trigger alters the object state upon insert or update</li>
	 * <li>after executing direct SQL (eg. a mass update) in the same session</li>
	 * <li>after inserting a Blob or Clob</li>
	 * </ul>
	 */
	public void refresh (com.pinpos.model.inventory.InventoryItem inventoryItem, Session s)
	{
		refresh((Object) inventoryItem, s);
	}


}