package com.pinpos.config.ui;

import com.pinpos.Messages;
import com.pinpos.config.AppConfig;
import com.pinpos.main.Application;
import com.pinpos.model.Restaurant;
import com.pinpos.model.dao.RestaurantDAO;
import com.pinpos.util.POSUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class TaxConfigurationView extends ConfigurationView {
	public static final String CONFIG_TAB_TAX = "Tax";
	public static final String COUPON_DEDUCTED_BEFORE_TAX = "coupen_deducted_before_tax";
	private Restaurant restaurant;
	private JCheckBox cbItemSalesPriceIncludesTax;
	//private JCheckBox cbCouponDeductedB4Tax;

	public TaxConfigurationView() {
		setLayout(new MigLayout("", "[]", "[]"));

		cbItemSalesPriceIncludesTax = new JCheckBox(Messages.getString("Item.Include.Tax"));
		add(cbItemSalesPriceIncludesTax, "cell 0 0");

       // cbCouponDeductedB4Tax = new JCheckBox(Messages.getString("Coupon.Deducted.Before.Tax"));
		//add(cbCouponDeductedB4Tax, "cell 0 1");
	}

	@Override
	public boolean save() throws Exception {
		if (!isInitialized()) {
			return true;
		}

		//AppConfig.setCouponDeductedBeforeTax(cbCouponDeductedB4Tax.isSelected());

		restaurant.setItemPriceIncludesTax(cbItemSalesPriceIncludesTax.isSelected());

		RestaurantDAO.getInstance().saveOrUpdate(restaurant);

		Application.getInstance().refreshRestaurant();


		return true;
	}

	@Override
	public void initialize() throws Exception {
		restaurant = RestaurantDAO.getInstance().get(1);
		cbItemSalesPriceIncludesTax.setSelected(POSUtil.getBoolean(restaurant.isItemPriceIncludesTax()));

        //cbCouponDeductedB4Tax.setSelected(AppConfig.isCouponDeductedBeforeTax());

		setInitialized(true);
	}

	@Override
	public String getName() {
		return Messages.getString(CONFIG_TAB_TAX);
	}

}
