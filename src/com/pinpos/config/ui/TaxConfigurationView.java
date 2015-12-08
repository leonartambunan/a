package com.pinpos.config.ui;

import com.pinpos.main.Application;
import com.pinpos.model.Restaurant;
import com.pinpos.model.dao.RestaurantDAO;
import com.pinpos.util.POSUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class TaxConfigurationView extends ConfigurationView {
	public static final String CONFIG_TAB_TAX = "Tax";
	private Restaurant restaurant;
	private JCheckBox cbItemSalesPriceIncludesTax;
	
	public TaxConfigurationView() {
		setLayout(new MigLayout("", "[]", "[]"));

		cbItemSalesPriceIncludesTax = new JCheckBox("Harga Item sudah termasuk pajak");
		add(cbItemSalesPriceIncludesTax, "cell 0 0");
	}

	@Override
	public boolean save() throws Exception {
		if (!isInitialized()) {
			return true;
		}

		restaurant.setItemPriceIncludesTax(cbItemSalesPriceIncludesTax.isSelected());

		RestaurantDAO.getInstance().saveOrUpdate(restaurant);

		Application.getInstance().refreshRestaurant();

		return true;
	}

	@Override
	public void initialize() throws Exception {
		restaurant = RestaurantDAO.getInstance().get(1);
		cbItemSalesPriceIncludesTax.setSelected(POSUtil.getBoolean(restaurant.isItemPriceIncludesTax()));

		setInitialized(true);
	}

	@Override
	public String getName() {
		return CONFIG_TAB_TAX;
	}

}
