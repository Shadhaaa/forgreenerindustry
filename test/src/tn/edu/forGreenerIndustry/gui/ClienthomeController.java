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
import tn.edu.forgreenerindustry.entities.User;
import tn.edu.forgreenerindustry.services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author shadha
 */
public class ClienthomeController implements Initializable {

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
    private Button btngestionpost;
    @FXML
    private AnchorPane ANhome_admin;
    @FXML
    public TextField role_home;
    @FXML
    private BorderPane BorderPane;
  
    @FXML
    public TextField TFid_corc;
    /**
     * Initializes the controller class.
     */
    
      ServiceUser service = new ServiceUser();
    User u = new User();
     User uc = new User();
    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
int id_use ;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO 
//      TFid_cor.setVisible(true);
          role_home.setVisible(false);
        TFid_corc.setVisible(false);
        }
            public void setUserId(int userId) {
        TFid_corc.setText(String.valueOf(userId));
    }
    

  private void loaderpage(String page) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(page + ".fxml"));
            BorderPane.setCenter(root); // Définissez le contenu du BorderPane avec la nouvelle page chargée
        } catch (IOException ex) {
            Logger.getLogger(AdminHomeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void acceuil(ActionEvent event) throws IOException {

       // loaderpage("UserModifierFXML");
             
           //chercher mail 
           boolean emailExiste = false;
    for (User utilisateur : service.getAll(u)) {
        if (utilisateur.getId_user() == (Integer.parseInt(TFid_corc.getText()))) {
            emailExiste = true;
            uc = utilisateur ;
            id_use = utilisateur.getId_user();
            break; // Sortir de la boucle dès qu'une correspondance est trouvée
        }
    }
            

          
                FXMLLoader loader = new FXMLLoader(getClass().getResource("UserModifierFXML.fxml"));
                Parent rootm = loader.load();
                BorderPane.setCenter(rootm);

                UserModifierFXMLController modificationController = loader.getController();
                //User u2 = service.getOne(uc);

               
                    modificationController.TFid_user.setText(String.valueOf(uc.getId_user()));
                    modificationController.TFNom_aj.setText(uc.getNom());
                    modificationController.TFPrenom_aj.setText(uc.getPrenom());
                    modificationController.TFAdresse_aj.setText(uc.getAdresse());
                    modificationController.TFNum_aj.setText(String.valueOf(uc.getNum()));
                    modificationController.TFMail_aj.setText(uc.getMail());
                    modificationController.TFMdp1_aj.setText(uc.getMdp1());
                    modificationController.TFPdp_aj.setText(uc.getPdp());
                    modificationController.BTNajouter_user.setText("modifer");
                    modificationController.btnrtr_home.setVisible(false);
                    modificationController.Paneentreprise.setVisible(false);
                    modificationController.rolemdf.setText("CLIENT");

                    if ("FEMME".equals(uc.getGenre())) {
                        modificationController.RBGenre_aj.selectToggle(modificationController.RBFemme);
                    } else {
                        modificationController.RBGenre_aj.selectToggle(modificationController.RBHomme);
                    }
                    System.out.println("tn.edu.forgreenerindustry.gui.ClienthomeController.acceuil()");
                    modificationController.CBRole_aj.setValue(role_home.getText());
                    modificationController.CBRole_aj.setDisable(true);
                    modificationController.Paneentreprise.setVisible(false);
                  
                     alert1.setTitle("bienvenue");
            alert1.setHeaderText("bienvenue");
            alert1.setContentText("bienvenue sur For greenr industry");
                    alert1.showAndWait();
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
    private void homegestionpost(ActionEvent event) {
    }
    
}
