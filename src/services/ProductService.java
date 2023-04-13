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
import entities.Category;
import entities.Product;
import tools.MyConnection;

/**
 *
 * @author ALPHA
 */
public class ProductService {

    Connection cnx;
    String sql = "";

    public ProductService() {
        cnx = MyConnection.getInstance().getCnx();

    }

    public void add(Product p) {
        sql = "insert into product(name,description,price,image,quantity,created_at,updated_at,category_id) values(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, p.getName());
            ste.setString(2, p.getDescription());
            ste.setDouble(3, p.getPrice());
            ste.setString(4, p.getImage());
            ste.setInt(5, p.getQuantity());
            ste.setDate(6, (Date) p.getCreatedAt());
            ste.setDate(7, (Date) p.getUpdatedAt());
            ste.setInt(8, p.getCategory().getId());
            ste.executeUpdate();
            System.out.println("product added");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public boolean isUnique(Product p) {
        sql = "SELECT COUNT(*) FROM product WHERE name=?";
        System.out.println(sql);
        boolean unique = false;
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, p.getName());
            ResultSet rs = ste.executeQuery();
            if (rs.next() && rs.getInt(1) == 0) {
                System.out.println("Product with the same name already exists!");
                unique = true;
            } else {
                unique = false;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return unique;
    }

    public void update(Product p) {
        sql = " update product set name=? ,description=? ,price=? ,image=? ,quantity=?,updated_at=?,category_id=? where id= ? ";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, p.getName());
            ste.setString(2, p.getDescription());
            ste.setDouble(3, p.getPrice());
            ste.setString(4, p.getImage());
            ste.setInt(5, p.getQuantity());
            ste.setDate(6, (Date) p.getUpdatedAt());
            ste.setInt(7, p.getCategory().getId());
            ste.setInt(8, p.getId());
            ste.executeUpdate();
            System.out.println("product updated");
            ste.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        sql = "select * from product p JOIN category c on p.category_id= c.id";
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                String image = rs.getString("image");
                int quantity = rs.getInt("quantity");
                Date createdAt = rs.getDate("created_at");
                Date updatedAt = rs.getDate("updated_at");
                int categoryId = rs.getInt("category_id");
                Category category = new Category(categoryId, rs.getString("category_name"), rs.getString(12), rs.getDate(13), rs.getDate(14));
//                Category category = cs.getCategoryById(categoryId);
                Product p = new Product(id, name, description, price, image, quantity, createdAt, updatedAt, category);
                products.add(p);

            }
            ste.close();
            rs.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return products;
    }

    public Product getProductById(int productId) {
        Product product = new Product();
        sql = "select * from product p JOIN category c on p.category_id= c.id where p.id=" + productId;
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                String image = rs.getString("image");
                int quantity = rs.getInt("quantity");
                Date createdAt = rs.getDate("created_at");
                Date updatedAt = rs.getDate("updated_at");
                int categoryId = rs.getInt("category_id");
                Category category = new Category(categoryId, rs.getString("category_name"), rs.getString(12), rs.getDate(13), rs.getDate(14));
                product.setId(id);
                product.setName(name);
                product.setDescription(description);
                product.setPrice(price);
                product.setImage(image);
                product.setQuantity(quantity);
                product.setCreatedAt(createdAt);
                product.setUpdatedAt(updatedAt);
                product.setCategory(category);
            }
            ste.close();
            rs.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return product;
    }

    public void delete(Product p) {
        sql = "delete from product where id=" + p.getId();
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("Produit" + p.getName() + " supprimé avec success");
            ste.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void deleteById(int productId) {
        sql = "delete from product where id=" + productId;
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("Produit supprimé avec success");
            ste.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
