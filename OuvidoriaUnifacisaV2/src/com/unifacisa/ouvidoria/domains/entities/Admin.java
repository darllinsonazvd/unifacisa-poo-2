package com.unifacisa.ouvidoria.domains.entities;

/**
 * Classe de representação do usuário admin da aplicação
 *
 * @author Darllinson Azevedo
 */
public class Admin extends Person {
    public Admin(String name, String resgistry, String password) {
        super(name, resgistry, password);
        this.setAdmin();
    }
}
