package com.brandex.util; 

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordManager {

    private static final String HASH_ALGORITHM = "SHA-256";
    private static final int PASSWORD_HISTORY_LIMIT = 2;

    private PasswordManager() {}

    public static String hashPassword(String plainPassword) {
        
        if (plainPassword == null || plainPassword.isEmpty()) {
            System.out.println("ERROR: Password cannot be empty");
            return null;
        }

        try {
            MessageDigest md = MessageDigest.getInstance(
                                             HASH_ALGORITHM);
            byte[] hashBytes = md.digest(
                plainPassword.getBytes("UTF-8"));

            
            StringBuilder hexHash = new StringBuilder();
            for (byte b : hashBytes) {
                hexHash.append(String.format("%02x", b));
            }
            return hexHash.toString();

        } catch (Exception e) {
            System.out.println("ERROR: Hashing failed");
            return null;
        }
    }

    
    public static boolean verifyPassword(String plainPassword,
                                          String storedHash) {
        /*
         * Hash the plain text input
         * Compare result to stored hash directly
         * String comparison works here unlike BCrypt
         * because SHA-256 always produces same hash
         * for same input
         */
        if (plainPassword == null || storedHash == null) {
            return false;
        }

        String hashedInput = hashPassword(plainPassword);
        return storedHash.equals(hashedInput);
    }

    public static boolean isPasswordReused(String newPassword,
                                            String[] passwordHistory) {
        if (passwordHistory == null || 
            passwordHistory.length == 0) {
            return false;
        }

        /*
         * Hash the new password
         * Compare against each stored hash in history
         */
        String hashedNew = hashPassword(newPassword);

        for (int i = 0; i < passwordHistory.length && 
                        i < PASSWORD_HISTORY_LIMIT; i++) {
            if (passwordHistory[i] == null) continue;

            if (passwordHistory[i].equals(hashedNew)) {
                System.out.println("ERROR: Cannot reuse your " +
                                   "last " + PASSWORD_HISTORY_LIMIT 
                                   + " passwords");
                return true;
            }
        }
        return false;
    }

    public static String generateTempPassword() {
        /*
         * Same generation logic as before
         * No library dependency needed here either
         */
        java.util.Random random = new java.util.Random();
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String special = "!@#$%^&*";
        String allChars = upper + lower + digits + special;

        StringBuilder tempPassword = new StringBuilder();

        tempPassword.append(upper.charAt(
            random.nextInt(upper.length())));
        tempPassword.append(lower.charAt(
            random.nextInt(lower.length())));
        tempPassword.append(digits.charAt(
            random.nextInt(digits.length())));
        tempPassword.append(special.charAt(
            random.nextInt(special.length())));

        for (int i = 4; i < 8; i++) {
            tempPassword.append(allChars.charAt(
                random.nextInt(allChars.length())));
        }

        char[] passwordArray = tempPassword.toString().toCharArray();
        for (int i = passwordArray.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char temp = passwordArray[i];
            passwordArray[i] = passwordArray[j];
            passwordArray[j] = temp;
        }

        return new String(passwordArray);
    }
}