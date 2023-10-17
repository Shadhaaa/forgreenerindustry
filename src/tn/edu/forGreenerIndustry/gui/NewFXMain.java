/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forgreenerindustry.gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author shadha
 */
public class NewFXMain extends Application{
    @Override

    public void start(Stage primaryStage) {
        try {
            //Parent root = FXMLLoader.load(getClass().getResource("UserAjouterFXML.fxml"));
            //Parent root = FXMLLoader.load(getClass().getResource("LoginFXML.fxml"));
            //Parent root = FXMLLoader.load(getClass().getResource("CnxUserFXML.fxml"));
             Parent root = FXMLLoader.load(getClass().getResource("UserModifierFXML.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("/*******user /******");
            primaryStage.show();
        
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
                launch(args);

    }
    
}
