package com.pinpos.model.dao;

import com.pinpos.model.CashDropTransaction;
import com.pinpos.model.Terminal;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;


public class CashDropTransactionDAO extends BaseCashDropTransactionDAO {

	/**
	 * Default constructor.  Can be used in place of getInstance()
	 */
	public CashDropTransactionDAO () {}

	public List<CashDropTransaction> findUnsettled(Terminal terminal) throws Exception {
		Session session = null;
		
		try {
			session = createNewSession();
			Criteria criteria = session.createCriteria(getReferenceClass());
			criteria.add(Restrictions.eq(CashDropTransaction.PROP_DRAWER_RESETTED, Boolean.FALSE));
			criteria.add(Restrictions.eq(CashDropTransaction.PROP_TERMINAL, terminal));

			return criteria.list();
		} catch (Exception e) {
			throw e;
		} finally {
			closeSession(session);
		}
	}
	
	public void deleteCashDrop(CashDropTransaction transaction, Terminal terminal) {
		Session session = null;
		org.hibernate.Transaction tx = null;
		
		terminal.setCurrentBalance(terminal.getCurrentBalance() + transaction.getSubtotalAmount());
		try {
			session = createNewSession();
			tx = session.beginTransaction();

			session.delete(transaction);
			session.update(terminal);
			tx.commit();
		} catch (Exception e) {
			try {
                if (tx != null) {
                    tx.rollback();
                }
            }catch(Exception x) {}
		} finally {
			closeSession(session);
		}
		
	}
	
	public void saveNewCashDrop(CashDropTransaction transaction, Terminal terminal) {
		Session session = null;
		org.hibernate.Transaction tx = null;
		
		terminal.setCurrentBalance(terminal.getCurrentBalance() - transaction.getSubtotalAmount());
		try {
			session = createNewSession();
			tx = session.beginTransaction();

			session.save(transaction);
			session.update(terminal);
			tx.commit();
		} catch (Exception e) {
			try {
                if (tx != null) {
                    tx.rollback();
                }
            }catch(Exception x) {}
		} finally {
			closeSession(session);
		}
		
	}
}