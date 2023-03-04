package com.unifacisa.ouvidoria.security;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

/**
 * Classe de criptografia das senhas dos usuários
 *
 * @author Darllinson Azevedo
 */
public abstract class PasswordEncrypt {
    private static final String IV;

    static {
        try {
            IV = IvParameterGenerator.generateIvParameter();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static final String encryptKey;

    static {
        try {
            encryptKey = SecretKeyGenerator.generateSecretKey();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Criptografar senha do usuário
     *
     * @author Darllinson Azevedo
     *
     * @param passwordToEncrypt Senha para ser criptografada
     * @return Senha criptografada
     * @throws Exception Se algum parâmetro Base64 estiver incompatível
     */
    public static String encrypt(String passwordToEncrypt) throws Exception {
        byte[] decodedEncryptKey = Base64.getDecoder().decode(encryptKey);

        Cipher encryptCipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(Arrays.copyOf(decodedEncryptKey, 16), "AES");
        encryptCipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8)));
        byte[] passwordEncrypted = encryptCipher.doFinal(passwordToEncrypt.getBytes(StandardCharsets.UTF_8));

        return Base64.getEncoder().encodeToString(passwordEncrypted);
    }

    /**
     * Descriptografar senha do usuário
     *
     * @author Darllinson Azevedo
     *
     * @param passwordToDecrypt Senha para ser descriptografada
     * @return Senha descriptografada
     * @throws Exception Se algum parâmetro Base64 estiver incompatível
     */
    public static String decrypt(String passwordToDecrypt) throws Exception {
        byte[] decodedEncryptKey = Base64.getDecoder().decode(encryptKey);

        Cipher decryptCipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(Arrays.copyOf(decodedEncryptKey, 16), "AES");
        decryptCipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8)));
        byte[] passwordDecrypted = decryptCipher.doFinal(Base64.getDecoder().decode(passwordToDecrypt));

        return new String(passwordDecrypted);
    }
}
