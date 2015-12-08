package com.pinpos.ui.views.order;

import com.pinpos.POSConstants;
import com.pinpos.actions.SettleTicketAction;
import com.pinpos.main.Application;
import com.pinpos.model.ActionHistory;
import com.pinpos.model.MenuCategory;
import com.pinpos.model.MenuGroup;
import com.pinpos.model.MenuItem;
import com.pinpos.model.MenuModifier;
import com.pinpos.model.Ticket;
import com.pinpos.model.TicketItem;
import com.pinpos.model.User;
import com.pinpos.model.dao.ActionHistoryDAO;
import com.pinpos.model.dao.MenuItemDAO;
import com.pinpos.model.dao.TicketDAO;
import com.pinpos.ui.views.SwitchboardView;
import com.pinpos.ui.views.order.actions.CategorySelectionListener;
import com.pinpos.ui.views.order.actions.GroupSelectionListener;
import com.pinpos.ui.views.order.actions.ItemSelectionListener;
import com.pinpos.ui.views.order.actions.ModifierSelectionListener;
import com.pinpos.ui.views.order.actions.OrderListener;

public class OrderController implements OrderListener, CategorySelectionListener, GroupSelectionListener, ItemSelectionListener, ModifierSelectionListener {
	private OrderView orderView;

	public OrderController(OrderView orderView) {
		this.orderView = orderView;

		orderView.getCategoryView().addCategorySelectionListener(this);
		orderView.getGroupView().addGroupSelectionListener(this);
		orderView.getItemView().addItemSelectionListener(this);
		orderView.getModifierView().addModifierSelectionListener(this);
		orderView.getTicketView().addOrderListener(this);
	}

	public void categorySelected(MenuCategory foodCategory) {
		orderView.showView(GroupView.VIEW_NAME);
		orderView.getGroupView().setMenuCategory(foodCategory);
	}

	public void groupSelected(MenuGroup foodGroup) {
		orderView.showView(MenuItemView.VIEW_NAME);
		orderView.getItemView().setMenuGroup(foodGroup);

		//		ItemView itemView = new ItemView();
		//		itemView.setMenuGroup(foodGroup);
		//		PanelTester.width = 600;
		//		PanelTester.height = 600;
		//		PanelTester.test(itemView);
	}

	public void itemSelected(MenuItem menuItem) {
		MenuItemDAO dao = new MenuItemDAO();
		menuItem = dao.initialize(menuItem);

		boolean hasModifiers = (menuItem.getMenuItemModiferGroups() != null && menuItem.getMenuItemModiferGroups().size() > 0);

		TicketItem ticketItem = new TicketItem();
		ticketItem.setItemId(menuItem.getId());
		ticketItem.setItemCount(1);
		ticketItem.setName(menuItem.getName());
		ticketItem.setGroupName(menuItem.getParent().getName());
		ticketItem.setCategoryName(menuItem.getParent().getParent().getName());
		ticketItem.setUnitPrice(menuItem.getPrice(Application.getInstance().getCurrentShift()));
		ticketItem.setDiscountRate(menuItem.getDiscountRate());
		ticketItem.setTaxRate(menuItem.getTax() == null ? 0 : menuItem.getTax().getRate());
		ticketItem.setHasModifiers(hasModifiers);
		if (menuItem.getParent().getParent().isBeverage()) {
			ticketItem.setBeverage(true);
			ticketItem.setShouldPrintToKitchen(false);
		}
		else {
			ticketItem.setBeverage(false);
			ticketItem.setShouldPrintToKitchen(true);
		}
		orderView.getTicketView().addTicketItem(ticketItem);

		if (hasModifiers) {
			ModifierView modifierView = orderView.getModifierView();
			modifierView.setMenuItem(menuItem, ticketItem);
			orderView.showView(ModifierView.VIEW_NAME);

			//			ModifierView modifierView = new ModifierView();
			//			modifierView.setMenuItem(menuItem, ticketItem);
			//			PanelTester.width = 800;
			//			PanelTester.height = 600;
			//			PanelTester.test(modifierView);
		}
	}

	public void modifierSelected(MenuItem parent, MenuModifier modifier) {
		//		TicketItemModifier itemModifier = new TicketItemModifier();
		//		itemModifier.setItemId(modifier.getId());
		//		itemModifier.setName(modifier.getName());
		//		itemModifier.setPrice(modifier.getPrice());
		//		itemModifier.setExtraPrice(modifier.getExtraPrice());
		//		itemModifier.setMinQuantity(modifier.getMinQuantity());
		//		itemModifier.setMaxQuantity(modifier.getMaxQuantity());
		//		itemModifier.setTaxRate(modifier.getTax() == null ? 0 : modifier.getTax().getRate());
		//		
		//		orderView.getTicketView().addModifier(itemModifier);
	}

	public void itemSelectionFinished(MenuGroup parent) {
		MenuCategory menuCategory = parent.getParent();
		GroupView groupView = orderView.getGroupView();
		if (!menuCategory.equals(groupView.getMenuCategory())) {
			groupView.setMenuCategory(menuCategory);
		}
		orderView.showView(GroupView.VIEW_NAME);
	}

	public void modifierSelectionFiniched(MenuItem parent) {
		MenuGroup menuGroup = parent.getParent();
		MenuItemView itemView = orderView.getItemView();
		if (!menuGroup.equals(itemView.getMenuGroup())) {
			itemView.setMenuGroup(menuGroup);
		}
		orderView.showView(MenuItemView.VIEW_NAME);
	}

	public void payOrderSelected(Ticket ticket) {
		RootView.getInstance().showView(SwitchboardView.VIEW_NAME);
		new SettleTicketAction(ticket.getId()).execute();
		SwitchboardView.instance.updateTicketList();
	}

	//VERIFIED
	public synchronized static void saveOrder(Ticket ticket) {
		if (ticket == null)
			return;

		boolean newTicket = ticket.getId() == null;

		TicketDAO ticketDAO = new TicketDAO();
		ticketDAO.saveOrUpdate(ticket);

		ActionHistoryDAO actionHistoryDAO = ActionHistoryDAO.getInstance();
		User user = Application.getCurrentUser();
		
		if (newTicket) {
			actionHistoryDAO.saveHistory(user, ActionHistory.NEW_CHECK, POSConstants.RECEIPT_REPORT_TICKET_NO_LABEL + ":" + ticket.getId());
		}
		else {
			actionHistoryDAO.saveHistory(user, ActionHistory.EDIT_CHECK, POSConstants.RECEIPT_REPORT_TICKET_NO_LABEL + ":" + ticket.getId());
		}
	}
}
