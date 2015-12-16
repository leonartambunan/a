package com.pinpos.ui.util;

import java.util.Calendar;
import java.util.Locale;

import org.jdesktop.swingx.JXDatePicker;

public class UiUtil {
	public static JXDatePicker getCurrentMonthStart() {
		Locale locale = Locale.getDefault();
		Calendar c = Calendar.getInstance(locale);
		c.set(Calendar.DAY_OF_MONTH, 1);

        return new JXDatePicker(c.getTime(), locale);
	}

	public static JXDatePicker getCurrentMonthEnd() {
		Locale locale = Locale.getDefault();
		
		Calendar c = Calendar.getInstance(locale);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));

        return new JXDatePicker(c.getTime(), locale);
	}

    public static JXDatePicker getCurrentDate() {
        Locale locale = Locale.getDefault();
        Calendar c = Calendar.getInstance(locale);

        return new JXDatePicker(c.getTime(), locale);
    }
}
