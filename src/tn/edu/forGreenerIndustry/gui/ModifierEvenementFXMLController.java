/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forgreenerindustry.gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author milou
 */
public class ModifierEvenementFXMLController implements Initializable {

    @FXML
    private Label event;
    @FXML
    private Pane paneEvent;
    @FXML
    private Label titreE;
    @FXML
    private Label dateE;
    @FXML
    private Label qrE;
    @FXML
    private Label imgeE;
    @FXML
    private Label lieuE;
    @FXML
    private Label descriptionE;
    @FXML
    private TextField TFtitre;
    @FXML
    private TextField TFdate;
    @FXML
    private TextField TFqr;
    @FXML
    private TextField TFimage;
    @FXML
    private TextField TFlieu;
    @FXML
    private TextField TFdescription;
    @FXML
    private Button btnModifier;
    @FXML
    private TableColumn<?, ?> idEventColumn;
    @FXML
    private TableColumn<?, ?> idEntrepriseColumn;
    @FXML
    private TableColumn<?, ?> titreEventColumn;
    @FXML
    private TableColumn<?, ?> dateEventColumn;
    @FXML
    private TableColumn<?, ?> qrEventColumn;
    @FXML
    private TableColumn<?, ?> imageEventColumn;
    @FXML
    private TableColumn<?, ?> lieuEventColumn;
    @FXML
    private TableColumn<?, ?> descriptionEventColumn;
    
    
    private Connection cnx;
    PreparedStatement pst;   
    public void Connect(){
        try {
            //Class.forName("com.mysql.cj.jdbc.Driver");
            cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/for_greener_industry","root","");
                    System.out.println("Connected to DB!"); // Message de succès

        }catch (SQLException ex){
                    System.err.println("Database Connection Error: " + ex.getMessage()); // Message d'erreur en cas de problème de connexion

        }

     }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Modifier(ActionEvent event) {
        
        
    }
    
}
