package com.pinpos.util;

import com.pinpos.config.AppConfig;
import com.pinpos.swing.MessageDialog;
import org.apache.commons.codec.binary.Base64;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.security.MessageDigest;

public class License implements  ILicense {

    public boolean isLicenseValid() {

        boolean machineIdValid = false;

        String machineId = AppConfig.getMachineId();
        System.out.println("machine id : "+machineId);
        String guid = Guid.constructGUID();

        if (guid.equalsIgnoreCase(machineId)) {
            machineIdValid = true;
        }

        if (machineIdValid) {

            try {
                MessageDigest merchantPass = MessageDigest.getInstance("SHA");
                merchantPass.update(guid.getBytes("UTF-8"));
                String key = new String(merchantPass.digest(),"UTF-8");

                Base64 base64 = new Base64(true);
                key = base64.encodeAsString(key.getBytes("UTF-8")).trim().toUpperCase();
                key = key.replaceAll("_", "").replaceAll("-", "");

                System.out.println("i:"+key);

                if (key.equals(AppConfig.getLicenceKey())) return true;

            } catch (Exception tableRowIndex) {
                tableRowIndex.printStackTrace();
            }
        } else {

            MessageDialog.showError("ID MESIN TIDAK VALID.\n Gunakan ID berikut sebagai machine_id pada pinposresto.properties. \n\n"+ guid +"\n\n(Note: sudah dicopy ke clipboard)");

            StringSelection defaultOrder = new StringSelection(guid);
            Clipboard name = Toolkit.getDefaultToolkit().getSystemClipboard();
            name.setContents(defaultOrder, defaultOrder);

            System.exit(0);
        }


        return false;

    }
}
