/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.sql.Date;
import java.util.Calendar;
import entities.Category;
import services.CategoryService;

/**
 *
 * @author ALPHA
 */
public class CategoryTests {

    CategoryService cs = new CategoryService();

    Date date = new Date(Calendar.getInstance().getTime().getTime());

    public void addCategory() {
        //add category
        Category c = new Category(7, "categoryTest2", "gsfdgdfg", date, date);
        cs.add(c);
    }

    public void getAllCategories() {
        // show all categories

        System.out.println(cs.getAllCategories());

    }
    // show category by Id

    public void getById() {
        System.out.println(cs.getCategoryById(5));
    }
    //delete category

    public void deleteById() {
        cs.deleteById(
                5);
    }

}
