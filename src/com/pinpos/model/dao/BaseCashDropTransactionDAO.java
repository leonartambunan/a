package com.pinpos.model.dao;

import com.pinpos.model.CashDropTransaction;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.Date;

/**
 * This is an automatically generated DAO class which should not be edited.
 */
public abstract class BaseCashDropTransactionDAO extends com.pinpos.model.dao._RootDAO {

	// query name references


	public static CashDropTransactionDAO instance;

	/**
	 * Return a singleton of the DAO
	 */
	public static CashDropTransactionDAO getInstance () {
		if (null == instance) instance = new CashDropTransactionDAO();
		return instance;
	}

	public Class getReferenceClass () {
		return com.pinpos.model.CashDropTransaction.class;
	}

    public Order getDefaultOrder () {
		return null;
    }

	/**
	 * Cast the object as a com.pinpos.model.CashDropTransaction
	 */
	public com.pinpos.model.CashDropTransaction cast (Object object) {
		return (com.pinpos.model.CashDropTransaction) object;
	}

	public com.pinpos.model.CashDropTransaction get(java.lang.Integer key)
	{
		return (com.pinpos.model.CashDropTransaction) get(getReferenceClass(), key);
	}

	public com.pinpos.model.CashDropTransaction get(java.lang.Integer key, Session s)
	{
		return (com.pinpos.model.CashDropTransaction) get(getReferenceClass(), key, s);
	}

	public com.pinpos.model.CashDropTransaction load(java.lang.Integer key)
	{
		return (com.pinpos.model.CashDropTransaction) load(getReferenceClass(), key);
	}

	public com.pinpos.model.CashDropTransaction load(java.lang.Integer key, Session s)
	{
		return (com.pinpos.model.CashDropTransaction) load(getReferenceClass(), key, s);
	}

	public com.pinpos.model.CashDropTransaction loadInitialize(java.lang.Integer key, Session s)
	{ 
		com.pinpos.model.CashDropTransaction obj = load(key, s);
		if (!Hibernate.isInitialized(obj)) {
			Hibernate.initialize(obj);
		} 
		return obj; 
	}

/* Generic methods */

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 */

	public java.util.List<com.pinpos.model.CashDropTransaction> findAll () {
		return super.findAll();
	}

	public java.util.List<com.pinpos.model.CashDropTransaction> findCashDrops(Date startDate, Date endDate) {
//		return super.findFiltered()

        Session session = null;
        try {
            session = getSession();
            Criteria criteria = session.createCriteria(getReferenceClass());
            criteria.add(Restrictions.ge(CashDropTransaction.PROP_TRANSACTION_TIME, startDate));
            criteria.add(Restrictions.le(CashDropTransaction.PROP_TRANSACTION_TIME, endDate));
//            criteria.add(Restrictions.eq(Ticket.PROP_CLOSED, Boolean.TRUE));
//            criteria.add(Restrictions.eq(Ticket.PROP_VOIDED, Boolean.FALSE));

            return criteria.list();
        } finally {
            closeSession(session);
        }
	}

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 */
	public java.util.List<com.pinpos.model.CashDropTransaction> findAll (Order defaultOrder) {
		return super.findAll(defaultOrder);
	}

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 * Use the session given.
	 * @param s the Session
	 */
	public java.util.List<com.pinpos.model.CashDropTransaction> findAll (Session s, Order defaultOrder) {
		return super.findAll(s, defaultOrder);
	}

	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.) 
	 * @param cashDropTransaction a transient instance of a persistent class 
	 * @return the class identifier
	 */
	public java.lang.Integer save(com.pinpos.model.CashDropTransaction cashDropTransaction)
	{
		return (java.lang.Integer) super.save(cashDropTransaction);
	}

	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.) 
	 * Use the Session given.
	 * @param cashDropTransaction a transient instance of a persistent class
	 * @param s the Session
	 * @return the class identifier
	 */
	public java.lang.Integer save(com.pinpos.model.CashDropTransaction cashDropTransaction, Session s)
	{
		return (java.lang.Integer) save((Object) cashDropTransaction, s);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default
	 * the instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the
	 * identifier property mapping. 
	 * @param cashDropTransaction a transient instance containing new or updated state 
	 */
	public void saveOrUpdate(com.pinpos.model.CashDropTransaction cashDropTransaction)
	{
		saveOrUpdate((Object) cashDropTransaction);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default the
	 * instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the identifier
	 * property mapping. 
	 * Use the Session given.
	 * @param cashDropTransaction a transient instance containing new or updated state.
	 * @param s the Session.
	 */
	public void saveOrUpdate(com.pinpos.model.CashDropTransaction cashDropTransaction, Session s)
	{
		saveOrUpdate((Object) cashDropTransaction, s);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * @param cashDropTransaction a transient instance containing updated state
	 */
	public void update(com.pinpos.model.CashDropTransaction cashDropTransaction)
	{
		update((Object) cashDropTransaction);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * Use the Session given.
	 * @param cashDropTransaction a transient instance containing updated state
	 */
	public void update(com.pinpos.model.CashDropTransaction cashDropTransaction, Session s)
	{
		update((Object) cashDropTransaction, s);
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
	 * @param cashDropTransaction the instance to be removed
	 */
	public void delete(com.pinpos.model.CashDropTransaction cashDropTransaction)
	{
		delete((Object) cashDropTransaction);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * Use the Session given.
	 * @param cashDropTransaction the instance to be removed
	 * @param s the Session
	 */
	public void delete(com.pinpos.model.CashDropTransaction cashDropTransaction, Session s)
	{
		delete((Object) cashDropTransaction, s);
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
	public void refresh (com.pinpos.model.CashDropTransaction cashDropTransaction, Session s)
	{
		refresh((Object) cashDropTransaction, s);
	}


}