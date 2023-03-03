package com.unifacisa.ouvidoria.utils;

import java.util.*;

import com.unifacisa.ouvidoria.utils.StringAlignUtils.Alignment;

/**
 * Classe com utilitários para formatação de dados
 *
 * @author Darllinson Azevedo
 */
public abstract class Formatter {
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[36m";
    private static final String ANSI_RESET = "\u001B[0m";

    /**
     * Imprimir linha
     *
     * @author Darllinson Azevedo
     *
     * @param size Tamanho da linha
     */
    public static void line(int size) {
        System.out.println("=".repeat(size));
    }

    /**
     * Imprimir cabeçalho
     *
     * @author Darllinson Azevedo
     *
     * @param text Título do cabeçalho
     * @param sizeOfLine Tamanho da linha separadora
     */
    public static void header(String text, int sizeOfLine) {
        StringAlignUtils textCenter = new StringAlignUtils(sizeOfLine, Alignment.CENTER);

        System.out.println("\n" + "-".repeat(sizeOfLine));
        System.out.print(textCenter.format(text));
        System.out.print("-".repeat(sizeOfLine) + "\n");
    }

    /**
     * Imprimir menu
     *
     * @author Darllinson Azevedo
     *
     * @param title Título do menu
     * @param options Lista de opções do menu
     */
    public static void menu(String title, List<String> options) {
        System.out.println("\n" + title + "\n");
        for (int i = 0; i < options.size(); i++) {
            System.out.println(String.format("%s%d)%s %s%s%s", ANSI_YELLOW, i + 1, ANSI_RESET, ANSI_BLUE,
                    options.get(i), ANSI_RESET));
        }
    }

    /**
     * Emitir mensagem de erro
     *
     * @author Darllinson Azevedo
     *
     * @param msg Mensagem
     */
    public static void errorEmitter(String msg) {
        System.out.println(String.format("%s%s%s%s%s", "\n", ANSI_RED, msg, ANSI_RESET, "\n"));
    }

    /**
     * Emitir mensagem de sucesso
     *
     * @author Darllinson Azevedo
     *
     * @param msg Mensagem
     */
    public static void successEmitter(String msg) {
        System.out.println(String.format("%s%s%s%s%s", "\n", ANSI_GREEN, msg, ANSI_RESET, "\n"));
    }
}
