/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.stripe.model.Charge;
import com.stripe.model.Token;
import entities.Product;
import entities.ShoppingCartItem;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
        Stripe.apiKey = "sk_test_51MhGaAGeGEgrQ6hOFaUPvKPr8iOv7UjDwPJ22UAHMhCVD0VCQw3CmEGh0mQoVN7b635WeO2rilB94j2hSWMNDxhu00UQXAHDAc";

        try {
            // Calculate the total price of the items in the shopping cart
            double totalPrice = 0;
            for (ShoppingCartItem item : data) {
                double price = item.getProduct().getPrice();
                int quantity = item.getQuantity();
                totalPrice += price * quantity;
            }

            // Create a new checkout session with Stripe
            SessionCreateParams params = new SessionCreateParams.Builder()
                    .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                    .setMode(SessionCreateParams.Mode.PAYMENT)
                    .setSuccessUrl("http://localhost:8080/success")
                    .setCancelUrl("http://localhost:8080/cancel")
                    .addLineItem(new SessionCreateParams.LineItem.Builder()
                            .setPriceData(new SessionCreateParams.LineItem.PriceData.Builder()
                                    .setCurrency("usd")
                                    .setUnitAmount((long) (totalPrice * 100))
                                    .setProductData(new SessionCreateParams.LineItem.PriceData.ProductData.Builder()
                                            .setName("Shopping Cart Items")
                                            .build())
                                    .build())
                            .setQuantity(1L)
                            .build())
                    .build();

            Session session = Session.create(params);
            Map<String, Object> tokenParams = new HashMap<String, Object>();

            Map<String, Object> cardParams = new HashMap<>();
            cardParams.put("number", "4100000000000019");
            cardParams.put("exp_month", 12);
            cardParams.put("exp_year", 2025);
            cardParams.put("cvc", "123");

            tokenParams.put("card", cardParams);
            Token token = Token.create(tokenParams);

            Map<String, Object> params2 = new HashMap<>();
            params2.put("amount", (long) (totalPrice * 100));
            params2.put("currency", "eur");
            params2.put("source", token.getId());
            params2.put(
                    "description",
                    "My First Test Charge (created for API docs at https://www.stripe.com/docs/api)"
            );
            try {
                Charge charge = Charge.create(params2);
                System.out.println(charge);
            } catch (StripeException ex) {
                System.out.println("error in payment: " + ex.getMessage());
                ex.printStackTrace();
                // Display an alert dialog with the error message
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Payment Error");
                alert.setHeaderText(null);
                alert.setContentText("An error occurred during the payment: " + ex.getMessage());
                alert.showAndWait();

            }

            // Redirect the user to the Stripe checkout page
        } catch (StripeException ex) {
            System.out.println("huh ?");
        }

    }

}
