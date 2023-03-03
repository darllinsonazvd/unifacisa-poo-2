package com.unifacisa.ouvidoria.models;

import com.unifacisa.ouvidoria.domains.entities.Person;
import com.unifacisa.ouvidoria.enums.FeedbackTypes;
import com.unifacisa.ouvidoria.gateways.Feedback;

/**
 * Interface para implementação de funções nao abstratas na classe "Feedback"
 *
 * @author Darllinson Azevedo
 */
public interface FeedbackModel {
    public void addFeedback(Feedback fb, FeedbackTypes type);
}
