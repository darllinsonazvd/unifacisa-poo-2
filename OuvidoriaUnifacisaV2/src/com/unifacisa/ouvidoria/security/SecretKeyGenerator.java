package com.unifacisa.ouvidoria.security;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Classe para gerar chave de criptografia AES
 *
 * @author Aniket Kulkarni
 */
public abstract class SecretKeyGenerator {
    /**
     * Gerar chave de criptografia AES
     *
     * @author Aniket Kulkarni
     *
     * @return Chave de criptografia AES
     */
    public static String generateSecretKey() {
        final int KEY_BIT_SIZE = 256;

        SecretKey secretKey;

        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = new SecureRandom();
            keyGenerator.init(KEY_BIT_SIZE, secureRandom);
            secretKey = keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }
}
