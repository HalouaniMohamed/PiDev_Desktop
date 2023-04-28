/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Token;
import entities.ShoppingCartItem;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.OrderService;
import services.ShoppingCartItemService;
import tools.Statics;

/**
 * FXML Controller class
 *
 * @author ALPHA
 */
public class CreditCardController implements Initializable {

    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfCardNumber;
    @FXML
    private TextField tftCVC;
    @FXML
    private Label totalPriceLabel;
    private Double totalPrice;
    @FXML
    private TextField tfMonth;
    @FXML
    private TextField tfYear;
    List<ShoppingCartItem> items = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void cancelStripe(ActionEvent event) {
        // Get the current window
        Stage currentStage = (Stage) totalPriceLabel.getScene().getWindow();
        // Close the window
        currentStage.close();
    }

    @FXML
    private void stripeCheckout(ActionEvent event) {
        try {
            Map<String, Object> tokenParams = new HashMap<String, Object>();

            Map<String, Object> cardParams = new HashMap<>();
            cardParams.put("number", tfCardNumber.getText());
            cardParams.put("exp_month", tfMonth.getText());
            cardParams.put("exp_year", tfYear.getText());
            cardParams.put("cvc", tftCVC.getText());

            tokenParams.put("card", cardParams);
            Token token = Token.create(tokenParams);

            Map<String, Object> params2 = new HashMap<>();
            params2.put("amount", (long) (totalPrice * 100));
            params2.put("currency", "usd");
            params2.put("source", token.getId());
            params2.put(
                    "description",
                    "My First Test Charge (created for API docs at https://www.stripe.com/docs/api)"
            );
            try {
                Charge charge = Charge.create(params2);
                System.out.println(charge);
                System.out.println("payment successful");
                System.out.println("decrementing quantities");
                CompletableFuture.supplyAsync(() -> {
                    OrderService os = new OrderService();
                    return os.decrementQuantities(items);
                }).thenAccept(result -> {
                    if (!result) {
                        Platform.runLater(() -> {
                            Alert alert = new Alert(AlertType.ERROR);
                            alert.setTitle("Decrement Error");
                            alert.setHeaderText(null);
                            alert.setContentText("An error occurred while decrementing quantities");
                            alert.showAndWait();
                        });
                    } else {
                        ShoppingCartItemService cartService = new ShoppingCartItemService();
                        cartService.deleteCartItemsForUser(Statics.currentUser.getId());
                        System.out.println("decrementing done");
                    }
                });
            } catch (StripeException ex) {
                // Display an alert dialog with the error message
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Payment Error");
                alert.setHeaderText(null);
                alert.setContentText("An error occurred during the payment: " + ex.getMessage());
                alert.showAndWait();
            }

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
    }

    public void setTotalPriceLabel(Double totalPrice) {
        totalPriceLabel.setText(String.format("%.2f", totalPrice) + "tnd");
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setItems(List<ShoppingCartItem> items) {
        this.items = items;
    }

}
