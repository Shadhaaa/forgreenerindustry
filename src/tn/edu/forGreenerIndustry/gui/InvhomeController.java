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
import tn.edu.forgreenerindustry.entities.User;
import tn.edu.forgreenerindustry.services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author shadha
 */
public class InvhomeController implements Initializable {

    @FXML
    private Button acceuil;
    @FXML
    private Button btngestionproduit;
    @FXML
    private Button btngestionevenement;
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
          role_home.setVisible(false);
        TFid_cor.setVisible(false);
    } 
    
       ServiceUser service = new ServiceUser();
    User u = new User();
     User uc = new User();
    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        //   int id_corr = Integer.parseInt(TFid_cor.getText());

      
      private void loaderpage(String page) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(page + ".fxml"));
            BorderPane.setCenter(root); // Définissez le contenu du BorderPane avec la nouvelle page chargée
        } catch (IOException ex) {
            Logger.getLogger(AdminHomeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }}
    public void setUserId(int userId) {
        TFid_cor.setText(String.valueOf(userId));
    }

    /**
     * Initializes the controller class.
     */
   
     
      
    @FXML
    private void acceuil(ActionEvent event) {
            loaderpage("UserModifierFXML");
               List<Integer> ids = service.getAllid();
        List<User> allUsers = service.getAll(u);
        for (User user : allUsers) {
boolean boll = TFid_cor.getText().equals(String.valueOf(user.getId_user()));
if (boll) {
                uc = user ;
                    break;
            }
        }
            

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("UserModifierFXML.fxml"));
                Parent rootm = loader.load();
                BorderPane.setCenter(rootm);

                UserModifierFXMLController modificationController = loader.getController();
                User u = service.getOne(uc);

                if (uc != null) {
                                        modificationController.rolemdf.setText("INVESTISSEUR");

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
            }}


    @FXML
    private void homegestionproduit(ActionEvent event) {
    }

    @FXML
    private void homegestionevenement(ActionEvent event) {
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

    private String toString(int id_user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
