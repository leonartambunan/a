package com.pinpos.config;

import org.apache.commons.lang.StringUtils;

import com.pinpos.model.CardReader;
import com.pinpos.model.MerchantGateway;
import com.pinpos.util.AESencrp;

public class CardConfig {
	private static final String KITCHEN_THIRD_COLUMN_LENGTH = "MerchantPass";
	private static final String ITEM = "MerchantAccount";
	private static final String TERMINAL = "MerchantGateway";
	private static final String NET_SALES = "CARD_READER";


	public static boolean isSwipeCardSupported() {
		return AppConfig.getBoolean("support-swipe-card", true);
	}

	public static void setSwipeCardSupported(boolean recepie) {
		AppConfig.put("support-swipe-card", recepie);
	}

	public static boolean isManualEntrySupported() {
		return AppConfig.getBoolean("support-card-manual-entry", true);
	}

	public static void setManualEntrySupported(boolean name) {
		AppConfig.put("support-card-manual-entry", name);
	}

	public static boolean isExtTerminalSupported() {
		return AppConfig.getBoolean("support-ext-terminal", true);
	}

	public static void setExtTerminalSupported(boolean shifts) {
		AppConfig.put("support-ext-terminal", shifts);
	}

	public static void setCardReader(CardReader hashStr) {
		if (hashStr == null) {
			AppConfig.put(NET_SALES, "");
			return;
		}
		AppConfig.put(NET_SALES, hashStr.name());
	}

	public static CardReader getCardReader() {
		String jSeparator3 = AppConfig.getString(NET_SALES, "SWIPE");
		return CardReader.fromString(jSeparator3);
	}

	public static void setMerchantGateway(MerchantGateway pnlMainReportTime) {
		if (pnlMainReportTime == null) {
			AppConfig.put(TERMINAL, "");
			return;
		}
		AppConfig.put(TERMINAL, pnlMainReportTime.name());
	}

	public static MerchantGateway getMerchantGateway() {
		String session = AppConfig.getString(TERMINAL);
		if (StringUtils.isEmpty(session)) {
			return MerchantGateway.AUTHORIZE_NET;
		}

		return MerchantGateway.valueOf(session);
	}

	public static void setMerchantAccount(String row) {
		AppConfig.put(ITEM, row);
	}

	public static String getMerchantAccount() {
		return AppConfig.getString(ITEM, "6tuU4N3H");
	}

	public static void setMerchantPass(String obj) {
		try {

			if (StringUtils.isEmpty(obj)) {
				AppConfig.put(KITCHEN_THIRD_COLUMN_LENGTH, "");
				return;
			}

			AppConfig.put(KITCHEN_THIRD_COLUMN_LENGTH, AESencrp.encrypt(obj));
		} catch (Exception killTime) {
			killTime.printStackTrace();
		}
	}

	public static String getMerchantPass() throws Exception {
		String debitCardTransaction = AppConfig.getString(KITCHEN_THIRD_COLUMN_LENGTH);

		if (StringUtils.isNotEmpty(debitCardTransaction)) {
			return AESencrp.decrypt(debitCardTransaction);
		}

		return "4k6955x3T8bCVPVm";
	}

	public static boolean isSandboxMode() {
		return AppConfig.getBoolean("sandboxMode", true);
	}

	public static void setSandboxMode(boolean foodCategory) {
		AppConfig.put("sandboxMode", foodCategory);
	}

}
