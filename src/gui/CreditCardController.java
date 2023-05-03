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
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.OrderService;
import services.ShoppingCartItemService;
import tools.MailSenderH;
import tools.PdfGeneratorH;
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

    @FXML
//    private Button done;

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
        String email = tfEmail.getText();
        String cardNumber = tfCardNumber.getText();
        String month = tfMonth.getText();
        String year = tfYear.getText();
        String cvc = tftCVC.getText();

        if (email == null || email.isEmpty() || !email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            // Display an alert dialog for invalid email
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Invalid Email");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid email address.");
            alert.showAndWait();
            return;
        }

        if (cardNumber == null || cardNumber.isEmpty() || !cardNumber.matches("^\\d{16}$")) {
            // Display an alert dialog for invalid card number
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Invalid Card Number");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid 16-digit card number.");
            alert.showAndWait();
            return;
        }

        if (month == null || month.isEmpty() || !month.matches("^(0[1-9]|1[0-2])$")) {
            // Display an alert dialog for invalid month
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Invalid Month");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid month (MM).");
            alert.showAndWait();
            return;
        }

        if (year == null || year.isEmpty() || !year.matches("^\\d{4}$")) {
            // Display an alert dialog for invalid year
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Invalid Year");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid year (YYYY).");
            alert.showAndWait();
            return;
        }

        if (cvc == null || cvc.isEmpty() || !cvc.matches("^\\d{3}$")) {
            // Display an alert dialog for invalid cvc
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Invalid CVC");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid 3-digit CVC code.");
            alert.showAndWait();
            return;
        }

        // Parse inputs
        int expMonth = Integer.parseInt(month);
        int expYear = Integer.parseInt(year);
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;

        // Validate expiration date
        if (expYear < currentYear || (expYear == currentYear && expMonth < currentMonth)) {
            // Display an alert dialog for expired card
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Expired Card");
            alert.setHeaderText(null);
            alert.setContentText("Your card has already expired.");
            alert.showAndWait();
            return;
        }
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

                        //generate pdf using the shopping cart items
                        PdfGeneratorH pdfGenerator = new PdfGeneratorH();
                        File pdfFile = pdfGenerator.generateInvoicePdf(items);

                        //send the email
                        MailSenderH mailer = new MailSenderH();
                        mailer.sendEmailH(email, pdfFile);

                        try {
                            Platform.runLater(() -> {
                                Stage currentStage = (Stage) totalPriceLabel.getScene().getWindow();
                                currentStage.close();
                            });
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        Platform.runLater(() -> {

                            FXMLLoader loader = new FXMLLoader(getClass().getResource("UserProductsList.fxml"));

                            try {
                                // set the main window to UserProductList.fxml
                                System.out.println("hi");
                                Parent root = loader.load();
                                Scene scene = new Scene(root);
                                Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                mainStage.setScene(scene);
                                mainStage.show();
                            } catch (IOException ex) {
                                System.out.println(ex.getMessage());
                                System.out.println("baaaaaaaa3");
                            }
                        });

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
