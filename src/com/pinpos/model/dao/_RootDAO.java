package com.pinpos.model.dao;

import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import com.pinpos.Database;
import com.pinpos.config.AppConfig;
import com.pinpos.model.ActionHistory;
import com.pinpos.model.AttendenceHistory;
import com.pinpos.model.CashDrawerResetHistory;
import com.pinpos.model.CookingInstruction;
import com.pinpos.model.CouponAndDiscount;
import com.pinpos.model.Customer;
import com.pinpos.model.DrawerPullReport;
import com.pinpos.model.Gratuity;
import com.pinpos.model.MenuCategory;
import com.pinpos.model.MenuGroup;
import com.pinpos.model.MenuItem;
import com.pinpos.model.MenuItemModifierGroup;
import com.pinpos.model.MenuItemShift;
import com.pinpos.model.MenuModifier;
import com.pinpos.model.MenuModifierGroup;
import com.pinpos.model.PayoutReason;
import com.pinpos.model.PayoutRecepient;
import com.pinpos.model.PosTransaction;
import com.pinpos.model.PrinterConfiguration;
import com.pinpos.model.Restaurant;
import com.pinpos.model.RestaurantTable;
import com.pinpos.model.Shift;
import com.pinpos.model.Tax;
import com.pinpos.model.Terminal;
import com.pinpos.model.Ticket;
import com.pinpos.model.TicketCouponAndDiscount;
import com.pinpos.model.TicketItem;
import com.pinpos.model.TicketItemModifier;
import com.pinpos.model.TicketItemModifierGroup;
import com.pinpos.model.User;
import com.pinpos.model.UserPermission;
import com.pinpos.model.UserType;
import com.pinpos.model.VoidReason;
import com.pinpos.model.ZipCodeVsDeliveryCharge;
import com.pinpos.model.inventory.InventoryGroup;
import com.pinpos.model.inventory.InventoryItem;
import com.pinpos.model.inventory.InventoryLocation;
import com.pinpos.model.inventory.InventoryMetaCode;
import com.pinpos.model.inventory.InventoryTransaction;
import com.pinpos.model.inventory.InventoryTransactionType;
import com.pinpos.model.inventory.InventoryUnit;
import com.pinpos.model.inventory.InventoryVendor;
import com.pinpos.model.inventory.InventoryWarehouse;
import com.pinpos.model.inventory.PurchaseOrder;
import com.pinpos.model.inventory.Recepie;
import com.pinpos.model.inventory.RecepieItem;

public abstract class _RootDAO extends com.pinpos.model.dao._BaseRootDAO {

	/*
	 * If you are using lazy loading, uncomment this Somewhere, you should call
	 * RootDAO.closeCurrentThreadSessions(); public void closeSession (Session
	 * session) { // do nothing here because the session will be closed later }
	 */

	/*
	 * If you are pulling the SessionFactory from a JNDI tree, uncomment this
	 * protected SessionFactory getSessionFactory(String configFile) { // If you
	 * have a single session factory, ignore the configFile parameter //
	 * Otherwise, you can set a meta attribute under the class node called
	 * "config-file" which // will be passed in here so you can tell what
	 * session factory an individual mapping file // belongs to return
	 * (SessionFactory) new
	 * InitialContext().lookup("java:/{SessionFactoryName}"); }
	 */
	
	public static void initialize(String configFileName, Configuration configuration) {
		com.pinpos.model.dao._RootDAO.setSessionFactory(configuration.buildSessionFactory());
	}

