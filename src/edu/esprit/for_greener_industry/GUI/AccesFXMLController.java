/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.for_greener_industry.GUI;

import static edu.esprit.for_greener_industry.GUI.Mod_or_addProdFXMLController.Mod_or_addProdFXMLController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ritha
 */
public class AccesFXMLController implements Initializable {
    @FXML
    private Button véhi;
    @FXML
    private Button prod;
    @FXML
    private Button ener;
    @FXML
    private Button cat;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        prod.setOnAction(event ->{
             try {
                 
            
            Parent root = FXMLLoader.load(getClass().getResource("ProduitFXML.fxml"));
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("/*******Gestion des Produits /******");
                 
            primaryStage.show();
            
        } catch (IOException ex) {
            
            System.out.println(ex.getMessage());
        }
            
        });
        
        
        cat.setOnAction(event ->{
             try {
                 
            
            Parent root = FXMLLoader.load(getClass().getResource("CatégorieFXML.fxml"));
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("/*******Gestion des Catégories /******");
                 
            primaryStage.show();
            
        } catch (IOException ex) {
            
            System.out.println(ex.getMessage());
        }
            
        });
        
        
        
        ener.setOnAction(event ->{
             try {
                 
            
            Parent root = FXMLLoader.load(getClass().getResource("EnergieFXML.fxml"));
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("/*******Gestion des Energies /******");
                 
            primaryStage.show();
            
        } catch (IOException ex) {
            
            System.out.println(ex.getMessage());
        }
            
        });
        
        
        
        
        véhi.setOnAction(event ->{
             try {
                 
            
            Parent root = FXMLLoader.load(getClass().getResource("VéhiculeFXML.fxml"));
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("/*******Gestion des Véhicules /******");
                 
            primaryStage.show();
            
        } catch (IOException ex) {
            
            System.out.println(ex.getMessage());
        }
            
        });
    }    
    
}
