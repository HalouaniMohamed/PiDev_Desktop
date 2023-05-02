/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import services.PostService;

/**
 *
 * @author ALPHA
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        CategoryTests ct = new CategoryTests();
//        ct.addCategory();
//        ct.getAllCategories();
//        ct.getById();
//        ct.deleteById();

//        ProductTests pt = new ProductTests();
//        pt.addProduct();
//        pt.getallProducts();
//        pt.getProductById();
//        pt.deleteById();
//        CartTests cartTests = new CartTests();
//        cartTests.getCart();
//        cartTests.addToCart();
PostService ps = new PostService();
ps.afficherPostParId(30);
    }
    

}
