/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forGreenerIndustry.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import static org.omg.CORBA.ORB.init;
import tn.edu.forGreenerIndustry.entities.Avis;
import static tn.edu.forGreenerIndustry.gui.Main.avis;
import tn.edu.forGreenerIndustry.services.ServiceAvis;
import tn.edu.forGreenerIndustry.services.ServiceReponse;

import tn.edu.forGreenerIndustry.tools.DataSource;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class AjouterAvisController implements Initializable {

    Connection cnx = DataSource.getInstance().getConnection();
    ServiceAvis sa = new ServiceAvis(cnx);
    ServiceReponse sr = new ServiceReponse(cnx);
    @FXML
    private Label TextAvis;

    @FXML
    private Label ErrText;
    private Integer[] a = {1, 2, 3, 4};
    @FXML
    private RadioButton gr1;
    @FXML
    private ToggleGroup grating;
    @FXML
    private RadioButton gr3;
    @FXML
    private RadioButton gr2;
    @FXML
    private RadioButton gr4;
    @FXML
    private RadioButton gr5;
    @FXML
    private Label notreat;
    String notte = "0";
    @FXML
    private TextArea texttttt;
    @FXML
    private ComboBox<Integer> Selcser;

   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        init();
        Selcser.getItems().addAll(a);
        init();
        Selcser.getItems().addAll(a);
        grating.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            RadioButton selectedRadioButton = (RadioButton) newValue;
            notte = selectedRadioButton.getText();
            notreat.setText(notte);
        });
        if (gr1.isSelected()) {
            notte = "Service investissement";
        } else if (gr2.isSelected()) {
            notte = "Service evenement ";
        } else if (gr3.isSelected()) {
            notte = "service commande";
        } else if (gr4.isSelected()) {
            notte = "Service Communication";
        } else if (gr5.isSelected()) {
            notte = "ServiceAvis";
        }

        notreat.setText(notte);
    }

    @FXML
    private void AjouterAvis(ActionEvent event) throws IOException {
        int note = 0;
        if (gr1.isSelected()) {
            note = 1;
        } else if (gr2.isSelected()) {
            note = 2;
        } else if (gr3.isSelected()) {
            note = 3;
        } else if (gr4.isSelected()) {
            note = 4;
        } else if (gr5.isSelected()) {
            note = 5;
        }

        if (texttttt.getText().isEmpty() ) {
            ErrText.setVisible(true);
            ErrText.setText("Champ Obligatoire");
        } else {
            Avis a = new Avis(Main.avis.getIdAvis(),texttttt.getText(),Integer.parseInt(notte), 1);
            sa.ajouter(avis);

            if ("admin".equals(Main.role)) {
                navigateToGestionAvis();
            } else {
                navigateToEspaceUser();
            }
        }
    }

    private void navigateToGestionAvis() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionAvis.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        closeCurrentWindow();
    }

    private void navigateToEspaceUser() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EspaceUser.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        closeCurrentWindow();
    }

    private void closeCurrentWindow() {
        ((Stage) texttttt.getScene().getWindow()).close();
    }

    @FXML
    private void AnuulerAvis(ActionEvent event) throws IOException {
        if (Main.role == "admin") {
            ErrText.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("GestionAvis.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
        } else {
            ErrText.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("EspaceUser.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();

        }
    }

  
}
