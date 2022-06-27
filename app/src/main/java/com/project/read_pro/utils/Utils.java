package com.project.read_pro.utils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.text.NumberFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Locale;

public class Utils {
    private Utils(){}

    public static String priceConverter(double price){
        Locale locale = new Locale("en", "US");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        return currencyFormatter.format(price);
    }

    public static String numberWithComma(int number){
        return NumberFormat.getNumberInstance(Locale.US).format(number);
    }

    /**
     * This method convert string created_at to ago
     * @param stringDate example "2020-01-01T00:00:00.000Z"
     * @return String
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String timeAgo(String stringDate) {
        long time_ago = Instant.parse(stringDate).toEpochMilli() / 1000;
        long cur_time = (Calendar.getInstance().getTimeInMillis()) / 1000;
        long time_elapsed = cur_time - time_ago;
        long seconds = time_elapsed;
        int minutes = Math.round(time_elapsed / 60);
        int hours = Math.round(time_elapsed / 3600);
        int days = Math.round(time_elapsed / 86400);
        int weeks = Math.round(time_elapsed / 604800);
        int months = Math.round(time_elapsed / 2600640);
        int years = Math.round(time_elapsed / 31207680);

        // Seconds
        if (seconds <= 60) {
            return "just now";
        }
        //Minutes
        else if (minutes <= 60) {
            if (minutes == 1) {
                return "one minute ago";
            } else {
                return minutes + " minutes ago";
            }
        }
        //Hours
        else if (hours <= 24) {
            if (hours == 1) {
                return "an hour ago";
            } else {
                return hours + " hrs ago";
            }
        }
        //Days
        else if (days <= 7) {
            if (days == 1) {
                return "yesterday";
            } else {
                return days + " days ago";
            }
        }
        //Weeks
        else if (weeks <= 4.3) {
            if (weeks == 1) {
                return "a week ago";
            } else {
                return weeks + " weeks ago";
            }
        }
        //Months
        else if (months <= 12) {
            if (months == 1) {
                return "a month ago";
            } else {
                return months + " months ago";
            }
        }
        //Years
        else {
            if (years == 1) {
                return "one year ago";
            } else {
                return years + " years ago";
            }
        }
    }
}
