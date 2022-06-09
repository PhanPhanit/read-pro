package com.project.read_pro.utils;

import java.text.NumberFormat;
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
}
