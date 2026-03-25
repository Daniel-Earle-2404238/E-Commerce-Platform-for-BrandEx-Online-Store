package com.brandex.util;

/**
 * Utility class responsible for validating all user inputs
 * across the BrandEx system.
 */

public class Validator {
    /* Regex Patterns:
     * Defined as constants so they are compiled once
     * and reused across all validation methods */
    
	private static final String NAME_REGEX = "^[a-zA-Z '-]+$";
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final String PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*]).{8,}$";

    /*
     * Private constructor prevents instantiation
     * This is a utility class, all methods are static
     * No objects of this class are created
     */
    private Validator() {}

    public static boolean isValidName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
		/* matches() searches a string for a match against a regular expression, 
		 * and returns the matches*/
        return name.matches(NAME_REGEX); 
    }

    public static boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        return email.matches(EMAIL_REGEX);
    }

    public static boolean isValidPassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            return false;
        }
        return password.matches(PASSWORD_REGEX);
    }

    public static boolean isValidProductID(int productID) {
        return productID > 0;
    }

    public static boolean isValidPrice(double price) {
        return price > 0;
    }

    public static boolean isValidQuantity(int quantity) {
        return quantity >= 0;
    }
}
