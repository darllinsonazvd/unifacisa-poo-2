package com.unifacisa.ouvidoria.domains.entities;

import com.unifacisa.ouvidoria.security.PasswordEncrypt;
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
    private String username;
    private String password;

    private static List<Person> listOfPersons = new ArrayList<Person>();

    public Person(String name, String username, String password) {
        this.name = name;
        this.username = username;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
     * @author Darllinson Azevedo
     *
     * @return Lista de pessoas
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
     * @author Darllinson Azevedo
     *
     * @param userToRegister Pessoa para ser adicionada
     * @throws Exception Se algo der errado durante a criptografia
     */
    public static String addPerson(Person userToRegister) throws Exception {
        String userUsername = userToRegister.getUsername();

        String encryptUserPassword = PasswordEncrypt.encrypt(userToRegister.getPassword());
        userToRegister.setPassword(encryptUserPassword);

        for (Person person : listOfPersons) {
            if (person.getUsername().equals(userUsername)) {
                return "Ja existe um usuario com este nome de usuario!";
            }
        }

        listOfPersons.add(userToRegister);
        return "Usuario cadastrado com sucesso!";
    }

    /**
     * Verificar login do usuario
     *
     * @author Darllinson Azevedo
     *
     * @param username Nome de usuário
     * @param password Senha
     * @return Se o dados estiverem coretos, retorna true, se não, retorna false
     * @throws Exception Se algo der errado durante a descriptografia
     */
    public static boolean verifyLogin(String username, String password) throws Exception {
        for (Person person : listOfPersons) {
            String decryptedPassword = PasswordEncrypt.decrypt(person.getPassword());

            if (person.getUsername().equals(username)
                    && decryptedPassword.equals(password)) {
                Formatter.successEmitter("Logado na plataforma!");
                return true;
            }
        }

        Formatter.errorEmitter("Nome de usuario ou senha invalidas");
        return false;
    }

    /**
     * Recuperar usuário autenticado no sistema
     *
     * @author Darllinson Azevedo
     *
     * @param username Nome de usuário
     * @param password Senha do usuário
     * @return Usuário autenticado
     * @throws Exception Se algo der errado durante a criptografia
     */
    public static Person userAuthenticated(String username, String password) throws Exception {
        String encryptUserPassword = PasswordEncrypt.encrypt(password);

        List<Person> userAuthenticated = listOfPersons.stream()
                .filter(p -> p.getUsername().equals(username)
                        && p.getPassword().equals(encryptUserPassword))
                .toList();

        return userAuthenticated.get(0);
    }

    @Override
    public String toString() {
        return "Nome: " + this.getName() + "\n" +
                "Matricula: " + this.getUsername() + "\n" +
                "Eh admin: " + this.isAdmin();
    }
}
