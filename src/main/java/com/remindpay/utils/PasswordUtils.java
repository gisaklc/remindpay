package com.remindpay.utils;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class PasswordUtils {
    public static String hash(String password) {
        return BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }

    public static boolean verify(String plainPassword, String hashedPassword) {
        return BCrypt.verifyer().verify(plainPassword.toCharArray(), hashedPassword).verified;
    }

}
