/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Category;
import entities.Product;
import java.net.URL;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import services.CategoryService;
import services.ProductService;

/**
 * FXML Controller class
 *
 * @author ALPHA
 */
public class AddProductController implements Initializable {

    @FXML
    private TextField tfName;
    @FXML
    private TextArea tfDescription;
    @FXML
    private TextField tfPrice;
    @FXML
    private TextField tfQuantity;
    @FXML
    private ChoiceBox<Category> cbCategorie;

    ProductService ps = new ProductService();
    CategoryService cs = new CategoryService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        List<Category> categories = cs.getAllCategories();
        cbCategorie.setItems(FXCollections.observableArrayList(categories));
        cbCategorie.setConverter(new StringConverter<Category>() {
            @Override
            public String toString(Category category) {
                return category == null ? "" : category.getCategoryName();
            }

            @Override
            public Category fromString(String string) {
                return null;
            }
        });
    }

    @FXML
    private void addProduct(ActionEvent event) {
        if (tfName.getText().isEmpty() || tfPrice.getText().isEmpty() || tfQuantity.getText().isEmpty()) {
            Alert aler = new Alert(Alert.AlertType.ERROR);
            aler.setTitle("Erreur");
            aler.setHeaderText("Le nom est vide !");
            ButtonType okButton = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
            aler.getButtonTypes().setAll(okButton);
            aler.showAndWait();
        } else {
            Date date = new Date(Calendar.getInstance().getTime().getTime());

            // get the values from the text fields
            double price = Double.parseDouble(tfPrice.getText());
            int quantity = Integer.parseInt(tfQuantity.getText());
            String image = "aaa";
            String name = tfName.getText();
            String description = tfDescription.getText();
            // get the selected category from the ChoiceBox
            Category category = cbCategorie.getValue();
            // check if a category was selected
            if (category == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("No category selected!");
                alert.showAndWait();
                return;
            }
            // create a new Product object with the values
            Product p = new Product(name, description, price, image, quantity, date, date, category);
            CategoryService cs = new CategoryService();
            ps.add(p);

            // on success show alert that displays a success message then empty the textfields
            Alert aler = new Alert(Alert.AlertType.INFORMATION);
            aler.setTitle("Success");
            aler.setHeaderText("produit ajouté");
            ButtonType okButton = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
            aler.getButtonTypes().setAll(okButton);
            aler.showAndWait();
            tfName.setText(null);
            tfDescription.setText(null);
        }

    }
}
