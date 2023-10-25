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
import tn.edu.forgreenerindustry.tools.DataSource;

/**
 *
 * @author shadha
 */
public class NewFXMain extends Application{
    @Override

    public void start(Stage primaryStage) {
        try {
                DataSource.getInstance();
            //Parent root = FXMLLoader.load(getClass().getResource("UserAjouterFXML.fxml"));
           // Parent root = FXMLLoader.load(getClass().getResource("UserAfficherFXML.fxml"));
         // Parent root = FXMLLoader.load(getClass().getResource("AdminHomeFXML.fxml"));
                   
        Parent root = FXMLLoader.load(getClass().getResource("CnxUserFXML.fxml"));
          // Parent root = FXMLLoader.load(getClass().getResource("UserAfficherFXML.fxml"));
//Parent root = FXMLLoader.load(getClass().getResource("Clienthome.fxml"));
     //  Parent root = FXMLLoader.load(getClass().getResource("Acceuiladmin.fxml"));

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("For greener Industry - welcome");
            primaryStage.show();
                                // primaryStage.getScene().getWindow().hide();

        
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
