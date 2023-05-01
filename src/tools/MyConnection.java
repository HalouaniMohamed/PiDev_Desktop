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
 * @author ALPHA
 */
public class MyConnection {

    private Connection cnx;
    public final String url = "jdbc:mysql://localhost:3306/pidevfinal1";
    public final String user = "root";
    public final String pwd = "";
    public static MyConnection ct;

    private MyConnection() {
        try {
            cnx = DriverManager.getConnection(url, user, pwd);
            System.out.println("Connexion etablie !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    // get instance method using singleton pattern
    public static MyConnection getInstance() {
        // create a new instance if no connection has been made before
        if (ct == null) {
            ct = new MyConnection();
        }
        return ct;
    }

    public Connection getCnx() {
        return cnx;
    }

}
