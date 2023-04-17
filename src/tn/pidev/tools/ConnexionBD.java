/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.pidev.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ASUS
 */
public class ConnexionBD {
    private Connection cnx;
    public final String url ="jdbc:mysql://localhost:3306/pidevfinal";
    public final String user="root";
    public final String pwd ="";
     public static ConnexionBD ct;
      private ConnexionBD () {
        try {
            cnx=DriverManager.getConnection(url, user, pwd);
            System.out.println("Connexion etablie !!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
}
      public static ConnexionBD getInstance(){
        if(ct==null){
            ct = new ConnexionBD();
        }
        return ct;
 
}

    public Connection getCnx() {
        return cnx;
    }
     
}
