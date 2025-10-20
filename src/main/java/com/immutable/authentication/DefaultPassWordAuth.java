package com.immutable.authentication;

import com.immutable.request.accounts.user.User;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class DefaultPassWordAuth {
    private static final Map<String, Users> database = new HashMap<>();
    private static final int SALT_LENGTH = 16;
    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 256;

    public static String register(String username, String password) {
        try {
            byte[] salt = generateSalt();
            String hashedPassword = hashPassword(password, salt);
            Users user = new Users();
            user.setSalt(salt);
            user.setHashedPassword(hashedPassword);
            user.setUsername(username);
            user.setId(new Random().nextInt());
            database.put(username, user);
            return hashedPassword;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static byte[] generateSalt() {
        byte[] salt = new byte[SALT_LENGTH];
        new SecureRandom().nextBytes(salt);
        return salt;
    }

    private static String hashPassword(String password, byte[] salt) throws Exception {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] hash = factory.generateSecret(spec).getEncoded();

        return Base64.getEncoder().encodeToString(hash);
    }

    public static Boolean login(String username, String password) {
        Users record = database.get(username);
        if (record == null) {
            System.out.println("User not found!");
            return false;
        }

        try {
            String hashedInput = hashPassword(password, record.salt);
            if (hashedInput.equals(record.hashedPassword)) {
                return true;

            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
