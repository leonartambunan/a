package com.pinpos.util;

import org.apache.commons.codec.binary.Base64;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;

import java.security.MessageDigest;

public class Guid {

    public static  String constructGUID() {

        String guid = "";

        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        String processorId = (hal.getProcessor().getIdentifier());
        String processorName = (hal.getProcessor().getName());
        String processorSystemSN = (hal.getProcessor().getSystemSerialNumber());
        String processorVendor = (hal.getProcessor().getVendor());
        long  hdTotalSpace = hal.getFileStores()[0].getTotalSpace();

        String machineId = processorId + processorName + processorSystemSN + processorVendor + hdTotalSpace;

        machineId = machineId.trim().replace(" ","");

        try {

            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(machineId.getBytes("UTF-8"));

            Base64 base64 = new Base64(true);
            String key = base64.encodeAsString(md.digest()).trim().toUpperCase();
            key = key.replaceAll("_", "").replaceAll("-", "");

            guid = key;

        }catch (Exception e) {
            e.printStackTrace();
        }

        return guid;
    }
}
