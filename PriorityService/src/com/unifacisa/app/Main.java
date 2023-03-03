package com.unifacisa.app;

import java.util.Scanner;

import com.unifacisa.entities.Client;

public class Main {
	public static void main(String[] args) {
		ServiceControls controls = new ServiceControls();
		Scanner textInput = new Scanner(System.in);
		Scanner numberInput = new Scanner(System.in);

		int option;
		String name;
		int age;
		int countPTokens = 0;
		int countNTokens = 0;

		boolean running = true;

		do {
			System.out.println("Bem vindo ao sistema de atendimento do Banco ABC\n");
			System.out.println("------------- Menu ------------------");
			System.out.println("Escolha uma opcao:");
			System.out.println("1 - Entrar na fila para ser atendido");
			System.out.println("2 - Ver fila");
			System.out.println("3 - Chamar proximo cliente");
			System.out.println("4 - Sair");
			System.out.println("-------------------------------------");

			option = numberInput.nextInt();

			switch (option) {
				case 1 -> {
					System.out.println("Insira seu nome: ");
					name = textInput.nextLine();
					System.out.println("Insira sua idade: ");
					age = numberInput.nextInt();

					if (age >= 60) {
						countPTokens++;
						Client pClient = new Client(name, 1, age, String.format("%d-P", countPTokens), 2);
						controls.add(pClient);
						System.out.println("Voce foi adicionado na fila de prioridade!");
					} else {
						countNTokens++;
						Client nClient = new Client(name, 1, age, String.format("%d-N", countNTokens), 1);
						controls.add(nClient);
						System.out.println("Voce foi adicionado na fila normal, aguarde as prioridades!");
					}
				}

				case 2 -> {
					if (controls.getQueue().isEmpty()) {
						System.out.println("Nao ha clientes na fila!");
					} else {
						System.out.println("-----------------------------------");
						System.out.println("\n" + controls.getQueue() + "\n");
						System.out.println("-----------------------------------");
					}
				}

				case 3 -> {
					if (controls.getQueue().isEmpty())
						System.out.println("Nao ha clientes para chamar!");
					else {
						System.out.println("-----------------------------------");
						System.out.println("\n" + controls.nextClient() + "\n");
						System.out.println("-----------------------------------");
					}
				}

				case 4 -> {
					System.out.println("Obrigado por usar o Banco ABC!");
					System.out.println("Encerrando programa...");
					textInput.close();
					numberInput.close();
					running = false;
				}

				default -> System.out.println("Opcao invalida. Tente novamente.");
			}
		} while (running);
		
		// SE QUISER TESTAR OS DADOS MOCKADOS, COMENTAR TUDO EM CIMA E DESCOMENTAR AQUI
		
//		Client c1 = new Client("Joao", 1, 19, "1-N", 1);
//		Client c2 = new Client("Pedro", 2, 23, "2-N", 1);
//		Client c3 = new Client("Cleito", 3, 56, "3-N", 1);
//		Client c4 = new Client("Thiago", 4, 62, "1-P", 2);
//		Client c5 = new Client("Igor", 5, 75, "2-P", 2);
//		Client c6 = new Client("Diego", 6, 82, "3-P", 2);
//		Client c7 = new Client("Davi", 7, 65, "4-P", 2);
//		Client c8 = new Client("Jose", 7, 65, "4-N", 1);
//		Client c9 = new Client("Jeremias", 7, 65, "5-P", 2);
//
//		controls.add(c1);
//		controls.add(c2);
//		controls.add(c3);
//		controls.add(c4);
//		controls.add(c5);
//		controls.add(c6);
//		controls.add(c7);
//		controls.add(c8);
//		controls.add(c9);
//
//		while (!controls.getQueue().isEmpty()) {
//			System.out.println(controls.nextClient() + "\n");
//		}
	}
}
