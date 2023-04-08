/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.sql.Date;
import java.util.Calendar;
import entities.Category;
import entities.Product;
import services.ProductService;

/**
 *
 * @author ALPHA
 */
public class ProductTests {

    ProductService ps = new ProductService();
    Date date = new Date(Calendar.getInstance().getTime().getTime());

    Category c = new Category(7, "categoryTest2", "gsfdgdfg", date, date);

    public void addProduct() {
        double price = 53.2;
        Product p = new Product(9, "p1", null, price, "imageUrl", 10, date, date, c);
        ps.add(p);
    }

    public void getallProducts() {
        System.out.println(ps.getAllProducts());

    }

    public void getProductById() {
        System.out.println(ps.getProductById(5));

    }

    public void deleteById() {
        ps.deleteById(5);

    }
}
