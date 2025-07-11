package com.remindpay.dto;

public class PhoneUtils {

    public static String format(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isBlank()) {
            return null;
        }

        phoneNumber = phoneNumber.trim();

        if (!phoneNumber.startsWith("+")) {
            phoneNumber = "+55" + phoneNumber;
        }

        return phoneNumber;
    }
}

