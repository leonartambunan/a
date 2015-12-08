package com.pinpos.main;

import com.pinpos.ui.dialog.LicenseDialog;
import com.pinpos.util.ILicense;
import com.pinpos.util.License;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;


public class Main  {

	private static final String DEVELOPMENT_MODE = "developmentMode";
    public static ILicense license = new License();
	public static void main(String[] args) throws Exception {

        Options options = new Options();
        options.addOption(DEVELOPMENT_MODE, true, "State if this is developmentMode");
        Application application = Application.getInstance();
        CommandLineParser parser = new BasicParser();
        CommandLine commandLine = parser.parse(options, args);
        String optionValue = commandLine.getOptionValue(DEVELOPMENT_MODE);
        if (optionValue != null) {
            application.setDevelopmentMode(Boolean.valueOf(optionValue));
        }

        if (license.isLicenseValid()) {
            application.start();
        } else {
            new LicenseDialog(application).setVisible(true);
        }

    }



}

