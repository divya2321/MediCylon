/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RandomValueGenerator;

import java.security.SecureRandom;
import java.util.Random;

/**
 *
 * @author divsi
 */
public class RandomValueGenerator {

    public static String generateRandomPassword() {
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }

        return sb.toString();
    }

    public static String generateRandomId() {
        char[] chars = "abcdefghijklmnopqrstuvwxyzABSDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
        Random r = new Random(System.currentTimeMillis());
        char[] id = new char[8];
        for (int i = 0; i < 8; i++) {
            id[i] = chars[r.nextInt(chars.length)];
        }
        return new String(id);
    }
    
    public static void main(String[] args) {
        System.out.println(generateRandomId());
        System.out.println(Encription.Encript.encript(generateRandomPassword()));
    }

   
}
