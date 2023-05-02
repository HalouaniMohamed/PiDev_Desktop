/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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

    private static int userId;
    private static String image_Name;

    public static int getUserId() {
        return userId;
    }

    public static void setUserId(int userId) {
        MyConnection.userId = userId;

    }

    public static String getImage_Name() {
        return image_Name;
    }

    public static void setImage_Name(String image_Name) {
        MyConnection.image_Name = image_Name;
    }

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
