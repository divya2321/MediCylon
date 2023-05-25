
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Encription;

import java.io.IOException;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * @author divsi
 */
public class Encript {

    Encript encriptInstance = null;

    private Encript() {
    }

    public Encript getEncript() {
        encriptInstance = new Encript();
        return encriptInstance;
    }

    private static final String algorithem = "AES";
    private static final byte[] keyValue = new byte[]{'2', '8', '5', 'd', 'S', 'w', 'S', 'j', 'Q', '1', '6', 'L', 'K', 'g', 'z', 'T'};

    public static String encript(String data){
        try{
            
        String encriptedValue = "";
        Key key = generateKey();
        Cipher c = Cipher.getInstance(algorithem);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(data.getBytes());
        encriptedValue = new BASE64Encoder().encode(encVal);
        return encriptedValue;
        
         } catch (Exception ex) {
            Logger.getLogger(Encript.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    public static String decript(String encriptedData) {
        try {
            Key key = generateKey();
            Cipher c = Cipher.getInstance(algorithem);
            c.init(Cipher.DECRYPT_MODE, key);
            byte[] decordedValue;
            decordedValue = new BASE64Decoder().decodeBuffer(encriptedData);

            byte[] decValue = c.doFinal(decordedValue);
            String decriptedValue = new String(decValue);
            return decriptedValue;
        } catch (Exception ex) {
            Logger.getLogger(Encript.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private static Key generateKey() {
        Key key = new SecretKeySpec(keyValue, algorithem);
        return key;
    }

    public static void main(String[] args) {
        try {
            System.out.println(decript("UaL7pCpnAe4bDOCKTvqECQ=="));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
