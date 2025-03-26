package org.example.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Password {
    public static String hashPassword(String password) {
        try {
            // Create MessageDigest instance for SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Hash the password
            byte[] hash = digest.digest(password.getBytes());

            // Convert byte array to hex string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }

            return hexString.toString(); // Return the hashed password as a hex string
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null; // Return null in case of an error
    }
}
