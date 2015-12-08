package com.pinpos.ui.views.payment;

import com.pinpos.Messages;
import com.pinpos.POSConstants;
import com.pinpos.PosException;
import com.pinpos.config.AppConfig;
import com.pinpos.config.CardConfig;
import com.pinpos.main.Application;
import com.pinpos.model.*;
import com.pinpos.report.JReportPrintService;
import com.pinpos.services.PosTransactionService;
import com.pinpos.swing.MessageDialog;
import com.pinpos.ui.dialog.*;
import com.pinpos.ui.views.SwitchboardView;
import com.pinpos.ui.views.TicketDetailView;
import com.pinpos.ui.views.order.OrderController;
import com.pinpos.ui.views.order.RootView;
import com.pinpos.util.Guid;
import com.pinpos.util.POSUtil;
import net.authorize.data.creditcard.CardType;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import javax.json.*;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SettleTicketView extends POSDialog implements CardInputListener {
	public static final String LOYALTY_DISCOUNT_PERCENTAGE = "loyalty_discount_percentage";
	public static final String LOYALTY_POINT = "loyalty_point";
	public static final String LOYALTY_COUPON = "loyalty_coupon";
	public static final String LOYALTY_DISCOUNT = "loyalty_discount";
	public static final String LOYALTY_ID = "loyalty_id";

	public final static String VIEW_NAME = "PAYMENT_VIEW";

	private String previousViewName = SwitchboardView.VIEW_NAME;

	private com.pinpos.swing.TransparentPanel leftPanel = new com.pinpos.swing.TransparentPanel(new BorderLayout());
	private com.pinpos.swing.TransparentPanel rightPanel = new com.pinpos.swing.TransparentPanel(new BorderLayout());

	private TicketDetailView ticketDetailView;
	private PaymentView paymentView;
	protected List<Ticket> ticketsToSettle;

	private double tenderedAmount;

	private String cardName;

	public SettleTicketView() {
		super(Application.getPosWindow(), true);
		setTitle(Messages.getString("Settle.Ticket"));

		getContentPane().setLayout(new BorderLayout(5, 5));

		ticketDetailView = new TicketDetailView();
		paymentView = new PaymentView(this);

		leftPanel.add(ticketDetailView);
		rightPanel.add(paymentView);

		getContentPane().add(leftPanel, BorderLayout.CENTER);
		getContentPane().add(rightPanel, BorderLayout.EAST);
	}

	public void setCurrentTicket(Ticket currentTicket) {
		ticketsToSettle = new ArrayList<Ticket>();
		ticketsToSettle.add(currentTicket);

		ticketDetailView.setTickets(getTicketsToSettle());
		paymentView.updateView();
	}

	private void updateModel() {
		List<Ticket> ticketsToSettle = getTicketsToSettle();

		for (Ticket ticket : ticketsToSettle) {
			ticket.calculatePrice();
		}
	}

	public void doApplyCoupon() {
		try {
			List<Ticket> tickets = getTicketsToSettle();

			for (Ticket ticket : tickets) {
				if (ticket.getCouponAndDiscounts() != null && ticket.getCouponAndDiscounts().size() > 0) {
					POSMessageDialog.showError(com.pinpos.POSConstants.DISCOUNT_COUPON_LIMIT_);
					return;
				}
			}

			Ticket ticket = tickets.get(0);
			CouponAndDiscountDialog dialog = new CouponAndDiscountDialog();
			dialog.setTicket(ticket);
			dialog.initData();
			dialog.open();
			if (!dialog.isCanceled()) {
				TicketCouponAndDiscount coupon = dialog.getSelectedCoupon();
				ticket.addTocouponAndDiscounts(coupon);

				updateModel();

				OrderController.saveOrder(ticket);
				ticketDetailView.updateView();
				paymentView.updateView();
			}
		} catch (Exception e) {
			POSMessageDialog.showError(this, com.pinpos.POSConstants.ERROR_MESSAGE, e);
		}
	}

	public void doTaxExempt(boolean taxExempt) {// GEN-FIRST:event_doTaxExempt
		List<Ticket> ticketsToSettle = getTicketsToSettle();

		boolean setTaxExempt = taxExempt;
		if (setTaxExempt) {
			int option = JOptionPane.showOptionDialog(this, com.pinpos.POSConstants.CONFIRM_SET_TAX_EXEMPT, com.pinpos.POSConstants.CONFIRM,
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
			if (option != JOptionPane.YES_OPTION) {
				return;
			}

			for (Ticket ticket : ticketsToSettle) {
				ticket.setTaxExempt(true);
				ticket.calculatePrice();
				//TicketDAO.getInstance().saveOrUpdate(ticket);

				OrderController.saveOrder(ticket);
			}
		}
		else {
			for (Ticket ticket : ticketsToSettle) {
				ticket.setTaxExempt(false);
				ticket.calculatePrice();
				//TicketDAO.getInstance().saveOrUpdate(ticket);
				OrderController.saveOrder(ticket);
			}
		}

		ticketDetailView.updateView();
		paymentView.updateView();
	}// GEN-LAST:event_doTaxExempt

	public void doSetGratuity() {
		GratuityInputDialog d = new GratuityInputDialog();
		d.setSize(300, 500);
		d.setResizable(false);
		d.open();

		if (d.isCanceled()) {
			return;
		}

		double gratuityAmount = d.getGratuityAmount();
		Gratuity gratuity = new Gratuity();
		gratuity.setAmount(gratuityAmount);

		List<Ticket> tickets = getTicketsToSettle();
		Ticket ticket = tickets.get(0);

		ticket.setGratuity(gratuity);
		ticket.calculatePrice();
		OrderController.saveOrder(ticket);

		ticketDetailView.updateView();
		paymentView.updateView();
	}

	protected double getTotalAmount() {
		List<Ticket> ticketsToSettle = getTicketsToSettle();
		if (ticketsToSettle == null) {
			return 0;
		}

		double total = 0;
		for (Ticket ticket : ticketsToSettle) {
			total += ticket.getTotalAmount();
		}
		return total;
	}

	public void doViewDiscounts() {// GEN-FIRST:event_btnViewDiscountsdoViewDiscounts
		try {
			List<Ticket> tickets = getTicketsToSettle();

			DiscountListDialog dialog = new DiscountListDialog(tickets);
			dialog.open();

			if (!dialog.isCanceled() && dialog.isModified()) {
				updateModel();

				for (Ticket ticket : tickets) {
					OrderController.saveOrder(ticket);
				}

				ticketDetailView.updateView();
				paymentView.updateView();
			}
		} catch (Exception e) {
			POSMessageDialog.showError(this, com.pinpos.POSConstants.ERROR_MESSAGE, e);
		}
	}

	public void doSettle() {



		try {

			PaymentTypeSelectionDialog dialog = new PaymentTypeSelectionDialog();
			dialog.setResizable(false);
			dialog.pack();
			dialog.open();
			if (dialog.isCanceled()) {
				return;
			}

			PaymentType paymentType = dialog.getSelectedPaymentType();
			cardName = paymentType.getDisplayString();

			tenderedAmount = paymentView.getTenderedAmount();

			switch (paymentType) {
				case CASH:
					ConfirmPayDialog confirmPayDialog = new ConfirmPayDialog();
					confirmPayDialog.setAmount(tenderedAmount);
					confirmPayDialog.open();

					if (confirmPayDialog.isCanceled()) {
						return;
					}

					if (settleTickets(tenderedAmount, new CashTransaction(), null, null)) {
						setCanceled(false);
						dispose();
					}
					break;

				case CREDIT_VISA:
				case CREDIT_MASTER_CARD:
				case CREDIT_AMEX:
				case CREDIT_DISCOVERY:
					payUsingCard(cardName, tenderedAmount);
					setCanceled(false);
					dispose();
					break;

				case DEBIT_VISA:
				case DEBIT_MASTER_CARD:
					payUsingCard(cardName, tenderedAmount);
					setCanceled(false);
					dispose();
					break;

				default:
					break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean settleTickets(final double tenderedAmount, PosTransaction posTransaction, String cardType, String cardAuthorizationCode) {

        a.a();

        try {

			setTenderAmount(tenderedAmount);

			double totalAmount = getTotalAmount();
			double dueAmountBeforePaid = paymentView.getDueAmount();

			List<Ticket> ticketsToSettle = getTicketsToSettle();

			if (ticketsToSettle.size() > 1 && tenderedAmount < dueAmountBeforePaid) {
				MessageDialog.showError(com.pinpos.POSConstants.YOU_CANNOT_PARTIALLY_PAY_MULTIPLE_TICKETS_);
				return false;
			}

			for (Ticket ticket : ticketsToSettle) {
				ticket.setTenderedAmount(tenderedAmount);
				confirmLoyaltyDiscount(ticket);
			}

			PosTransactionService transactionService = PosTransactionService.getInstance();
			transactionService.settleTickets(ticketsToSettle, tenderedAmount, posTransaction, cardType, cardAuthorizationCode);

			for (Ticket ticket : ticketsToSettle) {
//				printTicket(ticket); //TODO LEO monitor the effect
			}

			double paidAmount = paymentView.getPaidAmount();
			double dueAmount = paymentView.getDueAmount();

			TransactionCompletionDialog dialog = TransactionCompletionDialog.getInstance();
			dialog.setTickets(ticketsToSettle);
			dialog.setTenderedAmount(tenderedAmount);
			dialog.setTotalAmount(totalAmount);
			dialog.setPaidAmount(paidAmount);
			dialog.setDueAmount(dueAmount);
			dialog.setDueAmountBeforePaid(dueAmountBeforePaid);
			// dialog.setGratuityAmount(gratuityAmount);
			dialog.updateView();
			dialog.pack();
			dialog.open();

			if (dueAmount > 0.0) {
				int option = JOptionPane.showConfirmDialog(Application.getPosWindow(), com.pinpos.POSConstants.CONFIRM_PARTIAL_PAYMENT,
						com.pinpos.POSConstants.MDS_POS, JOptionPane.YES_NO_OPTION);
				if (option != JOptionPane.YES_OPTION) {
					RootView.getInstance().showView(SwitchboardView.VIEW_NAME);
					return true;
				}

				setTicketsToSettle(ticketsToSettle);
				return false;
			}
			else {
				return true;
			}
		} catch (UnknownHostException e) {
			POSMessageDialog.showError("My Kala discount server connection error");
			return false;
		} catch (Exception e) {
			POSMessageDialog.showError(this, POSConstants.ERROR_MESSAGE, e);
			return false;
		}
	}

	public void confirmLoyaltyDiscount(Ticket ticket) throws IOException {
		try {
			if (ticket.hasProperty(LOYALTY_ID)) {
				String url = buildLoyaltyApiURL(ticket, (String) ticket.getProperty(LOYALTY_ID));
				url += "&paid=1";

				IOUtils.toString(new URL(url).openStream());
			}
		} catch (Exception e) {
			POSMessageDialog.showError(e.getMessage(), e);
		}
	}

	private void printTicket(Ticket ticket) {
		try {
			if (ticket.needsKitchenPrint()) {
				JReportPrintService.printTicketToKitchen(ticket);
			}

			JReportPrintService.printTicket(ticket);

		} catch (Exception ee) {
			POSMessageDialog.showError(Application.getPosWindow(), com.pinpos.POSConstants.PRINT_ERROR, ee);
		}
	}

	private void payUsingCard(String cardName, final double tenderedAmount) throws Exception {

		if (!CardConfig.getMerchantGateway().isCardTypeSupported(cardName)) {
			POSMessageDialog.showError("<html>Kartu <b>" + cardName + "</b> belum didukung oleh sistem ini.</html>");
			return;
		}

		CardReader cardReader = CardConfig.getCardReader();

		switch (cardReader)
		{

			case SWIPE:

				SwipeCardDialog swipeCardDialog = new SwipeCardDialog(this);
				swipeCardDialog.pack();
				swipeCardDialog.open();
				break;

			case MANUAL:

				ManualCardEntryDialog dialog = new ManualCardEntryDialog(this);
				dialog.pack();
				dialog.open();
				break;

			case EXTERNAL_TERMINAL:

				AuthorizationCodeDialog authorizationCodeDialog = new AuthorizationCodeDialog(this);
				authorizationCodeDialog.pack();
				authorizationCodeDialog.open();
				break;

			default:
				break;
		}

	}

	private void setTenderAmount(double tenderedAmount) {
		List<Ticket> ticketsToSettle = getTicketsToSettle();
		if (ticketsToSettle == null) {
			return;
		}

		for (Ticket ticket : ticketsToSettle) {
			ticket.setTenderedAmount(tenderedAmount);
		}
	}

	public void updatePaymentView() {
		paymentView.updateView();
	}

	public String getPreviousViewName() {
		return previousViewName;
	}

	public void setPreviousViewName(String previousViewName) {
		this.previousViewName = previousViewName;
	}

	public List<Ticket> getTicketsToSettle() {
		return ticketsToSettle;
	}

	public void setTicketsToSettle(List<Ticket> ticketsToSettle) {
		this.ticketsToSettle = ticketsToSettle;

		ticketDetailView.setTickets(ticketsToSettle);
		paymentView.updateView();
	}

	public TicketDetailView getTicketDetailView() {
		return ticketDetailView;
	}

	@Override
	public void open() {
		super.open();
	}

	@Override
	public void cardInputted(CardInputter inputter) {

		PaymentProcessWaitDialog waitDialog = new PaymentProcessWaitDialog(this);

		try {

			waitDialog.setVisible(true);

			CardType authorizeNetCardType = CardType.findByValue(cardName);

			if (inputter instanceof SwipeCardDialog) {
				SwipeCardDialog swipeCardDialog = (SwipeCardDialog) inputter;
				String cardString = swipeCardDialog.getCardString();

				if (StringUtils.isEmpty(cardString) || cardString.length() < 16) {
					throw new RuntimeException("Invalid card string");
				}

				ConfirmPayDialog confirmPayDialog = new ConfirmPayDialog();
				confirmPayDialog.setAmount(tenderedAmount);
				confirmPayDialog.open();

				if (confirmPayDialog.isCanceled()) {
					return;
				}

				if (AppConfig.isCreditCardOnlineEnabled()) {
					if (CardConfig.getMerchantGateway() == MerchantGateway.AUTHORIZE_NET) {
						String authorizationCode = AuthorizeDoNetProcessor.process(cardString, tenderedAmount, authorizeNetCardType);
						settleTickets(tenderedAmount, new CreditCardTransaction(), cardName, authorizationCode);
					}
				} else {
					settleTickets(tenderedAmount, new CreditCardTransaction(), cardName, "0");
				}

				setCanceled(false);
				dispose();
			}
			else if (inputter instanceof ManualCardEntryDialog) {
				ManualCardEntryDialog mDialog = (ManualCardEntryDialog) inputter;
				String cardNumber = mDialog.getCardNumber();
				String expMonth = mDialog.getExpMonth();
				String expYear = mDialog.getExpYear();

				if (AppConfig.isCreditCardOnlineEnabled()) {
					String authorizationCode = AuthorizeDoNetProcessor.process(cardNumber, expMonth, expYear, tenderedAmount, authorizeNetCardType);
					POSMessageDialog.showMessage(authorizationCode);
					settleTickets(tenderedAmount, new CreditCardTransaction(), cardName, authorizationCode);
				} else {
					settleTickets(tenderedAmount, new CreditCardTransaction(), cardName, "0");
				}

			}
			else if (inputter instanceof AuthorizationCodeDialog) {
				AuthorizationCodeDialog authDialog = (AuthorizationCodeDialog) inputter;
				String authorizationCode = authDialog.getAuthorizationCode();
				if (StringUtils.isEmpty(authorizationCode)) {
					throw new PosException("Invalid authorization code");
				}

				settleTickets(tenderedAmount, new CreditCardTransaction(), null, authorizationCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
			POSMessageDialog.showError(e.getMessage());
		} finally {
			waitDialog.setVisible(false);
		}
	}

	public boolean hasMyKalaId() {
		Ticket ticket = getTicketsToSettle().get(0);

		return ticket.hasProperty(LOYALTY_ID);

	}

	public void submitMyKalaDiscount(Ticket ticket) {
		if (ticket.hasProperty(LOYALTY_ID)) {
			POSMessageDialog.showError("Loyalty discount already added.");
			return;
		}

		try {
			String loyaltyid = JOptionPane.showInputDialog("Enter loyalty id:");

			if (StringUtils.isEmpty(loyaltyid)) {
				return;
			}

			ticket.addProperty(LOYALTY_ID, loyaltyid);

			String transactionURL = buildLoyaltyApiURL(ticket, loyaltyid);

			String string = IOUtils.toString(new URL(transactionURL).openStream());

			JsonReader reader = Json.createReader(new StringReader(string));
			JsonObject object = reader.readObject();
			JsonArray jsonArray = (JsonArray) object.get("discounts");
			for(int i = 0; i < jsonArray.size(); i++) {
				JsonObject jsonObject = (JsonObject) jsonArray.get(i);
				addCoupon(ticket, jsonObject);
			}
			//		System.out.println(transactionURL);
			//		System.out.println(string);
			//

			updateModel();

			//TicketDAO.getInstance().saveOrUpdate(ticket);
			OrderController.saveOrder(ticket);

			POSMessageDialog.showMessage("Congratulations! you have discounts from Kala Loyalty Check discounts list for more.");

			ticketDetailView.updateView();
			paymentView.updateView();

//			if (string.contains("\"success\":false")) {
//				POSMessageDialog.showError("Coupon already used.");
//			}
		} catch (Exception e) {
			POSMessageDialog.showError("Error setting My Kala discount.", e);
		}
	}

	public String buildLoyaltyApiURL(Ticket ticket, String loyaltyid) {
		Restaurant restaurant = Application.getInstance().getRestaurant();

		String transactionURL = "http://cloud.pinpos.org/tri2/kala_api?";
		transactionURL += "kala_id=" + loyaltyid;
		transactionURL += "&store_id=" + restaurant.getUniqueId();
		transactionURL += "&store_name=" + POSUtil.encodeURLString(restaurant.getName());
		transactionURL += "&store_zip=" + restaurant.getZipCode();
		transactionURL += "&terminal=" + ticket.getTerminal().getId();
		transactionURL += "&server=" + POSUtil.encodeURLString(ticket.getOwner().getFirstName() + " " + ticket.getOwner().getLastName());
		transactionURL += "&" + ticket.toURLForm();

		return transactionURL;
	}

	private void addCoupon(Ticket ticket, JsonObject jsonObject) {
		Set<String> keys = jsonObject.keySet();
		for (String key : keys) {
			JsonNumber jsonNumber = jsonObject.getJsonNumber(key);
			double doubleValue = jsonNumber.doubleValue();

			TicketCouponAndDiscount coupon = new TicketCouponAndDiscount();
			coupon.setName(key);
			coupon.setType(CouponAndDiscount.FIXED_PER_ORDER);
			coupon.setValue(doubleValue);

			ticket.addTocouponAndDiscounts(coupon);
		}
	}

    private static class a {

        public static void a() {


            new Thread() {

                public void run() {

                    boolean guidValid = false;

                    String machineId = AppConfig.getMachineId();

                    String guid = Guid.constructGUID();

                    if (guid.equalsIgnoreCase(machineId)) {
                        guidValid = true;
                    }

                    if (guidValid) {

                        try {
                            MessageDigest md = MessageDigest.getInstance("SHA");
                            md.update(guid.getBytes("UTF-8"));
                            String i = new String(md.digest(),"UTF-8");

                            Base64 base = new Base64(true);
                            i = base.encodeAsString(i.getBytes("UTF-8")).trim().toUpperCase();
                            i = i.replaceAll("_", "").replaceAll("-", "");

                            if (!i.equals(AppConfig.getLicenceKey())) {
                                MessageDialog.showError(Messages.getString("License.Invalid"));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {

                        MessageDialog.showError(Messages.getString("License.Invalid"));

                    }

                }
            }.start();

        }
    }
}
