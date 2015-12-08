package com.pinpos.config.ui;

import com.pinpos.config.AppConfig;
import com.pinpos.main.Application;
import com.pinpos.swing.POSTextField;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class HostEnvConfigurationView extends ConfigurationView {

    public static final String CONFIG_TAB_TAX = "Env";

    private JCheckBox cbHostEnv = new JCheckBox("Mesin berjalan diatas Linux ARM (BeagleBone)");
    private POSTextField receiptFolder = new POSTextField();

	public HostEnvConfigurationView() throws HeadlessException {
		super();
		initUI();

	}

	protected void initUI() {


		setLayout(new MigLayout("fill", "[][grow,fill]", "[][][][][][][][grow,fill]"));

        setLayout(new MigLayout("", "[]", "[]"));

        add(cbHostEnv, "cell 0 0");

        JLabel lblTicketFooterMessage = new JLabel("Folder receipt (contoh. C:\\Temp\\receipt atau /opt/receipt:");
        add(lblTicketFooterMessage, "cell 0 1");

        add(receiptFolder, "cell 1 1");

	}

    public boolean save() throws Exception {

        Application.getInstance().setSystemInitialized(false);

        AppConfig.setArmLinuxFlag(String.valueOf(cbHostEnv.isSelected()));

        AppConfig.setReceiptFolderLocation(receiptFolder.getText());

        return true;

    }

	@Override
	public void initialize() throws Exception {

        String flag = AppConfig.getArmLinuxFlag();

        cbHostEnv.setSelected(Boolean.valueOf(flag));

        receiptFolder.setText(AppConfig.getReceiptFolderLocation());

        setInitialized(true);
	}

	@Override
	public String getName() {
		return "Host Environment";
	}

}
