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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author shadha
 */
public class CnxUserFXMLController implements Initializable {

    @FXML
    private Button BTN_cnx;
    @FXML
    private Hyperlink LINK_mdp_oublie;
    @FXML
    private TextField TFEmail_cnx;
    @FXML
    private TextField TFMdp_cnx;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void CnxUser(ActionEvent event) {
    }

    @FXML
    private void Mdp_oublie(ActionEvent event) {
    }
    
}
