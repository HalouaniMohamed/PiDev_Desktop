/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import entities.Product;
import entities.ShoppingCartItem;
import entities.User;
import services.ProductService;
import services.ShoppingCartItemService;

/**
 *
 * @author ALPHA
 */
public class CartTests {

    ShoppingCartItemService scs = new ShoppingCartItemService();
    ProductService ps = new ProductService();

    public void getCart() {
        System.out.println(scs.getCartItems(7));

    }

    public void addToCart() {
        Product product = ps.getProductById(3);
        User user = new User(7);
        ShoppingCartItem item = new ShoppingCartItem(1, product, user);
        scs.add(item);
    }
}
