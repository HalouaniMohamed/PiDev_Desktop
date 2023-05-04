/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Arrays;
import java.util.List;
import java.sql.Date;
import java.util.ArrayList;

public class User {

    private int id;
    private String email;
    private String address;
    private String password;
    private String full_name;
    private String img_user;
    private List<String> roles;
    private Date date_naissance;
    public static User Current_User;

    public User() {
    }

    public User(int id) {
        this.id = id;
    }

    public User(String email) {
        this.email = email;
    }

    public User(int id, String email, String address, String password, String full_name, String img_user, List<String> roles) {
        this.id = id;
        this.email = email;
        this.address = address;
        this.password = password;
        this.full_name = full_name;
        this.img_user = img_user;
        this.roles = roles;
    }

    public User(String email, String address, String password, String full_name, String img_user, List<String> roles) {
        this.email = email;
        this.address = address;
        this.password = password;
        this.full_name = full_name;
        this.img_user = img_user;
        this.roles = roles;
    }

    public User(String email, String address, String password, String full_name, String img_user, List<String> roles, Date naissance) {
        this.email = email;
        this.address = address;
        this.password = password;
        this.full_name = full_name;
        this.img_user = img_user;
        this.roles = roles;
        this.date_naissance = naissance;
    }

    public User(String email, String address, String full_name, String img_user, List<String> roles) {
        this.email = email;
        this.address = address;
        this.full_name = full_name;
        this.img_user = img_user;
        this.roles = roles;
    }

// enaa ahowa
    public User(String email, String address, String password, String full_name, String roles, Date date_naissance) {
        this.email = email;
        this.address = address;
        this.password = password;
        this.full_name = full_name;
        this.roles = new ArrayList<String>(Arrays.asList(roles));
        this.date_naissance = date_naissance;
    }

    public User(int id, String email, String address, String password, String full_name, String img_user) {
        this.id = id;
        this.email = email;
        this.address = address;
        this.password = password;
        this.full_name = full_name;
        this.img_user = img_user;
    }

    public User(int id, String full_name, String email, String password, String address, Date date_naissance, List<String> roles) {
        this.id = id;
        this.email = email;
        this.address = address;
        this.password = password;
        this.full_name = full_name;
        this.date_naissance = date_naissance;
        this.roles = roles;
    }

    public User(String email, String address, String password, String full_name, Date date_naissance, String img_user) {
        this.email = email;
        this.address = address;
        this.password = password;
        this.full_name = full_name;
        this.img_user = img_user;
        this.date_naissance = date_naissance;
    }

    public User(String email, String address, String full_name, String img_user, String password) {
        this.email = email;
        this.address = address;
        this.password = password;
        this.full_name = full_name;
        this.img_user = img_user;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(int id, String email, String address, String full_name, String img_user) {
        this.id = id;
        this.email = email;
        this.address = address;
        this.full_name = full_name;
        this.img_user = img_user;
    }

    public User(String email, String address, String password, String full_name, Date date_naissance) {
        this.email = email;
        this.address = address;
        this.password = password;
        this.full_name = full_name;
        this.date_naissance = date_naissance;
    }

    public User(String azizmansgmailcom, String address, String password, String mohamed_aziz_mansour, String oko, String image) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static User getCurrent_User() {
        return Current_User;
    }

    public static void setCurrent_User(User Current_User) {
        User.Current_User = Current_User;
    }

    public String getImg_user() {
        return img_user;
    }

    public void setImg_user(String img_user) {
        this.img_user = img_user;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", email='" + email + '\''
                + ", address='" + address + '\''
                + ", password='" + password + '\''
                + ", full_name='" + full_name + '\''
                + ", roles=" + roles
                + '}';
    }
}
