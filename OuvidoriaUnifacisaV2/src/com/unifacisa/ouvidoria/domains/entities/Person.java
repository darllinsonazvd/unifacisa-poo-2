package com.unifacisa.ouvidoria.domains.entities;

import com.unifacisa.ouvidoria.utils.Formatter;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe de usuários da aplicação
 *
 * @author Darllinson Azevedo
 */
public abstract class Person {
    private String name;

    private boolean isAdmin;
    private String registry;
    private String password;

    private static List<Person> listOfPersons = new ArrayList<Person>();

    public Person(String name, String registry, String password) {
        this.name = name;
        this.registry = registry;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin() {
        isAdmin = true;
    }

    public String getRegistry() {
        return registry;
    }

    public void setResgistry(String resgistry) {
        this.registry = resgistry;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Recuperar lista de pessoas
     *
     * @return Lista de pessoas
     * @author Darllinson Azevedo
     */
    public static String getListOfPersons() {
        String result = "";

        for (Person person : listOfPersons) {
            result += person + "\n";
        }

        return result;
    }

    /**
     * Adicionar pessoa na lista de pessoas
     *
     * @param userToRegister Pessoa para ser adicionada
     * @author Darllinson Azevedo
     */
    public static String addPerson(Person userToRegister) {
        String userRegistry = userToRegister.getRegistry();

        for (Person person : listOfPersons) {
            if (person.getRegistry().equals(userRegistry)) {
                return "Usuario ja existe na plataforma, faca seu login!";
            }
        }

        listOfPersons.add(userToRegister);
        return "Usuario cadastrado com sucesso!";
    }

    /**
     * Verificar login do usuario
     *
     * @param registry Matricula
     * @param password Senha
     * @return Se o dados estiverem coretos, retorna true, se não, retorna false
     * @author Darllinson Azevedo
     */
    public static boolean verifyLogin(String registry, String password) {
        for (Person person : listOfPersons) {
            if (person.getRegistry().equals(registry)
                    && person.getPassword().equals(password)) {
                Formatter.successEmitter("Logado na plataforma!");
                return true;
            }
        }

        Formatter.errorEmitter("Matricula ou senha invalidas");
        return false;
    }

    /**
     * Recuperar usuário autenticado no sistema
     *
     * @param registry Matrícula do usuário
     * @param password Senha do usuário
     * @return Usuário autenticado
     */
    public static Person userAuthenticated(String registry, String password) {
        List<Person> userAuthenticated = listOfPersons.stream()
                .filter(p -> p.getRegistry().equals(registry)
                        && p.getPassword().equals(password))
                .toList();

        return userAuthenticated.get(0);
    }

    @Override
    public String toString() {
        return "Nome: " + this.getName() + "\n" +
                "Matricula: " + this.getRegistry() + "\n" +
                "Eh admin: " + this.isAdmin();
    }
}
