/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forGreenerIndustry.gui;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author ACHREF
 */
public class Commandewindow1 extends Application {
    private static Stage commande1Stage; // Référence à la fenêtre Commande1

    public static Stage getCommande1Stage() {
        return commande1Stage;
    }
    
    @Override
    public void start(Stage primaryStage) {
       try {
            Parent root = FXMLLoader.load(getClass().getResource("Commande1.fxml"));
            Scene scene = new Scene(root);
        
            primaryStage.setTitle("Commande");
            primaryStage.setScene(scene);
            primaryStage.show();
            
             commande1Stage = primaryStage;
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
