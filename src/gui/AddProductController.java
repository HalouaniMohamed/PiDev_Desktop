/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Category;
import entities.Product;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;
import services.CategoryService;
import services.ProductService;
import tools.ImageUploader;

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
    private String image;
    private File selectedFile;

    ProductService ps = new ProductService();
    CategoryService cs = new CategoryService();
    @FXML
    private Label imageLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imageLabel.setText("no image");
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

    public void setImage(String image) {
        this.image = image;
    }

    @FXML
    private void addProduct(ActionEvent event) {
        if (tfName.getText().isEmpty() || tfPrice.getText().isEmpty() || tfQuantity.getText().isEmpty()) {
            Alert aler = new Alert(Alert.AlertType.ERROR);
            aler.setTitle("Erreur");
            aler.setHeaderText("Champ vide !");
            ButtonType okButton = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
            aler.getButtonTypes().setAll(okButton);
            aler.showAndWait();
        } else if (tfName.getText().length() < 3) {
            Alert aler = new Alert(Alert.AlertType.ERROR);
            aler.setTitle("Erreur");
            aler.setHeaderText("Nom invalide!");
            ButtonType okButton = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
            aler.getButtonTypes().setAll(okButton);
            aler.showAndWait();
        } else if (!isDouble(tfPrice.getText()) || !(Double.parseDouble(tfPrice.getText()) > 0)) {

            Alert aler = new Alert(Alert.AlertType.ERROR);
            aler.setTitle("Erreur");
            aler.setHeaderText("Prix invalide!");
            ButtonType okButton = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
            aler.getButtonTypes().setAll(okButton);
            aler.showAndWait();
        } //        check if thequantity is valid
        else if (!isInt(tfQuantity.getText()) || !(Integer.parseInt(tfQuantity.getText()) >= 0)) {
            Alert aler = new Alert(Alert.AlertType.ERROR);
            aler.setTitle("Erreur");
            aler.setHeaderText("Quantité invalide!");
            ButtonType okButton = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
            aler.getButtonTypes().setAll(okButton);
            aler.showAndWait();
        } else {
            Date date = new Date(Calendar.getInstance().getTime().getTime());

            // get the values from the text fields
            double price = Double.parseDouble(tfPrice.getText());
            int quantity = Integer.parseInt(tfQuantity.getText());

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

            if (selectedFile != null) {

                String imageName = uploadFile(selectedFile);
                p.setImage(imageName);
            }
            Task<Boolean> task = new Task<Boolean>() {
                @Override
                protected Boolean call() throws Exception {
                    return ps.isUnique(p);
                }
            };

            task.setOnSucceeded(e -> {
                boolean isUnique = task.getValue();
                if (!isUnique) {
                    Alert aler = new Alert(Alert.AlertType.ERROR);
                    aler.setHeaderText(null);
                    aler.setTitle("Erreur");
                    aler.setContentText("Le nom existe déjà");
                    ButtonType okButton = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
                    aler.getButtonTypes().setAll(okButton);
                    aler.showAndWait();
                } else {
                    ps.add(p);
                    // on success show alert that displays a success message then empty the textfields
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText("Produit ajouté");
                    ButtonType okButton = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
                    alert.getButtonTypes().setAll(okButton);
                    Button okBtn = (Button) alert.getDialogPane().lookupButton(okButton);
                    okBtn.setOnAction(evt -> {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminProductsList.fxml"));
                        try {
                            Parent root = loader.load();
                            tfName.getScene().setRoot(root);
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }
                    });
                    alert.showAndWait();
                    tfName.setText(null);
                    tfDescription.setText(null);
                }
            });

            new Thread(task).start();
        }

    }

    @FXML
    private void handleUploadImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");
        selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            String fileName = selectedFile.getName();
            imageLabel.setText(fileName);
        }
    }

    private String uploadFile(File file) {
        String uniqueImageName = null;
        try {
            ImageUploader imageUploader = new ImageUploader();
            uniqueImageName = imageUploader.uploadImage(file);

        } catch (IOException ex) {
            System.out.println("whaaaaaaaaaaaaat");

        }
        return uniqueImageName;

    }

    public boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
