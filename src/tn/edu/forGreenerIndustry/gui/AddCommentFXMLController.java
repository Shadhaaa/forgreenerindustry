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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.edu.forGreenerIndustry.entities.Commentaires;
import tn.edu.forGreenerIndustry.services.ServiceCommentaires;

/**
 * FXML Controller class
 *
 * @author mila
 */
public class AddCommentFXMLController implements Initializable {

    private Button btnADD;
    @FXML
    private Button home;
    
    @FXML
    private TextField tfUserID;
    @FXML
    private TextField tfComment;
    @FXML
    private ComboBox<String> ComboBoxStat;
    
    private ServiceCommentaires commentairesService;
    private int postId;
    @FXML
    private Button btnAdding;
    
    public void setPostId(int postId) {
        this.postId = postId;
        System.out.println(postId);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        commentairesService = new ServiceCommentaires();
        ComboBoxStat.getItems().addAll("Approved", "Pending", "Rejected");
        ComboBoxStat.setValue("Pending");
    }   
    
    @FXML
    private void btnAdding(ActionEvent event) {
        int userId = Integer.parseInt(tfUserID.getText());
        String contenu = tfComment.getText();
        String selectedValue = ComboBoxStat.getValue();
        
        int postId = getPostId();

        Commentaires newComment = new Commentaires(userId, contenu, selectedValue);
        
        // Add the comment to the database using the commentairesService
        commentairesService.ajouter(newComment);
        
        // Close the "Add Comment" interface
        Stage stage = (Stage) btnADD.getScene().getWindow();
        stage.close(); 
    }



    @FXML
    private void btnHome(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CommentsFXML.fxml"));
            Parent root = loader.load();
            Scene dashboardScene = new Scene(root);
            Stage stage = (Stage) home.getScene().getWindow();
            stage.setScene(dashboardScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int getPostId() {
        return postId;
    }

    
   
    
   
}
