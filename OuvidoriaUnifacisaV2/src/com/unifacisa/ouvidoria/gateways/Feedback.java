package com.unifacisa.ouvidoria.gateways;

import com.unifacisa.ouvidoria.domains.entities.Person;
import com.unifacisa.ouvidoria.domains.feedbacks.Claims;
import com.unifacisa.ouvidoria.domains.feedbacks.Compliments;
import com.unifacisa.ouvidoria.domains.feedbacks.Ideas;
import com.unifacisa.ouvidoria.enums.FeedbackTypes;
import com.unifacisa.ouvidoria.models.FeedbackModel;

/**
 * Classe para controle de feedbacks da aplicação
 *
 * @author Darllinson Azevedo
 */
public class Feedback implements FeedbackModel {
    private int id = 1;
    private Person author;
    private FeedbackTypes type;
    private String feeback;

    public Feedback(Person author, FeedbackTypes type, String feeback) {
        this.author = author;
        this.type = type;
        this.feeback = feeback;
        this.id++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getAuthor() {
        return author;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }

    public FeedbackTypes getType() {
        return type;
    }

    public void setType(FeedbackTypes type) {
        this.type = type;
    }

    public String getFeeback() {
        return feeback;
    }

    public void setFeeback(String feeback) {
        this.feeback = feeback;
    }

    /**
     * Recuperar feedbacks do usuário, caso for admin, recupera de todos os usuários
     *
     * @author Darllinson Azevedo
     *
     * @param person Usuário para verificação
     * @param type Tipo dos feedbacks
     */
    public static void getFeedbacks(Person person, FeedbackTypes type) {
        switch (type) {
            case CLAIM -> System.out.println(Claims.getClaims(person));
            case COMPLIMENT -> System.out.println(Compliments.getCompliments(person));
            case IDEA -> System.out.println(Ideas.getIdeas(person));
            case ALL -> {
                System.out.print(Claims.getClaims(person));
                System.out.print(Compliments.getCompliments(person));
                System.out.print(Ideas.getIdeas(person));
            }
        }
    }

    /**
     * Adicionar feedback
     *
     * @author Darllinson Azevedo
     *
     * @param fb Feedback para ser adicionado
     * @param type Tipo do feedback
     */
    @Override
    public void addFeedback(Feedback fb, FeedbackTypes type) {
        switch (type) {
            case CLAIM -> Claims.addClaim(fb);
            case COMPLIMENT -> Compliments.addCompliment(fb);
            case IDEA -> Ideas.addIdea(fb);
        }
    }

    /**
     * Excluir feedback
     *
     * @author Darllinson Azevedo
     *
     * @param id Id do feedback
     * @param type Tipo do feedback
     */
    public static void deleteFeedback(int id, FeedbackTypes type) {
        switch (type) {
            case CLAIM -> Claims.deleteClaim(id);
            case COMPLIMENT -> Compliments.deleteCompliment(id);
            case IDEA -> Ideas.deleteIdea(id);
        }
    }

    /**
     * Editar feedback
     *
     * @author Darllinson Azevedo
     *
     * @param id Id do feedback
     * @param type Tipo do feedback
     */
    public static void editFeedback(int id, FeedbackTypes type) {
        switch (type) {
            case CLAIM -> Claims.editClaim(id);
            case COMPLIMENT -> Compliments.editCompliment(id);
            case IDEA -> Ideas.editIdea(id);
        }
    }

    @Override
    public String toString() {
        return this.getType().toString() + " | " +
                this.getAuthor().getName() + " | " +
                this.getFeeback();
    }
}
