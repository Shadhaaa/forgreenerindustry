/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forgreenerindustry.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import tn.edu.forgreenerindustry.entities.Entreprise;
import tn.edu.forgreenerindustry.entities.User;
import tn.edu.forgreenerindustry.services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author shadha
 */
public class UserSupprimerFXMLController implements Initializable {
private boolean confirmationSuppression = false;
Stage stage = new Stage();
    @FXML
    private Button btnsupprimer_conf;
    @FXML
    private Button btn_retour_home_supprimer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnsupprimer_conf(ActionEvent event) {
    
    
    
  

    }

    @FXML
    private void btn_supprimer_home(ActionEvent event) {
    
    // Fermez l'interface courante
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.close();

    // Chargez l'interface AdminHomeFXML et affichez-la
    try {
        Parent root = FXMLLoader.load(getClass().getResource("AdminHomeFXML.fxml"));
        Scene scene = new Scene(root);
        Stage adminStage = new Stage();
        adminStage.setTitle("Accueil Administrateur");
        adminStage.setScene(scene);
        adminStage.show();
    } catch (IOException e) {
        e.printStackTrace();
        // Gérez les erreurs en cas de problème lors du chargement de l'interface AdminHomeFXML
    }
    }
   

// Ajoutez une méthode pour afficher la fenêtre de confirmation
public boolean afficherConfirmation() {
    // ...
    btnsupprimer_conf.setOnAction(event -> {
        confirmationSuppression = true; // L'utilisateur a confirmé la suppression
        stage.close();
    });

    btn_retour_home_supprimer.setOnAction(event -> {
        confirmationSuppression = false; // L'utilisateur a annulé la suppression
        stage.close();
    });
return confirmationSuppression; 
}

// Ajoutez une méthode pour obtenir le résultat de la confirmation
public boolean aConfirmeLaSuppression() {
    return confirmationSuppression;
}

}
