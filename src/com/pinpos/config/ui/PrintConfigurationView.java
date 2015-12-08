/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PrintConfiguration.java
 *
 * Created on Apr 5, 2010, 4:31:47 PM
 */

package com.pinpos.config.ui;

import java.awt.Component;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.*;

import com.pinpos.Messages;
import net.miginfocom.swing.MigLayout;

import com.pinpos.config.AppConfig;
import com.pinpos.config.PrintConfig;
import com.pinpos.ui.dialog.POSMessageDialog;

/**
 *
 * @author mshahriar
 */
public class PrintConfigurationView extends ConfigurationView {

	/** Creates new form PrintConfiguration */
	public PrintConfigurationView() {
		initComponents();
	}

	@Override
	public String getName() {
		return com.pinpos.POSConstants.CONFIG_TAB_PRINT;
	}

	@Override
	public void initialize() throws Exception {
		PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);

		cbReportPrinterName.setModel(new DefaultComboBoxModel(printServices));
		cbReceiptPrinterName.setModel(new DefaultComboBoxModel(printServices));
		cbKitchenPrinterName.setModel(new DefaultComboBoxModel(printServices));

		PrintServiceComboRenderer comboRenderer = new PrintServiceComboRenderer();
		cbReportPrinterName.setRenderer(comboRenderer);
		cbReceiptPrinterName.setRenderer(comboRenderer);
		cbKitchenPrinterName.setRenderer(comboRenderer);

		setSelectedPrinter(cbReportPrinterName, PrintConfig.REPORT_PRINTER_NAME);
		setSelectedPrinter(cbReceiptPrinterName, PrintConfig.RECEIPT_PRINTER_NAME);
		setSelectedPrinter(cbKitchenPrinterName, PrintConfig.KITCHEN_PRINTER_NAME);

        cbKitchenPrinterEnabled.setSelected(PrintConfig.isKitchenPrinterEnabled());

		setInitialized(true);

		if (printServices == null || printServices.length == 0) {
			POSMessageDialog.showMessage(Messages.getString("Printer.Not.Found"));
		}
	}

	private void setSelectedPrinter(JComboBox whichPrinter, String propertyName) {
		PrintService osDefaultPrinter = PrintServiceLookup.lookupDefaultPrintService();

		if (osDefaultPrinter == null) {
			return;
		}

		String receiptPrinterName = AppConfig.getString(propertyName, osDefaultPrinter.getName());

		int printerCount = whichPrinter.getItemCount();
		for (int i = 0; i < printerCount; i++) {
			PrintService printService = (PrintService) whichPrinter.getItemAt(i);
			if (printService.getName().equals(receiptPrinterName)) {
				whichPrinter.setSelectedIndex(i);
				return;
			}
		}
	}

	@Override
	public boolean save() throws Exception {
		PrintService printService = (PrintService) cbReportPrinterName.getSelectedItem();
		AppConfig.put(PrintConfig.REPORT_PRINTER_NAME, printService == null ? null : printService.getName());
		
		printService = (PrintService) cbReceiptPrinterName.getSelectedItem();
		AppConfig.put(PrintConfig.RECEIPT_PRINTER_NAME, printService == null ? null : printService.getName());

		printService = (PrintService) cbKitchenPrinterName.getSelectedItem();
		AppConfig.put(PrintConfig.KITCHEN_PRINTER_NAME, printService == null ? null : printService.getName());

        AppConfig.put(PrintConfig.KITCHEN_PRINTER_ENABLED, cbKitchenPrinterEnabled.isSelected());

		return true;
	}

	@SuppressWarnings("unchecked")
	private void initComponents() {
		setLayout(new MigLayout("", "[][grow,fill]", "[][][][18px][18px][18px][18px]"));
		
		JLabel lblReportPrinter = new JLabel("Printer Laporan");
		add(lblReportPrinter, "cell 0 0,alignx trailing");
		
		cbReportPrinterName = new JComboBox();
		add(cbReportPrinterName, "cell 1 0,growx");
		javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
		add(jLabel1, "cell 0 1,alignx right");

		jLabel1.setText(Messages.getString("Receipt.Printer")+":");
		cbReceiptPrinterName = new javax.swing.JComboBox();
		add(cbReceiptPrinterName, "cell 1 1,growx");
		javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
		add(jLabel2, "cell 0 2,alignx right");

		jLabel2.setText(Messages.getString("Kitchen.Printer") + ":");
		cbKitchenPrinterName = new javax.swing.JComboBox();
		add(cbKitchenPrinterName, "cell 1 2,growx");

		JLabel posTransaction = new JLabel();
		add(posTransaction, "cell 0 3,alignx right");
		cbKitchenPrinterEnabled = new JCheckBox(Messages.getString("Kitchen.Printer.Enabled"));
		add(cbKitchenPrinterEnabled, "cell 1 3,growx");

	}

	private JComboBox cbKitchenPrinterName;
	private JComboBox cbReceiptPrinterName;
	private JComboBox cbReportPrinterName;
	private JCheckBox cbKitchenPrinterEnabled;


	private class PrintServiceComboRenderer extends DefaultListCellRenderer {
		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
			JLabel listCellRendererComponent = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			PrintService printService = (PrintService) value;

			if (printService != null) {
				listCellRendererComponent.setText(printService.getName());
			}

			return listCellRendererComponent;
		}
	}

}
