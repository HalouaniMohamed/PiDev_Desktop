/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.controllers;

import services.ServiceUser;
import entities.User;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;

/**
 * FXML Controller class
 *
 * @author rayen
 */
public class User_ListController implements Initializable {

    @FXML
    private ListView<User> User_Listfx;
    ServiceUser sp = new ServiceUser();
    @FXML
    private TextField searchField;
    @FXML
    private Label label;
    @FXML
    private Button btn_pdf;
    @FXML
    private Button addUser;
    @FXML
    private Button deleteUser;
    @FXML
    private Button updateUser;

    int index = -1;

    // Declare an ObservableList to store the users
    private ObservableList<User> userList;

    static int id;
    static Date date;
    static String full_name, email, address, password;
    static User U = new User();

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {

        ListView<User> list1 = User_Listfx;
        ServiceUser inter = new ServiceUser();
        List<User> list2 = inter.ListUsers();
        userList = FXCollections.observableArrayList(list2);
        list1.setCellFactory(param -> new ListCell<User>() {
            @Override
            protected void updateItem(User item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getId() + "   |   " + item.getFull_name() + "   |   " + item.getEmail() + "   |   " + item.getAddress() + "   |   " + item.getRoles() + "   |   " + " (" + item.getDate_naissance() + ")");
                }
            }
        });

        // Ajouter une fonction de recherche
        FilteredList<User> filteredList = new FilteredList<>(userList, p -> true);
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(user -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (user.getFull_name().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Search by full name
                } else if (user.getEmail().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Search by email address
                } else if (String.valueOf(user.getId()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Search by ID
                } else if (user.getAddress().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Search by address
                }

                return false; // No matches found
            });
        });

        SortedList<User> sortedList = new SortedList<>(filteredList);
        list1.setItems(sortedList);

        //Font font = Font.loadFont(getClass().getResourceAsStream("/fonts/VTFRedzone-Classic.ttf"), 50);
        //label.setFont(font);
    }

    @FXML
    private void deleteUser(ActionEvent event) {
        ListView<User> list = (ListView<User>) User_Listfx;
        ServiceUser inter = new ServiceUser();

        int selectedIndex = list.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            User E = list.getSelectionModel().getSelectedItem();
            inter.supprimer(E);
            list.getItems().remove(selectedIndex);
            list.refresh();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "plz select a user to delete", ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    private void updateUser(ActionEvent event) {

        ListView<User> list = (ListView<User>) User_Listfx;
        int selectedIndex = list.getSelectionModel().getSelectedIndex();

        User e = list.getSelectionModel().getSelectedItem();

        int id = e.getId();
        String name = e.getFull_name();
        String email = e.getEmail();
        String address = e.getAddress();
        String password = e.getPassword();
        //String img_user = e.getImg_user();
        Date date = e.getDate_naissance();

        U = e;

        try {

            Parent page1 = FXMLLoader.load(getClass().getResource("../../../gui/User_Update.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(User_UpdateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void addUser(ActionEvent event) {
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("../../../gui/User_Add.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(User_AddController.class.getName()).log(Level.SEVERE, null, ex);
            //showAlert("Error loading");
        }

    }

    /*
    @FXML
    private void printUser(MouseEvent event) {
        try {
            // create new workbook
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("User Data");

            // create header row
            XSSFRow header = sheet.createRow(0);
            header.createCell(0).setCellValue("ID");
            header.createCell(1).setCellValue("Full Name");
            header.createCell(2).setCellValue("Email");
            header.createCell(3).setCellValue("Date of Birth");
            header.createCell(4).setCellValue("Address");
            header.createCell(5).setCellValue("Roles");

            // populate data rows
            ObservableList<User> users = User_Listfx.getItems();
            int rowIndex = 1;
            for (User user : users) {
                XSSFRow row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(user.getId());
                row.createCell(1).setCellValue(user.getFull_name());
                row.createCell(2).setCellValue(user.getEmail());
                // create a date formatter
                SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

// format the date of birth
                String formattedDate = dateFormatter.format(user.getDate_naissance());
                row.createCell(3).setCellValue(formattedDate);
                row.createCell(4).setCellValue(user.getAddress());
                row.createCell(5).setCellValue(String.join(",", user.getRoles()));
            }

            // save workbook to desktop
            Path desktop = FileSystemView.getFileSystemView().getHomeDirectory().toPath().resolve("Desktop");
            Path filePath = desktop.resolve("user_data.xlsx");
            try (FileOutputStream outputStream = new FileOutputStream(filePath.toFile())) {
                workbook.write(outputStream);
            }

            // show success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Export Successful");
            alert.setHeaderText(null);
            alert.setContentText("User data has been exported to:\n" + filePath.toString());
            alert.showAndWait();

        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Export Failed");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred while exporting user data:\n" + ex.getMessage());
            alert.showAndWait();
        }
    }
     */
    @FXML
    private void btnGenPDF(ActionEvent event) throws DocumentException, FileNotFoundException, IOException {
        long millis = System.currentTimeMillis();
        java.sql.Date DateRapport = new java.sql.Date(millis);

        String DateLyoum = new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH).format(DateRapport);
        System.out.println("Date d'aujourdhui : " + DateLyoum);

        com.itextpdf.text.Document document = new com.itextpdf.text.Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(String.valueOf("Rapport des utilisateurs " + DateLyoum + ".pdf")));
            document.open();

            // Ajouter un titre avec un style personnalisé
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLACK);
            Paragraph title = new Paragraph("Rapport détaillé des utilisateurs de notre application", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Ajouter un paragraphe avec un style personnalisé
            Font paragraphFont = FontFactory.getFont(FontFactory.TIMES, 12, BaseColor.BLACK);
            Paragraph ph1 = new Paragraph("Voici un rapport détaillé de notre application qui contient tous la liste des utilisateurs. Derniere mise a jour : " + DateRapport, paragraphFont);
            ph1.setSpacingAfter(10);
            document.add(ph1);

            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);

            // Créer une cellule avec un style personnalisé
            Font cellFont = FontFactory.getFont(FontFactory.TIMES, 12, BaseColor.WHITE);
            PdfPCell cell = new PdfPCell(new Phrase("Id", cellFont));
            cell.setBackgroundColor(BaseColor.BLACK);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("email", cellFont));
            cell.setBackgroundColor(BaseColor.BLACK);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Adresse", cellFont));
            cell.setBackgroundColor(BaseColor.BLACK);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Pseudo", cellFont));
            cell.setBackgroundColor(BaseColor.BLACK);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            User r = new User();
            ServiceUser rv = new ServiceUser();
            rv.ListUsers().forEach(e -> {
                table.addCell(String.valueOf(e.getId()));
                table.addCell(String.valueOf(e.getEmail()));
                table.addCell(String.valueOf(e.getAddress()));
                table.addCell(String.valueOf(e.getFull_name()));

            });

            document.add(table);
        } catch (DocumentException | FileNotFoundException e) {
            System.out.println(e);
        }
        document.close();

        // Ouvrir le fichier PDF
        File file = new File("Rapport Utilisateurs " + DateLyoum + ".pdf");
        if (file.exists()) {
            Desktop.getDesktop().open(file);
        }

    }

}
