package com.pinpos.ui.views.order.actions;

import com.pinpos.model.MenuGroup;
import com.pinpos.model.MenuItem;

public interface ItemSelectionListener {
	void itemSelected(MenuItem menuItem);
	void itemSelectionFinished(MenuGroup parent);
}
