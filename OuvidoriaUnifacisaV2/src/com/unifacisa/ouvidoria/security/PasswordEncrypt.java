package com.unifacisa.ouvidoria.security;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
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
     */
    public static String encrypt(String passwordToEncrypt) {
        byte[] passwordEncrypted;

        try {
            byte[] decodedEncryptKey = Base64.getDecoder().decode(encryptKey);
            Cipher encryptCipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
            SecretKeySpec key = new SecretKeySpec(Arrays.copyOf(decodedEncryptKey, 16), "AES");
            encryptCipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8)));
            passwordEncrypted = encryptCipher.doFinal(passwordToEncrypt.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException
                 | InvalidKeyException
                 | NoSuchProviderException
                 | NoSuchPaddingException
                 | InvalidAlgorithmParameterException
                 | IllegalBlockSizeException
                 | BadPaddingException err) {
            throw new RuntimeException(err);
        }

        return Base64.getEncoder().encodeToString(passwordEncrypted);
    }

    /**
     * Descriptografar senha do usuário
     *
     * @author Darllinson Azevedo
     *
     * @param passwordToDecrypt Senha para ser descriptografada
     * @return Senha descriptografada
     */
    public static String decrypt(String passwordToDecrypt) {
        byte[] passwordDecrypted;

        try {
            byte[] decodedEncryptKey = Base64.getDecoder().decode(encryptKey);
            Cipher decryptCipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
            SecretKeySpec key = new SecretKeySpec(Arrays.copyOf(decodedEncryptKey, 16), "AES");
            decryptCipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8)));
            passwordDecrypted = decryptCipher.doFinal(Base64.getDecoder().decode(passwordToDecrypt));

        } catch (NoSuchAlgorithmException
                 | NoSuchProviderException
                 | NoSuchPaddingException
                 | InvalidKeyException
                 | InvalidAlgorithmParameterException
                 | IllegalBlockSizeException
                 | BadPaddingException err) {
            throw new RuntimeException(err);
        }

        return new String(passwordDecrypted);
    }
}
