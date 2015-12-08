package com.pinpos.config.ui;

import com.pinpos.Messages;
import com.pinpos.POSConstants;
import com.pinpos.PosException;
import com.pinpos.ui.dialog.POSDialog;
import com.pinpos.ui.dialog.POSMessageDialog;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ConfigurationDialog extends POSDialog implements ChangeListener, ActionListener {
	private static final String OK = com.pinpos.POSConstants.OK;
	private static final String CANCEL = com.pinpos.POSConstants.CANCEL;
	private JTabbedPane tabbedPane = new JTabbedPane();
	private List<ConfigurationView> views = new ArrayList<ConfigurationView>();
	
	
	public ConfigurationDialog(Frame parent) {
		super(parent, false);
		setTitle(Messages.getString("CONFIGURATION_WINDOW_TITLE")); //$NON-NLS-1$
		
		setLayout(new MigLayout("fill")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		
		add(tabbedPane, "span, grow" ); //$NON-NLS-1$
		
		RestaurantConfigurationView restaurantConfigurationView = new RestaurantConfigurationView();
		addView(restaurantConfigurationView);
		
		addView(new TaxConfigurationView());
		addView(new PrintConfigurationView());
		addView(new DrawerPullConfigurationView());
		addView(new CardConfigurationView());
		addView(new DatabaseConfigurationView());
		addView(new BannerConfigurationView());
		addView(new HostEnvConfigurationView());
		addView(new FeaturesView());
		addView(new LogoConfigurationView());

		tabbedPane.addChangeListener(this);
		
		JPanel bottomPanel = new JPanel(new MigLayout("fillx")); //$NON-NLS-1$
		
		JButton btnOk = new JButton(CANCEL);
		btnOk.addActionListener(this);
		bottomPanel.add(btnOk, "dock east, gaptop 5"); //$NON-NLS-1$
		JButton btnCancel = new JButton(OK);
		btnCancel.addActionListener(this);
		bottomPanel.add(btnCancel, "dock east, gapright 5, gaptop 5"); //$NON-NLS-1$
		
		add(bottomPanel, "newline,growx, gaptop 10"); //$NON-NLS-1$
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	public void addView(ConfigurationView view) {
		tabbedPane.addTab(view.getName(), view);
		views.add(view);
	}

	@Override
	public void setVisible(boolean b) {
		super.setVisible(b);
		
		if(b) {
			stateChanged(null);
		}
	}

	public void stateChanged(ChangeEvent e) {
		ConfigurationView view = (ConfigurationView) tabbedPane.getSelectedComponent();
		if(!view.isInitialized()) {
			try {
				view.initialize();
			} catch (Exception e1) {
				POSMessageDialog.showError(this, POSConstants.ERROR_MESSAGE, e1);
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		if(OK.equalsIgnoreCase(e.getActionCommand())) {
			try {
				for (ConfigurationView view : views) {
					if(view.isInitialized())
						view.save();
				}
				setCanceled(false);
				dispose();
			} catch (PosException x) {
				POSMessageDialog.showError(this, x.getMessage());
			} catch (Exception x) {
				POSMessageDialog.showError(this, POSConstants.ERROR_MESSAGE, x);
			}
		}
		else if(CANCEL.equalsIgnoreCase(e.getActionCommand())) {
			setCanceled(true);
			dispose();
		}
	}

}
