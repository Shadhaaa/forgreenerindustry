/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forgreenerindustry.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author shadha
 */
public class EntrepriseAjouterFXMLController implements Initializable {

    @FXML
    private TextField TFNom_entreprise_aj;
    @FXML
    private TextField TFLogo_aj;
    @FXML
    private TextField TFSecteur_aj;
    @FXML
    private TextField TFDesc_aj;
    @FXML
    private Button BTNajouter_entreprise;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void BTNajouter_entreprise(ActionEvent event) {
    }
    
}
