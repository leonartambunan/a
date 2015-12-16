package com.pinpos.services;

import com.pinpos.Messages;
import com.pinpos.POSConstants;
import com.pinpos.main.Application;
import com.pinpos.model.*;
import com.pinpos.model.dao.ActionHistoryDAO;
import com.pinpos.model.dao.GenericDAO;
import com.pinpos.util.NumberUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class PosTransactionService {
	private static PosTransactionService paymentService = new PosTransactionService();

	public void settleTickets(List<Ticket> tickets, double tenderedAmount, PosTransaction transaction, String cardType, String cardAuthorizationCode) throws Exception {
		Application application = Application.getInstance();
		User currentUser = Application.getCurrentUser();
		Terminal terminal = application.getTerminal();

		Session session = null;
		Transaction tx = null;

		GenericDAO dao = new GenericDAO();
		
		try {
			Date currentDate = new Date();
			
			session = dao.getSession();
			tx = session.beginTransaction();
			
			double totalDueAmount = 0;
			double cashBackAmount = 0;
			for (Ticket ticket : tickets) {
				totalDueAmount += ticket.getDueAmount();
			}
			cashBackAmount = tenderedAmount - totalDueAmount;
			if(cashBackAmount < 0) {
				cashBackAmount = 0;
			}
			
			for (Iterator it = tickets.iterator(); it.hasNext(); ) {
				Ticket ticket = (Ticket) it.next();
				
				ticket.setVoided(false);
				ticket.setDrawerResetted(false);
				ticket.setTerminal(terminal);
				ticket.setCardNumber(cardAuthorizationCode);
				
				double paidAmount = ticket.getPaidAmount();
				double dueAmount = ticket.getDueAmount();
				
				if(tenderedAmount >= dueAmount) {
					paidAmount += dueAmount;
					tenderedAmount -= dueAmount;
					dueAmount = 0;
					
					ticket.setPaid(true);
					closeTicketIfApplicable(ticket, currentDate);
				}
				else {
					paidAmount += tenderedAmount;
					dueAmount -= tenderedAmount;
					
					ticket.setPaid(false);
					ticket.setClosed(false);
				}
				ticket.setPaidAmount(paidAmount);
				ticket.setDueAmount(dueAmount);
				
				PosTransaction posTransaction = null;
				if (transaction instanceof CashTransaction) {
					posTransaction = new CashTransaction();
					
					Double currentBalance = terminal.getCurrentBalance();
					Double totalPrice = ticket.getTotalAmount();
					double newBalance = currentBalance + totalPrice;

					terminal.setCurrentBalance(newBalance);
					
					ticket.setTransactionType(PosTransaction.TYPE_CASH);
				}
				else if (transaction instanceof CreditCardTransaction) {
					posTransaction = new CreditCardTransaction();
					((CreditCardTransaction) posTransaction).setCardNumber(cardAuthorizationCode);
					((CreditCardTransaction) posTransaction).setCardType(cardType);
					ticket.setCardType(cardType);
					ticket.setTransactionType(PosTransaction.TYPE_CREDIT_CARD);
				}
				else if (transaction instanceof DebitCardTransaction) {
					posTransaction = new DebitCardTransaction();
					((DebitCardTransaction) posTransaction).setCardNumber(cardAuthorizationCode);
					((DebitCardTransaction) posTransaction).setCardType(cardType);
					ticket.setCardType(cardType);
					ticket.setTransactionType(PosTransaction.TYPE_DEBIT_CARD);
				}
				else if (transaction instanceof GiftCertificateTransaction) {
					posTransaction = transaction;
					GiftCertificateTransaction giftCertificateTransaction = (GiftCertificateTransaction) posTransaction;
						
					Double currentBalance = terminal.getCurrentBalance();
					double newBalance = currentBalance - giftCertificateTransaction.getCashBackAmount();
					terminal.setCurrentBalance(newBalance);

					ticket.setTransactionType(PosTransaction.TYPE_GIFT_CERT);
				}
				
				posTransaction.setTicket(ticket);
				posTransaction.setSubtotalAmount(ticket.getSubtotalAmount());
				posTransaction.setDiscountAmount(ticket.getDiscountAmount());
				posTransaction.setTaxAmount(ticket.getTaxAmount());
				posTransaction.setTotalAmount(ticket.getTotalAmount());
				
				if(ticket.getGratuity() != null) {
					posTransaction.setGratuityAmount(ticket.getGratuity().getAmount());
				}

				posTransaction.setTerminal(terminal);
				posTransaction.setUser(currentUser);
				posTransaction.setTransactionTime(currentDate);
				
				dao.saveOrUpdate(ticket, session);
				dao.saveOrUpdate(posTransaction, session);
				dao.saveOrUpdate(terminal, session);
				
//				User assignedDriver = ticket.getAssignedDriver();
//				if(assignedDriver != null) {
//					assignedDriver.setAvailableForDelivery(true);
//					UserDAO.getInstance().saveOrUpdate(assignedDriver, session);
//				}
			}

			tx.commit();
		} catch (Exception e) {
			try {
				if (tx!=null) tx.rollback();
			} catch (Exception x) {
			}
			throw e;
		} finally {
			dao.closeSession(session);
		}
		
		for (Ticket ticket : tickets) {
//			SETTLE ACTION
            StringBuilder sb = new StringBuilder();
//			String actionMessage = com.pinpos.POSConstants.RECEIPT_REPORT_TICKET_NO_LABEL + ":" + ticket.getId();
			sb.append(com.pinpos.POSConstants.RECEIPT_REPORT_TICKET_NO_LABEL)
                    .append(":")
                    .append(ticket.getId());
//			actionMessage += ";" +  POSConstants.TOTAL + ":" + NumberUtil.formatNumber(ticket.getTotalAmount());
			sb.append(";").append(Messages.getString("Total")).append(":").append(NumberUtil.formatNumber(ticket.getTotalAmount()));
//			ActionHistoryDAO.getInstance().saveHistory(Application.getCurrentUser(), ActionHistory.SETTLE_CHECK, actionMessage);
			ActionHistoryDAO.getInstance().saveHistory(Application.getCurrentUser(), ActionHistory.SETTLE_CHECK, sb.toString());
		}
	}

	private void closeTicketIfApplicable(Ticket ticket, Date currentDate) {
		if(ticket.getTicketType() == null || Ticket.DINE_IN.equals(ticket.getTicketType()) || Ticket.TAKE_OUT.equals(ticket.getTicketType())) {
			ticket.setClosed(true);
			ticket.setClosingDate(currentDate);
		}
	}

	public void refundTicket(Ticket ticket) throws Exception {
		Application application = Application.getInstance();
		User currentUser = Application.getCurrentUser();
		Terminal terminal = application.getTerminal();

		Session session = null;
		Transaction tx = null;

		GenericDAO dao = new GenericDAO();

		try {
			Double currentBalance = terminal.getCurrentBalance();
			Double totalPrice = ticket.getTotalAmount();
			double newBalance = currentBalance - totalPrice;
			terminal.setCurrentBalance(newBalance);

			ticket.setVoided(false);
			ticket.setPaid(false);
			ticket.setClosed(false);
			ticket.setDrawerResetted(false);
			ticket.setClosingDate(null);
			ticket.setReOpened(true);
			ticket.setTerminal(terminal);

			RefundTransaction posTransaction = new RefundTransaction();
			posTransaction.setTicket(ticket);

			posTransaction.setSubtotalAmount(ticket.getSubtotalAmount());
			posTransaction.setDiscountAmount(ticket.getDiscountAmount());
			posTransaction.setTaxAmount(ticket.getTaxAmount());
			posTransaction.setTotalAmount(ticket.getTotalAmount());

			posTransaction.setTerminal(terminal);
			posTransaction.setUser(currentUser);
			posTransaction.setTransactionTime(new Date());

			session = dao.getSession();
			tx = session.beginTransaction();

			dao.saveOrUpdate(ticket, session);
			dao.saveOrUpdate(posTransaction, session);
			dao.saveOrUpdate(terminal, session);

			tx.commit();

		} catch (Exception e) {
			try {
				if (tx!=null) tx.rollback();
			} catch (Exception x) {
			}

			throw e;
		} finally {
			dao.closeSession(session);
		}

	}

	public static PosTransactionService getInstance() {
		return paymentService;
	}
}
