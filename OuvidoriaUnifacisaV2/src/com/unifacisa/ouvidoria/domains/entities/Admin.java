package com.unifacisa.ouvidoria.domains.entities;

/**
 * Classe de representação do usuário admin da aplicação
 *
 * @author Darllinson Azevedo
 */
public class Admin extends Person {
    public Admin(String name, String username, String password) {
        super(name, username, password);
        this.setAdmin();
    }
}
