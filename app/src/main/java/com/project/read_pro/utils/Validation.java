package com.project.read_pro.utils;

import java.util.regex.Pattern;

public class Validation {

    private Validation() {}

    public static boolean isValidEmail(String email){
        return !Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", email);
    }
    public static boolean isValidPassword(String password){
        return password.length() < 4;
        /**
         * At least one upper case English letter
         * At least one lower case English letter
         * At least one digit number
         * At least one special character
         * Minimum eight in length
         */
        // return !Pattern.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", password);
    }
}
