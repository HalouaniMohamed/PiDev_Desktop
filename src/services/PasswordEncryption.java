/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author rayen
 */
public class PasswordEncryption {

 private static final String ENCRYPTION_ALGORITHM = "AES";
    private static final String SECRET_KEY = "mysecretfreelancyyyyyyyy";

    public static String encrypt(String plaintext) throws Exception {
    SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY.getBytes(), ENCRYPTION_ALGORITHM);
    Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM + "/ECB/PKCS5Padding");
    cipher.init(Cipher.ENCRYPT_MODE, keySpec);
    byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes());
    return Base64.getEncoder().encodeToString(encryptedBytes);
}

public static String decrypt(String ciphertext) throws Exception {
    SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY.getBytes(), ENCRYPTION_ALGORITHM);
    Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM + "/ECB/PKCS5Padding");
    cipher.init(Cipher.DECRYPT_MODE, keySpec);
    byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(ciphertext));
    return new String(decryptedBytes);
}

private static String padString(String source) {
    char paddingChar = ' ';
    int size = 16;
    int x = source.length() % size;
    int padLength = size - x;

    for (int i = 0; i < padLength; i++) {
        source += paddingChar;
    }

    return source;
}

private static String unpadString(String source) {
    char paddingChar = ' ';
    int x = source.length() - 1;
    while (source.charAt(x) == paddingChar) {
        x--;
    }
    return source.substring(0, x + 1);
}
}