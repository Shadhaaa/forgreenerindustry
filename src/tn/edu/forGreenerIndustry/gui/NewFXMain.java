/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forgreenerindustry.gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author milou
 */
public class NewFXMain extends Application {
    
    @Override
     public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AjouterEvenementFXML.fxml"));
            Scene sc = new Scene(root);
            primaryStage.setScene(sc);
            primaryStage.setTitle("Evenement");
            primaryStage.show();
        
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
