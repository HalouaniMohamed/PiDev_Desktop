 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mongi
 */
public class MaConnexion {
    private Connection cnx;
    public final String url ="jdbc:mysql://localhost:3306/pidev";
    public final String user="root";
    public final String pwd ="";
    public static MaConnexion ct;

    public MaConnexion() {
        try {
            cnx=DriverManager.getConnection(url, user, pwd);
            System.out.println("Connexion etablie !!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    public static MaConnexion getInstance(){
        if(ct==null){
            ct = new MaConnexion();
        }
        return ct;
    }

    public Connection getCnx() {
        return cnx;
    }
    
 }
