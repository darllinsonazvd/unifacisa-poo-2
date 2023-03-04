package com.unifacisa.ouvidoria.domains.feedbacks;

import com.unifacisa.ouvidoria.domains.entities.Person;
import com.unifacisa.ouvidoria.gateways.Feedback;
import com.unifacisa.ouvidoria.utils.Formatter;
import com.unifacisa.ouvidoria.utils.StringAlignUtils;
import com.unifacisa.ouvidoria.utils.Validator;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe de elogios
 *
 * @author Darllinson Azevedo
 */
public abstract class Compliments {
    private static List<Feedback> listOfCompliments = new ArrayList<Feedback>();
    private static int count = 0;

    /**
     * Recuperar elogios do usuário, se for admin, de todos os usuários
     *
     * @author Darllinson Azevedo
     *
     * @param person Usuário para verificação
     * @return String com o resultado da busca
     */
    public static String getCompliments(Person person) {
        String result = "";
        count = 0;

        Formatter.header("Elogios", 100);

        if (person.isAdmin()) {
            if (!listOfCompliments.isEmpty()) {
                for (Feedback compliment : listOfCompliments) {
                    result += count + 1 + " | " + compliment + "\n";
                    count++;
                }

                return result;
            }
        }

        List<Feedback> userFeedbacks = listOfCompliments.stream()
                .filter(f -> f.getAuthor().getUsername().equals(person.getUsername()))
                .toList();

        for (Feedback compliment : userFeedbacks) {
            result += count + 1 + " | " + compliment + "\n";
            count++;
        }

        if (listOfCompliments.isEmpty() || userFeedbacks.isEmpty()) {
            StringAlignUtils textCenter = new StringAlignUtils(100, StringAlignUtils.Alignment.CENTER);
            return textCenter.format("Nao ha elogios");
        }

        return result;
    }

    /**
     * Adicionar feedback do tipo "Elogio" na lista de elogios
     *
     * @author Darllinson Azevedo
     *
     * @param compliment Elogio para ser adicionado
     */
    public static void addCompliment(Feedback compliment) {
        listOfCompliments.add(compliment);
        Formatter.successEmitter("Elogio adicionado com sucesso!");
    }

    /**
     * Excluir um elogio
     *
     * @author Darllinson Azevedo
     *
     * @param id Id do elogio
     */
    public static void deleteCompliment(int id) {
        if (id == 0 || id > listOfCompliments.size()) {
            Formatter.errorEmitter("Elogio nao encontrado!");
        } else {
            listOfCompliments.remove(id - 1);
            Formatter.successEmitter("Elogio removido com sucesso!");
        }
    }

    /**
     * Editar um elogio
     *
     * @author Darllinson Azevedo
     *
     * @param id Id do elogio
     */
    public static void editCompliment(int id) {
        if (id == 0 || id > listOfCompliments.size()) {
            Formatter.errorEmitter("Reclamacao nao encontrada!");
        } else {
            String newCompliment = Validator.readString("\nDigite sua nova reclamacao:\n");
            Feedback fbToEdit = listOfCompliments.get(id - 1);
            fbToEdit.setFeeback(newCompliment);
            listOfCompliments.set(id - 1, fbToEdit);
            Formatter.successEmitter("Elogio editado com sucesso!");
        }
    }
}
