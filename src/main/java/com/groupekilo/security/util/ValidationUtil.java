package com.groupekilo.security.util;

import java.util.regex.Pattern;

public class ValidationUtil {

    // Email regex pattern
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    // Validate email format
    public static boolean isValidEmail(String email) {
        return email != null && Pattern.compile(EMAIL_REGEX).matcher(email).matches();
    }

    // Validate password length (minimum 8 characters)
    public static boolean isValidPassword(String password) {
        return password != null && password.length() >= 8;
    }

    // Validate required fields
    public static boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }
}
