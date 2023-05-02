/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import edu.worshop.interfaces.IService;
import entities.User;
import tools.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

/**
 *
 * @author rayen
 */
public class ServiceUser implements IService {

    Statement ste;
    Connection conn = MyConnection.getInstance().getCnx();

    @Override
    public void add(Object p) {
        try {
            if (p instanceof User) { // Vérifie si p est un objet de type User

                // Crypter le mot de passe avec BCrypt
                //String qry = "INSERT INTO user(id, email , password, address, full_name ) VALUES ('" + ((User) p).getId() + "','" + ((User) p).getEmail() + "','" + ((User) p).getPassword() + "' ,'" + ((User) p).getAddress() + "','" + ((User) p).getFull_name() + "')";
                String qry = "INSERT INTO user(email, password, adresse, user_name, roles, date_naiss) VALUES ('" + ((User) p).getEmail() + "','" + ((User) p).getPassword() + "','" + ((User) p).getAddress() + "','" + ((User) p).getFull_name() + "','" + ((User) p).getRoles() + "','" + ((User) p).getDate_naissance() + "')";
                //String qry = "INSERT INTO user(email, password, address, full_name) VALUES ('" + ((User) p).getEmail() + "','" + ((User) p).getPassword() + "','" + ((User) p).getAddress() + "','" + ((User) p).getFull_name() + "')";
                ste = conn.createStatement();
                ste.executeUpdate(qry);
            } else {
                System.out.println("L'objet passé en paramètre n'est pas un utilisateur.");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void register(Object p) {
        try {
            if (p instanceof User) {
                String encryptedPassword = PasswordEncryption.encrypt(((User) p).getPassword());
                String qry = "INSERT INTO user(email, password, adresse, user_name, date_naiss, roles) VALUES ('" + ((User) p).getEmail() + "','" + encryptedPassword + "','" + ((User) p).getAddress() + "','" + ((User) p).getFull_name() + "','" + ((User) p).getDate_naissance() + "','" + ((User) p).getRoles() + "')";
                ste = conn.createStatement();
                ste.executeUpdate(qry);
            } else {
                System.out.println("L'objet passé en paramètre n'est pas un utilisateur.");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<User> afficher() {
        List<User> User = new ArrayList();

        try {
            //  String qry = "SELECT id, email, address, password, full_name  FROM user";
            String qry = "SELECT id, user_name, email,adresse, date_naiss  FROM user";
            ste = conn.createStatement();
            PreparedStatement ps = conn.prepareCall(qry);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User c = new User();
                c.setId(rs.getInt(1));
                // c.setId(rs.getInt(1));
                c.setFull_name(rs.getString(2));
                c.setEmail(rs.getString(3));
                // c.getPassword(rs.getString(3));
                c.setAddress(rs.getString(4));
                c.setDate_naissance(rs.getDate(5));

                User.add(c);
            }
            return User;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return User;
    }

    public List<User> ListUsers() {
        List<User> list = new ArrayList<>();
        try {
            String qry = "SELECT id, user_name, email,password, adresse, date_naiss, roles FROM user";
            Statement st = conn.createStatement();
            ResultSet RS = st.executeQuery(qry);
            while (RS.next()) {
                List<String> roles = Arrays.asList(RS.getString("roles").split(","));
                User R = new User(
                        RS.getInt("id"),
                        RS.getString("user_name"),
                        RS.getString("email"),
                        RS.getString("password"),
                        RS.getString("adresse"),
                        RS.getDate("date_naiss"),
                        roles
                );
                list.add(R);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    public User getUserById(int userId) {
        try {
            String qry = "SELECT id, user_name, email, adresse, date_naiss  FROM user WHERE id = ?";
            PreparedStatement ps = conn.prepareCall(qry);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setFull_name(rs.getString(2));
                user.setEmail(rs.getString(3));
                user.setAddress(rs.getString(4));
                user.setDate_naissance(rs.getDate(5));
                //user.setImg_user(rs.getString(6));
                return user;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public void supprimer(Object p) {
        try {
            if (p instanceof User) {
                String qry = "DELETE FROM user WHERE email=?";
                PreparedStatement pst = conn.prepareStatement(qry);
                pst.setString(1, ((User) p).getEmail());
                pst.executeUpdate();
            } else {
                System.out.println("L'objet passé en paramètre n'est pas un utilisateur.");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public boolean modifier(Object E) {
        try {
            String qry = "UPDATE user SET email = '" + ((User) E).getEmail() + "', password = '" + ((User) E).getPassword() + "', adresse = '" + ((User) E).getAddress() + "', user_name = '" + ((User) E).getFull_name() + "' WHERE id = " + ((User) E).getId();
            //String qry = "UPDATE user SET  address = '" + ((User) E).getAddress() + "', full_name = '" + ((User) E).getFull_name() + "' WHERE email = '" + ((User) E).getEmail() + "'";

            ste = conn.createStatement();
            ste.executeUpdate(qry);
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public void update(User user) {
        try {
            String qry = "UPDATE user SET email = '" + user.getEmail() + "', password = '" + user.getPassword() + "', adresse = '" + user.getAddress() + "', user_name = '" + user.getFull_name() + "', date_naiss = '" + user.getDate_naissance() + "' WHERE id = " + user.getId();
            Statement st = conn.createStatement();
            st.executeUpdate(qry);
            System.out.println("User updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public User authenticate(String email, String password) {
        try {
            String qry = "SELECT id, email, password, user_name, adresse, roles FROM user WHERE email = ?";
            PreparedStatement ps = conn.prepareStatement(qry);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String encryptedPassword = rs.getString("password");
                String decryptedPassword = PasswordEncryption.decrypt(encryptedPassword);

                if (decryptedPassword.equals(password)) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(decryptedPassword);
                    user.setAddress(rs.getString("adresse"));
                    user.setFull_name(rs.getString("user_name"));

                    // fetch roles from the database based on user's roles column
                    List<String> roles = Arrays.asList(rs.getString("roles").split(","));
                    user.setRoles(roles);

                    MyConnection.setUserId(user.getId());

                    return user;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public boolean emailExist(String email) {
        try {
            String qry = "SELECT email FROM user WHERE email = ?";
            PreparedStatement ps = conn.prepareStatement(qry);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true; // Email already exists
            } else {
                return false; // Email does not exist
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public int ChercherMail(String email) {
        String req = "SELECT * from `user` WHERE `user`.`email` ='" + email + "'  ";
        try {

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                if (rs.getString("email").equals(email)) {
                    System.out.println("mail trouvé ! ");
                    return 1;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return -1;
    }

    public void ResetPaswword(String email, String password) {
        String req = "UPDATE user SET password = ? WHERE email = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(1, password);
            ps.setString(2, email);

            ps.executeUpdate();
            System.out.println("Password updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
