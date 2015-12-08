package com.pinpos.ui.views.order.actions;

import com.pinpos.model.MenuItem;
import com.pinpos.model.MenuModifier;

public interface ModifierSelectionListener {
	void modifierSelected(MenuItem parent, MenuModifier modifier);
	void modifierSelectionFiniched(MenuItem parent);
}
