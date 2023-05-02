/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Product;
import entities.ShoppingCartItem;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import services.ProductService;
import services.ShoppingCartItemService;
import tools.Statics;

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
    @FXML
    private HBox pagination;

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

        // Clear the pagination HBox first
        pagination.getChildren().clear();

        int totalPages = (int) Math.ceil((double) products.size() / itemsPerPage);
        for (int i = 1; i <= totalPages; i++) {
            final int pageIndex = i - 1; // declare a final variable
            Button pageButton = new Button(Integer.toString(i));
            pageButton.setPrefWidth(30);
            pageButton.setOnAction(e -> {
                currentPageIndex = pageIndex; // use the final variable
                updateGridPane();
            });

            if (i == currentPageIndex + 1) {
                pageButton.setStyle("-fx-background-color: #26ace2; -fx-text-fill: white; -fx-border-radius: 5;");
            } else {
                pageButton.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-border-radius: 5;");
            }

            pagination.getChildren().add(pageButton);
        }
    }

    private Node createProductNode(Product product) {
        // Create a custom product node that displays the product's image
        // and other details. You can use any JavaFX layout and controls you want.
        String imageUrl = "http://127.0.0.1:8000/uploads/products/" + product.getImage();
        ImageView imageView = new ImageView(imageUrl);
        imageView.setFitWidth(150);
        imageView.setFitHeight(150);

        Label nameLabel = new Label(product.getName());
        nameLabel.setAlignment(Pos.CENTER);
        nameLabel.setMaxWidth(Double.MAX_VALUE);

        Label priceLabel = new Label(product.getPrice() + "DT");
        priceLabel.setAlignment(Pos.CENTER);
        priceLabel.setMaxWidth(Double.MAX_VALUE);

        Label quantityLabel = new Label(product.getQuantity() > 0 ? "disponible" : "non disponible");
        quantityLabel.setAlignment(Pos.CENTER);
        quantityLabel.setTextFill(product.getQuantity() > 0 ? Color.GREEN : Color.RED);
        quantityLabel.setMaxWidth(Double.MAX_VALUE);

        Button buyButton = new Button("Ajouter au panier");
        buyButton.setPrefWidth(100);
        buyButton.setPrefHeight(30);
        buyButton.setFont(Font.font(18));
        buyButton.setStyle("-fx-background-color: #26ace2; -fx-text-fill: white; -fx-border-radius: 5;");
        buyButton.setOnAction(e -> {
            ShoppingCartItem item = new ShoppingCartItem(1, product, Statics.currentUser);
            ShoppingCartItemService shoppingCartItemService = new ShoppingCartItemService();
            boolean success = shoppingCartItemService.add(item);
            if (success) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Produit ajouté");
                ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                alert.getButtonTypes().setAll(okButton);
                Button okBtn = (Button) alert.getDialogPane().lookupButton(okButton);
                okBtn.setOnAction(evt -> {
                    alert.close();
                });
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Quantité insuffisante");
                alert.setHeaderText("Quantité insuffisante");
                ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                alert.getButtonTypes().setAll(okButton);
                Button okBtn = (Button) alert.getDialogPane().lookupButton(okButton);
                okBtn.setOnAction(evt -> {
                    alert.close();
                });
                alert.showAndWait();
            }
        });

        VBox productBox = new VBox(imageView, nameLabel, priceLabel, quantityLabel, buyButton);
        productBox.setSpacing(8);
        productBox.setAlignment(Pos.CENTER);
        productBox.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(2))));
        Insets margin = new Insets(10);
        VBox.setMargin(productBox, margin);
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
