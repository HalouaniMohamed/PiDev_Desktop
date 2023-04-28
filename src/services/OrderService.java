/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.ShoppingCartItem;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import tools.MyConnection;
import tools.Statics;

/**
 *
 * @author ALPHA
 */
public class OrderService {

    Connection cnx;
    String sql = "";

    public OrderService() {
        cnx = MyConnection.getInstance().getCnx();

    }

    public boolean decrementQuantities(List<ShoppingCartItem> shoppingCartItems) {
        try {
            PreparedStatement statement = cnx.prepareStatement("UPDATE product SET quantity = quantity - ? WHERE id = ?");
            for (ShoppingCartItem shoppingCartItem : shoppingCartItems) {
                int productId = shoppingCartItem.getProduct().getId();
                int quantity = shoppingCartItem.getQuantity();
                statement.setInt(1, quantity);
                statement.setInt(2, productId);
                statement.executeUpdate();
            }
            PreparedStatement orderStatement = cnx.prepareStatement("insert into commande(user_id,address,created_at,is_confirmed) values (?,?,?,?)");
            orderStatement.setInt(1, Statics.currentUser.getId());
            orderStatement.setString(2, "new address");
            Date date = new Date(Calendar.getInstance().getTime().getTime());

            orderStatement.setDate(3, date);
            orderStatement.setBoolean(4, false);
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

}
