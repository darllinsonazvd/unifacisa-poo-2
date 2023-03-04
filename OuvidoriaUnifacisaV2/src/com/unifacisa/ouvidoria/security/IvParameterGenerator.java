package com.unifacisa.ouvidoria.security;

import java.util.Random;

/**
 * Classe para gerar parâmetro IV da criptografia AES
 *
 * @author Darllinson Azevedo
 */
public abstract class IvParameterGenerator {
    /**
     * Gerar parâmetro IV
     *
     * @author Darllinson Azevedo
     *
     * @return Parâmetro IV
     */
    public static String generateIvParameter() {
        final int INITIAL = 97; // Letter 'a'
        final int FINAL = 122; // Letter 'z'
        final int STRING_LENGTH = 16;
        Random random = new Random();

        String generatedString = random.ints(INITIAL, FINAL + 1)
                .limit(STRING_LENGTH)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString.toUpperCase();
    }
}
