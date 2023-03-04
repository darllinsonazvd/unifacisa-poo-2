package com.unifacisa.ouvidoria.app;

import com.unifacisa.ouvidoria.auth.Auth;
import com.unifacisa.ouvidoria.domains.entities.Admin;
import com.unifacisa.ouvidoria.domains.entities.Person;
import com.unifacisa.ouvidoria.utils.Formatter;
import com.unifacisa.ouvidoria.utils.Validator;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        Person userLogged;
        boolean userIsAdmin;
        boolean userIsAuthenticated;

        boolean running = true;

        final String[] OPTIONS_ADMIN = {
                "Listar feedbacks",
                "Adicionar feedback",
                "Remover feedback",
                "Editar feedback",
                "Sair\n"
        };
        final List<String> OPTIONS_ADMIN_LIST = Arrays.asList(OPTIONS_ADMIN);

        final String[] OPTIONS_USER = {
                "Listar feedbacks",
                "Adicionar feedback",
                "Editar feedback",
                "Sair\n"
        };
        final List<String> OPTIONS_USER_LIST = Arrays.asList(OPTIONS_USER);

        final Admin ADMIN = new Admin("Diego Braga", System.getenv("USERNAME_ADMIN"), System.getenv("PASSWORD_ADMIN"));
        Person.addPerson(ADMIN);

        while (running) {
            Formatter.header("Bem-vindo ao Sistema de Ouvidoria da Unifacisa!", 100);

            Auth.authentication(ADMIN);
            userIsAuthenticated = Auth.userIsAuthenticated;
            userIsAdmin = Auth.userIsAdmin;
            userLogged = Auth.userLogged;

            while (userIsAuthenticated) {
                Formatter.header("Bem-vindo(a) " + userLogged.getName(), 100);
                Formatter.line(100);
                if (userIsAdmin) Formatter.menu("Selecione uma opcao:", OPTIONS_ADMIN_LIST);
                else Formatter.menu("Selecione uma opcao:", OPTIONS_USER_LIST);
                Formatter.line(100);

                int option = Validator.readInt("\nOpcao: ");

                switch (option) {
                    // Show all feedbacks
                    case 1 -> AppControls.getFeedbacksList(userLogged);
                    // Add feedback by category
                    case 2 -> AppControls.addFeedbackForm(userLogged);
                    /* Admin -> Delete feedback
                     User -> Edit yours feedbacks */
                    case 3 -> {
                        if (userIsAdmin) AppControls.deleteFeedbackForm(userLogged);
                        else AppControls.editFeedbackForm(userLogged);
                    }
                    /* Admin -> Edit all users feedbacks
                     User -> Quit */
                    case 4 -> {
                        if (userIsAdmin) AppControls.editFeedbackForm(userLogged);
                        else {
                            Formatter.header("Obrigado por utilizar o Sistema de Ouvidoria da Unifacisa!", 100);
                            userIsAuthenticated = false;
                            Auth.logout();
                        }
                    }
                    /* Admin -> Quit
                     User -> Invalid option */
                    case 5 -> {
                        if (userIsAdmin) {
                            Formatter.header("Obrigado por utilizar o Sistema de Ouvidoria da Unifacisa!", 100);
                            userIsAuthenticated = false;
                            Auth.logout();
                        } else Formatter.errorEmitter("Opcao invalida!");
                    }
                    // Invalid option
                    default -> Formatter.errorEmitter("Opcao invalida!");
                }
            }
        }
    }
}