package com.unifacisa.ouvidoria.domains.feedbacks;

import com.unifacisa.ouvidoria.domains.entities.Person;
import com.unifacisa.ouvidoria.gateways.Feedback;
import com.unifacisa.ouvidoria.utils.Formatter;
import com.unifacisa.ouvidoria.utils.StringAlignUtils;
import com.unifacisa.ouvidoria.utils.Validator;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe de ideias/sugestões
 *
 * @author Darllinson Azevedo
 */
public abstract class Ideas {
    private static List<Feedback> listOfIdeas = new ArrayList<Feedback>();
    private static int count = 0;

    /**
     * Recuperar ideias do usuário, se for admin, de todos os usuários
     *
     * @author Darllinson Azevedo
     *
     * @param person Usuário para verificação
     * @return String com o resultado da busca
     */
    public static String getIdeas(Person person) {
        String result = "";
        count = 0;

        Formatter.header("Ideias", 100);

        if (person.isAdmin()) {
            if (!listOfIdeas.isEmpty()) {
                for (Feedback idea : listOfIdeas) {
                    result += count + 1 + " | " + idea + "\n";
                    count++;
                }

                return result;
            }
        }

        List<Feedback> userFeedbacks = listOfIdeas.stream()
                .filter(f -> f.getAuthor().getRegistry().equals(person.getRegistry()))
                .toList();

        for (Feedback idea : userFeedbacks) {
            result += count + 1 + " | " + idea + "\n";
            count++;
        }

        if (listOfIdeas.isEmpty() || userFeedbacks.isEmpty()) {
            StringAlignUtils textCenter = new StringAlignUtils(100, StringAlignUtils.Alignment.CENTER);
            return textCenter.format("Nao ha ideias");
        }

        return result;
    }

    /**
     * Adicionar feedback do tipo "Ideia" na lista de ideias
     *
     * @param idea Reclamação para ser adicionada
     */
    public static void addIdea(Feedback idea) {
        listOfIdeas.add(idea);
        Formatter.successEmitter("Ideia adicionada com sucesso!");
    }

    /**
     * Excluir uma ideia
     *
     * @param id Id da ideia
     */
    public static void deleteIdea(int id) {
        if (id == 0 || id > listOfIdeas.size()) {
            Formatter.errorEmitter("Ideia nao encontrada!");
        } else {
            listOfIdeas.remove(id - 1);
            Formatter.successEmitter("Ideia removida com sucesso!");
        }
    }

    /**
     * Editar uma ideia
     *
     * @param id Id da ideia
     */
    public static void editIdea(int id) {
        if (id == 0 || id > listOfIdeas.size()) {
            Formatter.errorEmitter("Ideia nao encontrada!");
        } else {
            String newIdea = Validator.readString("\nDigite sua nova reclamacao:\n");
            Feedback fbToEdit = listOfIdeas.get(id - 1);
            fbToEdit.setFeeback(newIdea);
            listOfIdeas.set(id - 1, fbToEdit);
            Formatter.successEmitter("Ideia editada com sucesso!");
        }
    }
}
