/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import entities.User;

/**
 *
 * @author rayen
 */
public final class SessionManager {

    private static SessionManager instance;

    private static User currentUser;
    private static int id;
    private static int CIN;
    private static String UserName;
    private static int Numero;
    private static String Email;
    private static String Adresse;

    private static String roles;

    //SessionManager.getInstace(rs.getInt("id"),rs.getInt("cin"),rs.getString("user_name"),rs.getInt("numero"),rs.getString("email"),rs.getString("adresse"),rs.getString("roles"));
    private SessionManager(int id, String user_name, int numero, String email, String address, String role) {
        SessionManager.id = id;
        SessionManager.UserName = user_name;
        SessionManager.Numero = numero;
        SessionManager.Email = email;
        SessionManager.Adresse = address;
        SessionManager.roles = role;
    }

    /**
     *
     * @param userName
     * @param employeeId
     * @param privileges
     * @return
     */
    public static SessionManager getInstace(int id, String user_name, int numero, String email, String address, String role) {
        if (instance == null) {
            instance = new SessionManager(id, user_name, numero, email, address, role);
        }
        return instance;
    }

    public static SessionManager getInstance() {
        return instance;
    }

    public static void setInstance(SessionManager instance) {
        SessionManager.instance = instance;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        SessionManager.id = id;
    }

    public static String getUserName() {
        return UserName;
    }

    public static void setUserName(String UserName) {
        SessionManager.UserName = UserName;
    }

    public static int getNumero() {
        return Numero;
    }

    public static void setNumero(int Numero) {
        SessionManager.Numero = Numero;
    }

    public static String getEmail() {
        return Email;
    }

    public static void setEmail(String Email) {
        SessionManager.Email = Email;
    }

    public static String getAdresse() {
        return Adresse;
    }

    public static void setAdresse(String Adresse) {
        SessionManager.Adresse = Adresse;
    }

    public static String getRoles() {
        return roles;
    }

    public static void setRoles(String roles) {
        SessionManager.roles = roles;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        SessionManager.currentUser = currentUser;
    }

    public static void cleanUserSession() {
        id = 0;
        UserName = "";
        Numero = 0;
        Email = "";
        Adresse = "";
        roles = "";
    }

    @Override
    public String toString() {
        return "UserSession{"
                + "userName='" + UserName + '\''
                + "email='" + Email + '\''
                + "id = '" + id + '\''
                + ", privileges=" + roles
                + '}';
    }

    static class cleanUserSession {

        public cleanUserSession() {
            id = 0;
            UserName = "";
            Numero = 0;
            Email = "";
            Adresse = "";
            roles = "";
        }
    }
}
