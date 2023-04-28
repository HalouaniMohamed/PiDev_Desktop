package tn.pidev.gui;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.text.SimpleDateFormat;
import tn.pidev.entites.Evenements;

public class Evenement_Component_Controller {

    @FXML
    private AnchorPane BODY;

    @FXML
    private Label _date;

    @FXML
    private Label _desc;

    @FXML
    private Label _lieu;

    @FXML
    private Label _nbr;

    @FXML
    private Label _nbr_places;

    @FXML
    private Label _nom;

    @FXML
    private ImageView _pic;

    @FXML
    private Label _time;

    @FXML
    private Label _type;
    private SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    private Evenements_Client_Controller controller ;
    private Evenements event;

    public void init(Evenements evenement, int index, Evenements_Client_Controller evenements_client_controller){
        this.controller = evenements_client_controller;
        this.event = evenement;
        _nbr.setText(String.valueOf(index));
        _nom.setText(evenement.getNom_evenement());
        _nbr_places.setText(String.valueOf(evenement.getNbr_de_places()));
        _time.setText(evenement.getHeure().toString());
        _date.setText(formatter.format(evenement.getDate_evenement()));
        _desc.setText(evenement.getDescription_evenement());
        _lieu.setText(evenement.getLieu_evenement());
        _pic.setImage(new Image("file:///"+evenement.getImage()));
        _type.setText(evenement.getType());
    }
    @FXML
    void preview(MouseEvent event) {
        controller.pop(this.event);
    }
}
