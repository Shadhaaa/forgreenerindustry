/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forGreenerIndustry.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.edu.forGreenerIndustry.entities.Post;
import tn.edu.forGreenerIndustry.services.ServicePost;

/**
 * FXML Controller class
 *
 * @author mila
 */
public class SearchFXMLController implements Initializable {

    @FXML
    private Label lblResult;
    @FXML
    private TextField tfSearch;
    @FXML
    private Button home;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnSearchPost(ActionEvent event) {
        try {
            int postId = Integer.parseInt(tfSearch.getText());

            // Call a method to search for the post by ID
            Post post = searchPostById(postId);

            if (post != null) {
                
                String resultText = "ID: " + post.getId_post() + "\n"
                    + "Entreprise: " + post.getId_entreprise() + "\n"
                    + "Titre: " + post.getTitre() + "\n"                    
                    + "Type: " + post.getTypeDeContenu() + "\n"
                    + "Contenu: " + post.getContenu() + "\n"
                    + "Date: " + post.getDate() + "\n"
                    + "Image: " + post.getImage();

                lblResult.setText(resultText);
            } else {
                
                lblResult.setText("Post not found.");
            }
        } catch (NumberFormatException e) {
            
            lblResult.setText("Please enter a valid post ID.");
        }
    }
    
    private Post searchPostById(int postId) {
        
        ServicePost service = new ServicePost();
        return service.getOne(postId);
    }

    @FXML
    private void btnHome(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DashboardFXML.fxml"));
            Parent root = loader.load();
            Scene dashboardScene = new Scene(root);
            Stage stage = (Stage) home.getScene().getWindow();
            stage.setScene(dashboardScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
