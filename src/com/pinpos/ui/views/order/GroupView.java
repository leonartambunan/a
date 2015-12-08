/*
 * GroupView.java
 *
 * Created on August 5, 2006, 9:29 PM
 */

package com.pinpos.ui.views.order;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;

import com.pinpos.model.MenuCategory;
import com.pinpos.model.MenuGroup;
import com.pinpos.model.dao.MenuGroupDAO;
import com.pinpos.swing.MessageDialog;
import com.pinpos.swing.PosButton;
import com.pinpos.ui.views.order.actions.GroupSelectionListener;

/**
 *
 * @author  MShahriar
 */
public class GroupView extends SelectionView {
	private Vector<GroupSelectionListener> listenerList = new Vector<GroupSelectionListener>();
    
	private MenuCategory menuCategory;
    
    public static final String VIEW_NAME = "GROUP_VIEW";
    
    /** Creates new form GroupView */
    public GroupView() {
        super(com.pinpos.POSConstants.GROUPS);
        
        setBackEnable(false);
    }

	public MenuCategory getMenuCategory() {
		return menuCategory;
	}

	public void setMenuCategory(MenuCategory foodCategory) {
		this.menuCategory = foodCategory;

		//reset();
		
		if (foodCategory == null) {
			return;
		}
		
		try {
			MenuGroupDAO dao = new MenuGroupDAO();
			List<MenuGroup> groups = dao.findEnabledByParent(foodCategory);
			
			if(groups.size() == 1) {
				MenuGroup menuGroup = groups.get(0);
				fireGroupSelected(menuGroup);
				return;
			}

//			int v = 0;
//			List<MenuGroup> groups2 = new ArrayList<MenuGroup>();
//			for (MenuGroup menuGroup : groups) {
//				String name = menuGroup.getName();
//				for (int i = 0; i < 30; i++) {
//					MenuGroup menuGroup2 = new MenuGroup(menuGroup.getId());
//					menuGroup2.setParent(menuGroup.getParent());
//					menuGroup2.setName(name + (++v));
//					groups2.add(menuGroup2);
//				}
//			}
//			
//			setItems(groups2);
			
			setItems(groups);
		} catch (Exception e) {
			MessageDialog.showError(e);
		}
	}
	
	public void addGroupSelectionListener(GroupSelectionListener listener) {
		listenerList.add(listener);
	}
	
	public void removeGroupSelectionListener(GroupSelectionListener listener) {
		listenerList.remove(listener);
	}
	
	private void fireGroupSelected(MenuGroup foodGroup) {
		for (GroupSelectionListener listener : listenerList) {
			listener.groupSelected(foodGroup);
		}
	}
	
	@Override
	protected JButton createItemButton(Object item) {
		MenuGroup menuGroup = (MenuGroup) item;

        return new GroupButton(menuGroup);
	}
	
	
	private class GroupButton extends PosButton implements ActionListener {
		MenuGroup foodGroup;
		
		GroupButton(MenuGroup foodGroup) {
			this.foodGroup = foodGroup;
			
			setText("<html><body><center>" + foodGroup.getName() + "</center></body></html>");
			addActionListener(this);
		}

		public void actionPerformed(ActionEvent e) {
			fireGroupSelected(foodGroup);
		}
	}

	@Override
	public void doGoBack() {
	}
}
