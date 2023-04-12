/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import entities.Category;
import tools.MyConnection;

/**
 *
 * @author ALPHA
 */
public class CategoryService {

    Connection cnx;
    String sql = "";

    public CategoryService() {
        cnx = MyConnection.getInstance().getCnx();

    }

    public void add(Category c) {
        sql = "insert into category(category_name,description,create_at,updated_at) values(?,?,?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, c.getCategoryName());
            ste.setString(2, c.getDescription());
            ste.setDate(3, (Date) c.getCreateAt());
            ste.setDate(4, (Date) c.getUpdatedAt());
            ste.executeUpdate();
            System.out.println("added");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        sql = "select * from category";
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
                Category c = new Category(rs.getInt("id"), rs.getString("category_name"), rs.getString("description"), rs.getDate("create_at"), rs.getDate("updated_at"));
                categories.add(c);

            }
            ste.close();
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categories;
    }

    public void update(Category c) {
        System.out.println("service" + c);
        sql = " update category set category_name=? ,description=? ,updated_at=? where id= ? ";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, c.getCategoryName());
            ste.setString(2, c.getDescription());
            ste.setDate(3, (Date) c.getUpdatedAt());
            ste.setInt(4, c.getId());
            ste.executeUpdate();
            System.out.println("category updated");
            ste.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Category c) {
        sql = " select count(*) from product where id=" + c.getId();
        int count = 0;
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        // check if the category is associated with any products, display an error message in that case
        if (count > 0) {
            System.out.println("Cette categorie ne peut pas etre supprimé");
            return;
        }
        sql = "delete from category where id=" + c.getId();
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("Categorie" + c.getCategoryName() + " supprimé avec success");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void deleteById(Integer categoryId) {

        sql = " select count(*) from product where id=" + categoryId;
        int count = 0;
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        // check if the category is associated with any products, display an error message in that case
        if (count > 0) {
            System.out.println("Cette categorie ne peut pas etre supprimé");
            return;
        }
        sql = "delete from category where id=" + categoryId;
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("Categorie" + categoryId + "supprimé avec success");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
