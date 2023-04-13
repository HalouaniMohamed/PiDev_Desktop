/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.echkili.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author rayen
 */
public class Connexion {
    
    final String URL="jdbc:mysql://127.0.0.1:3306/echkili1";
    final String USER ="root";
    final String PWD ="";
    private Connection cnx ;
    private static Connexion instance ;
  

    public  Connexion() {
    
       try {
                cnx = DriverManager.getConnection(URL, USER, PWD);
                System.out.println("connexion etablie ......");
         
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static Connexion getInstance() {

        if (instance == null) {
            instance = new Connexion();
        }

        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }

 

}

