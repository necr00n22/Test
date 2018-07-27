package com.mashushka.mashushka;

import android.content.Context;

import java.util.Date;
import java.util.Locale;

/**
 * Created by Mikhail Li (Jiub) on 27.07.2018.
 */

public class Utils {

    public static class DateUtils {
        public static String daysPassedString(Context context , long createdDate, long now) {
            Date timePassed = new Date(now - createdDate);
            long dPassed = timePassed.getTime() / (3600 * 24 * 1000) + 1;
            return String.format(Locale.getDefault() ,"%s %d", context.getString(R.string.day), dPassed);
        }

        public static String daysLeftString(Context context , long endDate, long now) {
            Date timeLeft = new Date (endDate - now);
            long dLeft = timeLeft.getTime() / (3600 * 24 * 1000);
            return String.format(Locale.getDefault() ,"%s %d %s", context.getString(R.string.days_left), dLeft, context.getResources().getQuantityString(R.plurals.days, (int) dLeft));
        }
    }

}
