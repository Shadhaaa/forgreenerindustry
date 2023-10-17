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
public class PostFXMLController implements Initializable {

    @FXML
    private Label tfPost;
    @FXML
    private TextField tfEntreprise;
    @FXML
    private TextField tfTitre;
    @FXML
    private TextField tfContenu;
    @FXML
    private ComboBox<String> comboBoxType;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField tfImage;
    
    private AllPostsFXMLController allPostsController;
    
    public void setAllPostsController(AllPostsFXMLController allPostsController) {
        this.allPostsController = allPostsController;
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize the ComboBox with default choices
        comboBoxType.getItems().addAll("Event", "Product", "News");

        
        comboBoxType.setValue("Product");
    }    

    @FXML
    private void btnAjouter(ActionEvent event) {
        try {
            int idPost = Integer.parseInt(tfPost.getText());
            int idEntreprise = Integer.parseInt(tfEntreprise.getText());
            String titre = tfTitre.getText();
            String selectedValue = comboBoxType.getValue();
            String contenu = tfContenu.getText();
            LocalDate localDate = datePicker.getValue();
            Date date = Date.valueOf(localDate); // Convert LocalDate to SQL Date
            String imageUrl = tfImage.getText();

            // lezem tasna3 Post object 
            Post newPost = new Post(idPost, idEntreprise, titre, selectedValue, contenu, date, imageUrl);
            

            // nedi l service bech tzid Post l database
            ServicePost service = new ServicePost();
            service.ajouter(newPost);

            showAlert("Post added successfully");
            if (allPostsController != null) {
                allPostsController.loadPostData(); // Add a method to refresh the table
        }

        showAlert("Post added successfully");
        } catch (NumberFormatException e) {
            showAlert("Invalid input. Please enter valid numbers.");
        }

    }
    
    @FXML
    private void btnShow(ActionEvent event) {
        try {
            // Load the AllPosts interface
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AllPostsFXML.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) tfPost.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void showAlert(String message) {
        // You can implement the logic to show an alert or message here
    }

   
}

