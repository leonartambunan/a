package com.pinpos.model.dao;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.pinpos.PosException;
import com.pinpos.model.Gratuity;
import com.pinpos.model.Terminal;
import com.pinpos.model.Ticket;
import com.pinpos.model.TipsCashoutReport;
import com.pinpos.model.TipsCashoutReportData;
import com.pinpos.model.User;
import com.pinpos.model.util.DateUtil;

public class GratuityDAO extends BaseGratuityDAO {

	/**
	 * Default constructor.  Can be used in place of getInstance()
	 */
	public GratuityDAO() {
	}

	public List<Gratuity> findByUser(User user) throws PosException {
		Session session = null;

		try {
			session = getSession();
			
			Criteria criteria = session.createCriteria(getReferenceClass());
			criteria.add(Restrictions.eq(Gratuity.PROP_OWNER, user));
			criteria.add(Restrictions.eq(Gratuity.PROP_PAID, Boolean.FALSE));

			return criteria.list();
		} catch (Exception e) {
			throw new PosException("An error has occured while retrieving gratuity for " + user.getFirstName() + " " + user.getLastName());
		} finally {
			closeSession(session);
		}
	}
	
	public void payGratuities(List<Gratuity> gratuities) {
		Session session = null;
		Transaction tx = null;
		
		double total = 0;
		try {
			session = getSession();
			tx = session.beginTransaction();
			for (Gratuity gratuity : gratuities) {
				total += gratuity.getAmount();
				gratuity.setPaid(true);
				session.saveOrUpdate(gratuity);
				
				Terminal terminal = gratuity.getTerminal();
				terminal.setCurrentBalance(terminal.getCurrentBalance() - gratuity.getAmount());
				session.saveOrUpdate(terminal);
			}
			
			tx.commit();
		} catch (Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			throw new PosException("An error occured, could not mark gratuities as paid");
		} finally {
			closeSession(session);
		}
	}
	
	public TipsCashoutReport createReport(Date fromDate, Date toDate, User user) {
		Session session = null;

		try {
			session = getSession();
			
			fromDate = DateUtil.startOfDay(fromDate);
			toDate = DateUtil.endOfDay(toDate);
			
			Criteria criteria = session.createCriteria(Ticket.class);
			//criteria = criteria.createAlias(Ti, "t");
			criteria.add(Restrictions.eq(Ticket.PROP_OWNER, user));
			//criteria.add(Restrictions.eq(Ticket.PROP_DRAWER_RESETTED, Boolean.FALSE));
			//criteria.add(Restrictions.eq(Ticket.PROP_CLOSED, Boolean.TRUE));
			criteria.add(Restrictions.ge(Ticket.PROP_CREATE_DATE, fromDate));
			criteria.add(Restrictions.le(Ticket.PROP_CREATE_DATE, toDate));

			List list = criteria.list();
			
			TipsCashoutReport report = new TipsCashoutReport();
			report.setServer(user.getUserId() + "/" + user.toString());
			report.setFromDate(fromDate);
			report.setToDate(toDate);
			report.setReportTime(new Date());
			
			for (Iterator iter = list.iterator(); iter.hasNext();) {
				Ticket ticket = (Ticket) iter.next();
				Gratuity gratuity = ticket.getGratuity();
				
				TipsCashoutReportData data = new TipsCashoutReportData();
				data.setTicketId(ticket.getId());
				data.setSaleType(ticket.getCardType());
				data.setTicketTotal(ticket.getTotalAmount());
				
				if(gratuity != null) {
					data.setTips(gratuity.getAmount());
					data.setPaid(gratuity.isPaid());
				}
				else {
					data.setTips((double) 0);
					
				}
				report.addReportData(data);
			}
			report.calculateOthers();
			return report;
		} catch (Exception e) {
			throw new PosException("An error has occured while retrieving gratuity for " + user.getFirstName() + " " + user.getLastName());
		} finally {
			closeSession(session);
		}
		
	}
}