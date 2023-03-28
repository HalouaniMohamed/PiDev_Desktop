/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.sql.Date;
import java.util.Calendar;
import pidev_desktop.entities.Category;
import services.CategoryService;

/**
 *
 * @author ALPHA
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CategoryService cs = new CategoryService();
        Date date = new Date(Calendar.getInstance().getTime().getTime());
        Category c = new Category(34, "categoryTest", "gsfdgdfg", date, date);
        cs.add(c);
    }

}
