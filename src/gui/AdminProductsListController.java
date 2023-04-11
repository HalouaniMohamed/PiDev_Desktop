/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Category;
import entities.Product;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import services.ProductService;

/**
 * FXML Controller class
 *
 * @author ALPHA
 */
public class AdminProductsListController implements Initializable {

    @FXML
    private AnchorPane id;
    @FXML
    private TableView<Product> productsView;
    @FXML
    private TableColumn<Product, String> idC;
    @FXML
    private TableColumn<Product, String> nameC;
    @FXML
    private TableColumn<Product, String> descriptionC;
    @FXML
    private TableColumn<Product, String> categoryC;
    @FXML
    private TableColumn<Product, String> priceC;
    @FXML
    private TableColumn<Product, String> quantityC;
    @FXML
    private TableColumn<Product, String> createdAtC;
    @FXML
    private TableColumn<Product, String> updatedAtC;
    @FXML
    private TableColumn<Product, String> updateC;
    @FXML
    private TableColumn<Product, String> deleteC;
    ProductService ps = new ProductService();

    Product product = null;
    ObservableList<Product> data = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        load();
    }

    @FXML
    private void refreshTable() {

        data.clear();

        List<Product> products = ps.getAllProducts();
        data.addAll(products);
        productsView.setItems(data);

    }

    public void load() {
        refreshTable();

        // Sets the cell value factories for each column in the table
        idC.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameC.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionC.setCellValueFactory(new PropertyValueFactory<>("description"));
        priceC.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityC.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        createdAtC.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        updatedAtC.setCellValueFactory(new PropertyValueFactory<>("updatedAt"));

        // Sets the cell value factory for the category column in the table
        // The category name is retrieved using an ObjectProperty
        categoryC.setCellValueFactory(cellData -> {
            ObjectProperty<String> nameProperty = new SimpleObjectProperty<>();
            Category category = cellData.getValue().getCategory();
            if (category != null) {
                nameProperty.set(category.getCategoryName());
            }
            return nameProperty;
        });

        // Sets the cell factory for the delete button column in the table
        Callback<TableColumn<Product, String>, TableCell<Product, String>> cellFoctory = (TableColumn<Product, String> param) -> {
            // make cell containing buttons
            final TableCell<Product, String> cell = new TableCell<Product, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {
                        // Creates a FontAwesomeIconView delete icon and sets its style and click event

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );

                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            // Deletes the selected product and refreshes the table

                            product = productsView.getSelectionModel().getSelectedItem();
                            ps.deleteById(product.getId());
                            refreshTable();

                        });
                        // Adds the delete icon to an HBox and sets its style

                        HBox managebtn = new HBox(deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        // Sets the cell factory for the edit button column in the table
        deleteC.setCellFactory(cellFoctory);
        Callback<TableColumn<Product, String>, TableCell<Product, String>> cellFoctory2 = (TableColumn<Product, String> param) -> {
            final TableCell<Product, String> cell = new TableCell<Product, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
//                        editIcon.setOnMouseClicked((MouseEvent event) -> {
//                            product = productsView.getSelectionModel().getSelectedItem();
//                            FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateProduct.fxml"));
//
//                            try {
//                                Parent root = loader.load();
//
//                                UpdateCategoryController controller = loader.getController();
//                                controller.setTfName(category.getCategoryName());
//                                controller.setTfDescription(category.getDescription());
//                                controller.setCategory(category);
//                                categoriesView.getScene().setRoot(root);
//                            } catch (IOException ex) {
//
//                                System.out.println(ex.getMessage());
//                            }
//
//                        });
                        HBox managebtn = new HBox(editIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        updateC.setCellFactory(cellFoctory2);
        productsView.setItems(data);

    }

}
