/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Product;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import services.ProductService;

/**
 * FXML Controller class
 *
 * @author ALPHA
 */
public class UserProductsListController implements Initializable {

    @FXML
    private GridPane gridPane;

    private ProductService productService = new ProductService();

    private List<Product> products;

    private int itemsPerPage = 8;

    private int currentPageIndex = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        products = productService.getAllProducts();
        updateGridPane();
    }

    private void updateGridPane() {
        gridPane.getChildren().clear();

        int startIndex = currentPageIndex * itemsPerPage;
        int endIndex = Math.min(startIndex + itemsPerPage, products.size());

        for (int i = startIndex; i < endIndex; i++) {
            Product product = products.get(i);
            Node productNode = createProductNode(product);
            int row = (i - startIndex) / 4;
            int col = (i - startIndex) % 4;
            gridPane.add(productNode, col, row);
        }
    }

    private Node createProductNode(Product product) {
        // Create a custom product node that displays the product's image
        // and other details. You can use any JavaFX layout and controls you want.

        //get current absolute path
        String absolutePath = System.getProperty("user.dir").replace("\\", "/");
        String imageDirectoryPath = absolutePath + "/src/uploads/products/";
        String imagePath = "file:///" + imageDirectoryPath + product.getImage();
        ImageView imageView = new ImageView(new Image(imagePath));

        imageView.setFitWidth(200);
        imageView.setFitHeight(200);

        Label nameLabel = new Label(product.getName());
        nameLabel.setAlignment(Pos.CENTER);
        nameLabel.setMaxWidth(Double.MAX_VALUE);

        Label priceLabel = new Label("$" + product.getPrice());
        priceLabel.setAlignment(Pos.CENTER);
        priceLabel.setMaxWidth(Double.MAX_VALUE);

        Button buyButton = new Button("Buy");
        buyButton.setPrefWidth(100);
        buyButton.setPrefHeight(30);
        buyButton.setFont(Font.font(18));
        buyButton.setStyle("-fx-background-color: #26ace2; -fx-text-fill: white; -fx-border-radius: 5;");
        buyButton.setOnAction(e -> {
            // handle button click event here
        });

        VBox productBox = new VBox(imageView, nameLabel, priceLabel, buyButton);
        productBox.setSpacing(10);
        productBox.setAlignment(Pos.CENTER);
        productBox.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(2))));

        return productBox;
    }

    @FXML
    private void handlePrevButton(ActionEvent event) {
        if (currentPageIndex > 0) {
            currentPageIndex--;
            updateGridPane();
        }
    }

    @FXML
    private void handleNextButton(ActionEvent event) {
        int totalPages = (int) Math.ceil((double) products.size() / itemsPerPage);
        if (currentPageIndex < totalPages - 1) {
            currentPageIndex++;
            updateGridPane();
        }
    }

}
