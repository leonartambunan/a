package com.pinpos.ui.views.order;

import java.awt.CardLayout;
import java.awt.Component;

import javax.swing.border.EmptyBorder;

import com.pinpos.ui.views.LoginScreen;
import com.pinpos.ui.views.SwitchboardView;
import com.pinpos.ui.views.payment.SettleTicketView;

public class RootView extends com.pinpos.swing.TransparentPanel {
	private CardLayout layout = new CardLayout();
	
	private LoginScreen loginScreen;
	private SwitchboardView switchboardView;
	private OrderView orderView;
	private SettleTicketView paymentView;

	
	private static RootView instance;
	
	private RootView() {
		setLayout(layout);
		setBorder(new EmptyBorder(3,3,3,3));
		
		loginScreen = new LoginScreen();
		addView(LoginScreen.VIEW_NAME, loginScreen);
		
		switchboardView = new SwitchboardView();
		addView(SwitchboardView.VIEW_NAME, switchboardView);
		
		orderView = OrderView.getInstance();
		orderView.init();
		addView(OrderView.VIEW_NAME, orderView);
		
		//paymentView = SettleTicketView.getInstance();
		//addView(SettleTicketView.VIEW_NAME, paymentView);
		
		showView(LoginScreen.VIEW_NAME);
	}
	
	public void addView(String viewName, Component view) {
		add(view, viewName);
	}
	
	public void showView(String viewName) {
		layout.show(this, viewName);
	}

	public OrderView getOrderView() {
		return orderView;
	}
	
	public void setOrderView(OrderView orderView) {
		this.orderView = orderView;
	}
	
	public LoginScreen getLoginScreen() {
		return loginScreen;
	}

	public SwitchboardView getSwitchboadView() {
		return switchboardView;
	}

	public void setSwitchboardView(SwitchboardView switchboardView) {
		this.switchboardView = switchboardView;
	}

	public synchronized static RootView getInstance() {
		if(instance == null) {
			instance = new RootView();
		}
		return instance;
	}

	public SettleTicketView getPaymentView() {
		return paymentView;
	}
}
