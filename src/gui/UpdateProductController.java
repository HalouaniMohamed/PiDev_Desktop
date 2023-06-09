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
import org.apache.commons.io.FileUtils;
import services.CategoryService;
import services.ProductService;

/**
 * FXML Controller class
 *
 * @author ALPHA
 */
public class UpdateProductController implements Initializable {

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

    private Product product;
    ProductService ps = new ProductService();
    CategoryService cs = new CategoryService();

    private String image;
    private File selectedFile;
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
        } //        check if the quantity is valid
        else if (!isInt(tfQuantity.getText()) || !(Integer.parseInt(tfQuantity.getText()) >= 0)) {
            Alert aler = new Alert(Alert.AlertType.ERROR);
            aler.setTitle("Erreur");
            aler.setHeaderText("Quantité invalide!");
            ButtonType okButton = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
            aler.getButtonTypes().setAll(okButton);
            aler.showAndWait();
        } else {
            System.out.println("1st step");
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
                System.out.println("category null");

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("No category selected!");
                alert.showAndWait();
                return;
            }
            // create a new Product object with the values
            product.setName(name);
            product.setDescription(description);
            product.setQuantity(quantity);
            product.setPrice(price);
            product.setUpdatedAt(date);
            System.out.println("3rd step");

            if (selectedFile != null) {
                System.out.println("yay i have image ");

                uploadFile(selectedFile);
                product.setImage(image);
            }
            Task<Boolean> task = new Task<Boolean>() {
                @Override
                protected Boolean call() throws Exception {
                    return ps.isUnique(product);
                }
            };

            task.setOnSucceeded(e -> {
                System.out.println("here");
                boolean isUnique = task.getValue();
                if (!isUnique) {
                    System.out.println("i am not unique");
                    Alert aler = new Alert(Alert.AlertType.ERROR);
                    aler.setHeaderText(null);
                    aler.setTitle("Erreur");
                    aler.setContentText("Le nom existe déjà");
                    ButtonType okButton = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
                    aler.getButtonTypes().setAll(okButton);
                    aler.showAndWait();
                } else {
                    ps.update(product);

                    // on success show alert that displays a success message then empty the textfields
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText("Produit modifié");
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

            // on success show alert that displays a success message then empty the textfields
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

    private void uploadFile(File file) {
        try {
            String uniqueFileName = generateUniqueFileName(file.getName());
            FileUtils.copyFile(file, new File("C:/Users/ALPHA/Documents/NetBeansProjects/Desktop/src/uploads/products/" + uniqueFileName));
            // Set the image name in the product object
            setImage(uniqueFileName);
            // Save a copy of the image in the xampp htdocs directory

            File destFile2 = new File("C:/xampp/htdocs/uploads/products/" + uniqueFileName);
            FileUtils.copyFile(file, destFile2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String generateUniqueFileName(String fileName) {
        // Generate a unique file name using a timestamp and the original file name
        long timestamp = System.currentTimeMillis();
        String[] parts = fileName.split("\\.");
        String extension = parts[parts.length - 1];
        return timestamp + "." + extension;
    }

    public void setTf(String name, String description, double price, String Image, int quantity, Category category, String image) {
        tfName.setText(name);
        tfDescription.setText(description);
        tfPrice.setText(String.valueOf(price));
        tfQuantity.setText(String.valueOf(quantity));
        cbCategorie.setValue(category);
        imageLabel.setText(image);

    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setProduct(Product product) {
        this.product = product;
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