	public static Configuration getNewConfiguration(String configFileName) {
		AnnotationConfiguration configuration = new AnnotationConfiguration();
		configuration = configuration.addClass(ActionHistory.class);
		configuration = configuration.addClass(AttendenceHistory.class);
		configuration = configuration.addClass(CashDrawerResetHistory.class);
		configuration = configuration.addClass(CookingInstruction.class);
		configuration = configuration.addClass(CouponAndDiscount.class);
		configuration = configuration.addClass(Gratuity.class);
		configuration = configuration.addClass(MenuCategory.class);
		configuration = configuration.addClass(MenuGroup.class);
		configuration = configuration.addClass(MenuItem.class);
		configuration = configuration.addClass(MenuItemModifierGroup.class);
		configuration = configuration.addClass(MenuItemShift.class);
		configuration = configuration.addClass(MenuModifier.class);
		configuration = configuration.addClass(MenuModifierGroup.class);
		configuration = configuration.addClass(PayoutReason.class);
		configuration = configuration.addClass(PayoutRecepient.class);
		configuration = configuration.addClass(Restaurant.class);
		configuration = configuration.addClass(RestaurantTable.class);
		configuration = configuration.addClass(Shift.class);
		configuration = configuration.addClass(Tax.class);
		configuration = configuration.addClass(Terminal.class);
		configuration = configuration.addClass(Ticket.class);
		configuration = configuration.addClass(TicketCouponAndDiscount.class);
		configuration = configuration.addClass(TicketItem.class);
		configuration = configuration.addClass(TicketItemModifier.class);
		configuration = configuration.addClass(TicketItemModifierGroup.class);
		configuration = configuration.addClass(PosTransaction.class);
		configuration = configuration.addClass(User.class);
		configuration = configuration.addClass(VoidReason.class);
		configuration = configuration.addClass(DrawerPullReport.class);
		configuration = configuration.addClass(PrinterConfiguration.class);
		configuration = configuration.addClass(UserPermission.class);
		configuration = configuration.addClass(UserType.class);
		configuration = configuration.addClass(Customer.class);
		configuration = configuration.addClass(PurchaseOrder.class);
		configuration = configuration.addClass(ZipCodeVsDeliveryCharge.class);
		
		configuration = configureInventoryClasses(configuration);
		
		Database defaultDatabase = AppConfig.getDefaultDatabase();

		configuration = configuration.setProperty("hibernate.dialect", defaultDatabase.getHibernateDialect());
		configuration = configuration.setProperty("hibernate.connection.driver_class", defaultDatabase.getHibernateConnectionDriverClass());
		
		configuration = configuration.setProperty("hibernate.connection.url", AppConfig.getConnectString());
		configuration = configuration.setProperty("hibernate.connection.username", AppConfig.getDatabaseUser());
		configuration = configuration.setProperty("hibernate.connection.password", AppConfig.getDatabasePassword());
		configuration = configuration.setProperty("hibernate.hbm2ddl.auto", "update");
		configuration = configuration.setProperty("hibernate.connection.autocommit", "false");
		configuration = configuration.setProperty("hibernate.max_fetch_depth", "3");
		configuration = configuration.setProperty("hibernate.show_sql", "false");

		return configuration;
	}
	
	private static AnnotationConfiguration configureInventoryClasses(AnnotationConfiguration configuration) {
		configuration = configuration.addClass(InventoryGroup.class);
		configuration = configuration.addClass(InventoryItem.class);
		configuration = configuration.addClass(InventoryLocation.class);
		configuration = configuration.addClass(InventoryMetaCode.class);
		configuration = configuration.addClass(InventoryTransaction.class);
		configuration = configuration.addClass(InventoryTransactionType.class);
		configuration = configuration.addClass(InventoryUnit.class);
		configuration = configuration.addClass(InventoryVendor.class);
		configuration = configuration.addClass(InventoryWarehouse.class);
		configuration = configuration.addClass(Recepie.class);
		configuration = configuration.addClass(RecepieItem.class);
		
		return configuration;
	}
	
	public static Configuration reInitialize() {
		Configuration configuration = getNewConfiguration(null);
		com.pinpos.model.dao._RootDAO.setSessionFactory(configuration.buildSessionFactory());
		
		return configuration;
	}
	
	public void refresh(Object obj) {
		Session session = createNewSession();
		super.refresh(obj, session);
		session.close();
	}
}