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
import entities.ShoppingCartItem;
import entities.User;
import tools.MyConnection;

/**
 *
 * @author ALPHA
 */
public class ShoppingCartItemService {

    Connection cnx;
    String sql = "";

    public ShoppingCartItemService() {
        cnx = MyConnection.getInstance().getCnx();

    }

    public List<ShoppingCartItem> getCartItems(int UserId) {
        List<ShoppingCartItem> items = new ArrayList<>();

        sql = " select * from shopping_cart_item c join product p on c.product_id=p.id JOIN category ct ON p.category_id = ct.id where user_id=" + UserId;

        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(6);
                int itemQuantity = rs.getInt(5);
                User user = new User(UserId);
                int productId = rs.getInt(6);
                String name = rs.getString(8);
                String description = rs.getString(9);
                double price = rs.getDouble(10);
                String image = rs.getString(11);
                int quantity = rs.getInt(12);
                Date createdAt = rs.getDate(13);
                Date updatedAt = rs.getDate(14);
                int categoryId = rs.getInt("category_id");
                Category category = new Category(categoryId, rs.getString("category_name"), rs.getString(17), rs.getDate(18), rs.getDate(19));
//                Category category = cs.getCategoryById(categoryId);
                Product p = new Product(productId, name, description, price, image, quantity, createdAt, updatedAt, category);
                ShoppingCartItem item = new ShoppingCartItem(id, itemQuantity, p, user);
                items.add(item);
            }
            ste.close();
            rs.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return items;
    }

    public void add(ShoppingCartItem item) {
        try {
            int count = 0;
            sql = "select count(*) from shopping_cart_item where user_id=" + item.getUser().getId() + " and product_id=" + item.getProduct().getId();

            try {
                Statement ste = cnx.createStatement();
                ResultSet rs = ste.executeQuery(sql);
                if (rs.next()) {
                    count = rs.getInt(1);
                    ste.close();
                    rs.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            //sql request to get the product's avaiable quantity
//            String productQuantitysql = "select quantity from product where id=" + item.getProduct().getId();
//            Statement ste2 = cnx.createStatement();
//            ResultSet rs = ste2.executeQuery(productQuantitysql);
//            int availableQuantity = rs.getInt(1);
            // check if the item already exists in the user's cart
            // create a new shoppingCartItem instance if the item dosen't exist
            if (count == 0) {

                sql = "insert into shopping_cart_item(user_id,quantity,product_id) values (?,?,?)";
                System.out.println(sql);
                try {
                    PreparedStatement ste = cnx.prepareStatement(sql);
                    ste.setInt(1, item.getUser().getId());
                    ste.setInt(2, 1);
                    ste.setInt(3, item.getProduct().getId());
                    System.out.println(sql);
                    ste.executeUpdate();
                    System.out.println("item added");
                    ste.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }

            } else {
                if (item.getQuantity() + 1 < item.getProduct().getQuantity()) {

                    sql = "UPDATE shopping_cart_item SET quantity = quantity +1 where user_id =" + item.getUser().getId() + " and product_id =" + item.getProduct().getId();
                    Statement ste = cnx.createStatement();
                    ste.executeUpdate(sql);
                    System.out.println("quantity incremented");
                    ste.close();

                } else {
                    System.out.println("insuffisant quantity");
                }

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
