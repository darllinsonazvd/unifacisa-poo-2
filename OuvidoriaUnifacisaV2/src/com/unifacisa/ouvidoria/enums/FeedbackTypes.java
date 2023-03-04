package com.unifacisa.ouvidoria.enums;

/**
 * Tipos de feedback
 *
 * @author Darllinson Azevedo
 */
public enum FeedbackTypes {
    CLAIM, COMPLIMENT, IDEA, ALL;

    /**
     * Traduzir tipos dos feedbacks para PT-BR
     *
     * @author Darllinson Azevedo
     *
     * @return Tipo do feedback em português
     */
    @Override
    public String toString() {
        return switch (this.name()) {
            case "CLAIM" -> "Reclamacao";
            case "COMPLIMENT" -> "Elogio";
            case "IDEA" -> "Ideia";
            default -> "Todas";
        };
    }
}
