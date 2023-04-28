package tn.pidev.gui;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import tn.pidev.entites.Evenements;
import tn.pidev.services.EvenementsService;

public class Evenements_Client_Controller implements Initializable {

    @FXML
    private AnchorPane BODY;

    @FXML
    private VBox events_container;


    @FXML
    void search(KeyEvent event) {
        Afficher(((TextField)event.getSource()).getText());
    }
    ObservableList<Evenements> _events_  = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(new EvenementsService().afficher());
        List<Integer> i = new ArrayList<>();
        _events_.addListener((ListChangeListener<Evenements>) change -> {
            i.clear();
            i.add(1);
            events_container.getChildren().clear();
            change.getList().stream().collect(Collectors.toList()).forEach(event -> {
                try{
                    int index = i.get(0);
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Evenement_Component.fxml"));
                    AnchorPane component = loader.load();
                    Evenement_Component_Controller controller = loader.getController();
                    controller.init(event,index,this);
                    i.set(0,index+1);
                    events_container.getChildren().add(component);
                }catch (Exception ignored){
                    System.out.println(ignored);
                }
            });
        });
        Afficher(null);
    }
    void Afficher(String searchable){
        if(searchable != null){
            _events_.setAll(new EvenementsService().afficher().stream().collect(Collectors.toList()).stream().filter(evenements -> evenements.getNom_evenement().toLowerCase().contains(searchable.toLowerCase()) || evenements.getDescription_evenement().toLowerCase().contains(searchable.toLowerCase())
                    || evenements.getLieu_evenement().toLowerCase().contains(searchable.toLowerCase()) || evenements.getType().toLowerCase().contains(searchable.toLowerCase())).collect(Collectors.toList()));
        }
        else {
            _events_.setAll(new EvenementsService().afficher());
        }
    }
    private VBox POP = new VBox();
    public void pop(Evenements event) {
        try {
            POP.setStyle("-fx-background-color:white;");
            AnchorPane.setTopAnchor(POP, 0.0);
            AnchorPane.setLeftAnchor(POP, 0.0);
            AnchorPane.setRightAnchor(POP, 0.0);
            AnchorPane.setBottomAnchor(POP, 0.0);
            POP.setAlignment(Pos.CENTER);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui//Reservation_Component.fxml"));
            AnchorPane component = loader.load();
            Reservation_Component_Controller controller = loader.getController();
            controller.set(this,event);
            POP.getChildren().setAll(component);
            BODY.getChildren().add(POP);
            POP.toFront();
        }
        catch (Exception e){
            BODY.getChildren().remove(POP);
            System.out.println(e.getMessage());
        }

    }

    public void clear() {
        BODY.getChildren().remove(POP);
        Afficher(null);
    }
    
    @FXML 
            private Button voirR;
    
    @FXML
    private void goToR(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/ConnexionReservation.fxml"));
        Parent root = loader.load();
        voirR.getScene().setRoot(root);

    }
    
}
