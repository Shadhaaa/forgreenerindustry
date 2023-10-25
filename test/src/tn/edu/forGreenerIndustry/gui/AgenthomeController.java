/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import tn.edu.forGreenerIndustry.services.ServiceEntreprise;
import tn.edu.forgreenerindustry.entities.Entreprise;

/**
 * FXML Controller class
 *
 * @author shadha
 */
public class AgenthomeController implements Initializable {

    @FXML
    private Button acceuil;
    @FXML
    private Button btngestionproduit;
    @FXML
    private Button btngestionevenement;
    @FXML
    private Button btngestioncommande;
    @FXML
    private Button btngestionrec;
    @FXML
    private Button btngestioninv;
    @FXML
    private Button btngestionpost;
    @FXML
    private AnchorPane ANhome_admin;
    @FXML
    public TextField role_home;
    @FXML
    public TextField TFid_cor;
    @FXML
    private BorderPane BorderPane;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        TFid_cor.setVisible(false);
        role_home.setVisible(false);
    }    
  Entreprise uc = new Entreprise();
    ServiceEntreprise service = new ServiceEntreprise();
      Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
   
      
      private void loaderpage(String page) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(page + ".fxml"));
            BorderPane.setCenter(root); // Définissez le contenu du BorderPane avec la nouvelle page chargée
        } catch (IOException ex) {
            Logger.getLogger(AdminHomeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
          public void setUserId(int userId) {
        TFid_cor.setText(String.valueOf(userId));
    }
    @FXML
    private void acceuil(ActionEvent event) {
          int id_corr = Integer.parseInt(TFid_cor.getText());
            loaderpage("UserModifierFXML");
               List<Integer> ids = service.getAllid();
        List<Entreprise> allUsers = service.getAll(uc);
        for (Entreprise user : allUsers) {
            if (user.getId_entreprise()== id_corr) {
                uc = user ;
                    break;
            }
        }
            

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("UserModifierFXML.fxml"));
                Parent rootm = loader.load();
                BorderPane.setCenter(rootm);

                UserModifierFXMLController modificationController = loader.getController();
                Entreprise u = service.getOne(uc);

                if (uc != null) {
                                        modificationController.rolemdf.setText("AGENT_ENTREPRISE");

                    modificationController.TFid_user.setText(String.valueOf(uc.getId_entreprise()));
                    modificationController.TFNom_aj.setText(uc.getNom());
                    modificationController.TFPrenom_aj.setText(uc.getPrenom());
                    modificationController.TFAdresse_aj.setText(uc.getAdresse());
                    modificationController.TFNum_aj.setText(String.valueOf(uc.getNum()));
                    modificationController.TFMail_aj.setText(uc.getMail());
                    modificationController.TFMdp1_aj.setText(uc.getMdp1());
                    modificationController.TFPdp_aj.setText(uc.getPdp());
                    modificationController.BTNajouter_user.setText("modifer");
                    modificationController.btnrtr_home.setVisible(false);
                    modificationController.Paneentreprise.setVisible(true);

                    if (uc.getGenre() == "FEMME") {
                        modificationController.RBGenre_aj.selectToggle(modificationController.RBFemme);
                    } else {
                        modificationController.RBGenre_aj.selectToggle(modificationController.RBHomme);
                    }
                    System.out.println("tn.edu.forgreenerindustry.gui.ClienthomeController.acceuil()");
                    modificationController.CBRole_aj.setValue(role_home.getText());
                    modificationController.CBRole_aj.setDisable(true);
                    
                  
                     alert1.setTitle("bienvenue");
            alert1.setHeaderText("bienvenue");
            alert1.setContentText("bienvenue sur For greenr industry");
                    alert1.showAndWait();
                } 
            } catch (IOException ex) {
                Logger.getLogger(AdminHomeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
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
