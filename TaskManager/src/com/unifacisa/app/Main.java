package com.unifacisa.app;

import java.util.ArrayDeque;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        ArrayDeque<String> tasks = new ArrayDeque<String>();
        Scanner textInput = new Scanner(System.in);
        Scanner numberInput = new Scanner(System.in);

        int option;
        boolean running = true;

        do {
            System.out.println("------------- Menu ------------------");
            System.out.println("Escolha uma opcao:");
            System.out.println("1 - Inserir tarefa na pilha");
            System.out.println("2 - Obter a proxima tarefa da pilha");
            System.out.println("3 - Sair");
            System.out.println("-----------------------------------");
            option = numberInput.nextInt();

            switch (option) {
                case 1 -> {
                    System.out.println("Digite a tarefa a ser inserida:");
                    String tarefa = textInput.nextLine();
                    tasks.push(tarefa);
                    System.out.println("Tarefa inserida na pilha.");
                }

                case 2 -> {
                    if (tasks.isEmpty()) {
                        System.out.println("A pilha esta vazia.");
                    } else {
                        String proximaTarefa = tasks.pop();
                        System.out.println("Proxima tarefa: " + proximaTarefa);
                    }
                }

                case 3 -> {
                    System.out.println("Encerrando o programa...");
                    textInput.close();
                    numberInput.close();
                    running = false;
                }

                default -> System.out.println("Opção invalida. Tente novamente.");
            }
        } while (running);
    }
}