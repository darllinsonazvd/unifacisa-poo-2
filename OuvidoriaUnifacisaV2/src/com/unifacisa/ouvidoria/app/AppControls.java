package com.unifacisa.ouvidoria.app;

import com.unifacisa.ouvidoria.domains.entities.Person;
import com.unifacisa.ouvidoria.enums.FeedbackTypes;
import com.unifacisa.ouvidoria.gateways.Feedback;
import com.unifacisa.ouvidoria.utils.Formatter;
import com.unifacisa.ouvidoria.utils.Validator;

import java.util.Arrays;
import java.util.List;

/**
 * Classe com métodos de controle da aplicação
 *
 * @author Darllinson Azevedo
 */
public abstract class AppControls {
    static final List<String> CATEGORIES = Arrays.asList("Reclamacao", "Elogio", "Ideia");

    /**
     * Recuperar feedbacks do usuário autenticado, se for admin, recupera todos os feedbacks
     *
     * @author Darllinson Azevedo
     *
     * @param userLogged Usuário autenticado
     */
    public static void getFeedbacksList(Person userLogged) {
        Feedback.getFeedbacks(userLogged, FeedbackTypes.ALL);
    }

    /**
     * Formulário para o usuário adicionar o feedback
     *
     * @author Darllinson Azevedo
     *
     * @param userLogged Usuário autenticado
     */
    public static void addFeedbackForm(Person userLogged) {
        Formatter.menu("Categorias:", CATEGORIES);

        int optionToAdd = Validator.readInt("\nQual a categoria do feedback deseja adicionar? (1 / 2 / 3): ");

        switch (optionToAdd) {
            case 1 -> {
                String claim = Validator.readString("\nDigite sua reclamacao:\n");
                Feedback fb = new Feedback(userLogged, FeedbackTypes.CLAIM, claim);
                fb.addFeedback(fb, FeedbackTypes.CLAIM);
            }
            case 2 -> {
                String compliment = Validator.readString("\nDigite seu elogio:\n");
                Feedback fb = new Feedback(userLogged, FeedbackTypes.COMPLIMENT, compliment);
                fb.addFeedback(fb, FeedbackTypes.COMPLIMENT);
            }
            case 3 -> {
                String idea = Validator.readString("\nDigite sua ideia:\n");
                Feedback fb = new Feedback(userLogged, FeedbackTypes.IDEA, idea);
                fb.addFeedback(fb, FeedbackTypes.IDEA);
            }
        }
    }

    /**
     * Formulário para o usuário excluir um feedback
     *
     * @author Darllinson Azevedo
     *
     * @param userLogged Usuário autenticado
     */
    public static void deleteFeedbackForm(Person userLogged) {
        Formatter.menu("Categorias:", CATEGORIES);
        int optionToRemove = Validator.readInt("\nQual a categoria do feedback deseja remover? (1 / 2 / 3): ");

        switch (optionToRemove) {
            case 1 -> {
                System.out.println("\nQual reclamacao deseja remover?\n");
                Feedback.getFeedbacks(userLogged, FeedbackTypes.CLAIM);

                int claimId = Validator.readInt("\nNumero da reclamacao: ");
                Feedback.deleteFeedback(claimId, FeedbackTypes.CLAIM);
            }
            case 2 -> {
                System.out.println("\nQual elogio deseja remover?\n");
                Feedback.getFeedbacks(userLogged, FeedbackTypes.COMPLIMENT);

                int complimentId = Validator.readInt("\nNumero do elogio: ");
                Feedback.deleteFeedback(complimentId, FeedbackTypes.COMPLIMENT);
            }
            case 3 -> {
                System.out.println("\nQual ideia deseja remover?\n");
                Feedback.getFeedbacks(userLogged, FeedbackTypes.IDEA);

                int ideaId = Validator.readInt("\nNumero da ideia: ");
                Feedback.deleteFeedback(ideaId, FeedbackTypes.IDEA);
            }
            default -> Formatter.errorEmitter("Categoria nao encontrada!");
        }
    }

    /**
     * Formulário para o usuário editar um feedback
     *
     * @author Darllinson Azevedo
     *
     * @param userLogged Usuário autenticado
     */
    public static void editFeedbackForm(Person userLogged) {
        Formatter.menu("Categorias:", CATEGORIES);
        int optionToEdit = Validator.readInt("\nQual a categoria do feedback deseja editar? (1 / 2 / 3): ");

        switch (optionToEdit) {
            case 1 -> {
                System.out.println("\nQual reclamacao deseja editar?\n");
                Feedback.getFeedbacks(userLogged, FeedbackTypes.CLAIM);

                int claimId = Validator.readInt("\nNumero da reclamacao: ");
                Feedback.editFeedback(claimId, FeedbackTypes.CLAIM);
            }
            case 2 -> {
                System.out.println("\nQual elogio deseja editar?\n");
                Feedback.getFeedbacks(userLogged, FeedbackTypes.COMPLIMENT);

                int complimentId = Validator.readInt("\nNumero do elogio: ");
                Feedback.editFeedback(complimentId, FeedbackTypes.COMPLIMENT);
            }
            case 3 -> {
                System.out.println("\nQual ideia deseja editar?\n");
                Feedback.getFeedbacks(userLogged, FeedbackTypes.IDEA);

                int ideaId = Validator.readInt("\nNumero da ideia: ");
                Feedback.editFeedback(ideaId, FeedbackTypes.IDEA);
            }
            default -> Formatter.errorEmitter("Categoria nao encontrada!");
        }
    }
}
