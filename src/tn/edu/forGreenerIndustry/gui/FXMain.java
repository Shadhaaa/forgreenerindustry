/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forGreenerIndustry.gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author mila
 */
public class FXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DashboardFXML.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            // Pass the scene to the controller
            DashboardFXMLController controller = loader.getController();
            controller.setScene(scene);

            primaryStage.setTitle("Home");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception ex) {
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
