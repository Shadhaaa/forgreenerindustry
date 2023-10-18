/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forGreenerIndustry.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
public class UpdatePostFXMLController implements Initializable {

    @FXML
    private TextField tfPost;
    @FXML
    private TextField tfEntreprise;
    @FXML
    private TextField tfTitre;
    @FXML
    private TextField tfContenu;
    @FXML
    private TextField tfImage;
    @FXML
    private ComboBox<String> comboBoxType;
    @FXML
    private DatePicker DatePicker;
    @FXML
    private Label testing;
    @FXML
    private Button home;
    
    private int postID;
    private Post originalPost;
    
    public void setPostID(int postID) {
        this.postID = postID;
        populateFields();
    }
    
     private void populateFields() {
        ServicePost service = new ServicePost();
        Post post = service.getOne(postID);

        if (post != null) {
            tfPost.setText(String.valueOf(post.getId_post()));
            tfEntreprise.setText(String.valueOf(post.getId_entreprise()));
            tfTitre.setText(post.getTitre()); 
            comboBoxType.setValue(post.getTypeDeContenu());
            tfContenu.setText(post.getContenu());
            tfImage.setText(post.getImage());
            DatePicker.setValue(post.getDate().toLocalDate());
            
            
        } else {
            
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboBoxType.getItems().addAll("Event", "Product", "News");
        
        
        ServicePost service = new ServicePost();
        originalPost = service.getOne(postID);

        if (originalPost != null) {
            // Populate the text fields with data from the retrieved Post object
            tfPost.setText(String.valueOf(originalPost.getId_post()));
            tfEntreprise.setText(String.valueOf(originalPost.getId_entreprise()));
            tfTitre.setText(originalPost.getTitre());
            tfContenu.setText(originalPost.getContenu());
            tfImage.setText(originalPost.getImage());
            comboBoxType.setValue(originalPost.getTypeDeContenu());

            // Convert the Date to a LocalDate and set it in the DatePicker
            if (originalPost.getDate() != null) {
                LocalDate localDate = originalPost.getDate().toLocalDate();
                DatePicker.setValue(localDate);
            }
        } else {
            testing.setText("Post not found");
        }
        
    }    

    @FXML
    private void btnUpdate(ActionEvent event) {
    
        int idPost = postID;
        
        int idEntreprise = Integer.parseInt(tfEntreprise.getText());
        String titre = tfTitre.getText();
        String selectedType = comboBoxType.getValue();
        String contenu = tfContenu.getText();
        LocalDate localDate = DatePicker.getValue();
        Date date = Date.valueOf(localDate); 
        String imageUrl = tfImage.getText();

   
        Post updatedPost = new Post(idPost, idEntreprise, titre, selectedType, contenu, date, imageUrl);

    //  ServicePost to update post
        ServicePost service = new ServicePost();
        service.modifier(updatedPost);

        testing.setText("Post updated successfully");
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
