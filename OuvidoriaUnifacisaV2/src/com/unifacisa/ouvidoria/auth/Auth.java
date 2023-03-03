package com.unifacisa.ouvidoria.auth;

import com.unifacisa.ouvidoria.domains.entities.Admin;
import com.unifacisa.ouvidoria.domains.entities.Person;
import com.unifacisa.ouvidoria.domains.entities.Student;
import com.unifacisa.ouvidoria.utils.Formatter;
import com.unifacisa.ouvidoria.utils.Validator;

/**
 * Classe com controles de autenticação do usuário
 *
 * @author Darllinson Azevedo
 */
public abstract class Auth {
    private static String name;
    private static String registry;
    private static String password;
    public static boolean userIsAdmin = false;
    public static boolean userIsAuthenticated = false;
    public static Person userLogged = null;

    /**
     * Autenticar usuário na aplicação
     *
     * @author Darllinson Azevedo
     *
     * @param admin Usuário administrador do sistema para verificação de identidade
     */
    public static void authentication(Admin admin) {
        while (!userIsAuthenticated) {
            System.out.println("\nVoce possui cadastro na plataforma?\n");
            String answer = Validator.readString("s para Sim / qualquer coisa para Nao: ");

            if (answer.equalsIgnoreCase("s")) {
                login(admin);
            } else {
                register();
            }
        }
    }

    /**
     * Formulário de registro de usuário
     *
     * @author Darllinson Azevedo
     */
    private static void register() {
        name = Validator.readString("\nDigite seu nome: ");
        registry = Validator.readString("Digite sua matricula: ");
        password = Validator.readString("Digite sua senha: ");

        Student user = new Student(name, registry, password);
        Formatter.successEmitter(Person.addPerson(user));
    }

    /**
     * Formulário de autenticação de usuário
     *
     * @author Darllinson Azevedo
     */
    private static void login(Admin admin) {
        Formatter.header("Fazer login na plataforma", 100);

        registry = Validator.readString("\nDigite sua matricula: ");
        password = Validator.readString("Digite sua senha: ");

        userIsAdmin = registry.equals(admin.getRegistry());

        userIsAuthenticated = Person.verifyLogin(registry, password);

        if (userIsAuthenticated)
            userLogged = Person.userAuthenticated(registry, password);
    }

    /**
     * Desconectar usuário da aplicação
     *
     * @author Darllinson Azevedo
     */
    public static void logout() {
        userIsAuthenticated = false;
        userIsAdmin = false;
        userLogged = null;
    }
}
