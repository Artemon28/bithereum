package com.example.demo.repositories;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;


public class DataEncrypter {

    private static String phrase = "Bella ciao, Bella ciao!";

    public DataEncrypter() {
    }

    public String encryptData(String data) {

        Security.addProvider(new BouncyCastleProvider());

        try {
            byte[] keyBytes = "mySecretKey12345".getBytes();
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);

            byte[] encryptedData = cipher.doFinal(data.getBytes());

            return Hex.toHexString(encryptedData);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public String decryptData(String encryptedData) {
        Security.addProvider(new BouncyCastleProvider());

        try {
            byte[] keyBytes = "mySecretKey12345".getBytes();
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC");
            cipher.init(Cipher.DECRYPT_MODE, keySpec);

            byte[] decryptedData = cipher.doFinal(Hex.decode(encryptedData));

            return new String(decryptedData);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
