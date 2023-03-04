package com.unifacisa.ouvidoria.security;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
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
     * @throws Exception Se ocorrer algum erro durante a geração da chave
     */
    public static String generateSecretKey() throws Exception {
        final int KEY_BIT_SIZE = 256;

        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = new SecureRandom();

        keyGenerator.init(KEY_BIT_SIZE, secureRandom);
        SecretKey secretKey = keyGenerator.generateKey();

        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }
}
