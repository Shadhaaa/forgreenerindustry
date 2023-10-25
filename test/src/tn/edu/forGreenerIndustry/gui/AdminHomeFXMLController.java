package tn.edu.forgreenerindustry.gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import tn.edu.forgreenerindustry.entities.User;
import tn.edu.forgreenerindustry.services.ServiceUser;

public class AdminHomeFXMLController implements Initializable {
    Alert alert1 = new Alert(Alert.AlertType.WARNING);

    @FXML
    public BorderPane bpAdmin;
    @FXML
    public Button acceuil;
    @FXML
    public Button btngestionuser;
    @FXML
    public Button btngestionproduit;
    @FXML
    public Button btngestionevenement;
    @FXML
    public Button btngestionpost;
    @FXML
    public Button btngestionrec;
    @FXML
    public Button btngestioncommande;
    @FXML
    public Button btngestioninv;
    @FXML
    public TextField role_home;
    @FXML
    public TextField TFid_cor;

    User uc = new User();
    ServiceUser service = new ServiceUser();
    User u = new User();
    String r = null ;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        role_home.setVisible(false);
        TFid_cor.setVisible(false);
    }

    private void loaderpage(String page) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(page + ".fxml"));
            bpAdmin.setCenter(root); // Définissez le contenu du BorderPane avec la nouvelle page chargée
        } catch (IOException ex) {
            Logger.getLogger(AdminHomeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void acceuil(ActionEvent event) {
      
    }

    @FXML
    private void gestionUser(ActionEvent event) {
        loaderpage("UserAfficherFXML");
    }

    @FXML
    private void homegestionproduit(ActionEvent event) {
    }

    @FXML
    private void homegestionevenement(ActionEvent event) {
    }

    @FXML
    private void homegestioncommande(ActionEvent event) {
    }

    @FXML
    private void homegestionreclamation(ActionEvent event) {
    }

    @FXML
    private void homegestioninvestissement(ActionEvent event) {
    }

    @FXML
    private void homegestionpost(ActionEvent event) {
    }


    
              
                   
  
   

   
  
}
