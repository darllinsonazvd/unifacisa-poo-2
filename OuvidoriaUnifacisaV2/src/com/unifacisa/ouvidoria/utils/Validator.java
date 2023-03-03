package com.unifacisa.ouvidoria.utils;

import java.util.*;

/**
 * Classe com utilitários para validação de dados
 *
 * @author Darllinson Azevedo
 */
public abstract class Validator {
    private static final Scanner textInput = new Scanner(System.in);
    private static final Scanner numberInput = new Scanner(System.in);

    /**
     * Ler número
     *
     * @author Darllinson Azevedo
     *
     * @param placeholder Descreva o que é sua entrada
     * @return Número inteiro, se a entrada não for um número retorna uma excessão
     */
    public static int readInt(String placeholder) {
        while (true) {
            System.out.print(placeholder);
            String number = numberInput.nextLine();

            if (number.matches("[0-9]+"))
                return Integer.parseInt(number);
            else
                Formatter.errorEmitter("Erro: Insira um numero inteiro!");
        }
    }

    /**
     * Ler texto
     *
     * @author Darllinson Azevvedo
     *
     * @param placeholder Descreva o que é sua entrada
     * @return Texto, se for vazio retorna uma excessão
     */
    public static String readString(String placeholder) {
        while (true) {
            System.out.print(placeholder);
            String text = textInput.nextLine();

            if (text.matches("(^\\s*$|^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.(?:[a-zA-Z]{2}|com|org|net|edu|gov|mil|biz|info|mobi|name|aero|asia|jobs|museum)$)"))
                Formatter.errorEmitter("Erro: Texto vazio, digite algo!");
            else
                return text;
        }
    }
}
