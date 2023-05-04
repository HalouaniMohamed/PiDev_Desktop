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

    /**
     * Adds a shopping cart item to the database. If the item already exists in
     * the user's cart, increments the quantity of the existing item. Otherwise,
     * creates a new shopping cart item with a quantity of 1.
     *
     * @param item The shopping cart item to add.
     */
    /**
     * Adds an item to the user's shopping cart.
     *
     * @param item The item to add.
     * @return true if the item was added successfully, false otherwise.
     */
    public boolean add(ShoppingCartItem item) {
        // Check if the item already exists in the user's cart
        String countSql = "SELECT COUNT(*) FROM shopping_cart_item WHERE user_id=? AND product_id=?";
        try (PreparedStatement countStatement = cnx.prepareStatement(countSql)) {
            countStatement.setInt(1, item.getUser().getId());
            countStatement.setInt(2, item.getProduct().getId());
            try (ResultSet countResult = countStatement.executeQuery()) {
                int count = countResult.next() ? countResult.getInt(1) : 0;

                // Check if the product is available
                String quantitySql = "SELECT quantity FROM product WHERE id=?";
                try (PreparedStatement quantityStatement = cnx.prepareStatement(quantitySql)) {
                    quantityStatement.setInt(1, item.getProduct().getId());
                    try (ResultSet quantityResult = quantityStatement.executeQuery()) {
                        int availableQuantity = quantityResult.next() ? quantityResult.getInt(1) : 0;

                        if (availableQuantity <= 0) {
                            System.out.println("Produit non disponible");
                            return false;
                        }

                        if (count == 0) {
                            // Create a new shopping cart item if the item doesn't exist
                            String insertSql = "INSERT INTO shopping_cart_item (user_id, quantity, product_id) VALUES (?, ?, ?)";
                            try (PreparedStatement insertStatement = cnx.prepareStatement(insertSql)) {
                                insertStatement.setInt(1, item.getUser().getId());
                                insertStatement.setInt(2, 1);
                                insertStatement.setInt(3, item.getProduct().getId());
                                int insertedRows = insertStatement.executeUpdate();

                                if (insertedRows > 0) {
                                    System.out.println("Item added");
                                    return true;
                                } else {
                                    System.out.println("Failed to add item");
                                    return false;
                                }
                            }
                        } else {
                            String cartQuantitySql = "SELECT quantity FROM shopping_cart_item WHERE user_id=? AND product_id=?";
                            try (PreparedStatement cartQuantityStatement = cnx.prepareStatement(cartQuantitySql)) {
                                cartQuantityStatement.setInt(1, item.getUser().getId());
                                cartQuantityStatement.setInt(2, item.getProduct().getId());
                                try (ResultSet cartQuantityResult = cartQuantityStatement.executeQuery()) {
                                    int cartQuantity = cartQuantityResult.next() ? cartQuantityResult.getInt(1) : 0;

                                    if (cartQuantity + 1 > availableQuantity) {
                                        System.out.println("Item in cart but no more available quantity");
                                        return false;
                                    }

                                    // Increment the quantity of the existing shopping cart item
                                    String updateSql = "UPDATE shopping_cart_item SET quantity = quantity + 1 WHERE user_id = ? AND product_id = ?";
                                    try (PreparedStatement updateStatement = cnx.prepareStatement(updateSql)) {
                                        updateStatement.setInt(1, item.getUser().getId());
                                        updateStatement.setInt(2, item.getProduct().getId());
                                        int updatedRows = updateStatement.executeUpdate();

                                        if (updatedRows > 0) {
                                            System.out.println("Quantity incremented");
                                            return true;
                                        } else {
                                            System.out.println("Failed to increment quantity");
                                            return false;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public void deleteCartItemsForUser(int userId) {
        String deleteSql = "DELETE FROM shopping_cart_item WHERE user_id = ?";
        try (PreparedStatement deleteStatement = cnx.prepareStatement(deleteSql)) {
            deleteStatement.setInt(1, userId);
            int deletedRows = deleteStatement.executeUpdate();
            if (deletedRows > 0) {
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
