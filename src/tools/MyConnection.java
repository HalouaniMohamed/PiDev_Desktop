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
 * @author belkn
 */
public class MyConnection {
    
   private static Connection conn; //DB Credations
    
String url = "jdbc:mysql://127.0.0.1:3306/echkili1";
String user = "root";
String pwd = "";

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



private static MyConnection instance;
    private MyConnection() {
        try {
            conn=DriverManager.getConnection(url, user, pwd);
            System.out.println("Connexion etablie!!!");
        } catch (SQLException ex) {
            System.out.println("Probleme de connexion");        }
    }

    public static MyConnection getInstance() {
        if(instance==null){
            instance= new MyConnection();
        }
        return instance;
    }
 
    
    public Connection getConn(){
        return MyConnection.getInstance().conn;
    }
    
}
