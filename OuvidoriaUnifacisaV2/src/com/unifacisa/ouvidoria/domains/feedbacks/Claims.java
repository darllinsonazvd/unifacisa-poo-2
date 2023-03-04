package com.unifacisa.ouvidoria.domains.feedbacks;

import com.unifacisa.ouvidoria.domains.entities.Person;
import com.unifacisa.ouvidoria.gateways.Feedback;
import com.unifacisa.ouvidoria.utils.Formatter;
import com.unifacisa.ouvidoria.utils.StringAlignUtils;
import com.unifacisa.ouvidoria.utils.Validator;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe de reclamações
 *
 * @author Darllinson Azevedo
 */
public abstract class Claims {
    private static List<Feedback> listOfClaims = new ArrayList<Feedback>();
    private static int count = 0;

    /**
     * Recuperar reclamações do usuário, se for admin, de todos os usuários
     *
     * @author Darllinson Azevedo
     *
     * @param person Usuário para verificação
     * @return String com o resultado da busca
     */
    public static String getClaims(Person person) {
        String result = "";
        count = 0;

        Formatter.header("Reclamacoes", 100);

        if (person.isAdmin()) {
            if (!listOfClaims.isEmpty()) {
                for (Feedback claim : listOfClaims) {
                    result += count + 1 + " | " + claim + "\n";
                    count++;
                }

                return result;
            }
        }

        List<Feedback> userFeedbacks = listOfClaims.stream()
                .filter(f -> f.getAuthor().getUsername().equals(person.getUsername()))
                .toList();

        for (Feedback claim : userFeedbacks) {
            result += count + 1 + " | " + claim + "\n";
            count++;
        }

        if (listOfClaims.isEmpty() || userFeedbacks.isEmpty()) {
            StringAlignUtils textCenter = new StringAlignUtils(100, StringAlignUtils.Alignment.CENTER);
            return textCenter.format("Nao ha reclamacoes");
        }

        return result;
    }

    /**
     * Adicionar feedback do tipo "Reclamação" na lista de reclamações
     *
     * @author Darllinson Azevedo
     *
     * @param claim Reclamação para ser adicionada
     */
    public static void addClaim(Feedback claim) {
        listOfClaims.add(claim);
        Formatter.successEmitter("Reclamacao adicionada com sucesso!");
    }

    /**
     * Excluir uma reclamação
     *
     * @author Darllinson Azevedo
     *
     * @param id Id da reclamação
     */
    public static void deleteClaim(int id) {
        if (id == 0 || id > listOfClaims.size()) {
            Formatter.errorEmitter("Reclamacao nao encontrada!");
        } else {
            listOfClaims.remove(id - 1);
            Formatter.successEmitter("Reclamacao removida com sucesso!");
        }
    }

    /**
     * Editar uma reclamação
     *
     * @author Darllinson Azevedo
     *
     * @param id Id da reclamação
     */
    public static void editClaim(int id) {
        if (id == 0 || id > listOfClaims.size()) {
            Formatter.errorEmitter("Reclamacao nao encontrada!");
        } else {
            String newClaim = Validator.readString("\nDigite sua nova reclamacao:\n");
            Feedback fbToEdit = listOfClaims.get(id - 1);
            fbToEdit.setFeeback(newClaim);
            listOfClaims.set(id - 1, fbToEdit);
            Formatter.successEmitter("Reclamacao editada com sucesso!");
        }
    }
}
