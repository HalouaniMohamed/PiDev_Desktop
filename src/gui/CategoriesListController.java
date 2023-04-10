/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Category;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import services.CategoryService;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author ALPHA
 */
public class CategoriesListController implements Initializable {

    @FXML
    private AnchorPane id;
    @FXML
    private TableView<Category> categoriesView;
    @FXML
    private TableColumn<Category, String> idC;
    @FXML
    private TableColumn<Category, String> nameC;
    @FXML
    private TableColumn<Category, String> descriptionC;
    @FXML
    private TableColumn<Category, String> createdAtC;
    @FXML
    private TableColumn<Category, String> updatedAtC;
    @FXML
    private TableColumn<Category, String> updateC;
    @FXML
    private TableColumn<Category, String> deleteC;

    CategoryService cs = new CategoryService();

    Category category = null;
    ObservableList<Category> data = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        load();
//        List<Category> categories = cs.getAllCategories();
//        ObservableList<Category> data = FXCollections.observableArrayList(categories);
//        categoriesView.setItems(data);
    }

    @FXML
    private void refreshTable() {

        data.clear();

        List<Category> c = cs.getAllCategories();
        data.addAll(c);
        categoriesView.setItems(data);

    }

    public void load() {
        refreshTable();
        idC.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameC.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
        descriptionC.setCellValueFactory(new PropertyValueFactory<>("description"));
        createdAtC.setCellValueFactory(new PropertyValueFactory<>("createAt"));
        updatedAtC.setCellValueFactory(new PropertyValueFactory<>("updatedAt"));
        Callback<TableColumn<Category, String>, TableCell<Category, String>> cellFoctory = (TableColumn<Category, String> param) -> {
            // make cell containing buttons
            final TableCell<Category, String> cell = new TableCell<Category, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );

                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {

                            category = categoriesView.getSelectionModel().getSelectedItem();
                            cs.deleteById(category.getId());
                            refreshTable();

                        });

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
        deleteC.setCellFactory(cellFoctory);
        Callback<TableColumn<Category, String>, TableCell<Category, String>> cellFoctory2 = (TableColumn<Category, String> param) -> {
            // make cell containing buttons
            final TableCell<Category, String> cell = new TableCell<Category, String>() {
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
//                            category = categoriesView.getSelectionModel().getSelectedItem();
//                            FXMLLoader loader = new FXMLLoader();
//                            loader.setLocation(getClass().getResource("UpdateCategory.fxml"));
//                            try {
//
//                                loader.load();
//
//                            } catch (IOException ex) {
//
//                                Logger.getLogger(UpdateCategoryController.class.getName()).log(Level.SEVERE, null, ex);
//                            }
//
//                            UpdateCategoryController addStudentController = loader.getController();
//
//                            UpdateCategoryController controller = new UpdateCategoryController();
//                            System.out.println("test");
//                            controller.setTfName(category.getCategoryName());
//                            System.out.println("test");
//
//                            controller.setTfDescription(category.getDescription());
//                            System.out.println("test");
//
//                            controller.setName(category.getCategoryName());
//                            controller.setDescription(category.getDescription());
//                            Parent parent = loader.getRoot();
//                            Stage stage = new Stage();
//                            stage.setScene(new Scene(parent));
//                            stage.initStyle(StageStyle.UTILITY);
//                            stage.show();
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
        categoriesView.setItems(data);

    }

}
