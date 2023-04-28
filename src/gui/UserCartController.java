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
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import services.ShoppingCartItemService;
import tools.Statics;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author ALPHA
 */
public class UserCartController implements Initializable {

    @FXML
    private TableView<ShoppingCartItem> cartTable;
    @FXML
    private TableColumn<ShoppingCartItem, String> itemName;
    @FXML
    private TableColumn<ShoppingCartItem, String> itemImage;
    @FXML
    private TableColumn<ShoppingCartItem, String> itemPrice;
    @FXML
    private TableColumn<ShoppingCartItem, String> itemQuantity;
    @FXML
    private TableColumn<ShoppingCartItem, String> itemTotalPrice;
    @FXML
    private TableColumn<ShoppingCartItem, String> itemDelete;
    ShoppingCartItemService service = new ShoppingCartItemService();
    ObservableList<ShoppingCartItem> data = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        load();
    }

    private void refreshTable() {

        data.clear();

        List<ShoppingCartItem> items = service.getCartItems(Statics.currentUser.getId());
        data.addAll(items);
        cartTable.setItems(data);

    }

    public void load() {
        refreshTable();

        itemImage.setCellFactory(column -> {
            return new TableCell<ShoppingCartItem, String>() {
                @Override
                protected void updateItem(String imageUrl, boolean empty) {
                    super.updateItem(imageUrl, empty);

                    if (empty || imageUrl == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        try {
                            // Load the image from the URL
                            Image image = new Image("http://127.0.0.1:8000/uploads/products/" + imageUrl);
                            ImageView imageView = new ImageView(image);
                            imageView.setFitWidth(100);
                            imageView.setFitHeight(100);

                            setGraphic(imageView);
                            setText(null);
                            setAlignment(Pos.CENTER);

                        } catch (Exception e) {
                            setText("Failed to load image");
                            setGraphic(null);
                        }
                    }
                }
            };
        });

        itemImage.setCellValueFactory(cellData -> {
            Product product = cellData.getValue().getProduct();
            if (product != null) {
                return new SimpleStringProperty(product.getImage());
            } else {
                return null;
            }
        });

        itemName.setCellValueFactory(cellData -> {
            ObjectProperty<String> nameProperty = new SimpleObjectProperty<>();
            Product product = cellData.getValue().getProduct();
            if (product != null) {
                nameProperty.set(product.getName());
            }
            return nameProperty;
        });
        itemQuantity.setCellValueFactory(cellData -> {
            ObjectProperty<String> quantityProperty = new SimpleObjectProperty<>();
            int quantity = cellData.getValue().getQuantity();

            String quantityString = String.valueOf(quantity); // convert the quantity to a string
            quantityProperty.set(quantityString);

            return quantityProperty;
        });
        itemPrice.setCellValueFactory(cellData -> {
            ObjectProperty<String> priceProperty = new SimpleObjectProperty<>();
            Product product = cellData.getValue().getProduct();
            if (product != null) {
                double price = product.getPrice();
                String priceString = String.format("%.2f", price); // format the price as a string with 2 decimal places
                priceProperty.set(priceString);
            }
            return priceProperty;
        });

        itemTotalPrice.setCellValueFactory(cellData -> {
            ObjectProperty<String> priceProperty = new SimpleObjectProperty<>();
            Product product = cellData.getValue().getProduct();
            int quantity = cellData.getValue().getQuantity();
            if (product != null) {
                double price = product.getPrice();
                double totalPrice = price * quantity;
                String totalPriceString = String.format("%.2f", totalPrice); // format the price as a string with 2 decimal places
                priceProperty.set(totalPriceString);
            }
            return priceProperty;
        });
        cartTable.setItems(data);

    }

    @FXML
    private void makeOrder(ActionEvent event) {

    }

}
