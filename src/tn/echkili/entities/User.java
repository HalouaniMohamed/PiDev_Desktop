/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.echkili.entities;

import java.sql.Date;
import java.time.LocalDateTime;

/**
 *
 * @author rayen
 */
public class User{
    private int id;
    private String UserName;
    private int Numero;
    private String Email;
    private String Adresse;
    private String Password;
    private String roles;
    public static User Current_User;
    

    public User(){
    }
    
    public User(int id,  String UserName, int Numero, String Email, String Adresse, String Password, String roles) {
        this.id = id;
        this.UserName = UserName;
        this.Numero = Numero;
        this.Email = Email;
        this.Adresse = Adresse;
        this.Password = Password;
        this.roles= roles;
    }

    public User( String UserName, int Numero, String Email, String Adresse, String Password) {
        
        this.UserName = UserName;
        this.Numero = Numero;
        this.Email = Email;
        this.Adresse = Adresse;
        this.Password = Password;
    }
     public User( String UserName, int Numero, String Email, String Adresse) {
        
        this.UserName = UserName;
        this.Numero = Numero;
        this.Email = Email;
        this.Adresse = Adresse;
      
    }
      public User(int id, String UserName, int Numero, String Email, String Adresse) {
        this.id = id;
        this.UserName = UserName;
        this.Numero = Numero;
        this.Email = Email;
        this.Adresse = Adresse;
      
    }
  public static User getCurrent_User() {
        return Current_User;
    }

    public static void setCurrent_User(User Current_User) {
        User.Current_User = Current_User;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public int getNumero() {
        return Numero;
    }

    public void setNumero(int Numero) {
        this.Numero = Numero;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String Adresse) {
        this.Adresse = Adresse;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
    
    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Users{" +  ", UserName=" + UserName + ", Numero=" + Numero + ", Email=" + Email + ", Adresse=" + Adresse + ", Password=" + Password + '}';
    }
    
    
}
